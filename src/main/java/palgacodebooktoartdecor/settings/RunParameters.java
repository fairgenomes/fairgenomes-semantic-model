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

package palgacodebooktoartdecor.settings;

import java.io.File;
import java.util.*;

/**
 * Parameters for a run
 */
public class RunParameters {
    private String codebookDirectory;
    private String projectId;
    private String projectPrefix;
    private String experimental;
    private String statusCode;
    private String defaultLanguage;

    private String authorString;
    private String copyrightString;
    private String authorsStringFormatted;
    private String copyrightStringFormatted;

    private Map<String, LanguageParameters> languageParametersMap = new HashMap<>();

    /**
     * Constructor for the runparameters
     * @param codebookDirectory directory that contains one or more codebooks
     * @param projectId         ART-DECOR project id
     * @param projectPrefix     ART-DECOR project prefix
     * @param experimental      whether the project is experimental
     * @param authorString      userid;email address;name
     * @param copyrightString      userid;email address;name
     * @param statusCode        status of the project (draft or final)
     */
    public RunParameters(String codebookDirectory, String projectId, String projectPrefix, String experimental, String authorString, String copyrightString, String statusCode){
        this.codebookDirectory = codebookDirectory;
        this.projectId = projectId;
        this.experimental = experimental;
        this.statusCode = statusCode;
        this.authorString = authorString;
        this.copyrightString = copyrightString;
        formatAuthorString();
        formatCopyrightString();
        formatProjectPrefix(projectPrefix);
    }

    /**
     * ensures the project prefix ends with a -
     * @param projectPrefix the current project prefix
     */
    private void formatProjectPrefix(String projectPrefix){
        if(!projectPrefix.endsWith("-") && !(projectPrefix.equalsIgnoreCase(""))){
            projectPrefix+="-";
        }
        this.projectPrefix = projectPrefix;
    }

    /**
     * returns the codebook directory+output.xml
     * @return the codebook directory+output.xml
     */
    public String getOutputFile(){
        return codebookDirectory.endsWith("\\")||codebookDirectory.endsWith("/")?codebookDirectory+"output.xml":codebookDirectory+File.separator+"output.xml";
    }

    /**
     * returns the author string
     * @return the author string
     */
    public String getAuthorString(){
        return authorString;
    }

    /**
     * returns the author string
     * @return the author string
     */
    public String getCopyrightString(){
        return copyrightString;
    }

    /**
     * formats the author string
     * the unformatted author string is userid;email address;name
     * this is changed into an xml format
     */
    private void formatAuthorString(){
        if(authorString.length()>0) {
            StringBuilder stringBuilder = new StringBuilder();
            int idIndex = 0;
            String[] splitString = authorString.split("\\n");
            for (String authorString : splitString) {
                String[] splitString2 = authorString.split(";");
                String username = splitString2[0];
                String email = splitString2[1];
                String name = splitString2[2];
                stringBuilder.append("<author id=\"").append(++idIndex).append("\" username=\"").append(username).append("\" email=\"").append(email).append("\" notifier=\"off\">").append(name).append("</author>");
            }
            authorsStringFormatted = stringBuilder.toString().trim();
        }
    }

    /**
     * formats the copyright string
     * the unformatted copyright string is institute;year(s);author/contributor/reviewer
     * this is changed into an xml format
     * <copyright by="NKI and VUmc " years="2016 2017 2018 2019" type="author"/>
     */
    private void formatCopyrightString(){
        if(copyrightString.length()>0) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] splitString = copyrightString.split("\\n");
            for (String authorString : splitString) {
                String[] splitString2 = authorString.split(";");
                String institute = splitString2[0];
                String years = splitString2[1];
                String contribution = splitString2[2];
                stringBuilder.append("<copyright by=\"").append(institute).append("\" years=\"").append(years).append("\" type=\"").append(contribution).append("\"/>");
            }
            copyrightStringFormatted = stringBuilder.toString().trim();
        }
    }

    /**
     * sets the default language
     * @param defaultLanguage the default language
     */
    public void setDefaultLanguage(String defaultLanguage){
        this.defaultLanguage = defaultLanguage;
    }

    /**
     * returns the default language
     * @return the default language
     */
    public String getDefaultLanguage(){
        return defaultLanguage;
    }

    /**
     * adds project information for a specific language
     * @param language           the language
     * @param projectDescription the description of the project in that language
     * @param projectName        the name of the project in that language
     */
    public void addLanguageSettings(String language, String projectDescription, String projectName){
        languageParametersMap.put(language, new LanguageParameters(projectDescription, projectName));
    }

    /**
     * returns all languages
     * @return a set with all the languages
     */
    public Set<String> getLanguages(){
        return languageParametersMap.keySet();
    }

    /**
     * returns the status of the project (draft or final)
     * @return the status of the project (draft or final)
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * returns the codebook directory
     * @return the codebook directory
     */
    public String getCodebookDirectory(){
        return codebookDirectory;
    }

    /**
     * returns the project identifier
     * @return the project identifier
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * returns the project prefix
     * @return the project prefix
     */
    public String getProjectPrefix() {
        return projectPrefix;
    }

    /**
     * returns whether the project is experimental
     * @return whether the project is experimental
     */
    public String getExperimental() {
        return experimental;
    }

    /**
     * returns the project name in a specific language
     * @param language the language
     * @return the name of the project in that language
     */
    public String getProjectName(String language){
        if(languageParametersMap.containsKey(language)) {
            return languageParametersMap.get(language).getProjectName();
        }
        System.out.println("getProjectName = NULL !!!");
        return "";
    }

    /**
     * returns the project description in a specific language
     * @param language the language
     * @return the project description in that language
     */
    public String getProjectDescription(String language){
        if(languageParametersMap.containsKey(language)) {
            return languageParametersMap.get(language).getProjectDescription();
        }
        return "";
    }

    /**
     * returns the xml variant of the author string
     * @return the xml variant of the author string
     */
    public String getAuthorsStringFormatted(){
        return authorsStringFormatted;
    }

    /**
     * returns the xml variant of the copyright string
     * @return the xml variant of the copyright string
     */
    public String getCopyrightStringFormatted(){
        return copyrightStringFormatted;
    }


    /**
     * returns the project reference, which is part of the URL
     * @return the project reference
     */
    public String getProjectReference(){
        return projectPrefix.substring(0, projectPrefix.length()-1);
    }

    /**
     * class for storing language parameters (the project name and description in a specific language)
     */
    class LanguageParameters{
        private String projectName;
        private String projectDescription;

        /**
         * constructor
         * @param projectDescription project description in a language
         * @param projectName        project name in a language
         */
        LanguageParameters(String projectDescription, String projectName){
            this.projectDescription = projectDescription;
            this.projectName = projectName;
            System.out.println("LanguageParameters: projectDescription " + projectDescription);
            System.out.println("LanguageParameters: projectName " + projectName);

        }

        /**
         * returns the project name
         * @return the project name
         */
        String getProjectName() {
            return projectName;
        }

        /**
         * returns the project description
         * @return the project description
         */
        String getProjectDescription() {
            return projectDescription;
        }
    }
}
