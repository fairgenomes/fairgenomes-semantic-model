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
 * Generate MOLGENIS-EMX2 database template ready for import into molgenis-emx2, see https://github.com/molgenis/molgenis-emx2
 */
public class ToMOLGENISEMX2 extends AbstractGenerator {

    public ToMOLGENISEMX2(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {

        String fileName = "molgenis.csv";
        FileWriter fw = new FileWriter(new File(outputFolder, fileName));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("tableName,columnName,columnType,key,required,refTable,description,semantics" + LE);


        //TODO
        //bw.write(PACKAGE_NAME + "," + fg.name + "," + fg.description + (fg.description.endsWith(".")?"":".") + " Version " + fg.version +  "-" + fg.releaseType + " (" + fg.date + ")" + LE);


                /*
        Write attribute metadata for lookups
        We could use one superclass of this if you like??
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {

                if (e.isLookup()) {
                    String tableName = e.technicalName;
                    bw.write(tableName + ",,,,,,\"" + m.description + "\"," + m.iri + LE);
                    bw.write(tableName + ",value,String,1,TRUE,,Value (English)" + LE);
                    bw.write(tableName + ",description,Text,,,,Description (English)" + LE);
                    bw.write(tableName + ",codesystem,String,,,,The code system (e.g. ontology) this term belongs to" + LE);
                    bw.write(tableName + ",code,String,,,,The code within the code system" + LE);
                    bw.write(tableName + ",iri,String,,,,The Internationalized Resource Identifier for this term" + LE);

                }
            }
        }

        /*
        Write model attributes for the modules (the actual tables that people use to enter data)
         */
        for (Module m : fg.modules) {

            String entityName = m.technicalName;
            bw.write(entityName + ",,,,,,\"" + m.description + "\"," + m.iri + LE);

            for (Element e : m.elements) {
                String key = e.valueTypeEnum.equals(ValueType.UniqueID) ? "1" : "";
                String required = e.valueTypeEnum.equals(ValueType.UniqueID) ? "TRUE" : "";
                bw.write(entityName + "," + e.technicalName + "," + e.valueTypeToEMX2() + "," + key + "," + required + "," + e.lookupOrReferencetoEMX2() + ",\"" + e.description + "\"," + e.iri + LE);
            }
        }

        //end molgenis.tsv
        bw.flush();
        bw.close();


        /*
        Write the actual lookups
         */
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if (e.isLookup()) {

                    String tableName = e.technicalName;
                    File targetFile = new File(outputFolder, tableName + ".csv");

                    fw = new FileWriter(targetFile);
                    bw = new BufferedWriter(fw);

                    bw.write("value,description,codesystem,code,iri" + LE);

                    HashMap<String, Lookup> ll = e.lookup.lookups;
                    for (String key : ll.keySet()) {
                        Lookup l = ll.get(key);
                        bw.write("\"" + l.value + "\",\"" + l.description + "\",\"" + l.codesystem + "\",\"" + l.code + "\",\"" + l.iri + "\"" + LE);
                    }

                    bw.flush();
                    bw.close();
                }
            }
        }

// TODO add home page and permission
//        /*
//        Landing page
//         */
//        String[] imgs = new String[]{"analysis", "lookups", "clinical", "leafletandconsentform", "individualconsent", "contribute", "info", "material", "personal", "samplepreparation", "sequencing", "study", "fair_genomes_logo_notext", "fair_genomes_logo"};
//        for(String img : imgs)
//        {
//            MCMDbw.write("mcmd add logo -p ../../misc/molgenis/img/"+img+".png" + LE);
//        }

//        /*
//        Demo permissions
//         */
//        MCMDbw.write("mcmd make --role ANONYMOUS fair-genomes_EDITOR" + LE);
//        MCMDbw.write("mcmd give anonymous view sys_md" + LE);


    }

}
