package org.fairgenomes.generator.implementations;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.*;
import org.fairgenomes.generator.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;

import static org.eclipse.rdf4j.model.util.Values.iri;

/**
 * Maintains the RDF fragments that make up the specific FAIR Genomes ontology
 */
public class ToRDFResources extends AbstractGenerator {

    private HashSet<String> uniqueTerms;
    private HashSet<String> uniqueLookups;

    public ToRDFResources(YamlModel fg, File outputFolder)
    {
        super(fg, outputFolder);
        uniqueTerms = new HashSet<>();
        uniqueLookups = new HashSet<>();
    }

    @Override
    public void start() throws Exception {

        for (Module m : fg.modules) {

            if(m.ontology.startsWith("FG:")){
                String term = m.parsedOntology.codeSystem + "_" + m.parsedOntology.code;
                if(uniqueTerms.contains(term))
                {
                    System.out.println("WARNING: Term already in use: " + term + ". If you are trying to define a new term, you must adopt an unused identifier. Ignore this warning if you are linking to a term that was newly defined in the FG namespace earlier in the model.");
                }
                else {
                    uniqueTerms.add(term);
                    FileWriter fw = new FileWriter(new File(outputFolder, term + ".xml"));
                    BufferedWriter bw = new BufferedWriter(fw);
                    IRI type = OWL.CLASS;
                    String srcTTL = fg.fileName + ".ttl";
                    bw.write(toRDF(m.parsedOntology.codeSystem, m.parsedOntology.code, type, m.name, m.description, iri(m.parsedOntology.iri), m.description, srcTTL));
                    bw.flush();
                    bw.close();
                }
            }

                for(Element e : m.elements) {

                if(e.ontology.startsWith("FG:")){

                    String term = e.parsedOntology.codeSystem + "_" + e.parsedOntology.code;
                    if(uniqueTerms.contains(term))
                    {
                        System.out.println("WARNING: Term already in use: " + term + ". If you are trying to define a new term, you must adopt an unused identifier. Ignore this warning if you are linking to a term that was newly defined in the FG namespace earlier in the model.");
                    }
                    else {
                        uniqueTerms.add(term);
                        FileWriter fw = new FileWriter(new File(outputFolder, term + ".xml"));
                        BufferedWriter bw = new BufferedWriter(fw);
                        IRI type = e.isLookup() || e.isReference() ? OWL.OBJECTPROPERTY : OWL.DATATYPEPROPERTY;
                        String srcTTL = fg.fileName + ".ttl";
                        bw.write(toRDF(e.parsedOntology.codeSystem, e.parsedOntology.code, type, e.name, e.description, iri(m.parsedOntology.iri), m.description, srcTTL));
                        bw.flush();
                        bw.close();
                    }
                }

                if(e.isLookup()) {

                    // Do not iterate over the same lookup list twice
                    if(uniqueLookups.contains(e.lookup.name))
                    {
                        continue;
                    }

                    for (String lookup : e.lookup.lookups.keySet()) {
                         Lookup l = e.lookup.lookups.get(lookup);

                         if(l.codesystem.equals("FG"))
                         {
                             String term = l.codesystem + "_" + l.code;
                             if(uniqueTerms.contains(term))
                             {
                                 throw new Exception("Term already in use: " + term + " for " + l.value);
                             }
                             uniqueTerms.add(term);
                             FileWriter fw = new FileWriter(new File(outputFolder, term + ".xml"));
                             BufferedWriter bw = new BufferedWriter(fw);
                             IRI type = iri(e.type);
                             String srcTTL = fg.fileName + "-" + e.lookup.name.toLowerCase() + ".ttl";
                             bw.write(toRDF(l.codesystem, l.code, type, l.value, l.description, null, null, srcTTL));
                             bw.flush();
                             bw.close();
                         }
                    }

                    uniqueLookups.add(e.lookup.name);
                }
            }
        }
    }


    private String toRDF(String prefix, String code, IRI type, String label, String description, IRI belongsToIRI, String belongsToDescription, String srcTTL)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + LE);
        sb.append("<?xml-stylesheet type=\"text/xsl\" href=\"https://fairgenomes.org/fairgenomes-semantic-model/misc/semanticscience.org/resource.xsl\" ?>" + LE + LE);
        sb.append("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + LE);
        sb.append("  xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + LE);
        sb.append("  xmlns:dc=\"http://purl.org/dc/terms/\"" + LE);
        sb.append("  xmlns:ns0=\"http://purl.org/dc/elements/1.1/\"" + LE);
        sb.append("  xmlns:owl=\"http://www.w3.org/2002/07/owl#\">" + LE + LE);

        if(belongsToIRI != null) {
            sb.append("  <rdf:Description rdf:about=\"" + belongsToIRI + "\">" + LE);
            sb.append("    <rdfs:label xml:lang=\"en\">" + belongsToDescription + "</rdfs:label>" + LE);
            sb.append("  </rdf:Description>" + LE + LE);
        }

        sb.append("  <rdf:Description rdf:about=\"" + resourceURL + prefix + "_" + code + "\">" + LE);
        sb.append("    <rdf:type rdf:resource=\"" + type + "\"/>" + LE);
        sb.append("    <rdfs:label xml:lang=\"en\">"+label+"</rdfs:label>" + LE);
        sb.append("    <rdfs:isDefinedBy rdf:resource=\"" + ontologyURL + srcTTL + "\"/>" + LE);
        if(belongsToIRI != null){sb.append("    <rdfs:domain rdf:resource=\"" + belongsToIRI + "\"/>" + LE);}
        sb.append("    <dc:description xml:lang=\"en\">" + description + "</dc:description>" + LE);
        sb.append("    <dc:identifier>"+ prefix + ":" + code +"</dc:identifier>" + LE);
        sb.append("    <ns0:identifier>"+ prefix + "_" + code +"</ns0:identifier>" + LE);
        sb.append("  </rdf:Description>" + LE + LE);

        sb.append("</rdf:RDF>" + LE);

        return sb.toString();
    }


}


