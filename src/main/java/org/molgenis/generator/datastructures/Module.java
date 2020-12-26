package org.molgenis.generator.datastructures;

import java.util.List;

public class Module {
    public String name;
    public String description;
    public List<Element> elements;

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", elements=" + elements +
                '}';
    }
}