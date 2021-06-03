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

package palgacodebooktoartdecor.artdecor;

import palgacodebooktoartdecor.utils.StringUtils;
import palgacodebooktoartdecor.settings.Statics;

import java.util.*;

/**
 * Representation of an Art-Decor Valueset
 */
public class ArtDecorValueSet {
    private String name;
    private String displayName;
    private String versionLabel;
    private String effectiveDate;
    private String statusCode = "draft";

    // normal concept options
    private List<ConceptOption> conceptOptionList = new ArrayList<>();
    // exception concept options
    private List<ConceptOption> exceptionConceptOptionList = new ArrayList<>();
    // description of the valueset in the languages
    private List<LanguageValueSet> languageValueSetList = new ArrayList<>();

    private String artdecorValueSetId;

    public ArtDecorValueSet(String name, String displayName, String versionLabel, String effectiveDate){
        this.name = StringUtils.removeNonAlphanumericCharacters(name);
        this.displayName = displayName;
        this.versionLabel = versionLabel;
        this.effectiveDate = effectiveDate;
    }

    /**
     * sets the identifier for this set
     * @param artdecorValueSetId the identifier
     */
    public void setArtdecorValueSetId(String artdecorValueSetId) {
        this.artdecorValueSetId = artdecorValueSetId;
    }

    /**
     * adds a description for a valueset in the language
     * @param language    the language
     * @param description the description
     */
    public void addConceptLanguageValueSet(String language, String description){
        languageValueSetList.add(new LanguageValueSet(language, description));
    }

    /**
     * add a new conceptOption. Either adds it to the exceptionConceptOptionList or to the conceptOptionsList
     * @param conceptCode           code of the conceptOption
     * @param conceptCodeSystem     codesystem id
     * @param conceptCodeSystemName codesystem name
     * @param displayName           description of the code
     * @param addToExceptionList    whether the option is an Exception (e.g. NULL value) or a normal option
     */
    public void addConceptOption(String conceptCode, String conceptCodeSystem, String conceptCodeSystemName, String displayName, boolean addToExceptionList){
        ConceptOption conceptOption = new ConceptOption(conceptCode, conceptCodeSystem, conceptCodeSystemName, displayName);
        if(addToExceptionList){
            exceptionConceptOptionList.add(conceptOption);
        }
        else {
            conceptOptionList.add(conceptOption);
        }
    }

    /**
     *
     * @param language           language
     * @param displayName        description of the concept option in the language
     * @param addToExceptionList whether it is an Exception (e.g. NULL value)
     */
    public void addConceptDesignation(String language, String displayName, boolean addToExceptionList){
        ConceptOption conceptOption;
        if(addToExceptionList){
            conceptOption = exceptionConceptOptionList.get(exceptionConceptOptionList.size()-1);
        }
        else {
            conceptOption = conceptOptionList.get(conceptOptionList.size()-1);
        }
        conceptOption.addDesignation(language, displayName);
    }

