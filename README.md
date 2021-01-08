# FAIR Genomes semantic model

![FAIR Genomes NGS FAIRification flow](misc/fg-ngs-fairification-flow.png)

## Model

The FAIR Genomes semantic metadata model is located at [fair-genomes.yml](fair-genomes.yml). The reference lookup lists are available at [lookups](lookups).

## Outputs

Currently, the following transformation output formats are under development:
- Markdown model overview at [markdown](transformation-output/markdown/fairgenomes-semantic-model.md).
- MOLGENIS EMX database template at [molgenis-emx](transformation-output/molgenis-emx).
- ART-DECOR codebook at [art-decor](transformation-output/art-decor).
- RDF-OWL ontology at [rdf-owl](transformation-output/rdf-owl/fair-genomes.owl).

## Dependencies
```
org.yaml:snakeyaml:1.27
com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.0
com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.0
```
