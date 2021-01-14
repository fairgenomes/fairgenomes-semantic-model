package org.fairgenomes.transformer.implementations;

import org.fairgenomes.transformer.datastructures.FAIRGenomes;

import java.io.File;

/**
 * Attributes and constructor shared by all transformer implementations
 */
public abstract class GenericTransformer {

    public FAIRGenomes fg;
    public File outputFolder;
    public static final String LE = "\n"; // using Unix line endings is the safer option

    public GenericTransformer(FAIRGenomes fg, File outputFolder) {
        this.fg = fg;
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        this.outputFolder = outputFolder;
    }

    public abstract void start() throws Exception;
}
