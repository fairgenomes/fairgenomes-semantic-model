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
    static final int DESCRIPTION_LIMIT = 1000;

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
            bw.write(m.description + " Ontology: " + "[" + m.codeSystem + ":" + m.code + "](" + m.iri + ")." + RN + RN);

            bw.write("| Element | Description | Ontology | Values |" + RN);
            bw.write("|---|---|---|---|" + RN);

            for (Element e : m.elements) {
                bw.write("| " + e.name + " | " + (e.description.length() < DESCRIPTION_LIMIT ? e.description : e.description.substring(0,DESCRIPTION_LIMIT) + "...") + " | " + "[" + e.codeSystem + ":" + e.code + "](" + e.iri + ")" + " | " + e.valueTypeToMarkDown() + " |" + RN);
            }
        }

        bw.flush();
        bw.close();

    }
}
