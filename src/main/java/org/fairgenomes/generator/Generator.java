package org.fairgenomes.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.fairgenomes.generator.datastructures.FAIRGenomes;
import org.fairgenomes.generator.implementations.markdown.ToMD;

import java.io.*;

public class Generator {

    private File inputF;

    public Generator() {
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
        fg.parseElementOntologies();

        System.out.println("Generating representations...");
        new ToMD(fg, new File("generated/markdown")).go();

    }
}
