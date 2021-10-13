package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.*;
import org.fairgenomes.generator.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * Generate PALGA Codebooks in TSV format
 *
 */
public class ToPALGACodeBook extends AbstractGenerator {

    public ToPALGACodeBook(FAIRGenomes fg, File outputFolder) throws Exception {
       super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {

        /*
        Write INFO metadata as TSV
         */
        FileWriter fw = new FileWriter(new File(outputFolder, "INFO.tsv"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("version" + "\t" + fg.version + LE);
        bw.write("effectivedate" + "\t" + fg.date + LE);
        bw.write("DatasetName_en" + "\t" + fg.name + LE);
        bw.write("DatasetDescription_en" + "\t" + fg.description + LE);
        bw.flush();
        bw.close();

        /*
        Write CODEBOOK metadata as TSV
         */
        fw = new FileWriter(new File(outputFolder, "CODEBOOK.tsv"));
        bw = new BufferedWriter(fw);
        bw.write("id\tdescription_en\tcodesystem\tcode\tdata_type\tdescription_code\tcodelist_ref\tinput_type\tproperties\tparent\tcomments" + LE);

        for (Module m : fg.modules) {
            bw.write(m.name + "\t" + m.description + "\t" + m.parsedOntology.codeSystem + "\t" + m.parsedOntology.code + "\t" + "ST" + "\t" + m.name + "\t" + "" + "\t" + "multi-select" + "\t" + "{url="+m.parsedOntology.iri+"}" + "\t" + "" + "\t" + "" + LE);
            for (Element e : m.elements) {
                bw.write(e.name + "\t" + e.description + "\t" + e.parsedOntology.codeSystem + "\t" + e.parsedOntology.code + "\t" + e.valueTypeToArtDecor() + "\t" + e.name + "\t" + (e.isLookup()? e.lookup.srcFile.getName().replace(".txt", "") : "") + "\t" + e.getArtDecorInputType() + "\t" + "{url="+e.parsedOntology.iri+"}" + "\t" + m.name + "\t" + "" + LE);
            }
        }
        bw.flush();
        bw.close();

        /*
        Write each codebook as TSV
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {

                if(e.isLookup())
                {
                    String fileName = e.lookup.name + ".tsv";
                    fw = new FileWriter(new File(outputFolder, fileName));
                    bw = new BufferedWriter(fw);

                    bw.write("value_en\tdescription_en\tcodesystem\tcode\tdescription_code" + LE);
                    HashMap<String, Lookup> map = e.lookup.lookups;
                    for(String key : map.keySet())
                    {
                        Lookup l = map.get(key);
                        bw.write(l.value + "\t" + l.description + "\t" + l.codesystem + "\t" + l.iri + "\t" + l.value + LE);
                    }

                    bw.flush();
                    bw.close();

                }
            }
        }

    }

}
