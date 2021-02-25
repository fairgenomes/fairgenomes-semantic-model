/*
 * Copyright 2017 NKI/AvL; VUmc 2018/2019/2020
 *
 * This file is part of PALGA Protocol Codebook to XML.
 *
 * PALGA Protocol Codebook to XML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PALGA Protocol Codebook to XML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PALGA Protocol Codebook to XML. If not, see <http://www.gnu.org/licenses/>
 */

package palgacodebooktoartdecor.codebook;

import palgacodebooktoartdecor.artdecor.*;
import palgacodebooktoartdecor.settings.IdentifierManager;
import palgacodebooktoartdecor.settings.RunParameters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

/**
 * datasets can contain multiple versions of a dataset
 * each dataset can contain multiple concepts
 * each concept can have a link to a conceptList
 * a conceptList is associated with a valueset in the terminology
 *
 * note that:
 * once concept's id is created it will be used by all versions and associations (via SPEC or INHERIT) use the same
 * concept id, but use the date to make them different (e.g. a new effectivedate)
 *
 * when a conceptList's options change in a new version, this leads to a new conceptListId. However,
 * in the terminology it is related to the same valueSet id. The original valueSet is set to deprecated and the
 * new one, with the date again reflecting when the set is in use
 */
public class CodebookToArtDecorConvertor {
    private CodebookManager codebookManager;

    private ArtDecorProject artDecorProject;

    // maps for keeping track of the most current versions of concepts and valuesets, which allow us to easily
    // compare the new version against a previous version
    private Map<String, ArtDecorConcept> artDecorConceptMap = new HashMap<>();
    private Map<String, ArtDecorValueSet> artDecorValueSetMap = new HashMap<>();
    private Map<String, ArtDecorValueSet> artDecorChangedValueSetMap;

    // keeps track of the current ArtDecorConceptListId for a conceptId, allowing us to reuse the conceptListId for inheritence
    private Map<String, String> conceptIdToArtDecorConceptListIdMap = new HashMap<>();
    // keeps track of the current ArtDecorConceptId for a conceptId, allowing us to reuse the conceptListId for inheritence
    private Map<String, String> conceptIdToArtDecorConceptIdMap = new HashMap<>();

    // track all the object we need to print
    private List<ArtDecorDataset> artDecorDatasetList = new ArrayList<>();
    private List<ArtDecorTerminologyConcept> artDecorTerminologyConceptList = new ArrayList<>();
    private List<ArtDecorTerminologyValueSet> artDecorTerminologyValueSetList = new ArrayList<>();
    private List<ArtDecorValueSet> artDecorValueSetList = new ArrayList<>();

    private IdentifierManager identifierManager = IdentifierManager.getIdentifierManager();

    public CodebookToArtDecorConvertor(CodebookManager codebookManager, RunParameters runParameters){
        this.artDecorProject = new ArtDecorProject(runParameters);
        this.codebookManager = codebookManager;
    }

    public void transformCodebooks(){
        transformToArtDecor();
        buildConceptHierarchy();
    }

    /**
     * build the hierarchy
     */
    private void buildConceptHierarchy(){
        artDecorDatasetList.forEach(ArtDecorDataset::connectConcepts);
    }

    /**
     * transform codebooks to artdecor datasets
     */
//    private void transformToArtDecor(){
//        Set<Integer> versions = codebookManager.getCodebookVersions();
//        for(int version:versions){
//            artDecorChangedValueSetMap = new HashMap<>();
//
//            // retrieve the codebook for the version
//            Codebook codebook = codebookManager.getCodebook(version);
//
//            // tell the identifier to use a different date to generate its conceptlist identifiers
//            identifierManager.setConceptListId(codebook.getEffectiveDateAsDate());
//
//            // create the dataset based on the codebook
//            generateArtDecorDataSet(codebook);
//
//            // add the changed ValueSets to the "current" valuesets
//            artDecorValueSetMap.putAll(artDecorChangedValueSetMap);
//        }
//    }

