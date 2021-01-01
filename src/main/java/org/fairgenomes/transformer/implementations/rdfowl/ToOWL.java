package org.fairgenomes.transformer.implementations.rdfowl;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generate OWL file from FAIR Genomes YAML.
 * TODO: refer to original ontology IRI or make 'FAIR Genomes' IRIs? or use both?
 * TODO: NullFlavors
 */
public class ToOWL {

    FAIRGenomes fg;
    File outputFolder;
    static final String RN = "\r\n";

    public ToOWL(FAIRGenomes fg, File outputFolder) throws Exception {
        this.fg = fg;
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        this.outputFolder = outputFolder;
    }



    public void go() throws IOException {

        FileWriter fw = new FileWriter(new File(outputFolder, "fair-genomes.owl"));

        /*
        Writer header
         */
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("<?xml version=\"1.0\"?>" + RN +
                "     <Ontology xmlns=\"http://www.w3.org/2002/07/owl#\"" + RN +
                "     xml:base=\"http://purl.org/fairgenomes/ontology\"" + RN +
                "     xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + RN +
                "     xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"" + RN +
                "     xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + RN +
                "     xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + RN +
                "     ontologyIRI=\"http://purl.org/fairgenomes/ontology\"" + RN +
                "     versionIRI=\"http://purl.org/fairgenomes/ontology" + fg.version + "\">" + RN +
                "    <Prefix name=\"\" IRI=\"http://purl.org/fairgenomes/ontology\"/>" + RN +
                "    <Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\"/>" + RN +
                "    <Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>" + RN +
                "    <Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\"/>" + RN +
                "    <Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\"/>" + RN +
                "    <Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\"/>" + RN);

        /*
        All modules, elements and lookups become classes.
        Elements are nested under modules, lookups under elements.
         */
        for (Module m : fg.modules) {
            String moduleName = m.name.replace(" ", "_");

            bw.write("    <Declaration>" + RN);
            bw.write("        <Class IRI=\"#"+moduleName+"\"/>\n");
            bw.write("    </Declaration>" + RN);

            for (Element e : m.elements) {
                String elementName = e.name.replace(" ", "_");

                bw.write("    <Declaration>" + RN);
                bw.write("        <Class IRI=\"#"+elementName+"\"/>\n");
                bw.write("    </Declaration>" + RN);
                bw.write("    <SubClassOf>" + RN);
                bw.write("        <Class IRI=\"#"+elementName+"\"/>"+ RN);
                bw.write("        <Class IRI=\"#"+moduleName+"\"/>"+ RN);
                bw.write("    </SubClassOf>" + RN);

                if(e.isLookup())
                {
                    for(String lookup : e.lookup.lookups.keySet())
                    {
                        String lookupName = lookup.replace(" ", "_");
                        bw.write("    <Declaration>" + RN);
                        bw.write("        <Class IRI=\"#"+lookupName+"\"/>" + RN);
                        bw.write("    </Declaration>" + RN);
                        bw.write("    <SubClassOf>" + RN);
                        bw.write("        <Class IRI=\"#"+lookupName+"\"/>"+ RN);
                        bw.write("        <Class IRI=\"#"+elementName+"\"/>"+ RN);
                        bw.write("    </SubClassOf>" + RN);
                        bw.write("    <AnnotationAssertion>" + RN);
                        bw.write("        <AnnotationProperty abbreviatedIRI=\"rdfs:label\"/>" + RN);
                        bw.write("        <IRI>#"+elementName+"</IRI>" + RN);
                        bw.write("        <Literal datatypeIRI=\"http://www.w3.org/2000/01/rdf-schema#Literal\">"+lookup+"</Literal>" + RN);
                        bw.write("    </AnnotationAssertion>" + RN);
                    }
                }
            }
        }

        bw.write("</Ontology>" + RN);

        bw.flush();
        bw.close();

    }
}

