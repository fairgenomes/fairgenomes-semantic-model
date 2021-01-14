package org.fairgenomes.transformer.implementations.rdfowl;

import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.implementations.GenericTransformer;

import java.io.File;

public class ToTTL extends GenericTransformer {

    public ToTTL(FAIRGenomes fg, File outputFolder) {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception {

    }
}