    private void transformToArtDecor(){
        Set<Double> versions = codebookManager.getCodebookVersions();
        for(double version:versions){
            artDecorChangedValueSetMap = new HashMap<>();

            // retrieve the codebook for the version
            Codebook codebook = codebookManager.getCodebook(version);

            // tell the identifier to use a different date to generate its conceptlist identifiers
            identifierManager.setConceptListId(codebook.getEffectiveDateAsDate());

            // create the dataset based on the codebook
            generateArtDecorDataSet(codebook);

            // add the changed ValueSets to the "current" valuesets
            artDecorValueSetMap.putAll(artDecorChangedValueSetMap);
        }
    }

    /**
     * generate artdecor dataset for a single codebook
     * @param codebook codebook object
     */
    private void generateArtDecorDataSet(Codebook codebook){
        // create a new artdecor dataset and store it
        ArtDecorDataset artDecorDataset = codebook.createArtDecorDataset(identifierManager.getNextDataSetId());
        artDecorDatasetList.add(artDecorDataset);

        // retrieve all concepts stored in the codebook and loop over them
        Collection<Concept> allConcepts = codebook.getAllConcepts();
        for(Concept concept:allConcepts){
            // check whether the concept already existed in a previous dataset
            // if not, create a art-decor concept identifier for it and store it, allowing it to be
            // reused by later dataset versions
            // So: each conceptID will always be connected with one ArtDecorConceptId, independent of versions, even if
            // descriptions etc. were to change
            // e.g. v1: conceptID=ColonBiopt; artDecorConceptId=1.2.3.4.5.6
            //      v2: conceptID=ColonBiopt; artDecorConceptId=1.2.3.4.5.6
            // etc.
            String conceptId = concept.getId();
            if(!conceptIdToArtDecorConceptIdMap.containsKey(conceptId)){
                conceptIdToArtDecorConceptIdMap.put(conceptId, identifierManager.getNextConceptId());
            }

            // retrieve the art-decor concept id and transform the codebook concept into a art-decor concept
            // add this newly created art-decor concept to the art-decor dataset
            String artdecorConceptId = conceptIdToArtDecorConceptIdMap.get(conceptId);
            ArtDecorConcept artDecorConcept = concept.generateArtDecorConcept(artdecorConceptId);
            artDecorDataset.addArtDecorConcept(artDecorConcept);

            // check whether the concept uses a valuelist; if so handle it
            if(concept.hasConceptOptions()) {
                handleConceptOptions(concept, artDecorConcept);
            }

            // check whether another art-decor concept already exists with this id and set parameters for
            // possible inheritance
            compareToPreviousConcept(conceptId, artDecorConcept);

            // add this concept to the terminology
            addConceptTerminology(concept, artdecorConceptId);
        }
    }

    /**
     * check whether another art-decor concept already exists with this id and set parameters for
     * possible inheritance
     * @param conceptId       the concept identifier
     * @param artDecorConcept the newly created art-decor concept
     */
    private void compareToPreviousConcept(String conceptId, ArtDecorConcept artDecorConcept){
        if(artDecorConceptMap.containsKey(conceptId)){
            // retrieve the previous art-decor concept with the same id and compare to previous
            ArtDecorConcept previousArtDecorConcept = artDecorConceptMap.get(conceptId);

            if(!artDecorConcept.identicalTo(previousArtDecorConcept)){
                // if things are different, set the current concept as the one to check against next time
                // this means that you inherit from the last changed concept. So if there are three codebooks/datasets:
                // item1 did not change in set2, it will inherit from set1 and set3 will also inherit (or specialise) from set1
                // item1 did     change in set2, it will specialise from set1 and set3 will inherit (or specialise) from set2
                artDecorConceptMap.put(conceptId, artDecorConcept);
            }
            artDecorConcept.setRefEffectiveDate(previousArtDecorConcept.getEffectiveDate());
        }
        else{
            artDecorConceptMap.put(conceptId, artDecorConcept);
        }
    }

