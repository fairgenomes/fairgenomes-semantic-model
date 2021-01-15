package org.fairgenomes.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.implementations.artdecor.ToARTDECOR;
import org.fairgenomes.transformer.implementations.markdown.ToMD;
import org.fairgenomes.transformer.implementations.molgenisemx.ToEMX;
import org.fairgenomes.transformer.implementations.rdfowl.ToOWL;
import org.fairgenomes.transformer.implementations.rdfowl.ToTTL;

import java.io.*;

public class TransformFGToAllFormats {

    private File inputF;

    public TransformFGToAllFormats() {
        this.inputF = new File("fair-genomes.yml");
    }

    public void generateResources() throws Exception {

        System.out.println("Parsing FAIR Genomes YAML...");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        FAIRGenomes fg = mapper.readValue(inputF, FAIRGenomes.class);

        System.out.println("Loading lookups and value types...");
        fg.loadLookupGlobalOptions();
        fg.parseElementValueTypes();
        fg.loadElementLookups();
        fg.parseOntologies();
        fg.setElementModules();
        fg.parseReferences();
        fg.createElementTechnicalNames();

        System.out.println("Transforming into other representations...");
        File outputs = new File("transformation-output");
        new ToMD(fg, new File(outputs, "markdown")).start();
        new ToEMX(fg, new File(outputs, "molgenis-emx")).start();
        new ToOWL(fg, new File(outputs, "rdf-xml")).start();
        new ToTTL(fg, new File(outputs, "rdf-ttl")).start();
        new ToARTDECOR(fg, new File(outputs, "art-decor")).start();


    }
}
