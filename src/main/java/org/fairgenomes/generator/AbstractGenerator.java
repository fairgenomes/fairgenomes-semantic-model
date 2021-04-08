package org.fairgenomes.generator;

import org.fairgenomes.generator.datastructures.FAIRGenomes;

import java.io.File;

/**
 * Attributes and constructor shared by all generator implementations
 */
public abstract class AbstractGenerator {

    public FAIRGenomes fg;
    public File outputFolder;
    public static final String LE = "\n"; // using Unix line endings is the safer option

    public AbstractGenerator(FAIRGenomes fg, File outputFolder) {
        this.fg = fg;
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        this.outputFolder = outputFolder;
    }

    public abstract void start() throws Exception;
}