    /**
     * add the concept's information to the terminology
     * @param concept           the concept in codebook format
     * @param artdecorConceptId the concept in art-decor format
     */
    private void addConceptTerminology(Concept concept, String artdecorConceptId){
        String codeSystemName = concept.getCodesystem();
        String effectiveDate = concept.getEffectiveDate();
        ArtDecorTerminologyConcept artDecorTerminologyConcept =
                new ArtDecorTerminologyConcept(
                        artdecorConceptId,
                        effectiveDate,
                        concept.getCode(),
                        codeSystemName,
                        concept.getDescription_code(),
                        effectiveDate,
                        IdentifierManager.getIdentifierManager().getCodeSystemId(codeSystemName, effectiveDate)
                );
        artDecorTerminologyConceptList.add(artDecorTerminologyConcept);
    }


    /**
     * valueSet has an id - lets call it the valueSetId. This id does not change! If a new valueSet is needed
     * the effectiveDate changes and the old valueSet is set to deprecated. A valueSet is the representation of
     * one set of code and options
     *
     * the terminologyAssociation associates a conceptListId to the valueSet id. This conceptListId DOES change if
     * the lists changes
     *
     * two concepts can use the same valueSet. In that case, both concepts have their own conceptList id which link
     * to the same valueSetId via two terminologyAssociations
     *
     * @param concept the concept in codebook format
     * @param artDecorConcept the concept in artdecor format
     */
    private void handleConceptOptions(Concept concept, ArtDecorConcept artDecorConcept){
        ArtDecorValueSet artDecorValueSet = concept.generateArtDecorValueSet();
        String conceptId = concept.getId();
        String effectiveDate = concept.getEffectiveDate();

        String codelistRef = concept.getCodelist_ref();

        // check whether the valueSet exists
        if(!artDecorValueSetMap.containsKey(codelistRef)){
            // if it doesn't create new artDecorValueSet for the concept
            handleNewValueSet(artDecorConcept, artDecorValueSet, conceptId, effectiveDate, codelistRef);
        }
        else{
            // if it does compare against the existing one
            handleExistingValueSet(artDecorConcept, artDecorValueSet, conceptId, effectiveDate, codelistRef);
        }
    }

    /**
     * New valueSet
     * @param artDecorConcept      the new artDecorConcept
     * @param artDecorValueSet     the new artDecorValueSet
     * @param conceptId            the id of the concept
     * @param effectiveDate        denotes after which the value set can be considered for use
     */
    private void handleNewValueSet(ArtDecorConcept artDecorConcept, ArtDecorValueSet artDecorValueSet, String conceptId, String effectiveDate, String codeListRef){
        // create an id for the valueset and tell the concept that the status of the valueset is NEW
        String artdecorValueSetId = identifierManager.getNextValueSetId();
        artDecorConcept.setXMLConceptListTypeStatus("NEW");
        // create associations for the valueset and for the conceptlist
        addValueSetAssociations(artDecorValueSet, artdecorValueSetId, codeListRef);
        addConceptListAssociations(artDecorConcept, conceptId, effectiveDate, artdecorValueSetId);
    }

