package org.fairgenomes.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.fairgenomes.generator.datastructures.FAIRGenomes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class DEBCentral {

    URL url = new URL("https://www.deb-central.org/api/v2/Patients");

    public DEBCentral() throws IOException {

        // FG model
        File inputF = new File("fair-genomes.yml");
        ObjectMapper fgMapper = new ObjectMapper(new YAMLFactory());
        fgMapper.findAndRegisterModules();
        FAIRGenomes fg = fgMapper.readValue(inputF, FAIRGenomes.class);

        // TODO
        //

        //fg.moduleMap.get("personal").elementMap.get("personalidentifier").

        ObjectMapper mapper = new ObjectMapper(); // just need one
        // Got a Java class that data maps to nicely? If so:
        //FacebookGraph graph = mapper.readValue(url, FaceBookGraph.class);
        // Or: if no class (and don't need one), just map to Map.class:
        Map<String,Object> map = mapper.readValue(url, Map.class);

        // OK:
        //System.out.println(map.toString());
        //System.out.println(map.get("items"));

        List<Map<String,Object>> items = (List<Map<String,Object>>) map.get("items");
        System.out.println(items.get(0).get("Patient_ID"));

//
//        FAIRGenomesRoot fgr = new FAIRGenomesRoot();
//        fgr.Personal.put("id", new Personal());
//        fgr.Personal.get("id").iets;
//        Personal p = new Personal();
//        p.setID(IDfromDEB);

    }

    public static void main(String args[]) throws Exception {

        new DEBCentral();
    }



}
