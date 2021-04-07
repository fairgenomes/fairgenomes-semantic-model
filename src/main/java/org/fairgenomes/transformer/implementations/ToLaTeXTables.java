package org.fairgenomes.transformer.implementations;

import org.fairgenomes.transformer.datastructures.Module;
import org.fairgenomes.transformer.datastructures.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Transform to LaTeX tables
 */
public class ToLaTeXTables extends GenericTransformer {

    static final int DESCRIPTION_LIMIT = 1000;

    public ToLaTeXTables(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {
        int totalNrOfElements = 0;
        for (Module m : fg.modules) {
            totalNrOfElements += m.elements.size();
        }

        FileWriter fw = new FileWriter(new File(outputFolder, "fairgenomes.tex"));
        BufferedWriter bw = new BufferedWriter(fw);

        /**
         * Header
         */
        bw.write("\\documentclass{article}" + LE);
        bw.write("\\usepackage[utf8]{inputenc}" + LE);
        bw.write("\\begin{document}" + LE);
        bw.write("\\setlength\\parindent{0pt}" + LE);

        bw.write("\\textbf{FAIR Genomes semantic metadata schema}" + LE);
        bw.write("\\newline" + LE);
        bw.write("\\newline" + LE);

        bw.write(fg.description + " Version "+fg.version + "-" + fg.releaseType + ", "+fg.date+". This model consists of " + fg.modules.size() + " modules that contain " + totalNrOfElements + " metadata elements in total." + LE);
        bw.write("\\newline" + LE);
        bw.write("\\newline" + LE);

        /**
         * Modules
         */
        bw.write("\\textbf{Module overview}" + LE);
        bw.write("\\begin{table}[htb]"+ LE);
        bw.write("\\begin{tabular}{lll}"+ LE);
        bw.write("Name & Ontology & Nr. of elements \\\\"+ LE);
        bw.write("\\hline"+ LE);
        for (Module m : fg.modules) {
            bw.write(m.name + " & " + m.codeSystem + ":" + m.code.replace("_", "\\_") + " & " + m.elements.size() + " \\\\" + LE);
        }
        bw.write("\\hline" + LE);
        bw.write("\\end{tabular}" + LE);
        bw.write("\\caption[modules]{\\label{table:table1} modules in fair genomes}" + LE);
        bw.write("\\end{table}" + LE);

        /**
         * Elements
         */
        for (Module m : fg.modules) {
            bw.write("\\begin{table}[htb]"+ LE);
            bw.write("\\begin{tabular}{lll}"+ LE);
            bw.write("Name & Ontology & Values \\\\"+ LE);
            bw.write("\\hline"+ LE);
            for (Element e : m.elements) {
                bw.write(e.name + " & " + e.codeSystem + ":" + e.code.replace("_", "\\_") + " & " + "todo" + " \\\\" + LE);
            }
            bw.write("\\hline" + LE);
            bw.write("\\end{tabular}" + LE);
            bw.write("\\caption[modules]{\\label{table:table1} Module: "+m.name + ". " + m.description + " Ontology: " + m.codeSystem + ":" + m.code.replace("_", "\\_") + ". }" + LE);
            bw.write("\\end{table}" + LE);

        }



        bw.write("\\end{document}" + LE);

        bw.flush();
        bw.close();



    }
}
