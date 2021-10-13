package org.fairgenomes.generator.datastructures;

/**
 * See:
 * https://www.w3.org/TR/skos-reference/#mapping
 */
public enum Match {
    exactMatch, // Two concepts have equivalent meaning, and the link can be exploited across a wide range of applications and schemes. The link is meant to be transitive.
    closeMatch, // Two concepts are sufficiently similar that they can be used interchangeably in many applications and schemes; this link is not meant to be transitive.
    relatedMatch, // Associative mapping link between two concepts.
    broadMatch, // The concept represented by the external entity is broader than the item.
    narrowMatch // The concept represented by the external entity is narrower than the item.
}
