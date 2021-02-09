# FAIR Genomes compatibility with CDE

Compatibility mapping of the FAIR Genomes semantic metadata model to Common Data Elements (CDE) by the European Commission's Joint Research Centre (JRC). The latest version of the CDE can be found [here](https://eu-rd-platform.jrc.ec.europa.eu/sites/default/files/CDS/EU_RD_Platform_CDS_Final.pdf).

| CDE Element no. | CDE Element name | FAIR Genomes equivalent | Remarks |
|---|---|---|---|
| 1.1 | Pseudonym | _Personal identifier_ in __Personal__ | FAIR Genomes accepts any identifier including pseudonyms. |
| 2.1 | Date of birth | _Year of birth_ in __Personal__ | FAIR Genomes limited this element to just year because the exact date is highly sensitive and often not needed. |
| 2.2 | Sex | _Gender_ in __Personal__ | For genetic details, _Genotypic sex_ may be used in conjunction with _Gender_. |
| 3.1 | Patient's status | _Inclusion status_ in __Personal__ | FAIR Genomes adopted this exact element from CDE but chose a more generic name. |
| 3.2 | Date of death | _Age at death_ in __Personal__ | FAIR Genomes limited this element to age because it is less sensitive than a date, though it may be combined with _Year of birth_ (if provided) to determine the year of death. |
| 4.1 | First contact with specialised centre | _First contact_ in __Clinical__ | FAIR Genomes further specified the definition to '... in context of disease or inclusion'. |
| 5.1 | Age at onset | _Age at onset_ in __Clinical__ | FAIR Genomes accepts an integer as age at onset instead of categorical/date in CDE. |
| 5.2 | Age at diagnosis | Age at diagnosis in __Clinical__ | FAIR Genomes accepts an integer as age at diagnosis instead of categorical/date in CDE. |
| 6.1 | Diagnosis of the rare disease | _Clinical diagnosis_ in __Clinical__ | FAIR Genomes provides lookups for Orpha codes as recommended (not ICD). |
| 6.2 | Genetic diagnosis | _Molecular diagnosis other_ in __Clinical__ | _Molecular diagnosis other_ specified the variant and _Molecular diagnosis gene_ the gene. |
| 6.3 | Undiagnosed case | _Phenotype_ in __Clinical__ | FAIR Genomes accepts phenotype for solved and unsolved cases. Full genotype information should be retrievable via _Abstract data location_ in __Analysis__. |
| 7.1 | Agreement to be contacted for research purposes | _Allow reidentification_ in __Individual consent__ | _Allow reidentification_ should be combined with other consent information such as _Restricted to_, _Consent form used_ and _Valid until_ to get all information for potential recontacting. |
| 7.2 | Consent to the reuse of data | A combination of __Informed consent form__ and __Individual consent__ | Consenting to reuse of data for research is a combination of the consent form type (e.g. broad or narrow) and patient's wishes in the individual consent (e.g. restrictions on use, data linkage). |
| 7.3 | Biological sample | _Allow research_ in __Individual consent__ | Despite different names, these fields have the same meaning. |
| 7.4 | Link to a biobank | _Physical location_ in __Material__ | The FAIR Genomes definition is broader but accepts biobank information. _Resources in other institutes_ captures material availability in biobanks elsewhere. |
| 8.1 | Classification of functioning/disability | _Functioning_ in __Clinical__ | Now free text in both CDE and FG, perhaps stronger if directly linked to ICF terms. |
