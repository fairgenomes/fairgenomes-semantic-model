package org.fairgenomes.transformer.implementations;

import org.fairgenomes.transformer.datastructures.*;
import org.fairgenomes.transformer.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * Write PALGA Codebooks in TSV format
 *
 */
public class ToPALGACodeBook extends GenericTransformer {

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
        bw.write("version" + "\t" + "0" + LE); //FIXME only supports numbers, so fg.version doesn't always work
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
            bw.write(m.name + "\t" + m.name + "\t" + m.codeSystem + "\t" + m.code + "\t" + "ST" + "\t" + m.description + "\t" + "" + "\t" + "multi-select" + "\t" + "{url="+m.iri+"}" + "\t" + "" + "\t" + m.description + LE); //if root, 'FAIR-Genomes' as parent
            for (Element e : m.elements) {
                bw.write(e.name + "\t" + e.name + "\t" + e.codeSystem + "\t" + e.code + "\t" + e.valueTypeToArtDecor() + "\t" + e.description + "\t" + (e.isLookup()? e.lookup.srcFile.getName().replace(".txt", "") : "") + "\t" + e.getArtDecorInputType() + "\t" + "{url="+e.iri+"}" + "\t" + m.name + "\t" + e.description + LE);
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
                        bw.write(l.value + "\t" + l.description + "\t" + l.codesystem + "\t" + l.code + "\t" + l.value + LE);
                    }

                    /*
                    If NoGlobals are not selected, add the global lookup options
                     */
                    if(!(e.valueTypeEnum.equals(ValueType.LookupOne_NoGlobals) || e.valueTypeEnum.equals(ValueType.LookupMany_NoGlobals)))
                    {
                        HashMap<String, Lookup> ll = fg.lookupGlobalOptionsInstance.lookups;
                        for(String key: ll.keySet())
                        {
                            Lookup l = ll.get(key);
                            bw.write(l.value + "\t" + l.description + "\t" + l.codesystem + "\t" + l.code + "\t" + l.iri + LE);
                        }
                    }

                    bw.flush();
                    bw.close();

                }
            }
        }

    }

}
