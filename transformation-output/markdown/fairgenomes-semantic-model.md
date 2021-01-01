# FAIR Genomes semantic metadata model
## Module: Personal
Data, facts or figures about an individual; the set of relevant items would depend on the use case.

| Element | Ontology | Values |
|---|---|---|
| Identifier | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | String |
| Gender | [SIO:010029](https://semanticscience.org/resource/SIO_010029.rdf) | Gender.txt (3 choices) |
| Genotypic sex | [PATO:0020000](http://purl.obolibrary.org/obo/PATO_0020000) | GenotypicSex.txt (11 choices) |
| Country of residence | [NCIT:C171105](http://purl.obolibrary.org/obo/NCIT_C171105) | Countries.txt (249 choices) |
## Module: Clinical
Data obtained through patient examination or treatment.

| Element | Ontology | Values |
|---|---|---|
| Belongs to person | [SIO:000764](http://semanticscience.org/resource/SIO_000764) | null |
| Age at diagnosis | [SNOMEDCT:423493009](http://purl.bioontology.org/ontology/SNOMEDCT/423493009) | PositiveInteger |