    /**
     * Existing valueset
     * @param artDecorConcept  the concept
     * @param artDecorValueSet the valueset for the concept
     * @param conceptId        identifier of the concept
     * @param effectiveDate    denotes after which the value set can be considered for use
     * @param codeListRef      reference to codelist, allowing us to find the existing value set
     */
    private void handleExistingValueSet(ArtDecorConcept artDecorConcept, ArtDecorValueSet artDecorValueSet, String conceptId, String effectiveDate, String codeListRef){
        String artdecorConceptListId;
        // retrieve the existing valueset
        ArtDecorValueSet existingArtDecorValueSet = artDecorValueSetMap.get(codeListRef);

        // check whether the valuesets are the same
        if(artDecorValueSet.sameValues(existingArtDecorValueSet)){
            // check whether there is a conceptListId we can reuse
            // this basically happens if a new version of a concept has the same ValueSet as the previous version of the concept
            if(conceptIdToArtDecorConceptListIdMap.containsKey(conceptId)){
                artdecorConceptListId = conceptIdToArtDecorConceptListIdMap.get(conceptId);
                artDecorConcept.setArtdecorConceptListId(artdecorConceptListId);
                artDecorConcept.setXMLConceptListTypeStatus("SAME");
            }
            // no conceptListId exists to reuse for this concept
            // this happens when the concept we're looking at is new and a different concept also uses the same ValueSet,
            // meaning that the ValueSet was registered, but no conceptListId exists to link it to our current concept
            else{
                String artdecorValueSetId = existingArtDecorValueSet.getArtDecorValueSetId();
                addConceptListAssociations(artDecorConcept, conceptId, effectiveDate, artdecorValueSetId);
                artDecorConcept.setXMLConceptListTypeStatus("SAME");
            }
        }
        // the ValueSet has changed
        else{
            // retrieve the artdecorValueSetId, as this is a fixed value which will be used by the new valueset
            String artdecorValueSetId = existingArtDecorValueSet.getArtDecorValueSetId();
            // tell the previous value set that its values are deprecated
            existingArtDecorValueSet.setStatusCode("deprecated");
            // set the current art-decor concept's list status to changed
            artDecorConcept.setXMLConceptListTypeStatus("CHANGED");

            //
            if(!artDecorChangedValueSetMap.containsKey(codeListRef)) {
                addValueSetAssociations(artDecorValueSet, artdecorValueSetId, codeListRef);
            }
            addConceptListAssociations(artDecorConcept, conceptId, effectiveDate, artdecorValueSetId);
        }
    }

    /**
     * add associations for the concept list
     * @param artDecorConcept
     * @param conceptId
     * @param effectiveDate
     * @param artdecorValueSetId
     */
    private void addConceptListAssociations(ArtDecorConcept artDecorConcept, String conceptId, String effectiveDate, String artdecorValueSetId){
        // generate a new conceptlist identifier based on this artdecor concept's identifier
        String artdecorConceptListId = identifierManager.getNextConceptListId(artDecorConcept.getArtdecorConceptId());
        artDecorConcept.setArtdecorConceptListId(artdecorConceptListId);

        // Store the conceptListId currently in use for the conceptId. This is used to refer to when optionlist is the same
        conceptIdToArtDecorConceptListIdMap.put(conceptId, artdecorConceptListId);

        // create terminology for it and store the terminology
        ArtDecorTerminologyValueSet artDecorTerminologyValueSet = new ArtDecorTerminologyValueSet(artdecorConceptListId, artdecorValueSetId, effectiveDate, effectiveDate);
        artDecorTerminologyValueSetList.add(artDecorTerminologyValueSet);
    }

    /**
     * keeps track of a value set
     * - tracks a global list of value sets (e.g. because multiple items and versions can refer to the same code list)
     * - marks this as a changed value set (otherwise we wouldn't be in this function)
     * @param artDecorValueSet   the ART-DECOR value set
     * @param artdecorValueSetId value set id
     * @param codeListRef        name of the codelist
     */
    private void addValueSetAssociations(ArtDecorValueSet artDecorValueSet, String artdecorValueSetId, String codeListRef){
        // set the valueset identifier
        artDecorValueSet.setArtdecorValueSetId(artdecorValueSetId);

        // check whether the ValueSet exists in the "current" valuesetmap, if not it's the first time the
        // valueset is encountered and we'll add it to the "current" map
        if(!artDecorValueSetMap.containsKey(codeListRef)) {
            artDecorValueSetMap.put(codeListRef, artDecorValueSet);
        }

        artDecorChangedValueSetMap.put(codeListRef, artDecorValueSet);

        // store the new artDecorValueSet
        artDecorValueSetList.add(artDecorValueSet);

    }

