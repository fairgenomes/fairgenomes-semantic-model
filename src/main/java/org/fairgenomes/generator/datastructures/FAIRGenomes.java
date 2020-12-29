package org.fairgenomes.generator.datastructures;

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
    private LookupList lookupGlobalOptionsInstance;

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
                if(e.valueTypeEnum.equals(ValueType.Lookup))
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

    /*
    Parse and split ontology info to code, codesystem and iri
     */
    public void parseElementOntologies() throws Exception {
        for (Module m : modules) {
            for (Element e : m.elements) {
                int whiteSpaceIndex = e.ontology.indexOf(" ");
                if(whiteSpaceIndex == -1)
                {
                    throw new Exception("bad ontology info for " + e.toString() + ", no whitespace");
                }
                String codeAndCodeSystem = e.ontology.substring(0, whiteSpaceIndex);
                if(!codeAndCodeSystem.contains(":")){
                    throw new Exception("bad ontology info for " + e.toString()+ ", no colon");
                }

                String[] splitCodeAndCodeSystem = codeAndCodeSystem.split(":", -1);
                e.codeSystem = splitCodeAndCodeSystem[0];
                e.code = splitCodeAndCodeSystem[1];
                e.iri = e.ontology.substring(whiteSpaceIndex).replace("[", "").replace("]", "").trim();
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