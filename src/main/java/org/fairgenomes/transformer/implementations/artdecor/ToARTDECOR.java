package org.fairgenomes.transformer.implementations.artdecor;

import org.fairgenomes.transformer.datastructures.Element;
import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.Lookup;
import org.fairgenomes.transformer.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Mapping:
 * value -> value_en
 * value -> description_en
 * description -> description_code
 * codesystem -> codesystem
 * code -> code
 * iri -> n/a
 *
 *  TODO: NullFlavors
 *
 */
public class ToARTDECOR {


    FAIRGenomes fg;
    File outputFolder;
    static final String RN = "\r\n";

    public ToARTDECOR(FAIRGenomes fg, File outputFolder) throws Exception {
        this.fg = fg;
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        this.outputFolder = outputFolder;
    }


    public void go() throws IOException {

        /*
        Write INFO tab (file?)
         */
        FileWriter fw = new FileWriter(new File(outputFolder, "INFO.tsv"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Version" + "\t" + fg.version + RN);
        bw.write("DatasetName_en" + "\t" + fg.name + RN);
        bw.write("DatasetDescription_en" + "\t" + fg.description + RN);
        bw.flush();
        bw.close();

        /*
        Write CODEBOOK metadata tab (file?)
         */
        fw = new FileWriter(new File(outputFolder, "CODEBOOK.tsv"));
        bw = new BufferedWriter(fw);
        bw.write("id\tdescription_en\tcodesystem\tcode\tdata_type\tdescription_code\tcodelist_ref\tinput_type\tproperties\tparent\tcomments" + RN);
        for (Module m : fg.modules) {
            bw.write(m.name + "\t" + m.name + "\t" + m.codeSystem + "\t" + m.code + "\t" + "ST" + "\t" + m.description + "\t" + "" + "\t" + "multi-select" + "\t" + "{url="+m.iri+"}" + "\t" + "" + "\t" + m.description + RN);
            for (Element e : m.elements) {
                bw.write(e.name + "\t" + e.name + "\t" + e.codeSystem + "\t" + e.code + "\t" + e.valueTypeToArtDecor() + "\t" + e.description + "\t" + (e.isLookup()? e.lookup.srcFile.getName().replace(".txt", "") : "") + "\t" + e.getArtDecorInputType() + "\t" + "{url="+e.iri+"}" + "\t" + m.name + "\t" + e.description + RN);
            }
        }
        bw.flush();
        bw.close();

        /*
        Write each codebook as a tab (file?)
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if(e.isLookup())
                {
                    String fileName = e.lookup.name + ".tsv";
                    fw = new FileWriter(new File(outputFolder, fileName));
                    bw = new BufferedWriter(fw);

                    bw.write("value_en\tdescription_en\tcodesystem\tcode\tdescription_code\tiri" + RN);
                    HashMap<String, Lookup> map = e.lookup.lookups;
                    for(String key : map.keySet())
                    {
                        Lookup l = map.get(key);
                        bw.write(l.value + "\t" + l.value + "\t" + e.description + "\t" + l.codesystem + "\t" + l.code + RN);
                    }

                    bw.flush();
                    bw.close();
                }
            }
        }

    }

}
