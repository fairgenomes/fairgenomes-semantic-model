package org.fairgenomes.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.fairgenomes.generator.datastructures.YamlModel;
import org.fairgenomes.generator.implementations.*;

import java.io.File;

public class GenerateOutputs {

    private File inputF;

    public GenerateOutputs(String inputFileLoc) {
        this.inputF = new File(inputFileLoc);
    }

    public void generateResources() throws Exception {
        YamlModel y = readYamlModel();
        System.out.println("Generating other representations...");
        File outputs = new File(inputF.getParentFile(), "generated");
        FileUtils.cleanDirectory(outputs);
        new ToMarkdown(y, new File(outputs, "markdown")).start();
        new ToMOLGENISEMX(y, new File(outputs, "molgenis-emx")).start();
        new ToMOLGENISEMX2(y, new File(outputs, "molgenis-emx2")).start();
        new ToApplicationOntology(y, new File(outputs, "ontology")).start();
        new ToPALGACodeBook(y, new File(outputs, "palga-codebook")).start();
        new ToARTDECOR(y, new File(outputs, "art-decor")).start();
        new ToRDFResources(y, new File(outputs, "resource")).start();
        new ToLaTeXTables(y, new File(outputs, "latex")).start();
        new ToJavaAPI(y, new File(outputs, "java")).start();
    }

    public YamlModel readYamlModel() throws Exception {
        System.out.println("Parsing YAML...");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        YamlModel y = mapper.readValue(inputF, YamlModel.class);
        System.out.println("Loading lookups and value types...");
        y.fileName = inputF.getName().replace(".yml","");
        y.createElementTechnicalNames();
        y.makeModuleMap();
        y.parseModuleSubclassing();
        y.loadLookupGlobalOptions();
        y.parseElementValueTypes();
        y.parseElementUnits();
        y.parseOntologies();
        y.parseMatches();
        y.parseReferences();
        y.loadElementLookups();
        y.setElementModules();
        y.parseModuleRelations();
        return y;
    }
}
