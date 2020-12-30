package org.fairgenomes.generator.implementations.artdecor;

import org.fairgenomes.generator.datastructures.Element;
import org.fairgenomes.generator.datastructures.FAIRGenomes;
import org.fairgenomes.generator.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            throw new Exception("outputFolder " + outputFolder.getAbsolutePath() + " does not exist");
        }
        if (!outputFolder.isDirectory()) {
            throw new Exception("outputFolder " + outputFolder.getAbsolutePath() + " is not a directory");
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
        Write CODEBOOK tab (file?)
         */
        fw = new FileWriter(new File(outputFolder, "CODEBOOK.tsv"));
        bw = new BufferedWriter(fw);
        bw.write("id\tdescription_en\tcodesystem\tcode\tdata_type\tdescription_code\tcodelist_ref\tinput_type\tproperties\tparent\tcomments" + RN);

        for (Module m : fg.modules) {
            bw.write(m.name + "\t" + m.name + "\t" + m.codeSystem + "\t" + m.code + "\t" + "TODO" + "\t" + m.description + "\t" + "TODO" + "\t" + "." + "\t" + "{url="+m.iri+"}" + "\t" + "." + RN) ;
            for (Element e : m.elements) {

            }
        }

        bw.flush();
        bw.close();

    }

}
