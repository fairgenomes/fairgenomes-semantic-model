package org.fairgenomes.transformer.implementations;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Lookup;
import org.fairgenomes.transformer.datastructures.Module;
import org.fairgenomes.transformer.datastructures.GenericTransformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ToRDFTTL extends GenericTransformer {

    public ToRDFTTL(FAIRGenomes fg, File outputFolder) {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception {

        FileWriter fw = new FileWriter(new File(outputFolder, "fair-genomes.ttl"));

        /*
        Writer header
         */
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("@prefix fg: <https://fair-genomes.org/> ." + LE);
        bw.write("@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ." + LE);
        bw.write("@prefix dc: <http://purl.org/dc/elements/1.1/> ." + LE);
        bw.write("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ." + LE);
        bw.write("@prefix owl: <http://www.w3.org/2002/07/owl#> ." + LE);
        bw.write("@prefix xsd: <http://www.w3.org/2001/XMLSchema#> ." + LE);
        bw.write(LE);
        bw.write("https://fair-genomes.org/ a owl:Ontology ; " + LE);
        bw.write("\tdc:title \"" + fg.name + "\" ;" + LE);
        bw.write("\tdc:date \"" + fg.date + "\" ;" + LE);
        bw.write("\tdc:description \"" + fg.description + "\" ." + LE);
        bw.write(LE);

        for (Module m : fg.modules) {
            String moduleName = m.name.replace(" ", "_");
            bw.write("fg:" + moduleName + " a " + "owl:Class ;" + LE);
            bw.write("\trdfs:isDefinedBy <" + m.iri + "> ; " + LE);
            bw.write("\trdfs:label \"" + m.name + "\" ; " + LE);
            bw.write("\tdc:description \"" + m.description + "\" ;" + LE);

            for(Element e : m.elements)
            {
                String elementName = moduleName + "_" + e.name.replace(" ","");
                bw.write("\tfg:" + elementName + " a " + "owl:DatatypeProperty ;" + LE);
                bw.write("\t\trdfs:domain fg:"+moduleName+" ; " + LE);
                bw.write("\t\trdfs:isDefinedBy <" + e.iri + "> ; " + LE);
                bw.write("\t\tdc:description \"" + e.description + "\" ;" + LE);
                bw.write("\t\trdfs:Datatype xsd:" + e.valueTypeToRDF() + " ;" + LE);

                if(e.isLookup()) {
                    for (String lookup : e.lookup.lookups.keySet()) {

                        Lookup l = e.lookup.lookups.get(lookup);
                        bw.write("\t\t\trdfs:label \"" + l.value + "\" ; " + LE);
                        bw.write("\t\t\tdc:description \"" + l.description + "\" ;" + LE);
                        bw.write("\t\t\trdfs:isDefinedBy <" + l.iri + "> ; " + LE);
                        bw.write("\t\t\trdf:type " + elementName + LE);

                    }
                }
            }
        }


        bw.flush();
        bw.close();

    }
}
