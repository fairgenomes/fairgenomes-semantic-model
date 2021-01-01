package org.fairgenomes.transformer.datastructures;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class FAIRGenomes {

    /*
    Variables mapped to the YAML file
     */
    public String name;
    public String description;
    public Double version;
    public LocalDate date;
    public File lookupGlobalOptions;
    public List<Module> modules;

    /*
    Variables loaded afterwards
     */
    public LookupList lookupGlobalOptionsInstance;

    /**
     * Load the lookupGlobalOptions
     * @throws Exception
     */
    public void loadLookupGlobalOptions() throws Exception {
        LookupList ll = new LookupList(lookupGlobalOptions);
        lookupGlobalOptionsInstance = ll;
    }


    /**
     * Parse the 'values' information to ValueType enum values
     * @throws Exception
     */
    public void parseElementValueTypes() throws Exception {
        for(Module m: modules)
        {
            for(Element e : m.elements)
            {
                int whiteSpaceIndex = e.values.indexOf(" ");
                String vt = whiteSpaceIndex > 0 ? e.values.substring(0, whiteSpaceIndex) : e.values;
                ValueType valueType = ValueType.valueOf(vt);
                e.valueTypeEnum = valueType;
            }
        }
    }

    /**
     * Load the lookups for each element
     * @throws Exception
     */
    public void loadElementLookups() throws Exception {
        for(Module m: modules)
        {
            for(Element e : m.elements)
            {
                if(e.isLookup())
                {
                    int whiteSpaceIndex = e.values.indexOf(" ");
                    String vt = whiteSpaceIndex > 0 ? e.values.substring(whiteSpaceIndex) : e.values;
                    vt = vt.replace("[", "").replace("]", "").trim();
                    LookupList ll = new LookupList(new File(vt));
                    e.lookup = ll;
                  //  System.out.println(vt);
                }
            }
        }
    }

    /**
     * Parse and split ontology info to code, codesystem and iri
     * @throws Exception
     */
    public void parseOntologyReferences() throws Exception {
        for (Module m : modules) {
            int whiteSpaceIndex = m.ontology.indexOf(" ");
            String[] split = parseOntoInfo(whiteSpaceIndex, m.ontology);
            m.codeSystem = split[0];
            m.code = split[1];
            m.iri = m.ontology.substring(whiteSpaceIndex).replace("[", "").replace("]", "").trim();
            for (Element e : m.elements) {
                whiteSpaceIndex = e.ontology.indexOf(" ");
                split = parseOntoInfo(whiteSpaceIndex, e.ontology);
                e.codeSystem = split[0];
                e.code = split[1];
                e.iri = e.ontology.substring(whiteSpaceIndex).replace("[", "").replace("]", "").trim();
            }
        }
    }

    private String[] parseOntoInfo(int whiteSpaceIndex, String ontoInfo) throws Exception {
        if(whiteSpaceIndex == -1)
        {
            throw new Exception("bad ontology info: " + ontoInfo + ", no whitespace");
        }
        String codeAndCodeSystem = ontoInfo.substring(0, whiteSpaceIndex);
        if(!codeAndCodeSystem.contains(":")){
            throw new Exception("bad ontology info: " + ontoInfo + ", no colon");
        }
        return codeAndCodeSystem.split(":", -1);
    }

    /**
     * Create technical names
     * @return
     */
    public void createElementTechnicalNames() throws Exception {
        for (Module m : modules) {
            m.technicalName = m.name.replace(" ", "").toLowerCase();
            for (Element e : m.elements) {
                e.technicalName = e.name.replace(" ", "").toLowerCase();
            }
        }
    }

    @Override
    public String toString() {
        return "FAIRGenomes{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", version=" + version +
                ", date=" + date +
                ", lookupGlobalOptions=" + lookupGlobalOptions +
                ", modules=" + modules +
                ", lookupGlobalOptionsInstance=" + lookupGlobalOptionsInstance +
                '}';
    }
}