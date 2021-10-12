package org.fairgenomes.generator.datastructures;

/**
 * e.g. parse
 *
 * OMIABIS:0000037 [http://purl.obolibrary.org/obo/OMIABIS_0000037]
 *
 * into
 *
 * codeSystem -> OMIABIS
 * code -> 0000037
 * iri -> http://purl.obolibrary.org/obo/OMIABIS_0000037
 */
public class Ontology {
    public String codeSystem;
    public String code;
    public String iri;
}
