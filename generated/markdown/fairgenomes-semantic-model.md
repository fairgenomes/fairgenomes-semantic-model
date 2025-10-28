# FAIR Genomes metadata schema

The FAIR Genomes semantic metadata schema to power reuse of NGS data in research and healthcare. Version 1.3-SNAPSHOT, 2025-10-21. This model consists of __16 modules__ that contain __152 metadata elements__ and __290452 lookups__ in total (excluding null flavors).

## Module overview

| Name | Description | Ontology | Nr. of elements |
|---|---|---|---|
| [Study](#module-study) | A detailed examination, analysis, or critical inspection of one or multiple subjects designed to discover facts. | [NCIT:C63536](http://purl.obolibrary.org/obo/NCIT_C63536) | 9 |
| [Personal](#module-personal) | Data, facts or figures about an individual; the set of relevant items would depend on the use case. | [NCIT:C90492](http://purl.obolibrary.org/obo/NCIT_C90492) | 14 |
| [Leaflet and consent form](#module-leaflet-and-consent-form) | A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian. | [NCIT:C16468](http://purl.obolibrary.org/obo/NCIT_C16468) | 9 |
| [Individual consent](#module-individual-consent) | Consent given by a patient to a surgical or medical procedure or participation in a study, examination or analysis after achieving an understanding of the relevant medical facts and the risks involved. | [NCIT:C16735](http://purl.obolibrary.org/obo/NCIT_C16735) | 12 |
| [Clinical](#module-clinical) | Findings and circumstances relating to the examination and treatment of a patient. | [NCIT:C25398](http://purl.obolibrary.org/obo/NCIT_C25398) | 19 |
| [Material](#module-material) | A natural substance derived from living organisms such as cells, tissues, proteins, and DNA. | [NCIT:C43376](http://purl.obolibrary.org/obo/NCIT_C43376) | 17 |
| [Fixed Block](#module-fixed-block) | Refers to preserved specimens embedded in support material. These are typically cut into thin slices for microscopic examination, with or without an enhancement such as staining. | [NCIT:C25436](http://purl.obolibrary.org/obo/NCIT_C25436) | 1 |
| [Sample preparation](#module-sample-preparation) | A sample preparation for a nucleic acids sequencing assay. | [OBI:0001902](http://purl.obolibrary.org/obo/OBI_0001902) | 9 |
| [Sequencing](#module-sequencing) | The determination of complete (typically nucleotide) sequences, including those of genomes (full genome sequencing, de novo sequencing and resequencing), amplicons and transcriptomes. | [EDAM:topic_3168](http://edamontology.org/topic_3168) | 12 |
| [Analysis](#module-analysis) | An analysis applies analytical (often computational) methods to existing data of a specific type to produce some desired output. | [EDAM:operation_2945](http://edamontology.org/operation_2945) | 11 |
| [HMD Submission](#module-hmd-submission) | A class specific for the 1+MG GDI project containing items for metadata submission. | [FG:0000750](https://w3id.org/fair-genomes/resource/FG_0000750) | 8 |
| [Treatment](#module-treatment) | An action or administration of therapeutic agents to produce an effect that is intended to alter or stop a pathologic process. | [NCIT:C49236](http://purl.obolibrary.org/obo/NCIT_C49236) | 3 |
| [Biomarker](#module-biomarker) | TODO | [NCIT:C16342](http://purl.obolibrary.org/obo/NCIT_C16342) | 2 |
| [Imaging Study](#module-imaging-study) | The Study IE defines the characteristics of a medical Study performed on a Patient. A Study is a collection of one or more Series of medical images, presentation states, and/or SR documents that are logically related for the purpose of diagnosing a Patient. Each Study is associated with exactly one Patient. A Study may include Composite Instances that are created by a single modality, multiple modalities or by multiple devices of the same modality. The Study IE is modality independent. | [DICOM:113014](http://dicom.nema.org/resources/ontology/DCM/113014) | 10 |
| [Imaging Series](#module-imaging-series) | A distinct logical set used to group composite instances. All instances within a Series are of the same modality, in the same Frame of Reference (if any), and created by the same equipment. | [DICOM:113015](http://dicom.nema.org/resources/ontology/DCM/113015) | 11 |
| [Image Instance](#module-image-instance) | Any record of an imaging event whether physical or electronic. | [NCIT:C48179](http://purl.obolibrary.org/obo/NCIT_C48179) | 5 |

## Module: Study
A detailed examination, analysis, or critical inspection of one or multiple subjects designed to discover facts. Ontology: [NCIT:C63536](http://purl.obolibrary.org/obo/NCIT_C63536).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Identifier | A unique proper name or character sequence that identifies this particular study. | [OMIABIS:0000006](http://purl.obolibrary.org/obo/OMIABIS_0000006) | UniqueID |
| Name | A name that designates this study. | [OMIABIS:0000037](http://purl.obolibrary.org/obo/OMIABIS_0000037) | String |
| Description | A statement or piece of writing that provides details on this study. | [OMIABIS:0000036](http://purl.obolibrary.org/obo/OMIABIS_0000036) | Text |
| Inclusion criteria | The conditions which, if met, make an person eligible for participation in this study. | [OBI:0500027](http://purl.obolibrary.org/obo/OBI_0500027) | [InclusionCriteria](../../lookups/InclusionCriteria.txt) lookup (14 choices [of type](http://purl.obolibrary.org/obo/OBI_0500027)) |
| Principal investigator | The principal investigator or responsible person for this study. | [OMIABIS:0000100](http://purl.obolibrary.org/obo/OMIABIS_0000100) | String |
| Contact information | An email address for the purpose of contacting the study contact person. | [OMIABIS:0000035](http://purl.obolibrary.org/obo/OMIABIS_0000035) | String |
| Study design | A plan specification comprised of protocols (which may specify how and what kinds of data will be gathered) that are executed as part of this study. | [OBI:0500000](http://purl.obolibrary.org/obo/OBI_0500000) | Text |
| Start date | The date on which this study began. | [NCIT:C69208](http://purl.obolibrary.org/obo/NCIT_C69208) | Date |
| Completion date | The date on which the concluding information for this study is completed. Usually, this is when the last subject has a final visit, or the main analysis has finished, or any other protocol-defined completion date. | [NCIT:C142702](http://purl.obolibrary.org/obo/NCIT_C142702) | Date |

## Module: Personal
Data, facts or figures about an individual; the set of relevant items would depend on the use case. Ontology: [NCIT:C90492](http://purl.obolibrary.org/obo/NCIT_C90492).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Personal identifier | A unique proper name or character sequence that identifies this particular person. | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | UniqueID |
| Gender identity | A person's concept of self as being male and masculine or female and feminine, or ambivalent, based in part on physical characteristics, parental responses, and psychological and social pressures. It is the internal experience of gender role. For practical reasons the lookups are limited to first and second-level entries, but can be expanded when needed. Note that 'Gender at birth', 'Genotypic sex' and any (gender-related) hormone therapies in 'Medication' are usually medically more relevant than this term. | [MESH:D005783](http://purl.bioontology.org/ontology/MESH/D005783) | [GenderIdentity](../../lookups/GenderIdentity.txt) lookup (15 choices [of type](http://purl.bioontology.org/ontology/MESH/D005783)) |
| Gender at birth | Assigned gender is one's gender which was assigned at birth, typically by a medical and/or legal organization, and then later registered with other organizations. Such a designation is typically based off of the superficial appearance of external genitalia present at birth. | [GSSO:009418](http://purl.obolibrary.org/obo/GSSO_009418) | [GenderAtBirth](../../lookups/GenderAtBirth.txt) lookup (13 choices [of type](http://purl.obolibrary.org/obo/GSSO_009418)) |
| Genotypic sex | A biological sex quality inhering in an individual based upon genotypic composition of sex chromosomes. | [PATO:0020000](http://purl.obolibrary.org/obo/PATO_0020000) | [GenotypicSex](../../lookups/GenotypicSex.txt) lookup (12 choices [of type](http://purl.obolibrary.org/obo/NCIT_C168871)) |
| Country of residence | Country of residence at enrollment. | [NCIT:C171105](http://purl.obolibrary.org/obo/NCIT_C171105) | [Countries](../../lookups/Countries.txt) lookup (249 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0001830)) |
| Ancestry | Population category defined using ancestry informative markers (AIMs) based on genetic/genomic data. | [NCIT:C176763](http://purl.obolibrary.org/obo/NCIT_C176763) | [Ancestry](../../lookups/Ancestry.txt) lookup (305 choices [of type](http://purl.obolibrary.org/obo/HANCESTRO_0004)) |
| Country of birth | The country that this person was born in. | [GENEPIO:0001094](http://purl.obolibrary.org/obo/GENEPIO_0001094) | [Countries](../../lookups/Countries.txt) lookup (249 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0001830)) |
| Year of birth | The year in which this person was born. | [NCIT:C83164](http://purl.obolibrary.org/obo/NCIT_C83164) | Integer |
| Inclusion status | An indicator that provides information on the current health status of this person. | [NCIT:C166244](http://purl.obolibrary.org/obo/NCIT_C166244) | [InclusionStatus](../../lookups/InclusionStatus.txt) lookup (4 choices [of type](http://purl.obolibrary.org/obo/NCIT_C19332)) |
| Age at death | The age at which death occurred. | [NCIT:C135383](http://purl.obolibrary.org/obo/NCIT_C135383) | Integer |
| Consanguinity | Information on whether the patient is a child from two family members who are second cousins or closer. | [OMIT:0004546](http://purl.obolibrary.org/obo/OMIT_0004546) | Boolean |
| Primary affiliated institute | The most significant institute for medical consultation and/or study inclusion in context of the genetic disease of this person. | [NCIT:C25412](http://purl.obolibrary.org/obo/NCIT_C25412) | [Institutes](../../lookups/Institutes.txt) lookup (219 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Resources in other institutes | Material or data related to this person that is not captured by this system though known to be available in other institutes such as biobanks or hospitals. | [NCIT:C19012](http://purl.obolibrary.org/obo/NCIT_C19012) | [Institutes](../../lookups/Institutes.txt) lookup (219 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Participates in study | Reference to the study or studies in which this person participates. | [RO:0000056](http://purl.obolibrary.org/obo/RO_0000056) | Reference to instances of Study |

## Module: Leaflet and consent form
A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian. Ontology: [NCIT:C16468](http://purl.obolibrary.org/obo/NCIT_C16468).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Leaflet title | A title or name given to the leaflet that belongs to this consent form. | [DC:title](http://purl.org/dc/terms/title) | String |
| Leaflet date | A point or period of time associated with the publication of this leaflet that belongs to this consent form. | [DC:date](http://purl.org/dc/terms/date) | Date |
| Leaflet version | The version, edition, or adaptation of this leaflet that belongs to this consent form. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form identifier | A unique proper name or character sequence that identifies this particular leaflet and consent form combination used in signing individual consent. Using a DOI would be optimal. Using any resolvable URL is suboptimal but still preferable over using a plain text value. | [DC:identifier](http://purl.org/dc/terms/identifier) | UniqueID |
| Consent form title | A title or name given to this consent form. | [DC:title](http://purl.org/dc/terms/title) | String |
| Consent form accepted date | Date of acceptance of this consent form. | [DC:dateAccepted](http://purl.org/dc/terms/dateAccepted) | Date |
| Consent form valid until | End date of the validity of this consent form. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Consent form creator | Indicates the authoritative body who brought this consent form into existence. | [DC:creator](http://purl.org/dc/terms/creator) | [Institutes](../../lookups/Institutes.txt) lookup (219 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Consent form version | The version, edition, or adaptation of this consent form. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |

## Module: Individual consent
Consent given by a patient to a surgical or medical procedure or participation in a study, examination or analysis after achieving an understanding of the relevant medical facts and the risks involved. Ontology: [NCIT:C16735](http://purl.obolibrary.org/obo/NCIT_C16735).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Individual consent identifier | A unique proper name or character sequence that identifies this particular signed individual consent. | [ICO:0000044](http://purl.obolibrary.org/obo/ICO_0000044) | UniqueID |
| Person consenting | Reference to the person (i.e. subject) to whom this individual consent applies. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to instances of Personal |
| Consent form used | Reference to the informed consent form that was signed. Points to a particular instance of leaflet and consent form that usually exists as a record (i.e. a row) within the same database as this individual consent. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to instances of Leaflet and consent form |
| Collected by | Indicates the institute who performed the collection act. | [NCIT:C45262](http://purl.obolibrary.org/obo/NCIT_C45262) | [Institutes](../../lookups/Institutes.txt) lookup (219 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Signing date | A date specification that designates when this individual consent form was signed. | [ICO:0000036](http://purl.obolibrary.org/obo/ICO_0000036) | Date |
| Valid from | Starting date of the validity of this individual consent. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Valid until | End date of the validity of this individual consent. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Represented by | An individual who is authorized under applicable State or local law to consent on behalf of a child or incapable person to general medical care including participation in clinical research. | [NCIT:C142600](http://purl.obolibrary.org/obo/NCIT_C142600) | [RepresentedBy](../../lookups/RepresentedBy.txt) lookup (3 choices [of type](http://purl.obolibrary.org/obo/BFO_0000023)) |
| Data use permissions | A data item that is used to indicate consent permissions for datasets and/or materials, and relates to the purposes for which datasets and/or material might be used. | [DUO:0000001](http://purl.obolibrary.org/obo/DUO_0000001) | [DataUsePermissions](../../lookups/DataUsePermissions.txt) lookup (5 choices [of type](http://purl.obolibrary.org/obo/DUO_0000001)) |
| Data use modifiers | Data use modifiers indicate additional conditions for use. For instance, a dataset is restricted to investigations into specific diseases or performed at specific geographical locations. | [DUO:0000017](http://purl.obolibrary.org/obo/DUO_0000017) | [DataUseModifiers](../../lookups/DataUseModifiers.txt) lookup (23 choices [of type](http://purl.obolibrary.org/obo/DUO_0000017)) |
| Data use specification | Further specification of applied data use permissions and modifiers. For example, a list of countries in case of geographic restrictions or a list of diseases when restricted to disease-specific research. | [SIO:000090](http://semanticscience.org/resource/SIO_000090) | Text |
| Allow recontacting | The procedure of recontacting the patient for specified reasons. This means the patient agrees to be re-identifiable under those circumstances. | [NCIT:C25737](http://purl.obolibrary.org/obo/NCIT_C25737) | [Recontacting](../../lookups/Recontacting.txt) lookup (3 choices [of type](http://purl.obolibrary.org/obo/NCIT_C176244)) |

## Module: Clinical
Findings and circumstances relating to the examination and treatment of a patient. Ontology: [NCIT:C25398](http://purl.obolibrary.org/obo/NCIT_C25398).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Clinical identifier | A unique proper name or character sequence that identifies this particular clinical examination. | [NCIT:C87853](http://purl.obolibrary.org/obo/NCIT_C87853) | UniqueID |
| Belongs to person | Reference to the person whom this clinical information is about. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to instances of Personal |
| Phenotype | The outward appearance of the individual. In medical context, these are often the symptoms caused by a disease. | [NCIT:C16977](http://purl.obolibrary.org/obo/NCIT_C16977) | [Phenotypes](../../lookups/Phenotypes.txt) lookup (15802 choices [of type](https://www.wikidata.org/wiki/Property:P3841)) |
| Unobserved phenotype | Phenotypes or symptoms that were looked for but not observed, which may help in differential diagnosis or establish incomplete penetrance. | [HL7:C0442737](http://purl.bioontology.org/ontology/HL7/C0442737) | [Phenotypes](../../lookups/Phenotypes.txt) lookup (15802 choices [of type](https://www.wikidata.org/wiki/Property:P3841)) |
| Phenotypic data available | Types of phenotypic data collected in a clinical setting that is potentially available upon request. | [NCIT:C15783](http://purl.obolibrary.org/obo/NCIT_C15783) | [DCMITypes](../../lookups/DCMITypes.txt) lookup (6 choices [of type](http://purl.org/dc/terms/DCMIType)) |
| Clinical diagnosis | A diagnosis made from a study of the signs and symptoms of a disease. | [NCIT:C15607](http://purl.obolibrary.org/obo/NCIT_C15607) | [Diseases](../../lookups/Diseases.txt) lookup (9700 choices [of type](http://edamontology.org/data_3667)) |
| Molecular diagnosis gene | Gene affected by pathogenic variation that is causal for disease of the patient. | [NCIT:C20826](http://purl.obolibrary.org/obo/NCIT_C20826) | [Genes](../../lookups/Genes.txt) lookup (19202 choices [of type](http://purl.obolibrary.org/obo/NCIT_C16612)) |
| Molecular diagnosis other | Causal variant in HGVS notation with optional classification or free text explaining any other molecular mechanisms involved. | [NCIT:C20826](http://purl.obolibrary.org/obo/NCIT_C20826) | Text |
| Age at diagnosis | The age, measured from some defined time point (e.g. birth) at which a patient is diagnosed with a disease. | [SNOMEDCT:423493009](http://purl.bioontology.org/ontology/SNOMEDCT/423493009) | Integer |
| Age at last screening | Age of the patient at the moment of the most recent screening. | [NCIT:C81258](http://purl.obolibrary.org/obo/NCIT_C81258) | Integer |
| Medication | A drug product that contains one or more active and/or inactive ingredients used by the patient intended to treat, prevent or alleviate the symptoms of disease. Any hormone therapies, gender-related or otherwise, should also be recorded here. | [NCIT:C459](http://purl.obolibrary.org/obo/NCIT_C459) | [Drugs](../../lookups/Drugs.txt) lookup (5632 choices [of type](http://edamontology.org/data_3103)) |
| Drug regimen | The specific way a therapeutic drug is to be taken, including formulation, route of administration, dose, dosing interval, and treatment duration. | [NCIT:C142516](http://purl.obolibrary.org/obo/NCIT_C142516) | Text |
| Family members affected | Family members related by descent rather than by marriage or law who were diagnosed with the same condition as the individual who is the primary focus of investigation (i.e. the proband). | [HP:0032320](http://purl.obolibrary.org/obo/HP_0032320) | [FamilyMembers](../../lookups/FamilyMembers.txt) lookup (41 choices [of type](http://purl.obolibrary.org/obo/NCIT_C71384)) |
| Family members sequenced | Family members related by descent rather than by marriage or law who were also tested by next-generation sequencing. | [NCIT:C79916](http://purl.obolibrary.org/obo/NCIT_C79916) | [FamilyMembers](../../lookups/FamilyMembers.txt) lookup (41 choices [of type](http://purl.obolibrary.org/obo/NCIT_C71384)) |
| Medical history | A record of a person's background regarding health, occurrence of disease events and surgical procedures. | [NCIT:C18772](http://purl.obolibrary.org/obo/NCIT_C18772) | [MedicalHistory](../../lookups/MedicalHistory.txt) lookup (1154 choices [of type](http://scdontology.h3abionet.org/ontology/SCDO_1000093)) |
| Age of onset | Age of onset of clinical manifestations related to the disease of the patient. | [Orphanet:C023](http://www.orpha.net/ORDO/Orphanet_C023) | Integer |
| First contact | First contact of the patient with a specialised center in context of disease or study inclusion. | [LOINC:MTHU048806](http://purl.bioontology.org/ontology/LNC/MTHU048806) | Date |
| Functioning | Patient's classification of functioning i.e. disability profile according to International Classification of Functioning and Disability (ICF). | [NCIT:C21007](http://purl.obolibrary.org/obo/NCIT_C21007) | Text |
| Material used in diagnosis | This diagnosis c.q. clinical examination is based on one or more sampled materials. | [SIO:000641](http://semanticscience.org/resource/SIO_000641) | String |

## Module: Material
A natural substance derived from living organisms such as cells, tissues, proteins, and DNA. Ontology: [NCIT:C43376](http://purl.obolibrary.org/obo/NCIT_C43376).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Material identifier | A unique proper name or character sequence that identifies this particular material. | [NCIT:C93400](http://purl.obolibrary.org/obo/NCIT_C93400) | UniqueID |
| Collected from person | Reference to the person from whom this material was collected. | [SIO:000244](http://semanticscience.org/resource/SIO_000244) | Reference to instances of Personal |
| Belongs to diagnosis | Reference to a diagnosis c.q. clinical examination of which this material may be a part of. There can be multiple diagnoses when a non-tumor material is reused as reference. | [SIO:000068](http://semanticscience.org/resource/SIO_000068) | Reference to instances of Clinical |
| Sampling timestamp | Date and time at which this material was collected. | [EFO:0000689](http://www.ebi.ac.uk/efo/EFO_0000689) | DateTime |
| Registration timestamp | Date and time at which this material was listed or recorded officially, i.e. officially qualified or enrolled. | [NCIT:C25646](http://purl.obolibrary.org/obo/NCIT_C25646) | DateTime |
| Sampling protocol | The procedure whereby this material was sampled for an analysis. | [EFO:0005518](http://www.ebi.ac.uk/efo/EFO_0005518) | Text |
| Sampling protocol deviation | A variation from processes or procedures defined in the sampling protocol. Deviations usually do not preclude the overall evaluability of subject data for either efficacy or safety, and are often acknowledged and accepted in advance by the sponsor. | [NCIT:C50996](http://purl.obolibrary.org/obo/NCIT_C50996) | String |
| Reason for sampling protocol deviation | The rationale for why a deviation from the sampling protocol has occurred. | [NCIT:C93529](http://purl.obolibrary.org/obo/NCIT_C93529) | String |
| Biospecimen type | The type of material taken from a biological entity for testing, diagnostic, propagation, treatment or research purposes. | [NCIT:C70713](http://purl.obolibrary.org/obo/NCIT_C70713) | [BiospecimenTypes](../../lookups/BiospecimenTypes.txt) lookup (403 choices [of type](http://purl.obolibrary.org/obo/NCIT_C70699)) |
| Anatomical source | Biological entity that constitutes the structural organization of an individual member of a biological species from which this material was taken. | [NCIT:C103264](http://purl.obolibrary.org/obo/NCIT_C103264) | [AnatomicalSources](../../lookups/AnatomicalSources.txt) lookup (13827 choices [of type](http://purl.obolibrary.org/obo/UBERON_0001062)) |
| Pathological state | The pathological state of the tissue from which this material was derived. | [NCIT:C28257](http://purl.obolibrary.org/obo/NCIT_C28257) | [PathologicalState](../../lookups/PathologicalState.txt) lookup (4 choices [of type](http://purl.obolibrary.org/obo/NCIT_C164617)) |
| Storage conditions | The conditions under which this biological material was stored. | [NCIT:C96145](http://purl.obolibrary.org/obo/NCIT_C96145) | [StorageConditions](../../lookups/StorageConditions.txt) lookup (26 choices [of type](http://purl.obolibrary.org/obo/NCIT_C96145)) |
| Expiration date | The date beyond which this material is no longer regarded as fit for use. | [NCIT:C164516](http://purl.obolibrary.org/obo/NCIT_C164516) | Date |
| Percentage tumor cells | The percentage of tumor cells compared to total cells present in this material. | [NCIT:C127771](http://purl.obolibrary.org/obo/NCIT_C127771) | Decimal |
| Physical location | A place on the Earth where this material is located, by its name or by its geographical location. This definition is intentionally vague to allow reuse locally (e.g. which freezer), for contacting (e.g. which institute), broadly for logistical or legal reasons (e.g. city, country or continent). | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | String |
| Analyses performed | Reports the existence of any analyses performed on this material other than genomics (e.g. transcriptomics, metabolomics, proteomics). | [IAO:0000702](http://purl.obolibrary.org/obo/IAO_0000702) | [AnalysesPerformed](../../lookups/AnalysesPerformed.txt) lookup (20 choices [of type](http://edamontology.org/topic_3391)) |
| Derived from | Indicate if this material was produced from or related to another. | [NCIT:C28355](http://purl.obolibrary.org/obo/NCIT_C28355) | String |

## Module: Fixed Block
Refers to preserved specimens embedded in support material. These are typically cut into thin slices for microscopic examination, with or without an enhancement such as staining. Ontology: [NCIT:C25436](http://purl.obolibrary.org/obo/NCIT_C25436).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Block identifier | A unique proper name or character sequence that identifies this fixed block. | [NCIT:C93400](http://purl.obolibrary.org/obo/NCIT_C93400) | UniqueID |

## Module: Sample preparation
A sample preparation for a nucleic acids sequencing assay. Ontology: [OBI:0001902](http://purl.obolibrary.org/obo/OBI_0001902).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sampleprep identifier | A unique proper name or character sequence that identifies this particular sample preparation. | [NCIT:C132299](http://purl.obolibrary.org/obo/NCIT_C132299) | UniqueID |
| Belongs to material | Reference to the source material from which this sample was prepared. | [NCIT:C25683](http://purl.obolibrary.org/obo/NCIT_C25683) | Reference to instances of Material |
| Input amount | Amount of input material in nanogram (ng). | [AFRL:0000010](http://purl.allotrope.org/ontologies/role#AFRL_0000010) | Integer |
| Library preparation kit | Pre-filled, ready-to-use reagent cartridges intented to improve chemistry, cluster density and read length as well as improve quality (Q) scores for this sample. Reagent components are encoded to interact with the sequencing system to validate compatibility with user-defined applications. | [GENEPIO:0000085](http://purl.obolibrary.org/obo/GENEPIO_0000085) | [NGSKits](../../lookups/NGSKits.txt) lookup (621 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0000081)) |
| PCR free | Indicates whether a polymerase chain reaction (PCR) was used to prepare this sample. PCR is a method for amplifying a DNA base sequence using multiple rounds of heat denaturation of the DNA and annealing of oligonucleotide primers complementary to flanking regions in the presence of a heat-stable polymerase. | [NCIT:C17003](http://purl.obolibrary.org/obo/NCIT_C17003) | Boolean |
| Target enrichment kit | Indicates which target enrichment kit was used to prepare this sample. Target enrichment is a pre-sequencing DNA preparation step where DNA sequences are either directly amplified (amplicon or multiplex PCR-based) or captured (hybrid capture-based) in order to only focus on specific regions of a genome or DNA sample. | [NCIT:C154307](http://purl.obolibrary.org/obo/NCIT_C154307) | [NGSKits](../../lookups/NGSKits.txt) lookup (621 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0000081)) |
| UMIs present | Indicates whether any unique molecular identifiers (UMIs) are present. An UMI barcode is a short nucleotide sequence that is used to identify reads originating from an individual mRNA molecule. | [EFO:0010199](http://www.ebi.ac.uk/efo/EFO_0010199) | Boolean |
| Intended insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:0000001](https://w3id.org/fair-genomes/resource/FG_0000001) | Integer |
| Intended read length | The number of nucleotides intended to be ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | Integer |

## Module: Sequencing
The determination of complete (typically nucleotide) sequences, including those of genomes (full genome sequencing, de novo sequencing and resequencing), amplicons and transcriptomes. Ontology: [EDAM:topic_3168](http://edamontology.org/topic_3168).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sequencing identifier | A unique proper name or character sequence that identifies this particular nucleic acid sequencing assay. | [NCIT:C171337](http://purl.obolibrary.org/obo/NCIT_C171337) | UniqueID |
| Belongs to sample preparation | Reference to the prepared sample, i.e. the source that was sequenced. | [NCIT:C25683](http://purl.obolibrary.org/obo/NCIT_C25683) | Reference to instances of Sample preparation |
| Sequencing date | Date on which this sequencing assay was performed. | [GENEPIO:0000069](http://purl.obolibrary.org/obo/GENEPIO_0000069) | Date |
| Sequencing platform | The used sequencing platform (i.e. brand, name of a company that produces sequencer equipment). | [GENEPIO:0000071](http://purl.obolibrary.org/obo/GENEPIO_0000071) | [SequencingPlatform](../../lookups/SequencingPlatform.txt) lookup (7 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0000071)) |
| Sequencing instrument model | The used product name and model number of a manufacturer's genomic (dna) sequencer. | [GENEPIO:0001921](http://purl.obolibrary.org/obo/GENEPIO_0001921) | [SequencingInstrumentModels](../../lookups/SequencingInstrumentModels.txt) lookup (45 choices [of type](http://purl.obolibrary.org/obo/GENEPIO_0001921)) |
| Sequencing method | Method used to determine the order of bases in a nucleic acid sequence. | [FIX:0000704](http://purl.obolibrary.org/obo/FIX_0000704) | [SequencingMethods](../../lookups/SequencingMethods.txt) lookup (35 choices [of type](http://purl.obolibrary.org/obo/NCIT_C17565)) |
| Median read depth | The median number of times a particular locus (site, nucleotide, amplicon, region) was sequenced. | [NCIT:C155320](http://purl.obolibrary.org/obo/NCIT_C155320) | Integer |
| Observed read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | Integer |
| Observed insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:0000002](https://w3id.org/fair-genomes/resource/FG_0000002) | Integer |
| Percentage Q30 | Percentage of reads with a Phred quality score over 30, which indicates less than a 1/1000 chance that the base was called incorrectly. | [GENEPIO:0000089](http://purl.obolibrary.org/obo/GENEPIO_0000089) | Decimal |
| Percentage TR20 | Percentage of the target sequence on which 20 or more unique reads were successfully mapped. | [FG:0000003](https://w3id.org/fair-genomes/resource/FG_0000003) | Decimal |
| Other quality metrics | Other NGS quality control metrics, including but not limited to (i) sequencer metrics such as yield, error rate, density (K/mm2), cluster PF (%) and phas/prephas (%), (ii) alignment metrics such as QM insert size, GC content, QM duplicated reads (%), QM error rate, uniformity/evenness of coverage and maternal cell contamination, and (iii) variant call metrics such as number of SNVs/CNVs/SVs called, number of missense/nonsense variants, common variants (%), unique variants (%), gender match and trio inheritance check. | [EDAM:data_3914](http://edamontology.org/data_3914) | Text |

## Module: Analysis
An analysis applies analytical (often computational) methods to existing data of a specific type to produce some desired output. Ontology: [EDAM:operation_2945](http://edamontology.org/operation_2945).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Analysis identifier | A unique proper name or character sequence that identifies this particular analysis. | [AFR:0001979](http://purl.allotrope.org/ontologies/result#AFR_0001979) | UniqueID |
| Belongs to sequencing | Reference to the sequencing that was performed, i.e. the source on which this analysis was based. | [NCIT:C25683](http://purl.obolibrary.org/obo/NCIT_C25683) | Reference to instances of Sequencing |
| Physical data location | A place on the Earth where the data is located, by its name or by its geographical location. This definition is intentionally vague to allow reuse locally (e.g. which computer), for contacting (e.g. which institute), broadly for logistical or legal reasons (e.g. city, country or continent). | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | String |
| Abstract data location | The file location of the data, or a copy of the data, on an electronically accessible device for preservation (either in plain-text or encrypted format). | [NCIT:C142494](http://purl.obolibrary.org/obo/NCIT_C142494) | String |
| Data formats stored | Which data file formats (i.e. defined ways or layouts of representing and structuring data in a computer file, blob, string, message, or elsewhere) are stored and potentially available. | [NCIT:C142494](http://purl.obolibrary.org/obo/NCIT_C142494) | [DataFormats](../../lookups/DataFormats.txt) lookup (582 choices [of type](http://edamontology.org/format_1915)) |
| Algorithms used | Any used problem-solving procedures implemented in software to be executed by a computer. | [NCIT:C16275](http://purl.obolibrary.org/obo/NCIT_C16275) | Text |
| Reference genome used | The specific build of the human genome used as reference for sequence alignment and variant calling. | [EDAM:data_2340](http://edamontology.org/data_2340) | [GenomeAccessions](../../lookups/GenomeAccessions.txt) lookup (29 choices [of type](http://edamontology.org/data_2787)) |
| Bioinformatic protocol used | A human-readable collection of information about about how a scientific experiment or analysis was carried out that results in a specific set of data or results used for further analysis or to test a specific hypothesis. | [EDAM:data_2531](http://edamontology.org/data_2531) | Text |
| Bioinformatic protocol deviation | A variation from processes or procedures defined in the bioinformatic protocol. Deviations usually do not preclude the overall evaluability of subject data for either efficacy or safety, and are often acknowledged and accepted in advance by the sponsor. | [NCIT:C50996](http://purl.obolibrary.org/obo/NCIT_C50996) | String |
| Reason for bioinformatic protocol deviation | The rationale for why a deviation from the bioinformatic protocol has occurred. | [NCIT:C93529](http://purl.obolibrary.org/obo/NCIT_C93529) | String |
| WGS guideline followed | Any followed systematic statement of policy rules or principles. Guidelines may be developed by government agencies at any level, institutions, professional societies, governing boards, or by convening expert panels. | [NCIT:C17564](http://purl.obolibrary.org/obo/NCIT_C17564) | String |

## Module: HMD Submission
A class specific for the 1+MG GDI project containing items for metadata submission. Ontology: [FG:0000750](https://w3id.org/fair-genomes/resource/FG_0000750).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Submitter Role | Role of the Submitter (e.g. Oncologist in charge of the patient, Research Project PI). | [FG:0000752](https://w3id.org/fair-genomes/resource/FG_0000752) | String |
| Data Center | Refers to the Department in charge of Data Production and/ or analysis | [FG:0000753](https://w3id.org/fair-genomes/resource/FG_0000753) | String |
| Clinical Center | Refers to the Clinical Department in charge of the patient. | [FG:0000754](https://w3id.org/fair-genomes/resource/FG_0000754) | String |
| Institution Clinical | Identifies the Institution/s involved. | [FG:0000755](https://w3id.org/fair-genomes/resource/FG_0000755) | [InstitutesROR](../../lookups/InstitutesROR.txt) lookup (102392 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Institution Data Center | Identifies the Institution/s involved. | [FG:0000756](https://w3id.org/fair-genomes/resource/FG_0000756) | [InstitutesROR](../../lookups/InstitutesROR.txt) lookup (102392 choices [of type](http://semanticscience.org/resource/SIO_000688)) |
| Publication description | Linked to the instance of publication_doi; should provide a human-readably description of the publication. | [FG:0000757](https://w3id.org/fair-genomes/resource/FG_0000757) | String |
| Collection | Identifies collection (Biobank, Collection, Cohort, other types of projects) in which data are part of. | [FG:0000751](https://w3id.org/fair-genomes/resource/FG_0000751) | String |
| Research Consortia | Identifies Research Consortia Involved. | [FG:0000758](https://w3id.org/fair-genomes/resource/FG_0000758) | String |

## Module: Treatment
An action or administration of therapeutic agents to produce an effect that is intended to alter or stop a pathologic process. Ontology: [NCIT:C49236](http://purl.obolibrary.org/obo/NCIT_C49236).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Dose Units | Indicates the total dose given in units (e.g. of Gray (Gy) when radiation, Millimeters/24hours for medication) | [FG:0000759](https://w3id.org/fair-genomes/resource/FG_0000759) | String |
| Setting | Indicate the treatment setting, which describes the treatment's purpose in relation to the primary treatment. | [NCIT:C124308](http://purl.obolibrary.org/obo/NCIT_C124308) | [TreatmentSettings](../../lookups/TreatmentSettings.txt) lookup (3 choices [of type](http://purl.obolibrary.org/obo/NCIT_C124308)) |
| Response to Treatment | The patients' response to the applied treatment regimen. | [SNOMEDCT:182985004](http://purl.bioontology.org/ontology/SNOMEDCT/182985004) | [TreatmentResponse](../../lookups/TreatmentResponse.txt) lookup (5 choices [of type](http://purl.bioontology.org/ontology/SNOMEDCT/182985004)) |

## Module: Biomarker
TODO Ontology: [NCIT:C16342](http://purl.obolibrary.org/obo/NCIT_C16342).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Type | What type is the biomarker classified as. | [NCIT:C164707](http://purl.obolibrary.org/obo/NCIT_C164707) | [BiomarkerType](../../lookups/BiomarkerType.txt) lookup (5 choices [of type](http://purl.obolibrary.org/obo/NCIT_C164707)) |
| Subtype_Molecular | Subtype of the molecular biomarker. | [FG:0000766](https://w3id.org/fair-genomes/resource/FG_0000766) | [BiomarkerMolecular](../../lookups/BiomarkerMolecular.txt) lookup (7 choices [of type](https://w3id.org/fair-genomes/resource/FG_0000766)) |

## Module: Imaging Study
The Study IE defines the characteristics of a medical Study performed on a Patient. A Study is a collection of one or more Series of medical images, presentation states, and/or SR documents that are logically related for the purpose of diagnosing a Patient. Each Study is associated with exactly one Patient. A Study may include Composite Instances that are created by a single modality, multiple modalities or by multiple devices of the same modality. The Study IE is modality independent. Ontology: [DICOM:113014](http://dicom.nema.org/resources/ontology/DCM/113014).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Imaging Study identifier | The user- or equipment-generated identifier of an instance of a DICOM study entity. | [NCIT:C164493](http://purl.obolibrary.org/obo/NCIT_C164493) | UniqueID |
| Belongs to person | Reference to the person whom this imaging study is about. | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | Reference to instances of Personal |
| Imaging Technique (Modality) | Any technology or method that aids in the visualization of any biological process, cell, tissue or organ for use in screening, diagnosis, surgical procedures or therapy. | [NCIT:C17369](http://purl.obolibrary.org/obo/NCIT_C17369) | [Modality](../../lookups/Modality.txt) lookup (5 choices [of type](http://purl.obolibrary.org/obo/NCIT_C17369)) |
| Body Region | Named areas of the body. | [NCIT:C12680](http://purl.obolibrary.org/obo/NCIT_C12680) | [AnatomicFocus](../../lookups/AnatomicFocus.txt) lookup (10 choices [of type](http://snomed.info/id/52530000)) |
| Imaging Procedure | An imaging procedure as defined by SNOMED CT, representing a specific diagnostic or acquisition procedure (e.g., 'CT of breast', 'MRI of head', or 'Whole Slide Imaging'), rather than the general modality or technique alone. The general modality or technique (e.g., 'CT', 'MR', 'SM') is captured separately in the 'Imaging Technique' field. | [NCIT:C17369](http://purl.obolibrary.org/obo/NCIT_C17369) | [ImagingProcedure](../../lookups/ImagingProcedure.txt) lookup (3 choices [of type](http://snomed.info/id/15220000)) |
| Reason for Imaging Procedure | The clinical or research rationale for performing the imaging procedure. This field captures why the procedure was indicated for the patient, such as screening, diagnosis, treatment planning, or research purposes. It should express the intent or medical reason, not the technical details of the procedure itself. | [NCIT:C69216](http://purl.obolibrary.org/obo/NCIT_C69216) | [ReasonForImagingProcedure](../../lookups/ReasonForImagingProcedure.txt) lookup (1 choices [of type](http://snomed.info/id/138875005)) |
| Study Start Date | The date on which a study began. | [NCIT:C69208](http://purl.obolibrary.org/obo/NCIT_C69208) | Date |
| DICOM Series Count | The number of DICOM series. | [NCIT:C164492](http://purl.obolibrary.org/obo/NCIT_C164492) | Integer |
| DICOM Images Count | The number of DICOM instances in the imaging study. | [NCIT:C164540](http://purl.obolibrary.org/obo/NCIT_C164540) | Integer |
| Affiliated Institution | The organisation or institution responsible for performing the imaging study. | [NCIT:C25412](http://purl.obolibrary.org/obo/NCIT_C25412) | [Institutes](../../lookups/Institutes.txt) lookup (219 choices [of type](http://semanticscience.org/resource/SIO_000688)) |

## Module: Imaging Series
A distinct logical set used to group composite instances. All instances within a Series are of the same modality, in the same Frame of Reference (if any), and created by the same equipment. Ontology: [DICOM:113015](http://dicom.nema.org/resources/ontology/DCM/113015).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Series Instance UID | A unique identifier for series of images. | [NCIT:C69219](http://purl.obolibrary.org/obo/NCIT_C69219) | UniqueID |
| Belongs to Imaging Study | Reference to the parent imaging study of which this series is a part, linking the series to the overall study context. | [NCIT:C164493](http://purl.obolibrary.org/obo/NCIT_C164493) | Reference to instances of Imaging Study |
| DICOM Images Count | The number of DICOM instances in the imaging study. | [NCIT:C164540](http://purl.obolibrary.org/obo/NCIT_C164540) | Integer |
| Imaging Technique (Modality) | Any technology or method that aids in the visualization of any biological process, cell, tissue or organ for use in screening, diagnosis, surgical procedures or therapy. | [NCIT:C17369](http://purl.obolibrary.org/obo/NCIT_C17369) | [Modality](../../lookups/Modality.txt) lookup (5 choices [of type](http://purl.obolibrary.org/obo/NCIT_C17369)) |
| Imaging Procedure | An imaging procedure as defined by SNOMED CT, representing a specific diagnostic or acquisition procedure (e.g., 'CT of breast', 'MRI of head', or 'Whole Slide Imaging'), rather than the general modality or technique alone. The general modality or technique (e.g., 'CT', 'MR', 'SM') is captured separately in the 'Imaging Technique' field. | [NCIT:C17369](http://purl.obolibrary.org/obo/NCIT_C17369) | [ImagingProcedure](../../lookups/ImagingProcedure.txt) lookup (3 choices [of type](http://snomed.info/id/15220000)) |
| Series Start Date | The date when this imaging series started within the parent study. | [NCIT:C68616](http://purl.obolibrary.org/obo/NCIT_C68616) | Date |
| Body Region | Named areas of the body. | [NCIT:C12680](http://purl.obolibrary.org/obo/NCIT_C12680) | [AnatomicFocus](../../lookups/AnatomicFocus.txt) lookup (10 choices [of type](http://snomed.info/id/52530000)) |
| Laterality | Indicates the side of the body examined within the imaging series. This field specifies whether the imaging procedure focused on the right side, left side, both sides, or an unpaired (midline) structure. | [NCIT:C25185](http://purl.obolibrary.org/obo/NCIT_C25185) | [Laterality](../../lookups/Laterality.txt) lookup (4 choices [of type](http://snomed.info/id/106233006)) |
| Imaging Device | A device for producing a representation or picture, usually of a part of the body, to aid in diagnosis and evaluation. | [NCIT:C19747](http://purl.obolibrary.org/obo/NCIT_C19747) | [ImagingDevice](../../lookups/ImagingDevice.txt) lookup (1 choices [of type](http://snomed.info/id/314789007)) |
| Manufacturer of Imaging Device | The company or organization that produces or supplies an imaging device, such as MRI, CT, X-ray, ultrasound, or whole-slide scanners, used to generate visual representations of anatomical structures or tissue samples for diagnostic, research, or clinical evaluation purposes. | [NCIT:C25392](http://purl.obolibrary.org/obo/NCIT_C25392) | [ManufacturerOfID](../../lookups/ManufacturerOfID.txt) lookup (14 choices [of type](http://purl.obolibrary.org/obo/OBI_0000835)) |
| Software Version | A form or variant of software; one of a sequence of copies of a software program, each incorporating new modifications. | [NCIT:C111093](http://purl.obolibrary.org/obo/NCIT_C111093) | [ImagingDeviceSoftwareVersion](../../lookups/ImagingDeviceSoftwareVersion.txt) lookup (1 choices [of type](http://snomed.info/id/706687001)) |

## Module: Image Instance
Any record of an imaging event whether physical or electronic. Ontology: [NCIT:C48179](http://purl.obolibrary.org/obo/NCIT_C48179).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Image Instance Identifier | A sequence of letters, numbers, or other characters that specifically identifies a particular image. | [NCIT:C81289](http://purl.obolibrary.org/obo/NCIT_C81289) | UniqueID |
| Belongs to Imaging Study | Reference to the parent imaging study of which this image is a part, linking the series to the overall study context. | [NCIT:C164493](http://purl.obolibrary.org/obo/NCIT_C164493) | Reference to instances of Imaging Study |
| Belongs to Imaging Series | Reference to the parent imaging series of which this image is a part, linking the series to the overall study context. | [NCIT:C69219](http://purl.obolibrary.org/obo/NCIT_C69219) | Reference to instances of Imaging Series |
| Image Instance Date | The date when this image was generated. | [NCIT:C68616](http://purl.obolibrary.org/obo/NCIT_C68616) | Date |
| Image Type | Indicates the characteristics and derivation of the image, e.g., whether it is a primary acquisition or a derived image. | [NCIT:C69268](http://purl.obolibrary.org/obo/NCIT_C69268) | [ImageType](../../lookups/ImageType.txt) lookup (1 choices [of type](http://snomed.info/id/363679005)) |

## Null flavors
Each lookup is supplemented with so-called 'null flavors' from HL7. These can be used to indicate precisely why a particular value could not be entered into the system, providing substantially more insight than simply leaving a field empty.

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

