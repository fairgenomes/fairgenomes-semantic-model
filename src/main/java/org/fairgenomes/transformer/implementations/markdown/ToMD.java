package org.fairgenomes.transformer.implementations.markdown;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Lookup;
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
        int totalNrOfElements = 0;
        for (Module m : fg.modules) {
            totalNrOfElements += m.elements.size();
        }

        FileWriter fw = new FileWriter(new File(outputFolder, "fairgenomes-semantic-model.md"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("# FAIR Genomes semantic metadata model" + RN + RN);
        bw.write(fg.description + " Version "+fg.version + ", "+fg.date+". This model consists of __" + fg.modules.size() + " modules__ that contain __" + totalNrOfElements + " metadata elements__ in total." + RN + RN);

        bw.write("## Module overview" + RN + RN);
        bw.write("| Name | Description | Ontology | Nr. of elements |" + RN);
        bw.write("|---|---|---|---|" + RN);
        for (Module m : fg.modules) {
            bw.write("| ["+m.name+"](" + m.toMarkdownAnchor() + ") | " + m.description + " | [" + m.codeSystem + ":" + m.code + "](" + m.iri + ") | " + m.elements.size() + " |" + RN);
        }
        bw.write(RN);

        for (Module m : fg.modules) {
            bw.write("## Module: " + m.name + RN);
            bw.write(m.description + " Ontology: " + "[" + m.codeSystem + ":" + m.code + "](" + m.iri + ")." + RN + RN);

            bw.write("| Element | Description | Ontology | Values |" + RN);
            bw.write("|---|---|---|---|" + RN);

            for (Element e : m.elements) {
                bw.write("| " + e.name + " | " + (e.description.length() < DESCRIPTION_LIMIT ? e.description : e.description.substring(0,DESCRIPTION_LIMIT) + "...") + " | " + "[" + e.codeSystem + ":" + e.code + "](" + e.iri + ")" + " | " + e.valueTypeToMarkDown() + " |" + RN);
            }
            bw.write(RN);
        }

        bw.write("## Null flavors" + RN);
        bw.write("Each lookup in FAIR Genomes is supplemented with so-called 'null flavors' from HL7. These can be used to indicate precisely why a particular value could not be entered into the system, providing substantially more insight than simply leaving a field empty." + RN + RN);
        bw.write("| Value | Description | Ontology |" + RN);
        bw.write("|---|---|---|" + RN);
        for(String key: fg.lookupGlobalOptionsInstance.lookups.keySet())
        {
            Lookup nf = fg.lookupGlobalOptionsInstance.lookups.get(key);
            bw.write("| " + key.substring(0,key.indexOf("(")-1) + " | " + nf.description + " | " + "[" + nf.codesystem + ":" + nf.code + "](" + nf.iri + ") |" + RN);
        }
        bw.write(RN);

        bw.flush();
        bw.close();

    }
}
