package org.fairgenomes.generator.datastructures;

import javax.management.relation.Relation;
import java.util.List;
import java.util.Map;

public class Module {

    /*
    Variables mapped to the YAML file
    */
    public String name;
    public String subclassOf;
    public String description;
    public String ontology;
    public List<RelationWith> relationWith;
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
                ", subclassOf='" + subclassOf + '\'' +
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