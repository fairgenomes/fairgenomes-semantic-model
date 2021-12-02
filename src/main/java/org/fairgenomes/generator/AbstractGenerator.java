package org.fairgenomes.generator;

import org.fairgenomes.generator.datastructures.YamlModel;

import java.io.File;

/**
 * Attributes and constructor shared by all generator implementations
 */
public abstract class AbstractGenerator {

    public YamlModel fg;
    public File outputFolder;
    public static final String LE = "\n"; // using Unix line endings is the safer option

    public static final String baseIRI = "https://w3id.org/fair-genomes/";
    public static final String resourceURL = baseIRI + "resource/";
    public static final String ontologyURL = baseIRI + "ontology/";

    public AbstractGenerator(YamlModel fg, File outputFolder) {
        this.fg = fg;
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        this.outputFolder = outputFolder;
    }

    public abstract void start() throws Exception;
}
