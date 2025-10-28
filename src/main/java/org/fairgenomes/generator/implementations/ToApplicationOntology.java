package org.fairgenomes.generator.implementations;

import org.eclipse.rdf4j.model.vocabulary.*;
import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.IRI;
import static org.eclipse.rdf4j.model.util.Values.iri;
import static org.eclipse.rdf4j.model.util.Values.literal;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.fairgenomes.generator.datastructures.Module;

public class ToApplicationOntology extends AbstractGenerator {

    public ToApplicationOntology(YamlModel fg, File outputFolder) {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception, IOException {

        // All prefixes and namespaces
        Map<String, String> prefixToNamespace = new HashMap<>();
        prefixToNamespace.put("fg", ontologyURL);
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
        FileWriter fw = new FileWriter(new File(outputFolder, fg.fileName + ".ttl"));
        BufferedWriter bw = new BufferedWriter(fw);
        RDFFormat applicationOntologyFormat = RDFFormat.TURTLE;
        for(String prefix : prefixToNamespace.keySet())
        {
            builder.setNamespace(prefix, prefixToNamespace.get(prefix));
        }

        // add FAIR Genomes project information, version, authors, copyright, license, etc
        IRI root = iri(ontologyURL);
        builder.add(root, RDF.TYPE, OWL.ONTOLOGY);
        builder.add(root, DC.TITLE, fg.name);
        builder.add(root, DC.DESCRIPTION, fg.description);
        builder.add(root, DC.DATE, fg.date);
        builder.add(root, OWL.VERSIONINFO, fg.version + "-" + fg.releaseType);
        builder.add(root, DC.LANGUAGE, "en");
        builder.add(root, DC.RIGHTS, "This ontology is distributed under a " + fg.license.name+" License - " + fg.license.url + ". Copyright: " + fg.copyright.holder + " ("+fg.copyright.years+").");
        builder.add(root, DC.PUBLISHER, iri(fg.technical.gitLocation));
        builder.add(root, DCTERMS.LICENSE, fg.license.name);
        builder.add(root, DCTERMS.LICENSE_DOCUMENT, iri(fg.license.url));
        for(Author a : fg.authors)
        {
            builder.add(root, DC.CREATOR, a.name + (a.email != null ? " <"+a.email+">" : ""));
            if(a.orcid != null)
            {
                builder.add(root, DC.CREATOR, iri("https://orcid.org/" + a.orcid));
            }
        }

        // Add modules to builder as moduleClasses
        for (Module m : fg.modules) {
            String moduleName = cleanLabel(m.name);
            IRI moduleClass = iri(ontologyURL, cleanLabel(moduleName));
            builder.add(moduleClass, RDF.TYPE, OWL.CLASS);
            builder.add(moduleClass, RDFS.ISDEFINEDBY, iri(m.parsedOntology.iri));
            builder.add(moduleClass, RDFS.LABEL, literal(m.name));
            builder.add(moduleClass, DC.DESCRIPTION, literal(m.description));
            if(m.relationWith != null){
                for(RelationWith rw : m.relationWith)
                {
                    builder.add(moduleClass, iri(rw.relationOnto.iri), iri(ontologyURL, cleanLabel(rw.module)));
                }
            }

            // Add elements to builder as moduleProperties
            for(Element e : m.elements) {
                String elementName = moduleName + "_" + cleanLabel(e.name);
                IRI moduleProperty = iri(ontologyURL, cleanLabel(elementName));
                builder.add(moduleProperty, RDF.TYPE, e.isLookup() || e.isReference() ? OWL.OBJECTPROPERTY : OWL.DATATYPEPROPERTY);
                builder.add(moduleProperty, RDFS.LABEL, literal(e.name));
                if(e.unitOntology != null) {builder.add(moduleProperty, iri(prefixToNamespace.get("sio"),"SIO_000074"), iri(e.unitOntology.iri));}
                builder.add(moduleProperty, RDFS.DOMAIN, moduleClass);
                builder.add(moduleProperty, RDFS.ISDEFINEDBY, iri(e.parsedOntology.iri));
                builder.add(moduleProperty, DC.DESCRIPTION, literal(e.description));
                // We need to check this annotation // TODO value type annotation
                //bw.write("\t\trdfs:Datatype xsd:" + e.valueTypeToRDF() + " ;" + LE);

                if(e.isLookup()){
                    if(e.type == null){
                        throw new Exception("type is null for " + e);
                    }
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
                        IRI lookupInstance = iri(ontologyURL, cleanLabel(lookupName));
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

        // FIXME
        // Add links to lookup TTLs
        // ideally we would use 'OWL:IMPORTS' but this breaks LODE, probably because
        // OWLAPI always attempts to import any TTL file which cannot be disabled
        // using non-resolvable locations or empty TTL files as workarounds does not work
        // so instead, as a hack, we abuse the DC.CONTRIBUTOR field to represent this information
        for(String key : lookupBuilders.keySet())
        {
            builder.add(root, DC.CONTRIBUTOR, iri(fg.technical.ttlLocation + fg.fileName + "-" + key.toLowerCase() + ".ttl"));
        }

        // Write main model
        Model model = builder.build();
        Rio.write(model, bw, applicationOntologyFormat);
        bw.flush();
        bw.close();

        // Write lookups as separate TTL files
        for(String key : lookupBuilders.keySet())
        {
            FileWriter fwL = new FileWriter(new File(outputFolder, fg.fileName + "-" + key.toLowerCase() + ".ttl"));
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
