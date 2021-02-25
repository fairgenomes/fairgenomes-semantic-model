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

import java.util.*;

/**
 * Static parameters
 */
public class Statics {
    private static Map<String, String> languageMap = new HashMap<>();
    private static Map<String, String> valueDomainTypeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Map<String, String> optionsInLangaugeMap = new HashMap<>();
    private static List<String> exceptionCodelists = new ArrayList<>();
    private static Map<String,String> typoMap = new HashMap<>();

    static{
        languageMap.put("nl", "nl-NL");
        languageMap.put("en", "en-US");

        // art-decor supports the following data types:
        // https://art-decor.org/mediawiki/index.php?title=DECOR-dataset
        // attempting to convert some of our datatypes to their datatypes
        valueDomainTypeMap.put("numeric", "decimal");
        valueDomainTypeMap.put("text", "string");
        valueDomainTypeMap.put("st", "string");
        valueDomainTypeMap.put("int", "count");
        valueDomainTypeMap.put("real", "decimal");

        valueDomainTypeMap.put("string", "string");
        valueDomainTypeMap.put("date", "date");
        valueDomainTypeMap.put("count", "count");
        valueDomainTypeMap.put("decimal", "decimal");
        valueDomainTypeMap.put("boolean", "boolean");

        valueDomainTypeMap.put("LOG", "code");
        valueDomainTypeMap.put("CAT", "code");
        valueDomainTypeMap.put("STR", "string");
        valueDomainTypeMap.put("REAL", "decimal");




        optionsInLangaugeMap.put("nl", "Opties voor");
        optionsInLangaugeMap.put("en", "Options for");

        exceptionCodelists.add("2.16.840.1.113883.5.1008"); // Nullflavors

        typoMap.put("SNOMEDCT", "SNOMED CT");
    }

    /**
     * check whether the codesystem name contains a commonly made mistake
     * @param codesystem the codesystem to check
     * @return whether there's probably a typo
     */
    public static boolean mayBeTypo(String codesystem){
        return typoMap.containsKey(codesystem);
    }

    /**
     * returns the codesystem name without the mistake
     * @param codesystem codesystem name with the issue
     * @return the codesystem name without the mistake
     */
    public static String getTypoValue(String codesystem){
        return typoMap.get(codesystem);
    }

    /**
     *
     * @param language the language
     * @return a string containing "options for" in that language
     */
    public static String getOptionsInLanguage(String language){
        return optionsInLangaugeMap.get(language);
    }

    /**
     *
     * @param language the language
     * @return the language in a format ART-DECOR expects
     */
    public static String getArtDecorLanguage(String language){
        return languageMap.get(language);
    }

    /**
     * returns all languages that are in the art-decor codebook (english and dutch)
     * @return all languages
     */
    public static Set<String> getLanguages(){
        return languageMap.keySet();
    }

    /**
     * attempts to convert the type found to a type that exists in ART-DECOR
     * @param type the type to convert
     * @return the converted type
     */
    public static String getArtDecorValueDomainType(String type){
        if(valueDomainTypeMap.containsKey(type.toLowerCase())) {
            return valueDomainTypeMap.get(type.toLowerCase());
        }
        System.err.println("NOT FOUND FOR CONVERSION: "+type);
        return "string";
    }

    /**
     * checks whether the codelist found is an Exception code list
     * @param codeSystemId the id of the codelist
     * @return true/false
     */
    public static boolean isExceptionCodeList(String codeSystemId){
        return exceptionCodelists.contains(codeSystemId);
    }
}
