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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * IdentifierManager keeps track of all the necessary identifiers required to create the Art-Decor XML file
 */
public class IdentifierManager {
    private static final Pattern idPattern = Pattern.compile(".*id=\"(.*?)\".*", Pattern.DOTALL);
    private static final Pattern alreadyACodeSystemPattern = Pattern.compile("(\\d+\\.)+\\d+");
    private static final SimpleDateFormat idDateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");

    private static IdentifierManager identifierManager;

    // tracks the available codesystems
    private Map<String, CodeSystem> codeSystemMap = new LinkedHashMap<>();

    private String conceptListId;

    private String dataSetId;
    private int dataSetIdNext=0;

    private String conceptId;
    private int conceptIdNext=0;

    private String valueSetId;
    private int valueSetIdNext=0;

    private String codesystemId;
    private int codeSystemIdNext=0;

    private List<String> idList;

    /**
     * creates the identifierManager based on the runparameters
     * @param runParameters the runpamareters
     */
    private IdentifierManager(RunParameters runParameters){
        setupIdList(runParameters.getProjectId(), runParameters.getProjectPrefix());

        dataSetId = findId("dataset")+".";
        conceptId = findId("dataelement")+".";
        valueSetId = findId("valueset")+".";
        codesystemId = findId("codesystem")+".";

        codeSystemMap.put("SNOMED CT", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.96"));
        codeSystemMap.put("NullFlavor", new CodeSystem("1900-01-01T00:00:00","2.16.840.1.113883.5.1008"));
        codeSystemMap.put("LOINC", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.1"));
        codeSystemMap.put("HPO", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.339"));
        codeSystemMap.put("ORPHA", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.2.4.3.46.10.4.1"));
        codeSystemMap.put("RADLEX", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.256"));

        // NL versions of:
        codeSystemMap.put("ATC", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.73"));
        codeSystemMap.put("ICD-10", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.3.2"));
        codeSystemMap.put("ICF", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.6.254"));
        codeSystemMap.put("ICPC", new CodeSystem("1900-01-01T00:00:00", "2.16.840.1.113883.2.4.4.31.1"));

    }

    /**
     * creates the identifierManager based on the runparameters
     * @param runParameters the runparameters
     */
    public static void createIdentifierManager(RunParameters runParameters){
        identifierManager = new IdentifierManager(runParameters);
    }

    /**
     * returns the identifierManager
     * @return the identifierManager
     */
    public static IdentifierManager getIdentifierManager(){
        return identifierManager;
    }

    /**
     * base the conceptlistId on a different date
     * @param date the date
     */
    public void setConceptListId(Date date){
        conceptListId = "."+idDateFormatter.format(date)+".";
    }

    /**
     * creates list with all the base identifiers necessary
     * @param projectId     the project identifier
     * @param projectPrefix the project prefix
     */
    private void setupIdList(String projectId, String projectPrefix){
        idList = Arrays.asList("<baseId id=\""+projectId+".1\" type=\"DS\" prefix=\""+projectPrefix+"dataset-\"/>\n",
                "<baseId id=\""+projectId+".2\" type=\"DE\" prefix=\""+projectPrefix+"dataelement-\"/>\n",
                "<baseId id=\""+projectId+".3\" type=\"SC\" prefix=\""+projectPrefix+"scenario-\"/>\n",
                "<baseId id=\""+projectId+".4\" type=\"TR\" prefix=\""+projectPrefix+"transaction-\"/>\n",
                "<baseId id=\""+projectId+".5\" type=\"CS\" prefix=\""+projectPrefix+"codesystem-\"/>\n",
                "<baseId id=\""+projectId+".6\" type=\"IS\" prefix=\""+projectPrefix+"issue-\"/>\n",
                "<baseId id=\""+projectId+".7\" type=\"AC\" prefix=\""+projectPrefix+"actor-\"/>\n",
                "<baseId id=\""+projectId+".8\" type=\"CL\" prefix=\""+projectPrefix+"conceptlist-\"/>\n",
                "<baseId id=\""+projectId+".9\" type=\"EL\" prefix=\""+projectPrefix+"template-element-\"/>\n",
                "<baseId id=\""+projectId+".10\" type=\"TM\" prefix=\""+projectPrefix+"template-\"/>\n",
                "<baseId id=\""+projectId+".11\" type=\"VS\" prefix=\""+projectPrefix+"valueset-\"/>\n",
                "<baseId id=\""+projectId+".12\" type=\"RL\" prefix=\""+projectPrefix+"rule-intern-\"/>\n",
                "<baseId id=\""+projectId+".18\" type=\"SX\" prefix=\""+projectPrefix+"test-scenario-\"/>\n",
                "<baseId id=\""+projectId+".19\" type=\"EX\" prefix=\""+projectPrefix+"example-instance-\"/>\n",
                "<baseId id=\""+projectId+".20\" type=\"QX\" prefix=\""+projectPrefix+"qualification-test-instance-\"/>\n",
                "<baseId id=\""+projectId+".21\" type=\"TX\" prefix=\""+projectPrefix+"test-transaction-\"/>\n",
                "<baseId id=\""+projectId+".22\" type=\"CM\" prefix=\""+projectPrefix+"community-\"/>\n",
                "<baseId id=\""+projectId+".24\" type=\"MP\" prefix=\""+projectPrefix+"concept-map-\"/>\n", //?
                "<defaultBaseId id=\""+projectId+".1\" type=\"DS\"/>\n",
                "<defaultBaseId id=\""+projectId+".2\" type=\"DE\"/>\n",
                "<defaultBaseId id=\""+projectId+".3\" type=\"SC\"/>\n",
                "<defaultBaseId id=\""+projectId+".4\" type=\"TR\"/>\n",
                "<defaultBaseId id=\""+projectId+".5\" type=\"CS\"/>\n",
                "<defaultBaseId id=\""+projectId+".6\" type=\"IS\"/>\n",
                "<defaultBaseId id=\""+projectId+".7\" type=\"AC\"/>\n",
                "<defaultBaseId id=\""+projectId+".8\" type=\"CL\"/>\n",
                "<defaultBaseId id=\""+projectId+".9\" type=\"EL\"/>\n",
                "<defaultBaseId id=\""+projectId+".10\" type=\"TM\"/>\n",
                "<defaultBaseId id=\""+projectId+".11\" type=\"VS\"/>\n",
                "<defaultBaseId id=\""+projectId+".12\" type=\"RL\"/>\n",
                "<defaultBaseId id=\""+projectId+".18\" type=\"SX\"/>\n",
                "<defaultBaseId id=\""+projectId+".19\" type=\"EX\"/>\n",
                "<defaultBaseId id=\""+projectId+".20\" type=\"QX\"/>\n",
                "<defaultBaseId id=\""+projectId+".21\" type=\"TX\"/>\n",
                "<defaultBaseId id=\""+projectId+".22\" type=\"CM\"/>\n",
                "<defaultBaseId id=\""+projectId+".24\" type=\"MP\"/>\n"
        );
    }

    /**
     * returns a string representation of the project identifiers
     * @return a string representation of the project identifiers
     */
    public String getProjectIdsXML(){
        return idList.stream().collect(Collectors.joining());
    }

    /**
     * find the id of something defined in the idList, e.g. the id of the codesystem
     * @param suffix the thing we're looking for
     * @return the id
     */
    private String findId(String suffix){
        for(String line:idList){
            if (line.contains(suffix)){
                Matcher matcher = idPattern.matcher(line);
                if(matcher.matches()){
                    return matcher.group(1);
                }
            }
        }
        return "";
    }

    /**
     * transforms the codesystem map into xml
     * @return xml representation of the codesystem map
     */
    public String getCodeSystemXML(){
        StringBuilder stringBuilder = new StringBuilder();

        for(Map.Entry<String, CodeSystem> entrySet:codeSystemMap.entrySet()){
            String name = entrySet.getKey();
            CodeSystem codeSystem = entrySet.getValue();

//            stringBuilder.append("<codeSystem ref=\"").append(codeSystem.codeSystemId).append("\" name=\"").append(name.replaceAll(" ", "_")).append("\" displayName=\"").append(name).append("\" effectiveDate=\"").append(codeSystem.effectiveDate).append("\"/>\n");
            stringBuilder.append("<codeSystem ref=\"").append(codeSystem.codeSystemId).append("\" name=\"").append(name.replaceAll(" ", "_")).append("\" displayName=\"").append(name).append("\"/>\n");
        }

        return stringBuilder.toString();
    }

    /**
     * generate an identifier for a codesystem, e.g. codesystem-cce18 --> 1.2.3.4.5.6.7.8
     * @param codeSystem name of the codesystem
     * @return an id for the codesystem
     */
    public String getCodeSystemId(String codeSystem, String effectiveDate){
        // check whether the codeSystem is already in the 1.2.3.4.5.6 something format
        // if so it probably already is a codesystem id, so just return that.
        Matcher matcher = alreadyACodeSystemPattern.matcher(codeSystem);
        if(matcher.matches()){
            return codeSystem;
        }

        // otherwise check whether the codeySystem already exists in the table
        // if not, generate an id for it
        if(!codeSystemMap.containsKey(codeSystem)) {
            codeSystemMap.put(codeSystem, new CodeSystem(effectiveDate, codesystemId+codeSystemIdNext++));
        }
        return codeSystemMap.get(codeSystem).codeSystemId;
    }

    /**
     * generate a valueset id
     * @return a valueset id
     */
    public String getNextValueSetId(){
        return valueSetId+valueSetIdNext++;
    }

    /**
     * generate a concept id
     * @return a concept id
     */
    public String getNextConceptId(){
        return conceptId+conceptIdNext++;
    }

    /**
     * generate a conceptlist id, based on the concept id
     * @param conceptId the id for the concept
     * @return a conceptlist id
     */
    public String getNextConceptListId(String conceptId){
//        return conceptId+conceptListId+conceptListIdNext++;
        return conceptId+conceptListId+"0";
    }

    /**
     * generate a dataset id
     * @return a dataset id
     */
    public String getNextDataSetId(){
        return dataSetId+dataSetIdNext++;
    }

    class CodeSystem{
        private String effectiveDate;
        private String codeSystemId;

        CodeSystem(String effectiveDate, String codeSystemId){
            this.codeSystemId = codeSystemId;
            this.effectiveDate = effectiveDate;
        }
    }
}
