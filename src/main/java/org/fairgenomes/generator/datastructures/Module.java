package org.fairgenomes.generator.datastructures;

import java.util.List;
import java.util.Map;

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
    public Ontology parsedOntology;
    public Map<String, Element> elementMap;

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", elements=" + elements +
                '}';
    }

    /**
     * Helper function to convert name into a Markdown anchor
     * @return
     */
    public String toMarkdownAnchor()
    {
        return "#module-" + name.replace(" ", "-").toLowerCase();
    }
}