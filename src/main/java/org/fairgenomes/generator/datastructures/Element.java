package org.fairgenomes.generator.datastructures;

import java.util.List;
import java.util.Map;

public class Element {

    /*
    Variables mapped to the YAML file
    */
    public String name;
    public String description;
    public String ontology;
    public String values;
    public String unit;
    public String exactMatch;
    public String closeMatch;
    public String relatedMatch;
    public String broadMatch;
    public String narrowMatch;
    /*
    Variables that may be loaded afterwards
     */
    public Module fromModule;
    public String technicalName;
    public ValueType valueTypeEnum;
    public LookupList lookup;
    public String referenceTo;
    public Ontology parsedOntology;
    public Ontology unitOntology;
    public String type;
    public int nrOfLookupsWithoutGlobals;
    public Map<Match, List<Ontology>> matches;

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
     *
     * @return
     */
    public boolean isLookup() {
        switch (valueTypeEnum) {
            case LookupOne:
                return true;
            case LookupMany:
                return true;
            case LookupOne_NoGlobals:
                return true;
            case LookupMany_NoGlobals:
                return true;
            default:
                return false;
        }
    }

    /**
     * Helper function to determine whether this element has a reference value type
     *
     * @return
     */
    public boolean isReference() {
        switch (valueTypeEnum) {
            case ReferenceOne:
                return true;
            case ReferenceMany:
                return true;
            default:
                return false;
        }
    }

    /**
     * Helper function to convert the value type to Markdown
     *
     * @return
     */
    public String valueTypeToMarkDown() {
        if (isLookup()) {
            return "[" + lookup.srcFile.getName().replace(".txt", "") + "](../../lookups/" + lookup.srcFile.getName() + ") lookup (" + nrOfLookupsWithoutGlobals + " choices" + (type == null ? "" : " [of type](" + type + ")") + ")";
        } else if (isReference()) {
            return "Reference to instances of " + referenceTo;
        } else {
            return valueTypeEnum.toString();
        }
    }

    /**
     * Helper function to convert the value type to LaTeX
     *
     * @return
     */
    public String valueTypeToLaTeX() {
        if (isLookup()) {
            return lookup.srcFile.getName().replace(".txt", "") + " lookup (" + nrOfLookupsWithoutGlobals + " choices)";
        } else if (isReference()) {
            return "Reference to " + referenceTo;
        } else {
            return valueTypeEnum.toString();
        }
    }

    /**
     * Helper function to convert the value type to ART-DECOR
     *
     * @return
     */
    public String valueTypeToArtDecor() {
        switch (valueTypeEnum) {
            case String:
                return "ST";
            case Text:
                return "ST";
            case UniqueID:
                return "ST";
            case LookupOne:
                return "ST";
            case LookupMany:
                return "ST";
            case LookupOne_NoGlobals:
                return "ST";
            case LookupMany_NoGlobals:
                return "ST";
            case Integer:
                return "INT";
            case ReferenceOne:
                return "ST";
            case ReferenceMany:
                return "ST";
            case Date:
                return "DATE";
            case DateTime:
                return "DATE";
            case Boolean:
                return "BOOLEAN";
            case Decimal:
                return "DECIMAL";
            default:
                return "ST";
        }
    }

    /**
     * Helper function to convert the value type to EMX
     *
     * @return
     */
    public String valueTypeToEMX() {
        switch (valueTypeEnum) {
            case String:
                return "string";
            case Text:
                return "text";
            case UniqueID:
                return "string";
            case LookupOne:
                return "xref";
            case LookupMany:
                return "mref";
            case LookupOne_NoGlobals:
                return "xref";
            case LookupMany_NoGlobals:
                return "mref";
            case Integer:
                return "int";
            case ReferenceOne:
                return "xref";
            case ReferenceMany:
                return "mref";
            case Date:
                return "date";
            case DateTime:
                return "datetime";
            case Boolean:
                return "bool";
            case Decimal:
                return "decimal";
            default:
                return "string";
        }
    }

    /**
     * Helper function to convert the value type to EMX2
     *
     * @return
     */
    public String valueTypeToEMX2() {
        switch (valueTypeEnum) {
            case Text:
                return "text";
            case LookupOne:
            case LookupOne_NoGlobals:
                return "ontology";
            case LookupMany:
            case LookupMany_NoGlobals:
                return "ontology_array";
            case Integer:
                return "int";
            case ReferenceOne:
                return "ref";
            case ReferenceMany:
                return "ref_array";
            case Date:
                return "date";
            case DateTime:
                return "datetime";
            case Boolean:
                return "bool";
            case Decimal:
                return "decimal";
            default:  //String, UniqueID, others
                return "string";
        }
    }

    /**
     * Helper function to convert a lookup or reference to EMX
     *
     * @return
     */
    public String lookupOrReferencetoEMX(String pkgName) {
        if (isReference()) {
            return pkgName + "_" + YamlModel.toTechName(referenceTo);
        } else if (isLookup()) {
            return pkgName + "_" + fromModule.technicalName + "_" + technicalName;
        } else {
            return "";
        }
    }

    public String lookupOrReferencetoEMX2() {
        if (isReference()) {
            return YamlModel.toTechName(referenceTo);
        } else if (isLookup()) {
            return technicalName;
        } else {
            return "";
        }
    }

    public String getArtDecorInputType() {
        switch (valueTypeEnum) {
            case LookupOne:
                return "single-select";
            case LookupMany:
                return "multi-select";
            case LookupOne_NoGlobals:
                return "single-select";
            case LookupMany_NoGlobals:
                return "multi-select";
            default:
                return "text";
        }
    }

    /**
     * Helper function to convert the value type to RDF
     *
     * @return
     */
    public String valueTypeToRDF() {
        switch (valueTypeEnum) {
            case String:
                return "string";
            case Text:
                return "string";
            case UniqueID:
                return "string";
            case LookupOne:
                return "string";
            case LookupMany:
                return "list";
            case LookupOne_NoGlobals:
                return "string";
            case LookupMany_NoGlobals:
                return "list";
            case Integer:
                return "integer";
            case ReferenceOne:
                return "string";
            case ReferenceMany:
                return "list";
            case Date:
                return "date";
            case DateTime:
                return "datetime";
            case Boolean:
                return "boolean";
            case Decimal:
                return "decimal";
            default:
                return "string";
        }
    }

    /**
     * Helper function to convert the value type to Java
     * @return
     */
    public String valueTypeToJava(String obj)
    {
        switch(valueTypeEnum) {
            case String: return "String";
            case Text: return "String";
            case UniqueID: return "String";
            case LookupOne: return "String";
            case LookupMany: return "List<String>";
            case LookupOne_NoGlobals: return "String";
            case LookupMany_NoGlobals: return "List<String>";
            case Integer: return "int";
            case ReferenceOne: return obj;
            case ReferenceMany: return "List<"+obj+">";
            case Date: return "String";
            case DateTime: return "String";
            case Boolean: return "boolean";
            case Decimal: return "double";
            default: return "String";
        }
    }

}