# FAIR Genomes semantic metadata model
## Module: Personal
Data, facts or figures about an individual; the set of relevant items would depend on the use case. Ontology: [NCIT:C90492](http://purl.obolibrary.org/obo/NCIT_C90492).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Personal identifier | An alphanumeric identifier assigned to a specific patient. | [NCIT:C164337](http://purl.obolibrary.org/obo/NCIT_C164337) | UniqueID |
| Gender | Biological sex is the quality of a biological organism based on reproductive function or organs. | [SIO:010029](https://semanticscience.org/resource/SIO_010029.rdf) | [Gender](../../lookups/Gender.txt) lookup (3 choices) |
| Genotypic sex | A biological sex quality inhering in an individual based upon genotypic composition of sex chromosomes. | [PATO:0020000](http://purl.obolibrary.org/obo/PATO_0020000) | [GenotypicSex](../../lookups/GenotypicSex.txt) lookup (11 choices) |
| Country of residence | Country of Residence at Enrollment. | [NCIT:C171105](http://purl.obolibrary.org/obo/NCIT_C171105) | [Countries](../../lookups/Countries.txt) lookup (249 choices) |
| Ethnicity | The biological quality of membership in a social group based on a common heritage. | [SIO:001014](http://semanticscience.org/resource/SIO_001014) | [Countries](../../lookups/Countries.txt) lookup (249 choices) |
| Country of birth | The country that a given person was born in. | [GENEPIO:0001094](http://purl.obolibrary.org/obo/GENEPIO_0001094) | [Countries](../../lookups/Countries.txt) lookup (249 choices) |
| Year of birth | The year in which a person was born. | [NCIT:C83164](http://purl.obolibrary.org/obo/NCIT_C83164) | Integer |
| Inclusion status | An indicator that provides information on the current health status of a patient. | [NCIT:C166244](http://purl.obolibrary.org/obo/NCIT_C166244) | [InclusionStatus](../../lookups/InclusionStatus.txt) lookup (4 choices) |
| Age at death | The age at which death occurred. | [NCIT:C135383](http://purl.obolibrary.org/obo/NCIT_C135383) | Integer |
| Inclusion criterion | An inclusion criterion defines and states a condition which, if met, makes an entity suitable for a given task or participation in a given process. | [OBI:0500027](http://purl.obolibrary.org/obo/OBI_0500027) | Text |
| Primary affiliated institute | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices) |
| Data available in other institutes | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices) |
| Participates in study | The study or studies in which this person participates. | [RO:0000056](http://purl.obolibrary.org/obo/RO_0000056) | Reference to Study module |
## Module: Study
A detailed examination, analysis, or critical inspection of a subject designed to discover facts about it. Ontology: [NCIT:C63536](http://purl.obolibrary.org/obo/NCIT_C63536).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Identifier | A sample collection or study proper name that is centrally registered and unique within one framework. | [OMIABIS:0000006](http://purl.obolibrary.org/obo/OMIABIS_0000006) | UniqueID |
| Name | A proper name that designates a study. | [OMIABIS:0000037](http://purl.obolibrary.org/obo/OMIABIS_0000037) | String |
| Description | A textual entity describing a study aim or a sample collection. | [OMIABIS:0000036](http://purl.obolibrary.org/obo/OMIABIS_0000036) | Text |
| Principal investigator | The principle investigtor or responsible person for a study or a sample collection. | [OMIABIS:0000100](http://purl.obolibrary.org/obo/OMIABIS_0000100) | String |
| Contact information | An email address for the purpose of contacting a sample collection or study contact person. | [OMIABIS:0000035](http://purl.obolibrary.org/obo/OMIABIS_0000035) | String |
| Study design | A plan specification comprised of protocols (which may specify how and what kinds of data will be gathered) that are executed as part of an investigation and is realized during a study design execution. | [OBI:0500000](http://purl.obolibrary.org/obo/OBI_0500000) | Text |
## Module: Informed consent form
A document explaining all the relevant information to assist an individual in understanding the expectations and risks in making a decision about a procedure. This document is presented to and signed by the individual or guardian. Ontology: [NCIT:C16468](http://purl.obolibrary.org/obo/NCIT_C16468).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Leaflet identifier | An unambiguous reference to the resource within a given context. | [DC:identifier](http://purl.org/dc/terms/identifier) | UniqueID |
| Leaflet title | A name given to the resource. | [DC:title](http://purl.org/dc/terms/title) | String |
| Leaflet date | A point or period of time associated with an event in the lifecycle of the resource. | [DC:date](http://purl.org/dc/terms/date) | Date |
| Leaflet version | A related resource that is a version, edition, or adaptation of the described resource. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form accepted date | Date of acceptance of the resource. | [DC:dateAccepted](http://purl.org/dc/terms/dateAccepted) | Date |
| Consent form valid until | Date (often a range) of validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Consent form creator | Institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective. | [SIO:000688](https://semanticscience.org/resource/SIO_000688.rdf) | [Institutes](../../lookups/Institutes.txt) lookup (218 choices) |
| Consent form version | A related resource that is a version, edition, or adaptation of the described resource. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Consent form restricted to | E.g. a dataset is restricted to an instance of an investigation for a specific disease or at geographical location. | [DUO:0000010](http://purl.obolibrary.org/obo/DUO_0000010) | [DataUseRestrictions](../../lookups/DataUseRestrictions.txt) lookup (30 choices) |
## Module: Individual consent
Consent by a patient to a surgical or medical procedure or participation in a clinical study after achieving an understanding of the relevant medical facts and the risks involved. Ontology: [NCIT:C16735](http://purl.obolibrary.org/obo/NCIT_C16735).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Individual consent identifier | A data item consisting of a unique identification code designating an informed consent. | [ICO:0000044](http://purl.obolibrary.org/obo/ICO_0000044) | UniqueID |
| About subject | The person whom this individual consent applies to. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to Personal module |
| Consent form used | The informed consent form that was signed. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to Informed consent form module |
| Consent form version | A related resource that is a version, edition, or adaptation of the described resource. Used when a patient requests adaptation or partial withdrawal of the consent. | [DC:hasVersion](http://purl.org/dc/terms/hasVersion) | String |
| Collected by | Indicates the person, group, or institution who performed the collection act. | [NCIT:C45262](http://purl.obolibrary.org/obo/NCIT_C45262) | String |
| Signing date | A date specification that designates when an informed consent form was signed. | [ICO:0000036](http://purl.obolibrary.org/obo/ICO_0000036) | Date |
| Valid from | Date (often a range) of validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Valid until | Date (often a range) of validity of a resource. | [DC:valid](http://purl.org/dc/terms/valid) | Date |
| Represented by | An individual who is authorized under applicable State or local law to consent on behalf of a child or incapable person to general medical care including participation in clinical research. | [NCIT:C51828](http://purl.obolibrary.org/obo/NCIT_C51828) | String |
| Restricted to | E.g. a dataset is restricted to an instance of an investigation for a specific disease or at geographical location. | [DUO:0000010](http://purl.obolibrary.org/obo/DUO_0000010) | [DataUseRestrictions](../../lookups/DataUseRestrictions.txt) lookup (30 choices) |
| Allow linkage | A study in which data from different sources are "linked". Usually used to compile epidemiological data. The logic of record linkage is that two or more items of information about a person recorded at different times, and perhaps in different places, may be of greater value when considered together than when either is considered alone. | [NCIT:C15424](http://purl.obolibrary.org/obo/NCIT_C15424) | Boolean |
| Allow reidentification | The procedure of having an identity established. | [NCIT:C25737](http://purl.obolibrary.org/obo/NCIT_C25737) | Boolean |
| Allow incidental findings | A planned process for a subject agrees not to be informed about any incidental finding. | [ICO:0000178](http://purl.obolibrary.org/obo/ICO_0000178) | Boolean |
| Recontact method | A document part that prescribes a method of contact in the future. | [ICO:0000009](http://purl.obolibrary.org/obo/ICO_0000009) | Text |
## Module: Clinical
Data obtained through patient examination or treatment. Ontology: [NCIT:C15783](http://purl.obolibrary.org/obo/NCIT_C15783).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Clinical identifier | A character or string used to name, or characterize a clinical events reference. | [NCIT:C87853](http://purl.obolibrary.org/obo/NCIT_C87853) | UniqueID |
| Belongs to person | Link to the person for which the clinical information was collected. | [IAO:0000136](http://purl.obolibrary.org/obo/IAO_0000136) | Reference to Personal module |
| Phenotype | The outward appearance of the individual. In medical context, these are often the symptoms caused by a disease. | [NCIT:C16977](http://purl.obolibrary.org/obo/NCIT_C16977) | [Phenotypes](../../lookups/Phenotypes.txt) lookup (15802 choices) |
| Unobserved phenotype | Phenotypes or symptoms that were definitely not observed, which may help in differential diagnosis or establish incomplete penetrance. | [HL7:C0442737](http://purl.bioontology.org/ontology/HL7/C0442737) | [Phenotypes](../../lookups/Phenotypes.txt) lookup (15802 choices) |
| Phenotypic data available | Types of phenotypic data collected in a clinical setting that is potentially available upon request. | [DC:DCMIType](http://purl.org/dc/terms/DCMIType) | [DCMITypes](../../lookups/DCMITypes.txt) lookup (6 choices) |
| Clinical diagnosis | A diagnosis made from a study of the signs and symptoms of a disease. | [NCIT:C15607](http://purl.obolibrary.org/obo/NCIT_C15607) | [Diseases](../../lookups/Diseases.txt) lookup (9700 choices) |
| Molecular diagnosis gene | Gene affected by pathogenic variation that is causal for disease of the patient. | [NCIT:C20826](http://purl.obolibrary.org/obo/NCIT_C20826) | [Genes](../../lookups/Genes.txt) lookup (19202 choices) |
| Molecular diagnosis other | Causal variant in HGVS notation with optional classification or free text explaining any other molecular mechanisms involved. | [NCIT:C20826](http://purl.obolibrary.org/obo/NCIT_C20826) | Text |
| Age at diagnosis | The age, measured from some defined time point e.g. birth at which a subject (e.g. a human patient) is diagnosed with some disease e.g. breast cancer. | [SNOMEDCT:423493009](http://purl.bioontology.org/ontology/SNOMEDCT/423493009) | Integer |
| Age at last screening | Age of the patient at the moment of the most recent screening. | [NCIT:C81258](http://purl.obolibrary.org/obo/NCIT_C81258) | Integer |
| Medication | Unique identifier of a drug conforming to the Anatomical Therapeutic Chemical (ATC) Classification System, a drug classification system controlled by the WHO Collaborating Centre for Drug Statistics Methodology (WHOCC). | [EDAM:data_3103](http://edamontology.org/data_3103) | [Drugs](../../lookups/Drugs.txt) lookup (5632 choices) |
| Drug regimen | The specific way a therapeutic drug is to be taken, including formulation, route of administration, dose, dosing interval, and treatment duration. | [NCIT:C142516](http://purl.obolibrary.org/obo/NCIT_C142516) | Text |
| Family members affected | This term applies to a family member who is diagnosed with the same condition as the individual who is the primary focus of investigation (the proband). | [HP:0032320](http://purl.obolibrary.org/obo/HP_0032320) | String |
| Family members sequenced | A person related by descent rather than by marriage or law that was also sequenced. | [NCIT:C71384](http://purl.obolibrary.org/obo/NCIT_C71384) | String |
| Consanguinity | Information on whether the patient is a child from two family members who are second cousins or closer. | [GSSO:007578](http://purl.obolibrary.org/obo/GSSO_007578) | String |
| Medical history | A record of a person's background regarding health, occurrence of disease events and surgical procedures. | [NCIT:C18772](http://purl.obolibrary.org/obo/NCIT_C18772) | [MedicalHistory](../../lookups/MedicalHistory.txt) lookup (1167 choices) |
| Age of onset | Age of onset of clinical manifestations related to a clinical entity. | [Orphanet:C023](http://www.orpha.net/ORDO/Orphanet_C023) | Integer |
| First contact | First contact with specialised center in context of disease or inclusion. | [LOINC:MTHU048806](http://purl.bioontology.org/ontology/LNC/MTHU048806) | Date |
## Module: Material
Natural substances derived from living organisms such as cells, tissues, proteins, and DNA. Ontology: [NCIT:C43376](http://purl.obolibrary.org/obo/NCIT_C43376).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Material identifier | The unique identification of a material in a specific context. | [NCIT:C93400](http://purl.obolibrary.org/obo/NCIT_C93400) | UniqueID |
| Sampling timestamp | Date and time at which this sample was collected. | [EFO:0000689](http://www.ebi.ac.uk/efo/EFO_0000689) | DateTime |
| Registration timestamp | The act of listing or recording officially; officially qualified or enrolled. | [NCIT:C25646](http://purl.obolibrary.org/obo/NCIT_C25646) | DateTime |
| Sampling protocol | Describes the procedure whereby biological material for an experiment is sampled. | [EFO:0005518](http://www.ebi.ac.uk/efo/EFO_0005518) | Text |
| Deviation from protocol | A variation from processes or procedures defined in the sampling protocol. Deviations usually do not preclude the overall evaluability of subject data for either efficacy or safety, and are often acknowledged and accepted in advance by the sponsor. | [NCIT:C50996](http://purl.obolibrary.org/obo/NCIT_C50996) | String |
| Reason for deviation | The rationale for why a deviation from the sampling protocol has occurred. | [NCIT:C93529](http://purl.obolibrary.org/obo/NCIT_C93529) | String |
| Material type | Material distinguishable as an identifiable class based on common qualities. | [NCIT:C25284](http://purl.obolibrary.org/obo/NCIT_C25284) | [MaterialTypes](../../lookups/MaterialTypes.txt) lookup (13 choices) |
| Anatomical source | Biological entity that constitutes the structural organization of an individual member of a biological species. | [UBERON:0001062](http://purl.obolibrary.org/obo/UBERON_0001062) | [AnatomicalSources](../../lookups/AnatomicalSources.txt) lookup (13827 choices) |
| Storage conditions | The conditions specified for the storage of a biological material. | [NCIT:C96145](http://purl.obolibrary.org/obo/NCIT_C96145) | [StorageConditions](../../lookups/StorageConditions.txt) lookup (26 choices) |
| Expiration date | The date beyond which a substance is no longer regarded as fit for use. | [NCIT:C164516](http://purl.obolibrary.org/obo/NCIT_C164516) | Date |
| Percentage tumor cells | The determination of the ratio of tumor cells compared to total cells present in a sample. The measurement may be expressed as a ratio or percentage. | [NCIT:C127771](http://purl.obolibrary.org/obo/NCIT_C127771) | Decimal |
| Physical location | A reference to a place on the Earth, by its name or by its geographical location. | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | String |
| Derived from | A material produced from or related to another. Ideally, this would be a self-reference to Material, but is instread defined as String to prevent issues with circular dependencies in database systems. | [NCIT:C28355](http://purl.obolibrary.org/obo/NCIT_C28355) | String |
## Module: Sample preparation
A sample preparation for assay that preparation of nucleic acids for a sequencing assay. Ontology: [OBI:0001902](http://purl.obolibrary.org/obo/OBI_0001902).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sampleprep identifier | Any of one or more unique codes that refers to a specific protocol. | [NCIT:C132299](http://purl.obolibrary.org/obo/NCIT_C132299) | UniqueID |
| Input material | Amount of input material in ng. | [AFRL:0000010](http://purl.allotrope.org/ontologies/role#AFRL_0000010) | Integer |
| Library preparation kit | Pre-filled, ready-to-use reagent cartridges. Used to produce improved chemistry, cluster density and read length as well as improve quality (Q) scores. Reagent components are encoded to interact with the sequencing system to validate compatibility with user-defined applications. | [GENEPIO:0000081](http://purl.obolibrary.org/obo/GENEPIO_0000081) | [NGSKits](../../lookups/NGSKits.txt) lookup (615 choices) |
| PCR free | A method for amplifying a DNA base sequence using multiple rounds of heat denaturation of the DNA and annealing of oligonucleotide primers complementary to flanking regions in the presence of a heat-stable polymerase. This results in duplication of the targeted DNA region. Newly synthesized DNA strands can subsequently serve as additional templates for the same primer sequences, so that successive rounds of primer annealing, strand elongation, and dissociation produce rapid and highly specific amplification of the desired sequence. PCR also can be used to detect the existence of the defined sequence in a DNA sample. | [NCIT:C17003](http://purl.obolibrary.org/obo/NCIT_C17003) | Boolean |
| Target enrichment kit | Any of various techniques designed to select or increase a target item in a mixed sample. | [NCIT:C154307](http://purl.obolibrary.org/obo/NCIT_C154307) | [NGSKits](../../lookups/NGSKits.txt) lookup (615 choices) |
| UMIs present | A unique molecular identifier (UMI) barcode is a short nucleotide sequence that is used to identify reads originating from an individual mRNA molecule. | [EFO:0010199](http://www.ebi.ac.uk/efo/EFO_0010199) | Boolean |
| Intended insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:IIS](https://fair-genomes.org/IIS) | Integer |
| Intended read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | Integer |
## Module: Sequencing
The determination of complete (typically nucleotide) sequences, including those of genomes (full genome sequencing, de novo sequencing and resequencing), amplicons and transcriptomes. Ontology: [EDAM:topic_3168](http://edamontology.org/topic_3168).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Sequencing identifier | A unique identifier assigned to raw data from a performed nucleic acid sequencing assay. | [NCIT:C171337](http://purl.obolibrary.org/obo/NCIT_C171337) | UniqueID |
| Sequencing date | Date the sequencing run was performed. | [GENEPIO:0000069](http://purl.obolibrary.org/obo/GENEPIO_0000069) | Date |
| Sequencing platform | A sequencing plaform (brand) is a name of a company that produces sequencer equipment. | [GENEPIO:0000071](http://purl.obolibrary.org/obo/GENEPIO_0000071) | [SequencingPlatform](../../lookups/SequencingPlatform.txt) lookup (7 choices) |
| Sequencing instrument model | A product name and model number of a manufacturer's genomic (dna) sequencer. | [GENEPIO:0001921](http://purl.obolibrary.org/obo/GENEPIO_0001921) | [SequencingInstrumentModels](../../lookups/SequencingInstrumentModels.txt) lookup (39 choices) |
| Sequencing type | Sequencing distinguishable as an identifiable class based on common qualities. | [NCIT:C25284](http://purl.obolibrary.org/obo/NCIT_C25284) | [SequencingTypes](../../lookups/SequencingTypes.txt) lookup (35 choices) |
| Average read depth | The average number of times a particular locus (site, nucleotide, amplicon, region) was sequenced. | [NCIT:C155320](http://purl.obolibrary.org/obo/NCIT_C155320) | Integer |
| Observed read length | The number of nucleotides successfully ordered from each side of a nucleic acid fragment obtained after the completion of a sequencing process. | [NCIT:C153362](http://purl.obolibrary.org/obo/NCIT_C153362) | Integer |
| Observed insert size | In paired-end sequencing, the DNA between the adapter sequences is the insert. The length of this sequence is known as the insert size, not to be confused with the inner distance between reads. So, fragment length equals read adapter length (2x) plus insert size, and insert size equals read lenght (2x) plus inner distance. | [FG:IIS](https://fair-genomes.org/OIS) | Integer |
| Percentage Q30 | Percentage of reads with a Phred quality score over 30, which indicates less than a 1/1000 chance that the base was called incorrectly. | [GENEPIO:0000089](http://purl.obolibrary.org/obo/GENEPIO_0000089) | Decimal |
| Percentage TR20 | Percentage of the target sequence on which 20 or more unique reads were successfully mapped. | [FG:PTR20](https://fair-genomes.org/PTR20) | Decimal |
## Module: Analysis
Apply analytical methods to existing data of a specific type. Ontology: [EDAM:operation_2945](http://edamontology.org/operation_2945).

| Element | Description | Ontology | Values |
|---|---|---|---|
| Analysis identifier | An assay identifier is an identifier that identifies some assay (analysis). | [AFR:0001979](http://purl.allotrope.org/ontologies/result#AFR_0001979) | UniqueID |
| Physical data location | A reference to a place on the Earth, by its name or by its geographical location. | [GAZ:00000448](http://purl.obolibrary.org/obo/GAZ_00000448) | [Countries](../../lookups/Countries.txt) lookup (249 choices) |
| Abstract data location | To maintain data by placing the data, or a copy of the data, onto an electronically accessible device for preservation (either in plain-text or encrypted format). | [NCIT:C142494](http://purl.obolibrary.org/obo/NCIT_C142494) | String |
| Data formats stored | A defined way or layout of representing and structuring data in a computer file, blob, string, message, or elsewhere. | [EDAM:format_1915](http://edamontology.org/format_1915) | [DataFormats](../../lookups/DataFormats.txt) lookup (582 choices) |
| Algorithms used | A defined procedure for solving a problem. Applied to a problem-solving procedure implemented in software to be executed by a computer. | [NCIT:C16275](http://purl.obolibrary.org/obo/NCIT_C16275) | Text |
| Bioinformatic protocol used | A human-readable collection of information about about how a scientific experiment or analysis was carried out that results in a specific set of data or results used for further analysis or to test a specific hypothesis. | [EDAM:data_2531](http://edamontology.org/data_2531) | Text |
| Deviation from protocol | A variation from processes or procedures defined in the bioinformatic protocol. Deviations usually do not preclude the overall evaluability of subject data for either efficacy or safety, and are often acknowledged and accepted in advance by the sponsor. | [NCIT:C50996](http://purl.obolibrary.org/obo/NCIT_C50996) | String |
| Reason for deviation | The rationale for why a deviation from the bioinformatic protocol has occurred. | [NCIT:C93529](http://purl.obolibrary.org/obo/NCIT_C93529) | String |
| WGS guideline followed | Any followed systematic statement of policy rules or principles. Guidelines may be developed by government agencies at any level, institutions, professional societies, governing boards, or by convening expert panels. | [NCIT:C17564](http://purl.obolibrary.org/obo/NCIT_C17564) | String |
