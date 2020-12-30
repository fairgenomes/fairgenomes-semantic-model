package org.fairgenomes.generator.datastructures;

import java.util.List;

public class Module {

    /*
    Variables mapped to the YAML file
    */
    public String name;
    public String description;
    public String ontology;
    public List<Element> elements;

    /*
    Variables that may be loaded afterwards
    */
    public String technicalName;
    public String codeSystem;
    public String code;
    public String iri;

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", elements=" + elements +
                '}';
    }
}