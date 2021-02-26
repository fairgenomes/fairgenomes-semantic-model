# FAIR Genomes semantic schema

![FAIR Genomes NGS FAIRification flow](misc/fg-ngs-fairification-flow.png)

## Explore

Discover the full schema at the [Markdown overview](transformation-output/markdown/fairgenomes-semantic-model.md). 

## Source

The source of the schema is located at [fair-genomes.yml](fair-genomes.yml). The reference lookup lists are available at [lookups](lookups).

## Outputs

Currently, the following transformation output formats are available:
- MOLGENIS database setup at [molgenis-emx](transformation-output/molgenis-emx).
- Application ontology at [rdf-ttl](transformation-output/rdf-ttl).
- Human-readable overview at [markdown](transformation-output/markdown).
- PALGA Codebook at [palga-codebook](transformation-output/palga-codebook).
- ART-DECOR at [art-decor](transformation-output/art-decor).

## EDC support

The ART-DECOR XML is uploaded to [NICTIZ](https://decor.nictiz.nl/art-decor/decor-datasets--fairgenomes) made available for [iCRF Generator](https://github.com/aderidder/iCRFGenerator).
FAIR Genomes interoperable case report forms can be created by iCRF Generator in these EDC formats:

- Castor - Step
- Castor - Report
- Castor - Survey
- REDCap
- OpenClinica 3

## RDF formats
The FAIR Genomes application ontology [Turtle file](transformation-output/rdf-ttl) can be converted to other RDF serialization formats including OWL-XML, RDF-XML, RDF-JSON, JSON-LD, N-Triples, TriG, TriX, Thrift, Manchester syntax and Functional syntax using [Ontology Converter](https://github.com/sszuev/ont-converter/releases/tag/v1.0).

For example, conversion to OWL-XML can be accomplished by running: 
```
java -jar ont-converter.jar -i /path/to/fairgenomes-semantic-model/transformation-output/rdf-ttl/fair-genomes.ttl -if TURTLE -o fair-genomes.owl -of OWL_XML
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

## Technical notes

### ART-DECOR validation

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
java -jar saxon-he-10.3.jar -o:warnings.xml -s:/path/to/fairgenomes-semantic-model/transformation-output/art-decor/fair-genomes_en-US.xml DECOR.xsl
```
Finally, `warnings.xml` is inspected for any errors or warnings.
