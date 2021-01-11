package org.fairgenomes.transformer.datastructures;

import org.fairgenomes.transformer.implementations.molgenisemx.ToEMX;

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
    public Module m;
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

    /**
     * Helper function to determine whether this element has a lookup value type
     * @return
     */
    public boolean isLookup()
    {
        switch(valueTypeEnum) {
            case LookupOne: return true;
            case LookupMany: return true;
            default: return false;
        }
    }

    /**
     * Helper function to determine whether this element has a reference value type
     * @return
     */
    public boolean isReference()
    {
        switch(valueTypeEnum) {
            case ReferenceOne: return true;
            case ReferenceMany: return true;
            default: return false;
        }
    }

    /**
     * Helper function to convert the value type to Markdown
     * @return
     */
    public String valueTypeToMarkDown()
    {
        if(isLookup())
        {
            return "[" + lookup.srcFile.getName().replace(".txt", "") + "](../../lookups/"+lookup.srcFile.getName()+") lookup (" + lookup.lookups.size() + " choices)";
        }else if(isReference())
        {
            return "Reference to instances of "+ referenceTo;
        }
        else
        {
            return valueTypeEnum.toString();
        }
    }

    /**
     * Helper function to convert the value type to ART-DECOR
     * @return
     */
    public String valueTypeToArtDecor()
    {
        switch(valueTypeEnum) {
            case String: return "ST";
            case Text: return "ST";
            case UniqueID: return "ST";
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

    /**
     * Helper function to convert the value type to EMX
     * @return
     */
    public String valueTypeToEMX()
    {
        switch(valueTypeEnum) {
            case String: return "string";
            case Text: return "text";
            case UniqueID: return "string";
            case LookupOne: return "xref";
            case LookupMany: return "mref";
            case Integer: return "int";
            case ReferenceOne: return "xref";
            case ReferenceMany: return "mref";
            case Date: return "date";
            case DateTime: return "datetime";
            case Boolean: return "bool";
            case Decimal: return "decimal";
            default: return "string";
        }
    }

    /**
     * Helper function to convert a lookup or reference to EMX
     * @return
     */
    public String lookupOrReferencetoEMX()
    {
        if(isReference())
        {
            return ToEMX.PACKAGE_NAME + "_" + FAIRGenomes.toTechName(referenceTo);
        }
        else if(isLookup())
        {
            return ToEMX.PACKAGE_NAME + "_" + m.technicalName + "_" + technicalName;
        }
        else
        {
            return "";
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