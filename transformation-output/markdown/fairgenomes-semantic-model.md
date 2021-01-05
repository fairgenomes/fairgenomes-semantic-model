# FAIR Genomes semantic metadata model
## Module: Personal
Data, facts or figures about an individual; the set of relevant items would depend on the use case.

| Element | Ontology | Values |
|---|---|---|
| Identifier | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | String |
| Gender | [SIO:010029](https://semanticscience.org/resource/SIO_010029.rdf) | Gender.txt (3 choices) |
| Genotypic sex | [PATO:0020000](http://purl.obolibrary.org/obo/PATO_0020000) | GenotypicSex.txt (11 choices) |
| Country of residence | [NCIT:C171105](http://purl.obolibrary.org/obo/NCIT_C171105) | Countries.txt (249 choices) |
| Ethnicity | [SIO:001014](http://semanticscience.org/resource/SIO_001014) | Countries.txt (249 choices) |
| Country of birth | [GENEPIO:0001094](http://purl.obolibrary.org/obo/GENEPIO_0001094) | Countries.txt (249 choices) |
| Year of birth | [NCIT:C83164](http://purl.obolibrary.org/obo/NCIT_C83164) | PositiveInteger |
| Inclusion status | [NCIT:C166244](http://purl.obolibrary.org/obo/NCIT_C166244) | InclusionStatus.txt (4 choices) |
| Age at death | [NCIT:C135383](http://purl.obolibrary.org/obo/NCIT_C135383) | PositiveInteger |
| Inclusion criterion | [OBI:0500027](http://purl.obolibrary.org/obo/OBI_0500027) | Text |
| Primary affiliated institute | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | Institutes.txt (218 choices) |
| Data available in other institutes | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | Institutes.txt (218 choices) |
| Participates in study | [RO:0000056](http://purl.obolibrary.org/obo/RO_0000056) | null |
## Module: Study
A detailed examination, analysis, or critical inspection of a subject designed to discover facts about it.

| Element | Ontology | Values |
|---|---|---|
| Identifier | [OMIABIS:0000006](http://purl.obolibrary.org/obo/OMIABIS_0000006) | String |
| Name | [OMIABIS:0000037](http://purl.obolibrary.org/obo/OMIABIS_0000037) | String |
| Description | [OMIABIS:0000036](http://purl.obolibrary.org/obo/OMIABIS_0000036) | Text |
| Principal investigator | [OMIABIS:0000100](http://purl.obolibrary.org/obo/OMIABIS_0000100) | String |
| Contact information | [OMIABIS:0000035](http://purl.obolibrary.org/obo/OMIABIS_0000035) | String |
| Study design | [OBI:0500000](http://purl.obolibrary.org/obo/OBI_0500000) | Text |
## Module: Informed consent form
A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian.

| Element | Ontology | Values |
|---|---|---|
| Leaflet title | [DC:title](http://purl.org/dc/terms/title) | String |
| Leaflet identifier | [DC:identifier](http://purl.org/dc/terms/identifier) | String |
| Leaflet date | [DC:date](http://purl.org/dc/terms/date) | Date |
| Leaflet version | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form accepted date | [DC:dateAccepted](http://purl.org/dc/terms/dateAccepted) | Date |
| Consent form creator | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | Institutes.txt (218 choices) |
| Consent form version | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form restricted to | [DUO:0000010](http://purl.obolibrary.org/obo/DUO_0000010) | DataUseRestrictions.txt (30 choices) |
## Module: Individual consent
Consent by a patient to a surgical or medical procedure or participation in a clinical study after achieving an understanding of the relevant medical facts and the risks involved.

| Element | Ontology | Values |
|---|---|---|
| About subject | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | null |
| Consent form used | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | null |
| Collected by | [NCIT:C45262](http://purl.obolibrary.org/obo/NCIT_C45262) | String |
| Signing date | [ICO:0000036](http://purl.obolibrary.org/obo/ICO_0000036) | Date |
| Valid from | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Valid until | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Represented by | [NCIT:C51828](http://purl.obolibrary.org/obo/NCIT_C51828) | String |
| Restricted to | [DUO:0000010](http://purl.obolibrary.org/obo/DUO_0000010) | DataUseRestrictions.txt (30 choices) |
| Allow linkage | [NCIT:C15424](http://purl.obolibrary.org/obo/NCIT_C15424) | Boolean |
| Allow reidentification | [NCIT:C25737](http://purl.obolibrary.org/obo/NCIT_C25737) | Boolean |
| Allow incidental findings | [ICO:0000178](http://purl.obolibrary.org/obo/ICO_0000178) | Boolean |
| Recontact method | [ICO:0000009](http://purl.obolibrary.org/obo/ICO_0000009) | Text |
## Module: Clinical
Data obtained through patient examination or treatment.

| Element | Ontology | Values |
|---|---|---|
| Belongs to person | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | null |
| Age at diagnosis | [SNOMEDCT:423493009](http://purl.bioontology.org/ontology/SNOMEDCT/423493009) | PositiveInteger |
