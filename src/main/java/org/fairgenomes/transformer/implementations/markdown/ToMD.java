package org.fairgenomes.transformer.implementations.markdown;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Lookup;
import org.fairgenomes.transformer.datastructures.Module;
import org.fairgenomes.transformer.implementations.GenericTransformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Transform to Markdown documentation of the model
 */
public class ToMD extends GenericTransformer {

    static final int DESCRIPTION_LIMIT = 1000;

    public ToMD(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {
        int totalNrOfElements = 0;
        for (Module m : fg.modules) {
            totalNrOfElements += m.elements.size();
        }

        FileWriter fw = new FileWriter(new File(outputFolder, "fairgenomes-semantic-model.md"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("# FAIR Genomes semantic metadata model" + LE + LE);
        bw.write(fg.description + " Version "+fg.version + ", "+fg.date+". This model consists of __" + fg.modules.size() + " modules__ that contain __" + totalNrOfElements + " metadata elements__ in total." + LE + LE);

        bw.write("## Module overview" + LE + LE);
        bw.write("| Name | Description | Ontology | Nr. of elements |" + LE);
        bw.write("|---|---|---|---|" + LE);
        for (Module m : fg.modules) {
            bw.write("| ["+m.name+"](" + m.toMarkdownAnchor() + ") | " + m.description + " | [" + m.codeSystem + ":" + m.code + "](" + m.iri + ") | " + m.elements.size() + " |" + LE);
        }
        bw.write(LE);

        for (Module m : fg.modules) {
            bw.write("## Module: " + m.name + LE);
            bw.write(m.description + " Ontology: " + "[" + m.codeSystem + ":" + m.code + "](" + m.iri + ")." + LE + LE);

            bw.write("| Element | Description | Ontology | Values |" + LE);
            bw.write("|---|---|---|---|" + LE);

            for (Element e : m.elements) {
                bw.write("| " + e.name + " | " + (e.description.length() < DESCRIPTION_LIMIT ? e.description : e.description.substring(0,DESCRIPTION_LIMIT) + "...") + " | " + "[" + e.codeSystem + ":" + e.code + "](" + e.iri + ")" + " | " + e.valueTypeToMarkDown() + " |" + LE);
            }
            bw.write(LE);
        }

        bw.write("## Null flavors" + LE);
        bw.write("Each lookup in FAIR Genomes is supplemented with so-called 'null flavors' from HL7. These can be used to indicate precisely why a particular value could not be entered into the system, providing substantially more insight than simply leaving a field empty." + LE + LE);
        bw.write("| Value | Description | Ontology |" + LE);
        bw.write("|---|---|---|" + LE);
        for(String key: fg.lookupGlobalOptionsInstance.lookups.keySet())
        {
            Lookup nf = fg.lookupGlobalOptionsInstance.lookups.get(key);
            bw.write("| " + key.substring(0,key.indexOf("(")-1) + " | " + nf.description + " | " + "[" + nf.codesystem + ":" + nf.code + "](" + nf.iri + ") |" + LE);
        }
        bw.write(LE);

        bw.flush();
        bw.close();

    }
}
