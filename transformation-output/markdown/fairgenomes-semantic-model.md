# FAIR Genomes semantic metadata model
## Module: Personal
Data, facts or figures about an individual; the set of relevant items would depend on the use case. Ontology: [NCIT:C90492](http://purl.obolibrary.org/obo/NCIT_C90492).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Identifier | An alphanumeric identifier assigned to a specific patient. | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | String |
| Gender | Biological sex is the quality of a biological organism based on reproductive function or organs. | [SIO:010029](https://semanticscience.org/resource/SIO_010029.rdf) | Gender lookup (3 choices) |
| Genotypic sex | A biological sex quality inhering in an individual based upon genotypic composition of sex chromosomes. | [PATO:0020000](http://purl.obolibrary.org/obo/PATO_0020000) | GenotypicSex lookup (11 choices) |
| Country of residence | Country of Residence at Enrollment. | [NCIT:C171105](http://purl.obolibrary.org/obo/NCIT_C171105) | Countries lookup (249 choices) |
| Ethnicity | The biological quality of membership in a social group based on a common heritage. | [SIO:001014](http://semanticscience.org/resource/SIO_001014) | Countries lookup (249 choices) |
| Country of birth | The country that a given person was born in. | [GENEPIO:0001094](http://purl.obolibrary.org/obo/GENEPIO_0001094) | Countries lookup (249 choices) |
| Year of birth | The year in which a person was born. | [NCIT:C83164](http://purl.obolibrary.org/obo/NCIT_C83164) | PositiveInteger |
| Inclusion status | An indicator that provides information on the current health status of a patient. | [NCIT:C166244](http://purl.obolibrary.org/obo/NCIT_C166244) | InclusionStatus lookup (4 choices) |
| Age at death | The age at which death occurred. | [NCIT:C135383](http://purl.obolibrary.org/obo/NCIT_C135383) | PositiveInteger |
| Inclusion criterion | An inclusion criterion defines and states a condition which, if met, makes an entity suitable for a given task or participation in a given process. | [OBI:0500027](http://purl.obolibrary.org/obo/OBI_0500027) | Text |
| Primary affiliated institute | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | Institutes lookup (218 choices) |
| Data available in other institutes | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | Institutes lookup (218 choices) |
| Participates in study | The study or studies in which this person participates. | [RO:0000056](http://purl.obolibrary.org/obo/RO_0000056) | Reference to Study module |
## Module: Study
A detailed examination, analysis, or critical inspection of a subject designed to discover facts about it. Ontology: [NCIT:C63536](http://purl.obolibrary.org/obo/NCIT_C63536).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Identifier | A sample collection or study proper name that is centrally registered and unique within one framework. | [OMIABIS:0000006](http://purl.obolibrary.org/obo/OMIABIS_0000006) | String |
| Name | A proper name that designates a study. | [OMIABIS:0000037](http://purl.obolibrary.org/obo/OMIABIS_0000037) | String |
| Description | A textual entity describing a study aim or a sample collection. | [OMIABIS:0000036](http://purl.obolibrary.org/obo/OMIABIS_0000036) | Text |
| Principal investigator | The principle investigtor or responsible person for a study or a sample collection. | [OMIABIS:0000100](http://purl.obolibrary.org/obo/OMIABIS_0000100) | String |
| Contact information | An email address for the purpose of contacting a sample collection or study contact person. | [OMIABIS:0000035](http://purl.obolibrary.org/obo/OMIABIS_0000035) | String |
| Study design | A plan specification comprised of protocols (which may specify how and what kinds of data will be gathered) that are executed as part of an investigation and is realized during a study design execution. | [OBI:0500000](http://purl.obolibrary.org/obo/OBI_0500000) | Text |
## Module: Informed consent form
A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian. Ontology: [NCIT:C16468](http://purl.obolibrary.org/obo/NCIT_C16468).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Leaflet title | A name given to the resource. | [DC:title](http://purl.org/dc/terms/title) | String |
| Leaflet identifier | An unambiguous reference to the resource within a given context. | [DC:identifier](http://purl.org/dc/terms/identifier) | String |
| Leaflet date | A point or period of time associated with an event in the lifecycle of the resource. | [DC:date](http://purl.org/dc/terms/date) | Date |
| Leaflet version | A related resource that is a version, edition, or adaptation of the described resource. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form accepted date | Date of acceptance of the resource. | [DC:dateAccepted](http://purl.org/dc/terms/dateAccepted) | Date |
| Consent form creator | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | Institutes lookup (218 choices) |
| Consent form version | A related resource that is a version, edition, or adaptation of the described resource. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form restricted to | E.g. a dataset is restricted to an instance of an investigation for a specific disease or at geographical location. | [DUO:0000010](http://purl.obolibrary.org/obo/DUO_0000010) | DataUseRestrictions lookup (30 choices) |
## Module: Individual consent
Consent by a patient to a surgical or medical procedure or participation in a clinical study after achieving an understanding of the relevant medical facts and the risks involved. Ontology: [NCIT:C16735](http://purl.obolibrary.org/obo/NCIT_C16735).

| Element | Description | Ontology | Values |
|---|---|---|---|
| About subject | The person whom this individual consent applies to. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to Personal module |
| Consent form used | The informed consent form that was signed. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to Informed consent form module |
| Collected by | Indicates the person, group, or institution who performed the collection act. | [NCIT:C45262](http://purl.obolibrary.org/obo/NCIT_C45262) | String |
| Signing date | A date specification that designates when an informed consent form was signed. | [ICO:0000036](http://purl.obolibrary.org/obo/ICO_0000036) | Date |
| Valid from | Date (often a range) of validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Valid until | Date (often a range) of validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Represented by | An individual who is authorized under applicable State or local law to consent on behalf of a child or incapable person to general medical care including participation in clinical research. | [NCIT:C51828](http://purl.obolibrary.org/obo/NCIT_C51828) | String |
| Restricted to | E.g. a dataset is restricted to an instance of an investigation for a specific disease or at geographical location. | [DUO:0000010](http://purl.obolibrary.org/obo/DUO_0000010) | DataUseRestrictions lookup (30 choices) |
| Allow linkage | A study in which data from different sources are "linked". Usually used to compile epidemiological data. The logic of record linkage is that two or more items of information about a person recorded at different times, and perhaps in different places, may be of greater value when considered together than when either is considered alone. | [NCIT:C15424](http://purl.obolibrary.org/obo/NCIT_C15424) | Boolean |
| Allow reidentification | The procedure of having an identity established. | [NCIT:C25737](http://purl.obolibrary.org/obo/NCIT_C25737) | Boolean |
| Allow incidental findings | A planned process for a subject agrees not to be informed about any incidental finding. | [ICO:0000178](http://purl.obolibrary.org/obo/ICO_0000178) | Boolean |
| Recontact method | A document part that prescribes a method of contact in the future. | [ICO:0000009](http://purl.obolibrary.org/obo/ICO_0000009) | Text |
## Module: Clinical
Data obtained through patient examination or treatment. Ontology: [NCIT:C15783](http://purl.obolibrary.org/obo/NCIT_C15783).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Belongs to person | Link to the person for which the clinical information was collected. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to Personal module |
| Age at diagnosis | The age, measured from some defined time point e.g. birth at which a subject (e.g. a human patient) is diagnosed with some disease e.g. breast cancer. | [SNOMEDCT:423493009](http://purl.bioontology.org/ontology/SNOMEDCT/423493009) | PositiveInteger |
## Module: Material
A material entity that occupies space and possesses a rest mass. Ontology: [AFM:0000275](http://purl.allotrope.org/ontologies/material#AFM_0000275).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sampling time stamp | Date and time at which this sample was collected. | [EFO:0000689](http://www.ebi.ac.uk/efo/EFO_0000689) | DateTime |
## Module: Sample preparation
A sample preparation for assay that preparation of nucleic acids for a sequencing assay. Ontology: [OBI:0001902](http://purl.obolibrary.org/obo/OBI_0001902).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Input material | Amount of input material in ng. | [AFRL:0000010](http://purl.allotrope.org/ontologies/role#AFRL_0000010) | PositiveInteger |
| Library preparation kit | Pre-filled, ready-to-use reagent cartridges. Used to produce improved chemistry, cluster density and read length as well as improve quality (Q) scores. Reagent components are encoded to interact with the sequencing system to validate compatibility with user-defined applications. | [GENEPIO:0000081](http://purl.obolibrary.org/obo/GENEPIO_0000081) | NGSKits lookup (615 choices) |
| PCR-free | A method for amplifying a DNA base sequence using multiple rounds of heat denaturation of the DNA and annealing of oligonucleotide primers complementary to flanking regions in the presence of a heat-stable polymerase. This results in duplication of the targeted DNA region. Newly synthesized DNA strands can subsequently serve as additional templates for the same primer sequences, so that successive rounds of primer annealing, strand elongation, and dissociation produce rapid and highly specific amplification of the desired sequence. PCR also can be used to detect the existence of the defined sequence in a DNA sample. | [NCIT:C17003](http://purl.obolibrary.org/obo/NCIT_C17003) | Boolean |
| Target enrichment kit | Any of various techniques designed to select or increase a target item in a mixed sample. | [NCIT:C154307](http://purl.obolibrary.org/obo/NCIT_C154307) | NGSKits lookup (615 choices) |
| UMIs present | A unique molecular identifier (UMI) barcode is a short nucleotide sequence that is used to identify reads originating from an individual mRNA molecule. | [EFO:0010199](http://www.ebi.ac.uk/efo/EFO_0010199) | Boolean |
| Intended insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:IIS](https://fair-genomes.org/IIS) | PositiveInteger |
| Intended read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | PositiveInteger |
## Module: Sequencing
The determination of complete (typically nucleotide) sequences, including those of genomes (full genome sequencing, de novo sequencing and resequencing), amplicons and transcriptomes. Ontology: [EDAM:topic_3168](http://edamontology.org/topic_3168).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Observed read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | PositiveInteger |
## Module: Analysis
Apply analytical methods to existing data of a specific type. Ontology: [EDAM:operation_2945](http://edamontology.org/operation_2945).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Physical data location | A reference to a place on the Earth, by its name or by its geographical location. | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | Countries lookup (249 choices) |
