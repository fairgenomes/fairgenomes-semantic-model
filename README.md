# FAIR Genomes semantic schema

![FAIR Genomes NGS FAIRification flow](misc/fg-ngs-fairification-flow.png)

## Explore

Discover the full schema at the [Markdown overview](transformation-output/markdown/fairgenomes-semantic-model.md). 

## Source

The source of the schema is located at [fair-genomes.yml](fair-genomes.yml). The reference lookup lists are available at [lookups](lookups).

## Technical outputs

Currently, the following transformation output formats are under development:
- MOLGENIS EMX database template at [molgenis-emx](transformation-output/molgenis-emx).
- ART-DECOR codebook at [art-decor](transformation-output/art-decor).
- OWL-XML ontology at [owl-xml](transformation-output/owl-xml/fair-genomes.owl).
- RDF-TTL triples at [rdf-ttl](transformation-output/rdf-ttl/fair-genomes.ttl).

## Dependencies
```
org.yaml:snakeyaml:1.27
com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.0
com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.0
```
