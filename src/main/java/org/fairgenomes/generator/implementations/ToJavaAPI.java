package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.Element;
import org.fairgenomes.generator.datastructures.YamlModel;
import org.fairgenomes.generator.datastructures.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToJavaAPI extends AbstractGenerator{

    public ToJavaAPI(YamlModel fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception {

        String pkg = fg.fileName.replace("-","");

        File mainPkg = new File(outputFolder, "org/"+pkg+"/generated");
        Path path = Paths.get(mainPkg.getAbsolutePath());
        Files.createDirectories(path);



        FileWriter fw = new FileWriter(new File(mainPkg, cap(pkg) + "Root.java"));
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("package org."+pkg+".generated;" + LE);
        bw.write(LE);
        bw.write("import java.util.Map;" + LE);
        bw.write(LE);
        bw.write("public class "+cap(pkg)+"Root {" + LE);

        for (Module m : fg.modules) {

            bw.write("\tpublic Map<String, " + cap(m.technicalName) + "> " + cap(m.technicalName) + ";" + LE);

            FileWriter modFw = new FileWriter(new File(mainPkg, cap(m.technicalName) + ".java"));
            BufferedWriter modBw = new BufferedWriter(modFw);

            modBw.write("package org."+pkg+".generated;" + LE);
            modBw.write(LE);
            modBw.write("import java.util.List;" + LE);
            modBw.write(LE);
            modBw.write("public class "+cap(m.technicalName)+" {" + LE);

            for(Element e : m.elements){

//                // write lookups to enum
//                if(e.isLookup())
//                {
//                    FileWriter enumFw = new FileWriter(new File(lookupsPkg, cap(e.lookup.technicalName)+ ".java"));
//                    BufferedWriter enumBw = new BufferedWriter(enumFw);
//
//                    enumBw.write("package org.fairgenomes.generated.lookups;" + LE);
//                    enumBw.write(LE);
//                    enumBw.write("enum "+cap(e.lookup.technicalName)+" {" + LE);
//                    for(String key : e.lookup.lookups.keySet())
//                    {
//                        enumBw.write("\t" + key + ",");
//                    }
//                    enumBw.write("}" + LE);
//                    enumBw.flush();
//                    enumBw.close();
//
//                }

                modBw.write("\tpublic " + e.valueTypeToJava(e.isReference() ? cap(YamlModel.toTechName(e.referenceTo)) : null) + " " + e.technicalName + ";" + LE);
            }

            modBw.write("}" + LE);
            modBw.flush();
            modBw.close();

        }

        bw.write("}" + LE);
        bw.flush();
        bw.close();

    }

    public String cap(String in)
    {
        return in.substring(0, 1).toUpperCase() + in.substring(1);
    }
}
