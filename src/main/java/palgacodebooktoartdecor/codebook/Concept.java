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

import palgacodebooktoartdecor.artdecor.ArtDecorConcept;
import palgacodebooktoartdecor.artdecor.ArtDecorValueSet;
import org.apache.poi.ss.usermodel.Row;
import palgacodebooktoartdecor.settings.IdentifierManager;
import palgacodebooktoartdecor.settings.Statics;
import palgacodebooktoartdecor.utils.ExcelUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of a concept as read in the excel (a row from the Excel)
 */
class Concept {

    private String effectiveDate;
    private String id;
    private String codesystem;
    private String code;
    private String description_code;
    private String codelist_ref="";
    private String parent;
    private String data_type;
    private String versionLabel;
    private String statusCode;
    private Map<String, LanguageConcept> languageConceptMap = new HashMap<>();
    private Map<String, ConceptOption> conceptOptionsMap = new LinkedHashMap<>();
    private Map<String, String> propertiesMap = new HashMap<>();

    /**
     * Concept in Excel constructor
     * @param id               id of the concept
     * @param codesystem       codesystem of the concept
     * @param code             code of the concept in the codesystem
     * @param description_code description of the code in the codesystem
     * @param properties       used for the ART-DECOR properties
     * @param codelist_ref     reference to a different Excel sheet which contains the codelist for the concept
     * @param parent           the parent of this concept
     * @param data_type        data type of the concept
     * @param effectiveDate    effective date of the concept
     * @param versionLabel     version label of the concept
     * @param statusCode       status code of the concept
     */
    Concept(String id, String codesystem, String code, String description_code, String properties, String codelist_ref, String parent, String data_type, String effectiveDate, String versionLabel, String statusCode){
        this.effectiveDate = effectiveDate;
        this.id = id;
        this.code = code;
        this.codesystem = codesystem;
        this.description_code = description_code;
        this.codelist_ref = codelist_ref;
        this.parent = parent;
        this.versionLabel = versionLabel;
        this.data_type = data_type;
        this.statusCode = statusCode;
        handleCustomProperties(properties);
    }

    /**
     * returns the concept's id
     * @return the concept's id
     */
    public String getId() {
        return id;
    }

    /**
     * returns the concept's code
     * @return the concept's code
     */
    public String getCode() {
        return code;
    }

    /**
     * validates whether the entry is valid
     * @param codeListEntryCodesystem       codelist entry's codesystem
     * @param codeListEntryCode             codelist entry's code
     * @param codeListEntryDescription_code codelist entry's code description
     * @param codelist_ref                  reference to the codelist's worksheet
     * @return true/false
     */
    private boolean isValidEntry(String codeListEntryCodesystem, String codeListEntryCode, String codeListEntryDescription_code, String codelist_ref){
        boolean isValidEntry=true;
        if(Statics.mayBeTypo(codesystem)){
            System.out.println("codebook version: "+versionLabel+"; Codelist Entry: Codesystem found: "+codesystem+" in sheet "+codelist_ref+". Did you mean "+Statics.getTypoValue(codesystem)+"?");
            isValidEntry = false;
        }

        // first check whether one of the mandatory fields is empty
        if(codeListEntryCode.equalsIgnoreCase("")){
            System.out.println("codebook version: "+versionLabel+"; Codelist Entry: Mandatory code missing in codelist "+codelist_ref+" for concept " + id);
            isValidEntry = false;
        }
        if(codeListEntryCodesystem.equalsIgnoreCase("")){
            System.out.println("codebook version: "+versionLabel+"; Codelist Entry: Mandatory codesystem missing in codelist "+codelist_ref+" for concept " + id);
            isValidEntry = false;
        }
        if(codeListEntryDescription_code.equalsIgnoreCase("")){
            System.out.println("codebook version: "+versionLabel+"; Codelist Entry: Mandatory code description missing in codelist "+codelist_ref+" for concept " + id);
            isValidEntry = false;
        }
        return isValidEntry;
    }

