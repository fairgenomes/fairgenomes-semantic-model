package org.fairgenomes.transformer.implementations;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Module;
import org.fairgenomes.transformer.datastructures.GenericTransformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generate OWL file from FAIR Genomes YAML.
 * TODO: refer to original ontology IRI or make 'FAIR Genomes' IRIs? or use both?
 * TODO: NullFlavors
 */
public class ToRDFXML extends GenericTransformer {

    public ToRDFXML(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {

        FileWriter fw = new FileWriter(new File(outputFolder, "fair-genomes.owl"));

        /*
        Writer header
         */
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("<?xml version=\"1.0\"?>" + LE +
                "     <Ontology xmlns=\"http://www.w3.org/2002/07/owl#\"" + LE +
                "     xml:base=\"http://purl.org/fairgenomes/ontology\"" + LE +
                "     xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + LE +
                "     xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"" + LE +
                "     xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + LE +
                "     xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + LE +
                "     ontologyIRI=\"http://purl.org/fairgenomes/ontology\"" + LE +
                "     versionIRI=\"http://purl.org/fairgenomes/ontology" + fg.version + "\">" + LE +
                "    <Prefix name=\"\" IRI=\"http://purl.org/fairgenomes/ontology\"/>" + LE +
                "    <Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\"/>" + LE +
                "    <Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>" + LE +
                "    <Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\"/>" + LE +
                "    <Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\"/>" + LE +
                "    <Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\"/>" + LE);

        /*
        All modules, elements and lookups become classes.
        Elements are nested under modules, lookups under elements.
         */
        for (Module m : fg.modules) {
            String moduleName = m.name.replace(" ", "_");

            bw.write("    <Declaration>" + LE);
            bw.write("        <Class IRI=\"#"+moduleName+"\"/>\n");
            bw.write("    </Declaration>" + LE);

            for (Element e : m.elements) {
                String elementName = e.name.replace(" ", "_");

                bw.write("    <Declaration>" + LE);
                bw.write("        <Class IRI=\"#"+elementName+"\"/>\n");
                bw.write("    </Declaration>" + LE);
                bw.write("    <SubClassOf>" + LE);
                bw.write("        <Class IRI=\"#"+elementName+"\"/>"+ LE);
                bw.write("        <Class IRI=\"#"+moduleName+"\"/>"+ LE);
                bw.write("    </SubClassOf>" + LE);

                if(e.isLookup())
                {
                    for(String lookup : e.lookup.lookups.keySet())
                    {
                        String lookupName = lookup.replace(" ", "_");
                        bw.write("    <Declaration>" + LE);
                        bw.write("        <Class IRI=\"#"+lookupName+"\"/>" + LE);
                        bw.write("    </Declaration>" + LE);
                        bw.write("    <SubClassOf>" + LE);
                        bw.write("        <Class IRI=\"#"+lookupName+"\"/>"+ LE);
                        bw.write("        <Class IRI=\"#"+elementName+"\"/>"+ LE);
                        bw.write("    </SubClassOf>" + LE);
                        bw.write("    <AnnotationAssertion>" + LE);
                        bw.write("        <AnnotationProperty abbreviatedIRI=\"rdfs:label\"/>" + LE);
                        bw.write("        <IRI>#"+elementName+"</IRI>" + LE);
                        bw.write("        <Literal datatypeIRI=\"http://www.w3.org/2000/01/rdf-schema#Literal\">"+lookup+"</Literal>" + LE);
                        bw.write("    </AnnotationAssertion>" + LE);
                    }
                }
            }
        }

        bw.write("</Ontology>" + LE);

        bw.flush();
        bw.close();

    }
}

