package org.fairgenomes.transformer.implementations.markdown;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * TODO: NullFlavors
 *
 */
public class ToMD {

    FAIRGenomes fg;
    File outputFolder;
    static final String RN = "\r\n";

    public ToMD(FAIRGenomes fg, File outputFolder) throws Exception {
        this.fg = fg;
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        this.outputFolder = outputFolder;
    }

    public void go() throws IOException {
        FileWriter fw = new FileWriter(new File(outputFolder, "fairgenomes-semantic-model.md"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("# FAIR Genomes semantic metadata model" + RN);


        for (Module m : fg.modules) {
            bw.write("## Module: " + m.name + RN);
            bw.write(m.description + RN + RN);

            bw.write("| Element | Ontology | Values |" + RN);
            bw.write("|---|---|---|" + RN);

            for (Element e : m.elements) {
                bw.write("| " + e.name + " | " + "[" + e.codeSystem + ":" + e.code + "](" + e.iri + ")" + " | " + e.valueTypeToMarkDown() + " |" + RN);
            }
        }

        bw.flush();
        bw.close();

    }
}
