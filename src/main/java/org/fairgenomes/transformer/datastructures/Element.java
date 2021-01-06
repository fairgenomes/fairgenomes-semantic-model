package org.fairgenomes.transformer.datastructures;

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
    public String referenceTo;
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
            case LookupMany: return true;
            default: return false;
        }
    }

    public boolean isReference()
    {
        switch(valueTypeEnum) {
            case ReferenceOne: return true;
            case ReferenceMany: return true;
            default: return false;
        }
    }

    public String valueTypeToMarkDown()
    {
        if(isLookup())
        {
            return lookup.srcFile.getName().replace(".txt", "") + " lookup (" + lookup.lookups.size() + " choices)";
        }else if(isReference())
        {
            return "Reference to "+ referenceTo +" module";
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
            case LookupMany: return "ST";
            case Integer: return "INT";
            case ReferenceOne: return "ST";
            case ReferenceMany: return "ST";
            case Date: return "DATE";
            case DateTime: return "DATE";
            case Boolean: return "BOOLEAN";
            case Decimal: return "FLOAT";
            default: return "ST";
        }
    }

    public String getArtDecorInputType()
    {
        switch(valueTypeEnum) {
            case LookupOne: return "single-select";
            case LookupMany: return "multi-select";
            default: return "text";
        }
    }

}