    /**
     * sets the statusCode (e.g. draft)
     * @param statusCode the statusCode
     */
    public void setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }

    /**
     * sort the conceptOptionsList based on the conceptCodes
     */
    private void sortList(){
        conceptOptionList.sort((o1, o2) -> o1.conceptCode.compareToIgnoreCase(o2.conceptCode));
    }

    /**
     * check whether two ArtDecorValueSets have identical conceptOptionLists
     * @param otherValueSet the ArtDecorValueSet to which to compare the current ArtDecorValueSet
     * @return true/false
     */
    public boolean sameValues(ArtDecorValueSet otherValueSet){
        if(conceptOptionList.size()!=otherValueSet.conceptOptionList.size())
            return false;

        // sort the lists by code
        sortList();
        otherValueSet.sortList();

        for(int i=0; i<conceptOptionList.size(); i++){
            ConceptOption conceptOption1 = conceptOptionList.get(i);
            ConceptOption conceptOption2 = otherValueSet.conceptOptionList.get(i);
            if(!conceptOption1.conceptCode.equalsIgnoreCase(conceptOption2.conceptCode) ||
               !conceptOption1.conceptCodeSystem.equalsIgnoreCase(conceptOption2.conceptCodeSystem) ||
               !conceptOption1.displayName.equalsIgnoreCase(conceptOption2.displayName)){
                return false;
            }
        }
        return true;
    }

    /**
     * transform this ArtDecorValueSet into XML
     * @return String representation of this valueset
     */
    public String toXML(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<valueSet name=\""+name+"_Opts\" displayName=\""+displayName+" Options\" versionLabel=\""+versionLabel+"\" id=\""+artdecorValueSetId+"\" effectiveDate=\""+effectiveDate+"\" statusCode=\""+statusCode+"\">\n");

        for(LanguageValueSet languageValueSet:languageValueSetList){
            stringBuilder.append("<desc language=\""+languageValueSet.language+"\">\n");
            stringBuilder.append(languageValueSet.description+"\n");
            stringBuilder.append("</desc>\n");
        }

        stringBuilder.append("<conceptList>\n");
        for(ConceptOption conceptOption:conceptOptionList){
            stringBuilder.append(conceptOption.toXML());
        }
        for(ConceptOption conceptOption:exceptionConceptOptionList){
            stringBuilder.append(conceptOption.toXMLException());
        }
        stringBuilder.append("</conceptList>\n");
        stringBuilder.append("</valueSet>\n");

        return stringBuilder.toString();
    }

    /**
     * returns the valuesetId
     * @return the valuesetId
     */
    public String getArtDecorValueSetId() {
        return artdecorValueSetId;
    }


    /**
     * single concept option
     */
    private class ConceptOption {
        private String conceptCode;
        private String conceptCodeSystem;
        private String conceptCodeSystemName;
        private String displayName;
        private String level = "0";
        private String type = "L";

        private List<Designation> designationList = new ArrayList<>();

        /**
         * constructs a ConceptOption
         * @param conceptCode           code of the option
         * @param conceptCodeSystem     codesystem id for the option
         * @param conceptCodeSystemName codesystem name for the option
         * @param displayName           displayName for the option's code
         */
        ConceptOption(String conceptCode, String conceptCodeSystem, String conceptCodeSystemName, String displayName){
            this.conceptCodeSystemName = conceptCodeSystemName;
            this.conceptCodeSystem = conceptCodeSystem;
            this.conceptCode = conceptCode;
            this.displayName = displayName;
        }

        /**
         * add a designation for this option
         * @param language    the language
         * @param displayName the displayName in this language
         */
        private void addDesignation(String language, String displayName){
            designationList.add(new Designation(language, displayName));
        }

        /**
         * transforms this option into XML
         * This is a concept with a designation per language
         * @return String representation for this option
         */
        private String toXML(){
            StringBuilder stringBuilder = new StringBuilder();
            // TODO: the exception vs code check should be done via a proper variable instead of interpreting the displayName string
            stringBuilder.append("<" + (displayName.contains("nullflavor") ? "exception" : "concept") + " code=\""+conceptCode+"\" codeSystem=\""+ conceptCodeSystem+"\" codeSystemName=\""+conceptCodeSystemName+"\" displayName=\""+escapeXML(displayName)+"\" level=\""+level+"\" type=\""+type+"\">\n");
            for(Designation designation:designationList){
                stringBuilder.append(designation.toXML());
            }
            stringBuilder.append("</concept>\n");
            return stringBuilder.toString();
        }

        /**
         * transforms this option into XML in case it's an Exception
         * This is an exception with a designation per language
         * @return String representation for this option
         */
        private String toXMLException(){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<exception code=\""+conceptCode+"\" codeSystem=\""+ conceptCodeSystem+"\" codeSystemName=\""+conceptCodeSystemName+"\" displayName=\""+escapeXML(displayName)+"\" level=\""+level+"\" type=\""+type+"\">\n");
            for(Designation designation:designationList){
                stringBuilder.append(designation.toXML());
            }
            stringBuilder.append("</exception>\n");
            return stringBuilder.toString();
        }
    }

    /**
     * "   &quot;
     * '   &apos;
     * <   &lt;
     * >   &gt;
     * &   &amp;
     * @param in
     * @return
     */
    public String escapeXML(String in)
    {
        return in.replace("\"", "&quot;").replace("'", "&apos;").replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;");
    }

    /**
     * stores the valueset's description in a language
     */
    private class LanguageValueSet{
        private String language;
        private String description;

        LanguageValueSet(String language, String description){
            this.description = description;
            this.language = Statics.getArtDecorLanguage(language);
        }
    }

    /**
     * stores an option's designation (its language-specific description)
     */
    private class Designation {
        private String language;
        private String type = "preferred";
        private String displayName;

        Designation(String language, String displayName){
            this.displayName = displayName;
            this.language = language;
        }

        private String toXML(){
            return "<designation language=\""+ Statics.getArtDecorLanguage(language)+"\" type=\""+type+"\" displayName=\""+escapeXML(displayName)+"\"/>\n";
        }
    }

}
