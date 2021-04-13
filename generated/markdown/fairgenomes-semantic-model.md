# FAIR Genomes semantic metadata schema

The FAIR Genomes semantic metadata schema to power reuse of NGS data in research and healthcare. Version 0.3-SNAPSHOT, 2021-02-25. This model consists of __9 modules__ that contain __110 metadata elements__ in total.

## Module overview

| Name | Description | Ontology | Nr. of elements |
|---|---|---|---|
| [Study](#module-study) | A detailed examination, analysis, or critical inspection of a subject designed to discover facts about it. | [NCIT:C63536](http://purl.obolibrary.org/obo/NCIT_C63536) | 9 |
| [Personal](#module-personal) | Data, facts or figures about an individual; the set of relevant items would depend on the use case. | [NCIT:C90492](http://purl.obolibrary.org/obo/NCIT_C90492) | 12 |
| [Leaflet and consent form](#module-leaflet-and-consent-form) | A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian. | [NCIT:C16468](http://purl.obolibrary.org/obo/NCIT_C16468) | 8 |
| [Individual consent](#module-individual-consent) | Consent by a patient to a surgical or medical procedure or participation in a clinical study after achieving an understanding of the relevant medical facts and the risks involved. | [NCIT:C16735](http://purl.obolibrary.org/obo/NCIT_C16735) | 13 |
| [Clinical](#module-clinical) | Relating to the examination and treatment of patients dependent on direct observation. | [NCIT:C25398](http://purl.obolibrary.org/obo/NCIT_C25398) | 20 |
| [Material](#module-material) | Natural substances derived from living organisms such as cells, tissues, proteins, and DNA. | [NCIT:C43376](http://purl.obolibrary.org/obo/NCIT_C43376) | 16 |
| [Sample preparation](#module-sample-preparation) | A sample preparation for assay that preparation of nucleic acids for a sequencing assay. | [OBI:0001902](http://purl.obolibrary.org/obo/OBI_0001902) | 9 |
| [Sequencing](#module-sequencing) | The determination of complete (typically nucleotide) sequences, including those of genomes (full genome sequencing, de novo sequencing and resequencing), amplicons and transcriptomes. | [EDAM:topic_3168](http://edamontology.org/topic_3168) | 12 |
| [Analysis](#module-analysis) | Apply analytical methods to existing data of a specific type. | [EDAM:operation_2945](http://edamontology.org/operation_2945) | 11 |

## Module: Study
A detailed examination, analysis, or critical inspection of a subject designed to discover facts about it. Ontology: [NCIT:C63536](http://purl.obolibrary.org/obo/NCIT_C63536).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Identifier | A sample collection or study proper name that is centrally registered and unique within one framework. | [OMIABIS:0000006](http://purl.obolibrary.org/obo/OMIABIS_0000006) | UniqueID |
| Name | A proper name that designates a study. | [OMIABIS:0000037](http://purl.obolibrary.org/obo/OMIABIS_0000037) | String |
| Description | A textual entity describing a study aim or a sample collection. | [OMIABIS:0000036](http://purl.obolibrary.org/obo/OMIABIS_0000036) | Text |
| Inclusion criteria | An inclusion criterion defines and states a condition which, if met, makes an entity suitable for a given task or participation in a given process. | [OBI:0500027](http://purl.obolibrary.org/obo/OBI_0500027) | [InclusionCriteria](../../lookups/InclusionCriteria.txt) lookup (11 choices [of type](http://purl.obolibrary.org/obo/NCIT_C47953)) |
| Principal investigator | The principle investigtor or responsible person for a study or a sample collection. | [OMIABIS:0000100](http://purl.obolibrary.org/obo/OMIABIS_0000100) | String |
| Contact information | An email address for the purpose of contacting a sample collection or study contact person. | [OMIABIS:0000035](http://purl.obolibrary.org/obo/OMIABIS_0000035) | String |
| Study design | A plan specification comprised of protocols (which may specify how and what kinds of data will be gathered) that are executed as part of an investigation and is realized during a study design execution. | [OBI:0500000](http://purl.obolibrary.org/obo/OBI_0500000) | Text |
| Start date | The date on which a study began. | [NCIT:C69208](http://purl.obolibrary.org/obo/NCIT_C69208) | Date |
| Completion date | The date on which the concluding information for a clinical investigation is completed. Usually, this is when the last subject has a final visit or any other protocol-defined completion date. | [NCIT:C142702](http://purl.obolibrary.org/obo/NCIT_C142702) | Date |

## Module: Personal
Data, facts or figures about an individual; the set of relevant items would depend on the use case. Ontology: [NCIT:C90492](http://purl.obolibrary.org/obo/NCIT_C90492).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Personal identifier | An alphanumeric identifier assigned to a specific patient. | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | UniqueID |
| Phenotypic sex | An organismal quality inhering in a bearer by virtue of the bearer's physical expression of sexual characteristics. | [PATO:0001894](http://purl.obolibrary.org/obo/PATO_0001894) | [PhenotypicSex](../../lookups/PhenotypicSex.txt) lookup (4 choices [of type](http://purl.obolibrary.org/obo/PATO_0001894)) |
| Genotypic sex | A biological sex quality inhering in an individual based upon genotypic composition of sex chromosomes. | [PATO:0020000](http://purl.obolibrary.org/obo/PATO_0020000) | [GenotypicSex](../../lookups/GenotypicSex.txt) lookup (11 choices [of type](http://purl.obolibrary.org/obo/PATO_0020000)) |
| Country of residence | Country of Residence at Enrollment. | [NCIT:C171105](http://purl.obolibrary.org/obo/NCIT_C171105) | [Countries](../../lookups/Countries.txt) lookup (249 choices [of type](http://semanticscience.org/resource/SIO_000664)) |
| Ancestry | Population category defined using ancestry informative markers (AIMs) based on genetic/genomic data. | [HANCESTRO:0004](http://purl.obolibrary.org/obo/HANCESTRO_0004) | [Ancestry](../../lookups/Ancestry.txt) lookup (305 choices [of type](http://purl.obolibrary.org/obo/HANCESTRO_0004)) |
| Country of birth | The country that a given person was born in. | [GENEPIO:0001094](http://purl.obolibrary.org/obo/GENEPIO_0001094) | [Countries](../../lookups/Countries.txt) lookup (249 choices [of type](http://semanticscience.org/resource/SIO_000664)) |
| Year of birth | The year in which a person was born. | [NCIT:C83164](http://purl.obolibrary.org/obo/NCIT_C83164) | Integer |
| Inclusion status | An indicator that provides information on the current health status of a patient. | [NCIT:C166244](http://purl.obolibrary.org/obo/NCIT_C166244) | [InclusionStatus](../../lookups/InclusionStatus.txt) lookup (4 choices [of type](http://purl.obolibrary.org/obo/NCIT_C19332)) |
| Age at death | The age at which death occurred. | [NCIT:C135383](http://purl.obolibrary.org/obo/NCIT_C135383) | Integer |
| Primary affiliated institute | The most significant institute for medical consultation and/or study inclusion in context of the genetic disease of a person. | [SIO:000688](https://semanticscience.org/resource/SIO_000688) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Resources in other institutes | Material or data not captured by this system though known to be available in other institutes such as biobanks or hospitals. | [SIO:000688](https://semanticscience.org/resource/SIO_000688) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Participates in study | The study or studies in which this person participates. | [RO:0000056](http://purl.obolibrary.org/obo/RO_0000056) | Reference to instances of Study |

## Module: Leaflet and consent form
A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian. Ontology: [NCIT:C16468](http://purl.obolibrary.org/obo/NCIT_C16468).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Leaflet title | A name given to the resource. | [DC:title](http://purl.org/dc/terms/title) | String |
| Leaflet date | A point or period of time associated with an event in the lifecycle of the resource. | [DC:date](http://purl.org/dc/terms/date) | Date |
| Leaflet version | A related resource that is a version, edition, or adaptation of the described resource. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form identifier | An unambiguous reference to the resource within a given context. Using a DOI would be optimal. Using a resolvable URL is suboptimal but preferable over a string value. | [DC:identifier](http://purl.org/dc/terms/identifier) | UniqueID |
| Consent form accepted date | Date of acceptance of the resource. | [DC:dateAccepted](http://purl.org/dc/terms/dateAccepted) | Date |
| Consent form valid until | End date of the validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Consent form creator | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Consent form version | A related resource that is a version, edition, or adaptation of the described resource. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |

## Module: Individual consent
Consent by a patient to a surgical or medical procedure or participation in a clinical study after achieving an understanding of the relevant medical facts and the risks involved. Ontology: [NCIT:C16735](http://purl.obolibrary.org/obo/NCIT_C16735).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Individual consent identifier | A data item consisting of a unique identification code designating an informed consent. | [ICO:0000044](http://purl.obolibrary.org/obo/ICO_0000044) | UniqueID |
| Person consenting | The person (subject) whom this individual consent applies. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to instances of Personal |
| Consent form used | The informed consent form that was signed. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to instances of Leaflet and consent form |
| Collected by | Indicates the person, group, or institution who performed the collection act. | [NCIT:C45262](http://purl.obolibrary.org/obo/NCIT_C45262) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Signing date | A date specification that designates when an informed consent form was signed. | [ICO:0000036](http://purl.obolibrary.org/obo/ICO_0000036) | Date |
| Valid from | Starting date of the validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Valid until | End date of the validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Represented by | An individual who is authorized under applicable State or local law to consent on behalf of a child or incapable person to general medical care including participation in clinical research. | [NCIT:C51828](http://purl.obolibrary.org/obo/NCIT_C51828) | [RepresentedBy](../../lookups/RepresentedBy.txt) lookup (2 choices [of type](http://purl.obolibrary.org/obo/BFO_0000023)) |
| Data use permissions | A data item that is used to indicate consent permissions for datasets and/or materials, and relates to the purposes for which datasets and/or material might be removed, stored or used. | [DUO:0000001](http://purl.obolibrary.org/obo/DUO_0000001) | [DataUsePermissions](../../lookups/DataUsePermissions.txt) lookup (5 choices [of type](http://purl.obolibrary.org/obo/DUO_0000001)) |
| Data use modifiers | Data use modifiers indicate additional conditions for use. For instance, a dataset is restricted to an instance of an investigation for a specific disease or at geographical location. | [DUO:0000017](http://purl.obolibrary.org/obo/DUO_0000017) | [DataUseModifiers](../../lookups/DataUseModifiers.txt) lookup (18 choices [of type](http://purl.obolibrary.org/obo/DUO_0000017)) |
| Modifiers specification | Details on any applied restrictions, such as which countries in case of a geographic restriction or which diseases when restricted to disease-specific research. | [SIO:000090](http://semanticscience.org/resource/SIO_000090) | Text |
| Allow linkage | A study in which data from different sources are linked. Usually used to compile epidemiological data. The logic of record linkage is that two or more items of information about a person recorded at different times, and perhaps in different places, may be of greater value when considered together than when either is considered alone. | [NCIT:C15424](http://purl.obolibrary.org/obo/NCIT_C15424) | Boolean |
| Allow recontacting | The procedure of recontacting the patient for specified reasons. This means the patient agrees to be re-identifiable under those circumstances. | [NCIT:C25737](http://purl.obolibrary.org/obo/NCIT_C25737) | [Recontacting](../../lookups/Recontacting.txt) lookup (3 choices [of type](http://purl.obolibrary.org/obo/NCIT_C47953)) |

## Module: Clinical
Relating to the examination and treatment of patients dependent on direct observation. Ontology: [NCIT:C25398](http://purl.obolibrary.org/obo/NCIT_C25398).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Clinical identifier | A character or string used to name, or characterize a clinical events reference. | [NCIT:C87853](http://purl.obolibrary.org/obo/NCIT_C87853) | UniqueID |
| Belongs to person | Link to the person whom the clinical information is about. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to instances of Personal |
| Phenotype | The outward appearance of the individual. In medical context, these are often the symptoms caused by a disease. | [NCIT:C16977](http://purl.obolibrary.org/obo/NCIT_C16977) | [Phenotypes](../../lookups/Phenotypes.txt) lookup (15802 choices [of type](http://purl.obolibrary.org/obo/NCIT_C16977)) |
| Unobserved phenotype | Phenotypes or symptoms that were definitely not observed, which may help in differential diagnosis or establish incomplete penetrance. | [HL7:C0442737](http://purl.bioontology.org/ontology/HL7/C0442737) | [Phenotypes](../../lookups/Phenotypes.txt) lookup (15802 choices [of type](http://purl.obolibrary.org/obo/NCIT_C16977)) |
| Phenotypic data available | Types of phenotypic data collected in a clinical setting that is potentially available upon request. | [NCIT:C15783](http://purl.obolibrary.org/obo/NCIT_C15783) | [DCMITypes](../../lookups/DCMITypes.txt) lookup (6 choices [of type](http://purl.org/dc/terms/DCMIType)) |
| Clinical diagnosis | A diagnosis made from a study of the signs and symptoms of a disease. | [NCIT:C15607](http://purl.obolibrary.org/obo/NCIT_C15607) | [Diseases](../../lookups/Diseases.txt) lookup (9700 choices [of type](http://semanticscience.org/resource/SIO_010299)) |
| Molecular diagnosis gene | Gene affected by pathogenic variation that is causal for disease of the patient. | [NCIT:C20826](http://purl.obolibrary.org/obo/NCIT_C20826) | [Genes](../../lookups/Genes.txt) lookup (19202 choices [of type](http://purl.obolibrary.org/obo/NCIT_C16612)) |
| Molecular diagnosis other | Causal variant in HGVS notation with optional classification or free text explaining any other molecular mechanisms involved. | [NCIT:C20826](http://purl.obolibrary.org/obo/NCIT_C20826) | Text |
| Age at diagnosis | The age, measured from some defined time point e.g. birth at which a subject (e.g. a human patient) is diagnosed with some disease e.g. breast cancer. | [SNOMEDCT:423493009](http://purl.bioontology.org/ontology/SNOMEDCT/423493009) | Integer |
| Age at last screening | Age of the patient at the moment of the most recent screening. | [NCIT:C81258](http://purl.obolibrary.org/obo/NCIT_C81258) | Integer |
| Medication | Unique identifier of a drug conforming to the Anatomical Therapeutic Chemical (ATC) Classification System, a drug classification system controlled by the WHO Collaborating Centre for Drug Statistics Methodology (WHOCC). | [NCIT:C459](http://purl.obolibrary.org/obo/NCIT_C459) | [Drugs](../../lookups/Drugs.txt) lookup (5632 choices [of type](http://edamontology.org/data_3103)) |
| Drug regimen | The specific way a therapeutic drug is to be taken, including formulation, route of administration, dose, dosing interval, and treatment duration. | [NCIT:C142516](http://purl.obolibrary.org/obo/NCIT_C142516) | Text |
| Family members affected | This term applies to a family member who is diagnosed with the same condition as the individual who is the primary focus of investigation (the proband). | [HP:0032320](http://purl.obolibrary.org/obo/HP_0032320) | [FamilyMembers](../../lookups/FamilyMembers.txt) lookup (41 choices [of type](http://purl.obolibrary.org/obo/NCIT_C41256)) |
| Family members sequenced | A person related by descent rather than by marriage or law that was also sequenced. | [NCIT:C71384](http://purl.obolibrary.org/obo/NCIT_C71384) | [FamilyMembers](../../lookups/FamilyMembers.txt) lookup (41 choices [of type](http://purl.obolibrary.org/obo/NCIT_C41256)) |
| Consanguinity | Information on whether the patient is a child from two family members who are second cousins or closer. | [GSSO:007578](http://purl.obolibrary.org/obo/GSSO_007578) | String |
| Medical history | A record of a person's background regarding health, occurrence of disease events and surgical procedures. | [NCIT:C18772](http://purl.obolibrary.org/obo/NCIT_C18772) | [MedicalHistory](../../lookups/MedicalHistory.txt) lookup (1154 choices [of type](http://scdontology.h3abionet.org/ontology/SCDO_1000093)) |
| Age of onset | Age of onset of clinical manifestations related to a clinical entity. | [Orphanet:C023](http://www.orpha.net/ORDO/Orphanet_C023) | Integer |
| First contact | First contact with specialised center in context of disease or inclusion. | [LOINC:MTHU048806](http://purl.bioontology.org/ontology/LNC/MTHU048806) | Date |
| Functioning | Patient's classification of functioning i.e. disability profile according to International Classification of Functioning and Disability (ICF). | [NCIT:C21007](http://purl.obolibrary.org/obo/NCIT_C21007) | Text |
| Material used in diagnosis | This diagnosis c.q. clinical examination is based on one or more sampled materials. [WORK IN PROGRESS. This field should be proper ReferenceMany to Material, but this is technically problematic due to circular dependencies.] | [SIO:000641](http://semanticscience.org/resource/SIO_000641) | String |

## Module: Material
Natural substances derived from living organisms such as cells, tissues, proteins, and DNA. Ontology: [NCIT:C43376](http://purl.obolibrary.org/obo/NCIT_C43376).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Material identifier | The unique identification of a material in a specific context. | [NCIT:C93400](http://purl.obolibrary.org/obo/NCIT_C93400) | UniqueID |
| Collected from person | Link to the person from whom the material was collected. | [SIO:000244](http://semanticscience.org/resource/SIO_000244) | Reference to instances of Personal |
| Belongs to diagnosis | This material is part of a diagnosis c.q. clinical examination. There can be multiple diagnoses when a non-tumor material is reused as reference. | [SIO:000068](http://semanticscience.org/resource/SIO_000068) | Reference to instances of Clinical |
| Sampling timestamp | Date and time at which this sample was collected. | [EFO:0000689](http://www.ebi.ac.uk/efo/EFO_0000689) | DateTime |
| Registration timestamp | The act of listing or recording officially; officially qualified or enrolled. | [NCIT:C25646](http://purl.obolibrary.org/obo/NCIT_C25646) | DateTime |
| Sampling protocol | Describes the procedure whereby biological material for an experiment is sampled. | [EFO:0005518](http://www.ebi.ac.uk/efo/EFO_0005518) | Text |
| Sampling protocol deviation | A variation from processes or procedures defined in the sampling protocol. Deviations usually do not preclude the overall evaluability of subject data for either efficacy or safety, and are often acknowledged and accepted in advance by the sponsor. | [NCIT:C50996](http://purl.obolibrary.org/obo/NCIT_C50996) | String |
| Reason for sampling protocol deviation | The rationale for why a deviation from the sampling protocol has occurred. | [NCIT:C93529](http://purl.obolibrary.org/obo/NCIT_C93529) | String |
| Biospecimen type | The type of a material sample taken from a biological entity for testing, diagnostic, propagation, treatment or research purposes. This includes particular types of cellular molecules, cells, tissues, organs, body fluids, embryos, and body excretory substances. | [NCIT:C70713](http://purl.obolibrary.org/obo/NCIT_C70713) | [MaterialTypes](../../lookups/MaterialTypes.txt) lookup (13 choices [of type](http://purl.obolibrary.org/obo/NCIT_C70713)) |
| Anatomical source | Biological entity that constitutes the structural organization of an individual member of a biological species. | [UBERON:0001062](http://purl.obolibrary.org/obo/UBERON_0001062) | [AnatomicalSources](../../lookups/AnatomicalSources.txt) lookup (13827 choices [of type](http://purl.obolibrary.org/obo/UBERON_0001062)) |
| Pathological state | A coded value specifying the pathological state of the tissue from which the biospecimen is derived. | [NCIT:C25687](http://purl.obolibrary.org/obo/NCIT_C25687) | [PathologicalState](../../lookups/PathologicalState.txt) lookup (4 choices [of type](http://purl.obolibrary.org/obo/NCIT_C164617)) |
| Storage conditions | The conditions specified for the storage of a biological material. | [NCIT:C96145](http://purl.obolibrary.org/obo/NCIT_C96145) | [StorageConditions](../../lookups/StorageConditions.txt) lookup (26 choices [of type](http://purl.obolibrary.org/obo/NCIT_C96145)) |
| Expiration date | The date beyond which a substance is no longer regarded as fit for use. | [NCIT:C164516](http://purl.obolibrary.org/obo/NCIT_C164516) | Date |
| Percentage tumor cells | The determination of the ratio of tumor cells compared to total cells present in a sample. The measurement may be expressed as a ratio or percentage. | [NCIT:C127771](http://purl.obolibrary.org/obo/NCIT_C127771) | Decimal |
| Physical location | A reference to a place on the Earth, by its name or by its geographical location. This definition is intentionally vague to allow reuse locally (e.g. which freezer), for contacting (e.g. which institute), broadly for logistical or legal reasons (e.g. city, country or continent). | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | String |
| Derived from | A material produced from or related to another. [WORK IN PROGRESS. This field should be proper ReferenceOne to Material, but this is technically problematic due to circular dependencies.] | [NCIT:C28355](http://purl.obolibrary.org/obo/NCIT_C28355) | String |

## Module: Sample preparation
A sample preparation for assay that preparation of nucleic acids for a sequencing assay. Ontology: [OBI:0001902](http://purl.obolibrary.org/obo/OBI_0001902).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sampleprep identifier | Any of one or more unique codes that refers to a specific protocol. | [NCIT:C132299](http://purl.obolibrary.org/obo/NCIT_C132299) | UniqueID |
| Belongs to material | Link to the source material from which this sample was prepared. | [NCIT:C25683](http://purl.obolibrary.org/obo/NCIT_C25683) | Reference to instances of Material |
| Input amount | Amount of input material in ng. | [AFRL:0000010](http://purl.allotrope.org/ontologies/role#AFRL_0000010) | Integer |
| Library preparation kit | Pre-filled, ready-to-use reagent cartridges. Used to produce improved chemistry, cluster density and read length as well as improve quality (Q) scores. Reagent components are encoded to interact with the sequencing system to validate compatibility with user-defined applications. | [GENEPIO:0000085](http://purl.obolibrary.org/obo/GENEPIO_0000085) | [NGSKits](../../lookups/NGSKits.txt) lookup (615 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0000085)) |
| PCR free | A method for amplifying a DNA base sequence using multiple rounds of heat denaturation of the DNA and annealing of oligonucleotide primers complementary to flanking regions in the presence of a heat-stable polymerase. This results in duplication of the targeted DNA region. Newly synthesized DNA strands can subsequently serve as additional templates for the same primer sequences, so that successive rounds of primer annealing, strand elongation, and dissociation produce rapid and highly specific amplification of the desired sequence. PCR also can be used to detect the existence of the defined sequence in a DNA sample. | [NCIT:C17003](http://purl.obolibrary.org/obo/NCIT_C17003) | Boolean |
| Target enrichment kit | Any of various techniques designed to select or increase a target item in a mixed sample. | [GENEPIO:0000081](http://purl.obolibrary.org/obo/GENEPIO_0000081) | [NGSKits](../../lookups/NGSKits.txt) lookup (615 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0000081)) |
| UMIs present | A unique molecular identifier (UMI) barcode is a short nucleotide sequence that is used to identify reads originating from an individual mRNA molecule. | [EFO:0010199](http://www.ebi.ac.uk/efo/EFO_0010199) | Boolean |
| Intended insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:0000001](https://w3id.org/fair-genomes/resource/FG_0000001) | Integer |
| Intended read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | Integer |

## Module: Sequencing
The determination of complete (typically nucleotide) sequences, including those of genomes (full genome sequencing, de novo sequencing and resequencing), amplicons and transcriptomes. Ontology: [EDAM:topic_3168](http://edamontology.org/topic_3168).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sequencing identifier | A unique identifier assigned to raw data from a performed nucleic acid sequencing assay. | [NCIT:C171337](http://purl.obolibrary.org/obo/NCIT_C171337) | UniqueID |
| Belongs to sample | Link to the prepared sample i.e. source that was sequenced. | [NCIT:C25683](http://purl.obolibrary.org/obo/NCIT_C25683) | Reference to instances of Sample preparation |
| Sequencing date | Date the sequencing run was performed. | [GENEPIO:0000069](http://purl.obolibrary.org/obo/GENEPIO_0000069) | Date |
| Sequencing platform | A sequencing plaform (brand) is a name of a company that produces sequencer equipment. | [GENEPIO:0000071](http://purl.obolibrary.org/obo/GENEPIO_0000071) | [SequencingPlatform](../../lookups/SequencingPlatform.txt) lookup (7 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0000071)) |
| Sequencing instrument model | A product name and model number of a manufacturer's genomic (dna) sequencer. | [GENEPIO:0001921](http://purl.obolibrary.org/obo/GENEPIO_0001921) | [SequencingInstrumentModels](../../lookups/SequencingInstrumentModels.txt) lookup (39 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0001921)) |
| Sequencing type | Sequencing distinguishable as an identifiable class based on common qualities. | [NCIT:C25284](http://purl.obolibrary.org/obo/NCIT_C25284) | [SequencingTypes](../../lookups/SequencingTypes.txt) lookup (35 choices [of type](http://purl.obolibrary.org/obo/NCIT_C17565)) |
| Average read depth | The average number of times a particular locus (site, nucleotide, amplicon, region) was sequenced. | [NCIT:C155320](http://purl.obolibrary.org/obo/NCIT_C155320) | Integer |
| Observed read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | Integer |
| Observed insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:0000002](https://w3id.org/fair-genomes/resource/FG_0000002) | Integer |
| Percentage Q30 | Percentage of reads with a Phred quality score over 30, which indicates less than a 1/1000 chance that the base was called incorrectly. | [GENEPIO:0000089](http://purl.obolibrary.org/obo/GENEPIO_0000089) | Decimal |
| Percentage TR20 | Percentage of the target sequence on which 20 or more unique reads were successfully mapped. | [FG:0000003](https://w3id.org/fair-genomes/resource/FG_0000003) | Decimal |
| Other quality metrics | Other NGS quality control metrics, including but not limited to (i) sequencer metrics such as yield, error rate, density (K/mm2), cluster PF (%) and phas/prephas (%), (ii) alignment metrics such as QM insert size, GC content, QM duplicated reads (%), QM error rate, uniformity/evenness of coverage and maternal cell contamination, and (iii) variant call metrics such as number of SNVs/CNVs/SVs called, number of missense/nonsense variants, common variants (%), unique variants (%), gender match and trio inheritance check. | [EDAM:data_3914](http://edamontology.org/data_3914) | Text |

## Module: Analysis
Apply analytical methods to existing data of a specific type. Ontology: [EDAM:operation_2945](http://edamontology.org/operation_2945).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Analysis identifier | An assay identifier is an identifier that identifies some assay (analysis). | [AFR:0001979](http://purl.allotrope.org/ontologies/result#AFR_0001979) | UniqueID |
| Belongs to sequencing | Link to the sequencing performed i.e. source on which the analysis is based. | [NCIT:C25683](http://purl.obolibrary.org/obo/NCIT_C25683) | Reference to instances of Sequencing |
| Physical data location | A reference to a place on the Earth, by its name or by its geographical location. This definition is intentionally vague to allow reuse locally (e.g. which computer), for contacting (e.g. which institute), broadly for logistical or legal reasons (e.g. city, country or continent). | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | String |
| Abstract data location | To maintain data by placing the data, or a copy of the data, onto an electronically accessible device for preservation (either in plain-text or encrypted format). | [NCIT:C142494](http://purl.obolibrary.org/obo/NCIT_C142494) | String |
| Data formats stored | A defined way or layout of representing and structuring data in a computer file, blob, string, message, or elsewhere. | [EDAM:format_1915](http://edamontology.org/format_1915) | [DataFormats](../../lookups/DataFormats.txt) lookup (582 choices [of type](http://edamontology.org/format_1915)) |
| Algorithms used | A defined procedure for solving a problem. Applied to a problem-solving procedure implemented in software to be executed by a computer. | [NCIT:C16275](http://purl.obolibrary.org/obo/NCIT_C16275) | Text |
| Reference genome used | An identifier of a build of a particular genome. | [EDAM:data_2340](http://edamontology.org/data_2340) | [GenomeAccessions](../../lookups/GenomeAccessions.txt) lookup (29 choices [of type](http://edamontology.org/data_2787)) |
| Bioinformatic protocol used | A human-readable collection of information about about how a scientific experiment or analysis was carried out that results in a specific set of data or results used for further analysis or to test a specific hypothesis. | [EDAM:data_2531](http://edamontology.org/data_2531) | Text |
| Bioinformatic protocol deviation | A variation from processes or procedures defined in the bioinformatic protocol. Deviations usually do not preclude the overall evaluability of subject data for either efficacy or safety, and are often acknowledged and accepted in advance by the sponsor. | [NCIT:C50996](http://purl.obolibrary.org/obo/NCIT_C50996) | String |
| Reason for bioinformatic protocol deviation | The rationale for why a deviation from the bioinformatic protocol has occurred. | [NCIT:C93529](http://purl.obolibrary.org/obo/NCIT_C93529) | String |
| WGS guideline followed | Any followed systematic statement of policy rules or principles. Guidelines may be developed by government agencies at any level, institutions, professional societies, governing boards, or by convening expert panels. | [NCIT:C17564](http://purl.obolibrary.org/obo/NCIT_C17564) | String |

## Null flavors
Each lookup in FAIR Genomes is supplemented with so-called 'null flavors' from HL7. These can be used to indicate precisely why a particular value could not be entered into the system, providing substantially more insight than simply leaving a field empty.

| Value | Description | Ontology |
|---|---|---|
| NoInformation | The value is exceptional (missing, omitted, incomplete, improper). No information as to the reason for being an exceptional value is provided. This is the most general exceptional value. It is also the default exceptional value. | [HL7:NI](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#NI) |
| Invalid | The value as represented in the instance is not a member of the set of permitted data values in the constrained value domain of a variable. | [HL7:INV](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#INV) |
| Derived | An actual value may exist, but it must be derived from the provided information (usually an EXPR generic data type extension will be used to convey the derivation expression . | [HL7:DER](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#DER) |
| Other | The actual value is not a member of the set of permitted data values in the constrained value domain of a variable.The actual value is not a member of the set of permitted data values in the constrained value domain of a variable. (e.g., concept not provided by required code system). | [HL7:OTH](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#OTH) |
| Negative infinity | Negative infinity of numbers. | [HL7:NINF](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#NINF) |
| Positive infinity | Positive infinity of numbers. | [HL7:PINF](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#PINF) |
| Un-encoded | The actual value has not yet been encoded within the approved value domain. | [HL7:UNC](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#UNC) |
| Masked | There is information on this item available but it has not been provided by the sender due to security, privacy or other reasons. There may be an alternate mechanism for gaining access to this information. | [HL7:MSK](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#MSK) |
| Not applicable | Known to have no proper value (e.g., last menstrual period for a male). | [HL7:NA](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#NA) |
| Unknown | A proper value is applicable, but not known. | [HL7:UNK](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#UNK) |
| Asked but unknown | Information was sought but not found (e.g., patient was asked but didn't know) | [HL7:ASKU](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#ASKU) |
| Temporarily unavailable | Information is not available at this time but it is expected that it will be available later. | [HL7:NAV](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#NAV) |
| Not asked | This information has not been sought. (e.g., patient was not asked) | [HL7:NASK](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#NASK) |
| Not available | Information is not available at this time (with no expectation regarding whether it will or will not be available in the future). | [HL7:NAVU](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#NAVU) |
| Sufficient quantity | The specific quantity is not known, but is known to be non-zero and is not specified because it makes up the bulk of the material. e.g. 'Add 10mg of ingredient X, 50mg of ingredient Y, and sufficient quantity of water to 100mL.' The null flavor would be used to express the quantity of water. | [HL7:QS](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#QS) |
| Trace | The content is greater than zero, but too small to be quantified. | [HL7:TRC](http://terminology.hl7.org/CodeSystem/v3-NullFlavor#TRC) |

