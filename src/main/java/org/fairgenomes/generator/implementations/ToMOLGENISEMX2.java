package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.Module;
import org.fairgenomes.generator.datastructures.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Generate MOLGENIS-EMX2 database template ready for import into molgenis-emx2, see https://github.com/molgenis/molgenis-emx2
 */
public class ToMOLGENISEMX2 extends AbstractGenerator {

    public ToMOLGENISEMX2(YamlModel fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws IOException {

        String fileName = "molgenis.csv";
        FileWriter fw = new FileWriter(new File(outputFolder, fileName));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("tableName,tableExtends,columnName,columnType,key,required,refSchema,refTable,refLink,refBack,validation,semantics,description,origin" + LE);

        /*
        Write model attributes for the modules (the actual tables that people use to enter data)
         */
        for (Module m : fg.modules) {

            String entityName = m.technicalName;
            bw.write("\"" +entityName + "\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"" + m.parsedOntology.iri + "\",\"" + m.description + "\",\"" + "FAIR Genomes" + "\"" + LE);

            for (Element e : m.elements) {
                String key = e.valueTypeEnum.equals(ValueType.UniqueID) ? "1" : "";
                String required = e.valueTypeEnum.equals(ValueType.UniqueID) ? "TRUE" : "";
                bw.write("\"" + entityName + "\",\"\",\"" + e.technicalName + "\",\"" + e.valueTypeToEMX2() + "\",\"" + key + "\",\"" + required + "\",\"\",\"" + e.lookupOrReferencetoEMX2() + "\",\"\",\"\",\"\",\"" + e.parsedOntology.iri + "\",\"" + e.description + "\",\"" + "FAIR Genomes" + "\"" + LE);
            }

            // find modules that have references to this one to make ref-backs
            List<Element> refBackFields = new ArrayList<>();
            for (Module m2 : fg.modules) {
                if(m2.technicalName.equals(m.technicalName)){
                    continue;
                }
                for (Element e2 : m2.elements) {
                    // NOTE: here name, not technical name to reference things in the YAML schema
                    if (e2.isReference() && e2.referenceTo.equals(m.name)) {
                        refBackFields.add(e2);
                    }
                }
            }
            for(Element e : refBackFields){
                String key = e.valueTypeEnum.equals(ValueType.UniqueID) ? "1" : "";
                String required = e.valueTypeEnum.equals(ValueType.UniqueID) ? "TRUE" : "";
                bw.write("\"" + entityName + "\",\"\",\"" + e.fromModule.technicalName + "_" + e.technicalName + "\",\"" + "refback" + "\",\"" + key + "\",\"" + required + "\",\"\",\"" + e.fromModule.technicalName + "\",\"\",\""+e.technicalName+"\",\"\",\"" + e.parsedOntology.iri + "\",\"" + e.description + "\",\"" + "FAIR Genomes" + "\"" + LE);
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

                    bw.write("name,definition,codesystem,code,ontologyTermURI" + LE);

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

        /*
        Semantics for ontology tables
         */
        File targetFile = new File(outputFolder, "SEMANTICS.csv");
        fw = new FileWriter(targetFile);
        bw = new BufferedWriter(fw);
        bw.write("tableName,tableType,semantics" + LE);
        for (Module m : fg.modules) {
            for (Element e : m.elements) {
                if (e.isLookup()) {
                    String tableName = e.technicalName;
                    bw.write("\"" + tableName + "\",\"" + "ONTOLOGIES" + "\",\"" + e.type + "\"" + LE);
                }
            }
        }
        bw.flush();
        bw.close();

    }
}
