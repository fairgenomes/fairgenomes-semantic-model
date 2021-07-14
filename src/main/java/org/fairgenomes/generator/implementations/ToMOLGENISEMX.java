package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.Module;
import org.fairgenomes.generator.datastructures.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Generate MOLGENIS-EMX database template ready for import
 */
public class ToMOLGENISEMX extends AbstractGenerator {

    public static final String PACKAGE_NAME = "fair-genomes";

    public ToMOLGENISEMX(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {

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
        bw.write("id\tlabel\tdescription" + LE);
        bw.write(PACKAGE_NAME + "\t" + fg.name + "\t" + fg.description + (fg.description.endsWith(".")?"":".") + " Version " + fg.version +  "-" + fg.releaseType + " (" + fg.date + ")" + LE);
        bw.flush();
        bw.close();
        MCMDbw.write("mcmd import -p sys_md_Package.tsv" + LE);

        /*
        Write attribute metadata for lookups
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if(e.isLookup())
                {
                    String entityName = m.technicalName + "_" + e.technicalName;
                    String fileName = entityName + "_attributes.tsv";
                    fw = new FileWriter(new File(outputFolder, fileName));
                    bw = new BufferedWriter(fw);
                    bw.write("name\tentity\tlabel\tdataType\tidAttribute\tnillable" + LE);
                    bw.write("value\t" + entityName + "\t" + "Value (English)" + "\t" + "String" + "\t" + "TRUE" + "\t" + "FALSE" + LE);
                    bw.write("description\t" + entityName + "\t" + "Description (English)" + "\t" + "Text" + "\t" + "FALSE" + "\t" + "TRUE" + LE);
                    bw.write("codesystem\t" + entityName + "\t" + "The code system (e.g. ontology) this term belongs to" + "\t" + "String" + "\t" + "FALSE" + "\t" + "TRUE" + LE);
                    bw.write("code\t" + entityName + "\t" + "The code within the code system" + "\t" + "String" + "\t" + "FALSE" + "\t" + "TRUE" + LE);
                    bw.write("iri\t" + entityName + "\t" + "The Internationalized Resource Identifier for this term" + "\t" + "String" + "\t" + "FALSE" + "\t" + "TRUE" + LE);
                    bw.flush();
                    bw.close();
                    MCMDbw.write("mcmd import -p " + fileName + " --as attributes --in " + PACKAGE_NAME + LE);
                }
            }
        }

        /*
        Write the actual lookups
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if (e.isLookup()) {

                    String entityName = m.technicalName + "_" + e.technicalName;
                    File targetFile = new File(outputFolder, entityName + ".tsv");

                    fw = new FileWriter(targetFile);
                    bw = new BufferedWriter(fw);

                    bw.write("value\tdescription\tcodesystem\tcode\tiri" + LE);

                    HashMap<String, Lookup> ll = e.lookup.lookups;
                    for (String key : ll.keySet()) {
                        Lookup l = ll.get(key);
                        bw.write(l.value + "\t" + l.description + "\t" + l.codesystem + "\t" + l.code + "\t" + l.iri + LE);
                    }

                    bw.flush();
                    bw.close();

                    MCMDbw.write("mcmd import -p " + targetFile.getName() + " --as fair-genomes_" + entityName + " --in " + PACKAGE_NAME + LE);

                }
            }
        }

        /*
        Write model attributes for the modules (the actual tables that people use to enter data)
         */
        for (Module m : fg.modules) {
            String entityName = m.technicalName;
            String fileName = entityName + "_attributes.tsv";
            fw = new FileWriter(new File(outputFolder, fileName));
            bw = new BufferedWriter(fw);
            bw.write("name\tlabel\tdescription\tentity\tdataType\tidAttribute\tlabelAttribute\tvisible\tnillable\trefEntity" + LE);

            for (Element e : m.elements) {
                if(e.valueTypeEnum.equals(ValueType.UniqueID))
                {
                    bw.write(e.technicalName+"\t"+e.name+"\t"+e.description + " ("+e.codeSystem+":"+e.code+")" + "\t"+entityName+"\t"+e.valueTypeToEMX()+"\t"+"TRUE"+"\t"+"TRUE"+"\t"+"TRUE"+"\t"+"FALSE"+"\t"+e.lookupOrReferencetoEMX()+ LE);
                }
                else{
                    bw.write(e.technicalName+"\t"+e.name+"\t"+e.description+ " ("+e.codeSystem+":"+e.code+")" + "\t"+entityName+"\t"+e.valueTypeToEMX()+"\t"+"FALSE"+"\t"+"FALSE"+"\t"+"TRUE"+"\t"+"TRUE"+"\t"+e.lookupOrReferencetoEMX()+ LE);
                }
            }
            bw.flush();
            bw.close();
            MCMDbw.write("mcmd import -p " + fileName + " --as attributes --in " + PACKAGE_NAME + LE);
        }

        /*
        Landing page
         */
        MCMDbw.write("mcmd import -p ../../misc/molgenis/other/sys_StaticContent.tsv -a add_update_existing" + LE);
        String[] imgs = new String[]{"analysis", "lookups", "clinical", "leafletandconsentform", "individualconsent", "contribute", "info", "material", "personal", "samplepreparation", "sequencing", "study", "fair_genomes_logo_notext", "fair_genomes_logo_white"};
        for(String img : imgs)
        {
            MCMDbw.write("mcmd add logo -p ../../misc/molgenis/img/"+img+".png" + LE);
        }

        /*
        Demo permissions
         */
        MCMDbw.write("mcmd make --role ANONYMOUS fair-genomes_EDITOR" + LE);
        MCMDbw.write("mcmd give anonymous view sys_md" + LE);


        /*
        Flush and close MOLGENIS Commander script
         */
        MCMDbw.flush();
        MCMDbw.close();

    }

}