    /**
     * adds a codelist entry to the concept object, which represent a single concept in the Excel codebook
     * the codelist entry is basically the row we found by taking the codelist_ref for the concept, open the
     * appropriate worksheet and loop over the codelist rows
     * @param row                row in the codelist
     * @param codelistHeaderList codelist header
     * @param languages          languages which should be added
     * @param codelist_ref       reference to the codelist sheet
     */
    void addCodeListEntry(Row row, List<String> codelistHeaderList, Set<String> languages, String codelist_ref){
        String codeListEntryCode = ExcelUtils.getValue(row, "code", codelistHeaderList);
        String codeListEntryDescription_code = ExcelUtils.getValue(row, "description_code", codelistHeaderList);
        String codeListEntryCodesystem = ExcelUtils.getValue(row, "codesystem", codelistHeaderList);

        if(isValidEntry(codeListEntryCodesystem, codeListEntryCode, codeListEntryDescription_code, codelist_ref)){
            // create a ConceptOption object for this entry, based on the codesystem, the code within the codesystem and the description of that code.
            ConceptOption conceptOption = new ConceptOption(codeListEntryCodesystem, codeListEntryCode, codeListEntryDescription_code);
            // get the language-specific description and value
            for (String language : languages) {
                String languageDescription = ExcelUtils.getValue(row, "description_" + language, codelistHeaderList);
                String languageValue = ExcelUtils.getValue(row, "value_" + language, codelistHeaderList);
                // add them as languageConceptOptions
                conceptOption.addLanguageConceptOptions(language, languageValue, languageDescription);
            }
            // add the conceptOption to this concept's conceptOptionsMap
            conceptOptionsMap.put(conceptOption.code, conceptOption);
        }
    }

    void addCodeListEntryTSV(String[] row, Map<String, Integer> codeListTsvIndexHeader, Set<String> languages, File codelist_ref){
        String codeListEntryCode = row[codeListTsvIndexHeader.get("code")];
        String codeListEntryDescription_code = row[codeListTsvIndexHeader.get("description_code")];
        String codeListEntryCodesystem = row[codeListTsvIndexHeader.get("codesystem")];

       // System.out.println("ADDING: " + codeListEntryCodesystem + ":" + codeListEntryCode);

        if(isValidEntry(codeListEntryCodesystem, codeListEntryCode, codeListEntryDescription_code, codelist_ref.getName())){
            ConceptOption conceptOption = new ConceptOption(codeListEntryCodesystem, codeListEntryCode, codeListEntryDescription_code);

            for (String language : languages) {
                String languageDescription = row[codeListTsvIndexHeader.get("description_" + language)];
                String languageValue = row[codeListTsvIndexHeader.get("value_" + language)];
                conceptOption.addLanguageConceptOptions(language, languageValue, languageDescription);
            }
            conceptOptionsMap.put(conceptOption.code, conceptOption);
        }
    }


    /**
     * creates an ART-DECOR ValueSet
     * @return the newly created valueset
     */
    ArtDecorValueSet generateArtDecorValueSet(){
        ArtDecorValueSet artDecorValueSet = new ArtDecorValueSet(id, id, versionLabel, effectiveDate);
        for(ConceptOption conceptOption:conceptOptionsMap.values()){
            String codesystemName = conceptOption.codesystemName;
            String codesystemId = IdentifierManager.getIdentifierManager().getCodeSystemId(codesystemName, effectiveDate);
            boolean addToExceptionList = Statics.isExceptionCodeList(codesystemId);
            // add the option to the valueset
            artDecorValueSet.addConceptOption(conceptOption.code,
                    codesystemId,
                    codesystemName,
                    conceptOption.description_code,
                    addToExceptionList);

            // add the concept's language specific options

            // TODO: this line below works but is ugly and confusing. We replaced it with the lines slightly lower
            //  Test whether the output is the same! Should be, but test anyway!!
//            conceptOption.addValueSetDesignations(artDecorValueSet, addToExceptionList);

            Collection<LanguageConceptOptions> collection = conceptOption.getLanguageConceptOptions();
            for(LanguageConceptOptions languageConceptOptions:collection){
                artDecorValueSet.addConceptDesignation(languageConceptOptions.language, languageConceptOptions.description, addToExceptionList);
            }

        }


        for(LanguageConcept languageConcept:languageConceptMap.values()){
            String language = languageConcept.language;
            artDecorValueSet.addConceptLanguageValueSet(language, Statics.getOptionsInLanguage(language)+" "+languageConcept.description);
        }

        return artDecorValueSet;
    }

    /**
     * Generate an ART-DECOR concept for this Excel concept
     * @param artdecorConceptId identifier for this concept in ART-DECOR
     * @return the generate ART-DECOR concept
     */
    ArtDecorConcept generateArtDecorConcept(String artdecorConceptId){
        ArtDecorConcept artDecorConcept;
        if(conceptOptionsMap.size()>0){
            String type = "code";
            artDecorConcept = new ArtDecorConcept(id, artdecorConceptId, effectiveDate, type, parent, statusCode);
        }
        else{
            String type = Statics.getArtDecorValueDomainType(data_type);
            artDecorConcept = new ArtDecorConcept(id, artdecorConceptId, effectiveDate, type, parent, statusCode);
        }

        artDecorConcept.setPropertyMap(propertiesMap);
        //System.out.println("languageConceptMap.values() = " + languageConceptMap.values().toString());
        for(LanguageConcept languageConcept:languageConceptMap.values()){
            artDecorConcept.addLanguageConcept(languageConcept.language, languageConcept.name, languageConcept.description);
        }
        return artDecorConcept;
    }

