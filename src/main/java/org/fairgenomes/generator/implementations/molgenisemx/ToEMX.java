package org.fairgenomes.generator.implementations.molgenisemx;

import org.fairgenomes.generator.datastructures.Element;
import org.fairgenomes.generator.datastructures.FAIRGenomes;
import org.fairgenomes.generator.datastructures.Module;
import org.fairgenomes.generator.datastructures.ValueType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ToEMX {


    FAIRGenomes fg;
    File outputFolder;
    static final String RN = "\r\n";
    static final String PACKAGE_NAME = "fair-genomes";

    public ToEMX(FAIRGenomes fg, File outputFolder) throws Exception {
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
        MOLGENIS Commander installation script.
        Start here, keep adding stuff as we iterate over the data, and close at the very end.
         */
        FileWriter MCMDfw = new FileWriter(new File(outputFolder, "setup.sh"));
        BufferedWriter MCMDbw = new BufferedWriter(MCMDfw);

        /*
        Write package info
         */
        FileWriter fw = new FileWriter(new File(outputFolder, "sys_md_Package.tsv"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("id\tlabel\tdescription" + RN);
        bw.write(PACKAGE_NAME + "\t" + fg.name + "\t" + fg.description + (fg.description.endsWith(".")?"":".") + " Version " + fg.version + " (" + fg.date + ")" + RN);
        bw.flush();
        bw.close();
        MCMDbw.write("mcmd import -p sys_md_Package.tsv" + RN);

        /*
        Write attribute metadata for lookups
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if(e.valueTypeEnum.equals(ValueType.Lookup))
                {
                    String fileName = m.technicalName + "_lookup_" + e.technicalName + "_attributes.tsv";
                    fw = new FileWriter(new File(outputFolder, fileName));
                    bw = new BufferedWriter(fw);
                    bw.write("name\tentity\tlabel\tdataType\tidAttribute\tnillable" + RN);
                    bw.write("value\t" + m.technicalName + "\t" + "Value (English)" + "\t" + "String" + "\t" + "TRUE" + "\t" + "FALSE" + RN);
                    bw.write("description\t" + m.technicalName + "\t" + "Description (English)\t" + "\t" + "Text" + "\t" + "FALSE" + "\t" + "TRUE" + RN);
                    bw.write("codesystem\t" + m.technicalName + "\t" + "The code system (e.g. ontology) this term belongs to" + "\t" + "String" + "\t" + "FALSE" + "\t" + "TRUE" + RN);
                    bw.write("code\t" + m.technicalName + "\t" + "The code within the code system" + "\t" + "String" + "\t" + "FALSE" + "\t" + "TRUE" + RN);
                    bw.write("iri\t" + m.technicalName + "\t" + "The Internationalized Resource Identifier for this term" + "\t" + "String" + "\t" + "FALSE" + "\t" + "TRUE" + RN);
                    bw.flush();
                    bw.close();
                    MCMDbw.write("mcmd import -p " + fileName + " --as attributes --in " + PACKAGE_NAME + RN);
                }
            }
        }

        /*
        Write the actual lookups
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if (e.valueTypeEnum.equals(ValueType.Lookup)) {

                    //copy and add NF
                    File targetFile = new File(outputFolder,m.technicalName + "_lookup_" + e.technicalName + ".tsv");

                    Path copied = Paths.get(targetFile.getAbsolutePath());
                    Path originalPath = e.lookup.srcFile.toPath();
                    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);

                    // work in progress
                    //MCMDbw.write("mcmd import -p $1_with_nf.tsv --as fair-genomes_$1 --in " + PACKAGE_NAME + RN);
                }
            }
        }


        /*
        Flush and close MOLGENIS Commander script
         */
        MCMDbw.flush();
        MCMDbw.close();

    }

}
