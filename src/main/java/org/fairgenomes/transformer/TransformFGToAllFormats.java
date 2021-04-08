package org.fairgenomes.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.implementations.*;

import java.io.File;

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
        fg.parseOntologies();
        fg.parseReferences();
        fg.loadElementLookups();
        fg.setElementModules();
        fg.createElementTechnicalNames();

        System.out.println("Transforming into other representations...");
        File outputs = new File("transformation-output");
        FileUtils.cleanDirectory(outputs);
        new ToMarkdown(fg, new File(outputs, "markdown")).start();
        new ToMOLGENISEMX(fg, new File(outputs, "molgenis-emx")).start();
        new ToApplicationOntology(fg, new File(outputs, "ontology")).start();
        new ToPALGACodeBook(fg, new File(outputs, "palga-codebook")).start();
        new ToARTDECOR(fg, new File(outputs, "art-decor")).start();
        new ToRDFResources(fg, new File(outputs, "resource")).start();
        new ToLaTeXTables(fg, new File(outputs, "latex")).start();


    }
}