    /**
     * Generate the starting XML
     * @return starting XML string
     */
    private String generateStartXML(){
        return ("<?xml-model\n" +
                "href=\"http://art-decor.org/ADAR/rv/DECOR.sch\" type=\"application/xml\" schematypes=\"http://purl.oclc.org/dsdl/schematron\"\n" +
                "?>" +
                "<decor xmlns:cda=\"urn:hl7-org:v3\" xmlns:hl7=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xsi:noNamespaceSchemaLocation=\"http://art-decor.org/ADAR/rv/DECOR.xsd\" repository=\"false\" private=\"true\" " +
                "cda:dummy-1=\"urn:hl7-org:v3\" hl7:dummy-2=\"urn:hl7-org:v3\" xsi:dummy-1=\"http://www.w3.org/2001/XMLSchema-instance\">\n");
    }

    /**
     * Generate the Project XML
     * @return XML string
     */
    private String generateProjectXML(){
        return artDecorProject.toXML();
    }

    /**
     * Generates the XML for the datasets, with each dataset generating its own XML
     * @return XML string
     * @throws Exception
     */
    private String generateDatasetsXML() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<datasets>\n");
        for(ArtDecorDataset artDecorDataset:artDecorDatasetList){
            stringBuilder.append(artDecorDataset.toXML());
        }
        stringBuilder.append("</datasets>\n");
        return stringBuilder.toString();
    }

    /**
     * Generate the Scenarios XML. Currently has no real contents
     * @return XML string
     */
    private String generateScenarioXML(){
        return "<scenarios>\n" +
                "<actors/>\n" +
                "</scenarios>\n";
    }

    /**
     * Generate the XML for the identifiers part
     * @return XML string
     */
    private String generateIDsXML(){
        String outputXML = "<ids>\n";
        outputXML += identifierManager.getProjectIdsXML();
        outputXML += "</ids>\n";
        return outputXML;
    }

    /**
     * Generate the terminology XML part
     * @return XML string
     */
    private String generateTerminologyAssessmentXML(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<terminology>\n");

        // generate XML linking a Concept to its ValueSet
        for(ArtDecorTerminologyValueSet artDecorTerminologyValueSet:artDecorTerminologyValueSetList){
            stringBuilder.append(artDecorTerminologyValueSet.toXML());
        }

        // generate XML linking concept to a codesystem and code
        for(ArtDecorTerminologyConcept artDecorTerminologyConcept:artDecorTerminologyConceptList){
            stringBuilder.append(artDecorTerminologyConcept.toXML());
        }

        // add the codesystem xml
        stringBuilder.append(identifierManager.getCodeSystemXML());

        // add the Value Set XML
        stringBuilder.append(generateValueSetXML());

        stringBuilder.append("</terminology>");
        return stringBuilder.toString();
    }

    /**
     * Generate the XML for all the valuesets
     * @return XML string
     */
    private String generateValueSetXML(){
        StringBuilder stringBuilder = new StringBuilder();
        for(ArtDecorValueSet artDecorValueSet:artDecorValueSetList){
            stringBuilder.append(artDecorValueSet.toXML());
        }
        return stringBuilder.toString();
    }

    /**
     * Generate the final part of the XML
     * @return XML string
     */
    private String generateFinishXML(){
        return "<rules/>\n" +
               "<issues notifier=\"on\"/>\n" +
               "</decor>\n";
    }

    /**
     * Write the output to a file
     * @param fileName output filename
     * @throws Exception
     */
    public void writeOutput(String fileName) throws Exception{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            bufferedWriter.write(generateStartXML());
            bufferedWriter.write(generateProjectXML());
            bufferedWriter.write(generateDatasetsXML());
            bufferedWriter.write(generateScenarioXML());
            bufferedWriter.write(generateIDsXML());
            bufferedWriter.write(generateTerminologyAssessmentXML());
            bufferedWriter.write(generateFinishXML());
            bufferedWriter.flush();
            bufferedWriter.close();

    }
}
