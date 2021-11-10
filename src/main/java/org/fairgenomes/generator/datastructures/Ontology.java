package org.fairgenomes.generator.datastructures;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Ontology{" +
                "codeSystem='" + codeSystem + '\'' +
                ", code='" + code + '\'' +
                ", iri='" + iri + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ontology ontology = (Ontology) o;
        return Objects.equals(codeSystem, ontology.codeSystem) &&
                Objects.equals(code, ontology.code) &&
                Objects.equals(iri, ontology.iri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeSystem, code, iri);
    }
}
