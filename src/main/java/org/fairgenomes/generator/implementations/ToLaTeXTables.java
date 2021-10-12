package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.Module;
import org.fairgenomes.generator.datastructures.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generate LaTeX tables
 */
public class ToLaTeXTables extends AbstractGenerator {

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
        int tableNr = 1;

        FileWriter fw = new FileWriter(new File(outputFolder, "fair-genomes.tex"));
        BufferedWriter bw = new BufferedWriter(fw);

        /**
         * Header
         */
        bw.write("\\documentclass{article}" + LE);
        bw.write("\\usepackage[utf8]{inputenc}" + LE);
        bw.write("\\begin{document}" + LE);
        bw.write("\\setlength\\parindent{0pt}" + LE);
        bw.write(LE);
        bw.write("\\textbf{FAIR Genomes semantic metadata schema}" + LE);
        bw.write("\\newline" + LE);
        bw.write(LE);
        bw.write(fg.description + " Version "+fg.version + "-" + fg.releaseType + ", "+fg.date+". This model consists of " + fg.modules.size() + " modules that contain " + totalNrOfElements + " metadata elements and " + fg.totalNrOfLookupsWithoutGlobals + " lookups in total (excluding null flavors)." + LE);
        bw.write(LE);
        /**
         * Modules
         */
        bw.write("\\begin{table}[htb]"+ LE);
        bw.write("\\begin{tabular}{lll}"+ LE);
        bw.write("Name & Ontology & Nr. of elements \\\\"+ LE);
        bw.write("\\hline"+ LE);
        for (Module m : fg.modules) {
            bw.write(m.name + " & " + m.codeSystem + ":" + m.code.replace("_", "\\_") + " & " + m.elements.size() + " \\\\" + LE);
        }
        bw.write("\\hline" + LE);
        bw.write("\\end{tabular}" + LE);
        bw.write("\\caption[Module overview]{\\label{table:table" + (tableNr++) + "} FAIR Genomes v"+fg.version + "-" + fg.releaseType + " overview of all modules.}" + LE);
        bw.write("\\end{table}" + LE);
        bw.write(LE);

        /**
         * Elements
         */
        for (Module m : fg.modules) {
            bw.write("\\begin{table}[htb]"+ LE);
            bw.write("\\begin{tabular}{lll}"+ LE);
            bw.write("Name & Ontology & Values \\\\"+ LE);
            bw.write("\\hline"+ LE);
            for (Element e : m.elements) {
                bw.write(e.name + " & " + e.parsedOntology.codeSystem + ":" + e.parsedOntology.code.replace("_", "\\_") + " & " + e.valueTypeToLaTeX() + " \\\\" + LE);
            }
            bw.write("\\hline" + LE);
            bw.write("\\end{tabular}" + LE);
            bw.write("\\caption[Module: " + m.name + "]{\\label{table:table" + (tableNr++) + "} Module: " + m.name + ". " + m.description + " Ontology: " + m.codeSystem + ":" + m.code.replace("_", "\\_") + ". }" + LE);
            bw.write("\\end{table}" + LE);
            bw.write(LE);
        }

        /**
         * Null flavors
         */
        bw.write("\\begin{table}[htb]"+ LE);
        bw.write("\\begin{tabular}{ll}"+ LE);
        bw.write("Value & Ontology \\\\"+ LE);
        bw.write("\\hline"+ LE);
        for(String key: fg.lookupGlobalOptionsInstance.lookups.keySet())
        {
            Lookup nf = fg.lookupGlobalOptionsInstance.lookups.get(key);
            bw.write(key.substring(0,key.indexOf("(")-1) + " & " + nf.codesystem+":"+nf.code + " \\\\" + LE);
        }
        bw.write("\\hline" + LE);
        bw.write("\\end{tabular}" + LE);
        bw.write("\\caption[NullFlavors]{\\label{table:table" + (tableNr++) + "} Overview of null flavors. Each lookup in FAIR Genomes is supplemented with so-called 'null flavors' from HL7. These can be used to indicate precisely why a particular value could not be entered into the system, providing substantially more insight than simply leaving a field empty. }" + LE);
        bw.write("\\end{table}" + LE);
        bw.write(LE);


        bw.write("\\end{document}" + LE);

        bw.flush();
        bw.close();


        /**
         * Printer helper Bash script to make PDF from LaTeX
         */
        FileWriter fw2 = new FileWriter(new File(outputFolder, "toPDF.sh"));
        BufferedWriter bw2 = new BufferedWriter(fw2);
        bw2.write("latex fair-genomes.tex" + LE);
        bw2.write("dvips fair-genomes.dvi" + LE);
        bw2.write("ps2pdf fair-genomes.ps" + LE);
        bw2.write("mv fair-genomes.pdf ../../derived/pdf/" + LE);
        bw2.flush();
        bw2.close();


    }
}
