# FAIR Genomes semantic schema

![FAIR Genomes NGS FAIRification flow](misc/fg-ngs-fairification-flow.png)

## Explore

Discover the full schema at the [interactive overview](generated/markdown/fairgenomes-semantic-model.md). 

## Source

The source of the schema is located at [fair-genomes.yml](fair-genomes.yml). The reference lookup lists are available at [lookups](lookups).

## Outputs

Representations of the schema for specific systems/users are generated from the schema. The following outputs are available:

#### Electronic Data Capture (EDC) systems

- MOLGENIS EMX1 database setup at [molgenis-emx](generated/molgenis-emx).
- PALGA Codebook at [palga-codebook](generated/palga-codebook).
- ART-DECOR at [art-decor](generated/art-decor). The ART-DECOR XML is uploaded to [NICTIZ](https://decor.nictiz.nl/art-decor/decor-datasets--fairgenomes) made available for [iCRF Generator](https://github.com/aderidder/iCRFGenerator). FAIR Genomes interoperable case report forms can be created by iCRF Generator in these EDC formats:
  - Castor - Step
  - Castor - Report
  - Castor - Survey
  - REDCap
  - OpenClinica 3

#### Resource Description Framework (RDF)

- Application ontology at [ontology](generated/ontology).
- Resources for new terms at [resource](generated/resource).

#### Documentation

- Interactive overview at [markdown](generated/markdown). View it [here](generated/markdown/fairgenomes-semantic-model.md).
- Typesetted LaTeX/PDF at [latex](generated/latex) and [pdf](derived/pdf). View it [here](derived/pdf/fair-genomes.pdf).
- LODE page at [lode](derived/ontology/lode). View it [here](https://w3id.org/fair-genomes/ontology).

## Demo
Please try the [public demo](https://fairgenomes-acc.gcc.rug.nl) at give us feedback.

## Other links
- Persistent [namespace](https://w3id.org/fair-genomes) is provided by W3ID, linking to [ontology](https://w3id.org/fair-genomes/ontology) and [resources](https://w3id.org/fair-genomes/resource/FG_0000001).
- Find us on [FAIRSharing](https://fairsharing.org/bsg-s001533/).
- Available on [BioPortal](https://bioportal.bioontology.org/ontologies/FG).

## Technical notes

#### RDF formats

The FAIR Genomes application ontology [TTL files](generated/ontology) can be converted to other RDF serialization formats including OWL-XML, RDF-XML, RDF-JSON, JSON-LD, N-Triples, TriG, TriX, Thrift, Manchester syntax and Functional syntax using [Ontology Converter](https://github.com/sszuev/ont-converter/releases/tag/v1.0).

For example, conversion to OWL-XML can be accomplished by running: 
```
java -jar ont-converter.jar -i /path/to/fairgenomes-semantic-model/generated/ontology/fair-genomes.ttl -if TURTLE -o fair-genomes.owl -of OWL_XML
```

Please be aware that the original TTL format is highly efficient. Other RDF formats typically consume more disk space. Conversions using the FAIR Genomes TTL of 25-02-2021 as a reference results in the following relative file size differences:

| Format | Difference |
|---|---|
| TTL | 0% (reference) |
| OWL-XML | +660% |
| RDF-XML | +107% |
| RDF-JSON | +176% |
| JSON-LD | +13% |
| N-Triples | +357% |
| TriG | -1% |
| TriX | +696% |
| Thrift | +284% |
| Manchester | +435% |
| Functional | +300% |

#### ART-DECOR validation

The ART-DECOR XML is validated using [Saxon](http://saxon.sourceforge.net), requiring these resources:
```
http://art-decor.org/ADAR/rv/DECOR.sch
https://github.com/Schematron/stf/blob/master/iso-schematron-xslt2/iso_svrl_for_xslt2.xsl
https://github.com/Schematron/stf/blob/master/iso-schematron-xslt2/iso_schematron_skeleton_for_saxon.xsl
```
The Schematron must first be converted to XSLT:
```
java -jar saxon-he-10.3.jar -o:DECOR.xsl -s:DECOR.sch iso_svrl_for_xslt2.xsl
```
The XML can then be validated as follows:
```
java -jar saxon-he-10.3.jar -o:warnings.xml -s:/path/to/fairgenomes-semantic-model/generated/art-decor/fair-genomes_en-US.xml DECOR.xsl
```
Finally, `warnings.xml` is inspected for any errors or warnings.

#### Release SOP

1. Set correct date, version number and release type in YAML file.
2. Generate outputs (by running Main.java).
3. Validate implementations:
    - Run ART-DECOR XML validation
    - Run MOLGENIS setup script
    - Import application ontology into GraphDB
    - Check Markdown rendering
4. Update PDF (run toPDF.sh in generated/latex).
5. Update version in [sys_StaticContent.tsv](misc/molgenis/other/sys_StaticContent.tsv).
6. Update version in [pom.xml](pom.xml).
7. Create a new Excel file at [misc/excel](misc/excel).
8. Make commits and push to fork.
9. Pull request and merge with main (i.e. _fairgenomes_ organization).
10. Create updated LODE page by following the [link](http://150.146.207.114/lode/extract?url=https%3A%2F%2Fraw.githubusercontent.com%2Ffairgenomes%2Ffairgenomes-semantic-model%2Fmain%2Fgenerated%2Fontology%2Ffair-genomes.ttl&owlapi=true&lang=en) to TTL file in main repo.
11. Commit LODE page to fork, then again PR and merge with main.
12. Create Github release on main.
13. Prepare for further development: increment version in [fair-genomes.yml](fair-genomes.yml) and set to SNAPSHOT. Also update [pom.xml](pom.xml) and [sys_StaticContent.tsv](misc/molgenis/other/sys_StaticContent.tsv).
14. Use [Ontology Converter](https://github.com/sszuev/ont-converter/releases/tag/v1.0) to convert to OWL and publish new version on [BioPortal](https://bioportal.bioontology.org/ontologies/FG).
15. Reset [demo server](https://fairgenomes-acc.gcc.rug.nl/) and import new app.

#### MOLGENIS-EMX2

To upload to molgenis-emx2, make a zip of the generated/molgenis-emx2 and upload.
