package org.fairgenomes.ecosystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.fairgenomes.ecosystem.datastructures.AccessType;
import org.fairgenomes.ecosystem.datastructures.Adapter;
import org.fairgenomes.ecosystem.datastructures.Source;
import org.fairgenomes.ecosystem.reader.APIConnector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Parse adapter definitions, extract the data, apply mapping,
 * and write results to EDC importable files.
 *
 * TODO:
 * validate mapping targets against current FAIR Genomes schema
 * map actual results back to FAIR Genomes schema via Java API
 * write the results to a proper importable format (e.g. EMX2)
 * replace baseDir location with parameter that works outside IDE
 *
 * QUESTIONS:
 * handle the mapping per source, or overall?
 * how to deal with different JSON structures?
 *
 * @throws IOException
 */
public class HarvestData {

    private static final String baseDir = "src/main/java/org/fairgenomes/ecosystem/adapter/";

    /**
     * Constructor
     */
    public HarvestData()
    {
        super();
    }

    /**
     * Parse all adapters in directory, run connectors, extract data
     * @throws IOException
     */
    public void start() throws Exception
    {
        ObjectMapper adapterOM = new ObjectMapper(new YAMLFactory());
        adapterOM.findAndRegisterModules();

        for(File aFile : getAllAdapters(baseDir))
        {
            Adapter a = adapterOM.readValue(aFile, Adapter.class);
            for(Source s : a.source)
            {
                if(s.accessType.equals(AccessType.API))
                {
                    APIConnector ac = new APIConnector(s.uri);
                    List<HashMap<String,Object>> values = ac.findValuesFor(a.mapping);
                    debugPrintVals(values);
                }
            }
        }
        System.out.println("done!");
    }

    /**
     * Helper function to print preliminary results
     * @param values
     */
    public void debugPrintVals(List<HashMap<String,Object>> values)
    {
        for(HashMap<String,Object> hm : values)
        {
            for(String key : hm.keySet())
            {
                System.out.println(key + " -> " + hm.get(key));
            }
            System.out.println("---");
        }
    }

    /**
     * Helper function to get all adapter YAML files
     * @param baseDir
     * @return
     */
    public List<File> getAllAdapters(String baseDir) throws Exception {
        ArrayList<File> result = new ArrayList<>();
        File dir = new File(baseDir);
        for(File f : dir.listFiles())
        {
            if(f.getName().endsWith("yml")){
                result.add(f);
            }
        }
        if(result.size() == 0)
        {
            throw new Exception("No adapters found");
        }
        return result;
    }

    /**
     * Main
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception
    {
        HarvestData hd = new HarvestData();
        hd.start();
    }

}