    /**
     * returns the effective date for the concept
     * @return the effective date
     */
    String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * returns the codesystem for the concept
     * @return the codesystem
     */
    String getCodesystem() {
        return codesystem;
    }

    /**
     * returns the description of the code
     * @return the description of the code
     */
    String getDescription_code() {
        return description_code;
    }

    /**
     * returns the codelist reference
     * @return the codelist reference
     */
    String getCodelist_ref() {
        return codelist_ref;
    }

    /**
     * adds a language with the description of the project in that language
     * @param language            the language
     * @param languageDescription project description in that language
     */
    void addLanguageConcept(String language, String name, String languageDescription){
        LanguageConcept languageConcept = new LanguageConcept(language, name, languageDescription);
        languageConceptMap.put(language, languageConcept);
    }

    /**
     * returns whether the concept has options
     * @return true/false
     */
    boolean hasConceptOptions(){
        return conceptOptionsMap.size()>0;
    }

    /**
     * Parses a properties value
     * e.g. {DATA_COLNAME=Aantalinzendingen}{OTHER_PROPERTY=SomeValue}
     * @param properties the string which contains the properties
     */
    private void handleCustomProperties(String properties){
        if(!properties.equalsIgnoreCase("")){
            Pattern pattern = Pattern.compile("\\{(.+?)=(.+?)}.*");
            Matcher matcher = pattern.matcher(properties);
            while(matcher.find()){
                propertiesMap.put(matcher.group(1).trim(), matcher.group(2).trim());
            }
        }
    }

    /**
     * Class which stores a language with the description of the codebook in that language
     */
    private static class LanguageConcept{
        private String language;
        private String name;
        private String description;

        LanguageConcept(String language, String name, String description){
            this.description = description;
            this.name = name;
            this.language = language;
        }
    }

    /**
     * Class which stores options (codelist values) for a concept
     * This is basically the contents of an Excel codelist worksheet, e.g. line 2.
     * 1. value_en	description_en	codesystem	        code	description_code
     * 2. value1	description1	snki-codesystem-1	1	    The description from the ontology for code 1
     */
    private class ConceptOption {
        private String codesystemName;
        private String code;
        private String description_code;
        private Map<String, LanguageConceptOptions> languageConceptOptionsMap = new HashMap<>();

        /**
         * constructor
         * @param codesystemName   name of the codesystem for the option
         * @param code             code for the option
         * @param description_code description of the code
         */
        ConceptOption(String codesystemName, String code, String description_code){
            this.code = code;
            this.codesystemName = codesystemName;
            this.description_code = description_code;
        }


//        private void addLanguageConceptOptions(String language, LanguageConceptOptions languageConceptOptions){
//            languageConceptOptionsMap.put(language, languageConceptOptions);
//        }

        /**
         * adds the value and description of a codelist entry for a certain language, e.g. for English, line 2.
         * 1. value_en	description_en
         * 2. value1	description1
         * @param language            the language
         * @param languageValue       the value in that language
         * @param languageDescription the description in that language
         */
        private void addLanguageConceptOptions(String language, String languageValue, String languageDescription){
            LanguageConceptOptions languageConceptOptions = new LanguageConceptOptions(language, languageValue, languageDescription);
            languageConceptOptionsMap.put(language, languageConceptOptions);
        }


        private void addValueSetDesignations(ArtDecorValueSet artDecorValueSet, boolean addToExceptionList){
            for(LanguageConceptOptions languageConceptOptions:languageConceptOptionsMap.values()){
                artDecorValueSet.addConceptDesignation(languageConceptOptions.language, languageConceptOptions.description, addToExceptionList);
            }
        }

        /**
         * returns all the conceptOptions from the map
         * @return all the conceptOptions from the map
         */
        Collection<LanguageConceptOptions> getLanguageConceptOptions(){
            return languageConceptOptionsMap.values();
        }
    }

    /**
     * class for the language concept options
     */
    private class LanguageConceptOptions{
        private String language;
        private String value;
        private String description;

        /**
         * constructor
         * @param language    the language
         * @param value       the value in that language
         * @param description the description in that language
         */
        LanguageConceptOptions(String language, String value, String description){
            this.description = description;
            this.language = language;
            this.value = value;
        }
    }


}




