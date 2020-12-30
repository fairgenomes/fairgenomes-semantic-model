package org.fairgenomes.generator.datastructures;

public class Element {

    /*
    Variables mapped to the YAML file
    */
    public String name;
    public String description;
    public String ontology;
    public String values;

    /*
    Variables that may be loaded afterwards
     */
    public String technicalName;
    public ValueType valueTypeEnum;
    public LookupList lookup;
    public String crossRefTo;
    public String codeSystem;
    public String code;
    public String iri;

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ontology='" + ontology + '\'' +
                ", values='" + values + '\'' +
                '}';
    }

    public boolean isLookup()
    {
        switch(valueTypeEnum) {
            case LookupOne: return true;
            case LookupMultiple: return true;
            default: return false;
        }
    }

    public String valueTypeToMarkDown()
    {
        if(isLookup())
        {
            return lookup.srcFile.getName() + " (" + lookup.lookups.size() + " choices)";
        }else if(valueTypeEnum.equals(ValueType.CrossReference))
        {
            return crossRefTo;
        }
        else
        {
            return valueTypeEnum.toString();
        }
    }

    public String valueTypeToArtDecor()
    {
        switch(valueTypeEnum) {
            case String: return "ST";
            case LookupOne: return "ST";
            case LookupMultiple: return "ST";
            case PositiveInteger: return "INT";
            case CrossReference: return "ST";
            case Date: return "DATE";
            case DateTime: return "DATE";
            case Boolean: return "BOOLEAN";
            case Float: return "FLOAT";
            default: return "ST";
        }
    }

    public String getArtDecorInputType()
    {
        switch(valueTypeEnum) {
            case LookupOne: return "single-select";
            case LookupMultiple: return "multi-select";
            default: return "text";
        }
    }

}