package org.fairgenomes.generator.implementations;

import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.IRI;
import static org.eclipse.rdf4j.model.util.Values.iri;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.DC;
import static org.eclipse.rdf4j.model.util.Values.literal;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.fairgenomes.generator.datastructures.Module;

public class ToApplicationOntology extends AbstractGenerator {

    public ToApplicationOntology(FAIRGenomes fg, File outputFolder) {
        super(fg, outputFolder);
    }

    // Start of each file name
    public static final String baseFileName = "fair-genomes";

    @Override
    public void start() throws Exception, IOException {

        // Replace this later with w3id or purl
        String baseUrl = "https://github.com/fairgenomes/fairgenomes-semantic-model/";

        // All prefixes and namespaces
        Map<String, String> prefixToNamespace = new HashMap<>();
        prefixToNamespace.put("fg", "https://fair-genomes.org/");
        prefixToNamespace.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        prefixToNamespace.put("dc", "http://purl.org/dc/elements/1.1/");
        prefixToNamespace.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        prefixToNamespace.put("owl", "http://www.w3.org/2002/07/owl#");
        prefixToNamespace.put("xsd", "http://www.w3.org/2001/XMLSchema#");
        prefixToNamespace.put("obo", "http://purl.obolibrary.org/obo/");
        prefixToNamespace.put("sio", "https://semanticscience.org/resource/");
        prefixToNamespace.put("ordo", "http://www.orpha.net/ORDO/");

        // Main model builder
        ModelBuilder builder = new ModelBuilder();
        Map<String, ModelBuilder> lookupBuilders = new HashMap<String, ModelBuilder>(); // group builders together that refer to the same lookup, i.e. Country
        FileWriter fw = new FileWriter(new File(outputFolder, baseFileName+".ttl"));
        BufferedWriter bw = new BufferedWriter(fw);
        RDFFormat applicationOntologyFormat = RDFFormat.TURTLE;
        for(String prefix : prefixToNamespace.keySet())
        {
            builder.setNamespace(prefix, prefixToNamespace.get(prefix));
        }

        // TODO
        // add FAIR Genomes project information, version, authors, copyright, license, etc!

        // Add modules to builder as moduleClasses
        for (Module m : fg.modules) {
            String moduleName = cleanLabel(m.name);
            IRI moduleClass = iri(baseUrl, cleanLabel(moduleName));
            builder.add(moduleClass, RDF.TYPE, OWL.CLASS);
            builder.add(moduleClass, RDFS.ISDEFINEDBY, iri(m.iri));
            builder.add(moduleClass, RDFS.LABEL, literal(m.name));
            builder.add(moduleClass, DC.DESCRIPTION, literal(m.description));

            // Add elements to builder as moduleProperties
            for(Element e : m.elements) {
                String elementName = moduleName + "_" + cleanLabel(e.name);
                IRI moduleProperty = iri(baseUrl, cleanLabel(elementName));
                builder.add(moduleProperty, RDF.TYPE, e.isLookup() || e.isReference() ? OWL.OBJECTPROPERTY : OWL.DATATYPEPROPERTY);
                builder.add(moduleProperty, RDFS.LABEL, literal(e.name));
                builder.add(moduleProperty, RDFS.DOMAIN, moduleClass);
                builder.add(moduleProperty, RDFS.ISDEFINEDBY, iri(e.iri));
                builder.add(moduleProperty, DC.DESCRIPTION, literal(e.description));
                // We need to check this annotation // TODO value type annotation
                //bw.write("\t\trdfs:Datatype xsd:" + e.valueTypeToRDF() + " ;" + LE);

                if(e.isLookup()){
                    builder.add(moduleProperty, RDFS.RANGE, iri(e.type));

                    // Group together elements with the same lookup list
                    if(!lookupBuilders.containsKey(e.lookup.name))
                    {
                        ModelBuilder lookupBuilder = new ModelBuilder();
                        lookupBuilders.put(e.lookup.name, lookupBuilder);
                    }

                    // Lookup builder
                    ModelBuilder lookupBuilder = lookupBuilders.get(e.lookup.name);
                    for(String prefix : prefixToNamespace.keySet())
                    {
                        lookupBuilder.setNamespace(prefix, prefixToNamespace.get(prefix));
                    }

                    // Add each lookup option to builder
                    for (String lookup : e.lookup.lookups.keySet()) {
                        Lookup l = e.lookup.lookups.get(lookup);
                        String lookupName = elementName + "_" + cleanLabel(l.value);
                        IRI lookupInstance = iri(baseUrl, cleanLabel(lookupName));
                        lookupBuilder.add(lookupInstance, RDF.TYPE, iri(e.type));
                        lookupBuilder.add(lookupInstance, RDFS.LABEL, literal(l.value));
                        lookupBuilder.add(lookupInstance, DC.DESCRIPTION, literal(l.description));
                        lookupBuilder.add(lookupInstance, RDFS.ISDEFINEDBY, iri(l.iri));
                        // We need to check this annotation // TODO value type annotation
                        //bw.write("\t\t\trdf:type " + elementName + LE);

                    }

                }
            }
        }

        // Write main model
        Model model = builder.build();
        Rio.write(model, bw, applicationOntologyFormat);
        bw.flush();
        bw.close();

        // Write lookups as separate TTL files
        for(String key : lookupBuilders.keySet())
        {
            FileWriter fwL = new FileWriter(new File(outputFolder, baseFileName+"-"+key.toLowerCase()+".ttl"));
            BufferedWriter bwL = new BufferedWriter(fwL);
            Model lookupModel = lookupBuilders.get(key).build();
            Rio.write(lookupModel, bwL, applicationOntologyFormat);
            bwL.flush();
            bwL.close();
        }

    }

    private String cleanLabel(String label) {
        label = label.trim();
        label = label.replace(" ", "_");
        label = label.replace("[","");
        label = label.replace("]","");
        label = label.replace("|","_");
        label = label.replace("<","lt");
        label = label.replace(">","gt");
        label = label.replace("%","pc");
        return label;
    }
}
