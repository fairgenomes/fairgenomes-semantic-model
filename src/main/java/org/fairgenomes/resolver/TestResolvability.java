package org.fairgenomes.resolver;

import org.fairgenomes.generator.datastructures.Lookup;
import org.fairgenomes.generator.datastructures.Ontology;
import org.fairgenomes.generator.datastructures.YamlModel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Set;

/**
 * Helper tool to check the resolvability of IRIs in the FAIR Genomes schema
 */
public class TestResolvability {

    /**
     * Run TestResolvability tool
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        System.out.println("Starting...");
        new TestResolvability().start();
        System.out.println("done!");
    }

    /**
     * Constructor
     */
    public TestResolvability() {
        super();
    }

    /**
     * Get FAIR Genomes schema, try out IRIs of modules, elements and lookups (first 3 of each list)
     * @throws Exception
     */
    public void start() throws Exception {
        YamlModel FG = new org.fairgenomes.generator.GenerateOutputs("fair-genomes.yml").readYamlModel();
        checkOntologies(FG.allModuleOntologies); // OK
        checkOntologies(FG.allElementOntologies); // OK
        checkAllLookups(FG, 3);
    }

    /**
     * Go over model and check all lookup lists
     * @param FG
     * @throws IOException
     */
    public void checkAllLookups(YamlModel FG, int max) throws IOException {
        for(org.fairgenomes.generator.datastructures.Module m : FG.modules){
            for(org.fairgenomes.generator.datastructures.Element e : m.elements)
            {
                if(e.isLookup())
                {
                    checkLookups(e.lookup.lookups.values(), max);
                }
            }
        }
    }

    /**
     * Check resolvability of an ontology list
     * @param ontologySet
     * @throws IOException
     */
    public void checkOntologies(Set<Ontology> ontologySet) throws IOException {
        for(Ontology o : ontologySet) {
            int resolvable = isResolvable(o.iri);
            handleCode(o.iri, resolvable);
        }
    }

    /**
     * Check resolvability of lookups
     * @param lookups
     * @throws IOException
     */
    public void checkLookups(Collection<Lookup> lookups, int max) throws IOException {
        int count = 0;
        for(Lookup l : lookups) {
            if(count == max){
                break;
            }
            int resolvable = isResolvable(l.iri);
            handleCode(l.iri, resolvable);
            count++;
        }
    }

    /**
     * Print messages based on response codes
     * @param URL
     * @param code
     */
    public void handleCode(String URL, int code)
    {
        if(code < 300) {
            System.out.println(URL + " code " + code + " OK");
        } else if(code < 400){
            System.out.println("* " + URL + " code " + code + " probably OK, but might redirect to error page! *");
        } else {
            System.out.println("*** " + URL + " is NOT resolvable, error code "+code+" ***");
        }
    }

    /**
     * Test if URL is resolvable
     * Credits https://mkyong.com/java/java-httpurlconnection-follow-redirect-example/
     */
    public int isResolvable(String URL) throws IOException {
        try{
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            connection.addRequestProperty("User-Agent", "Mozilla");
            connection.addRequestProperty("Referer", "google.com");
            connection.connect();
            int responseCode = connection.getResponseCode();

            // redirect
            if(responseCode >= 300 && responseCode < 400)
            {
                System.out.println("REDIRECT " + responseCode);
                String newUrl = connection.getHeaderField("Location");
                String cookies = connection.getHeaderField("Set-Cookie");
                connection = (HttpURLConnection) new URL(newUrl).openConnection();
                connection.setRequestProperty("Cookie", cookies);
                connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                connection.addRequestProperty("User-Agent", "Mozilla");
                connection.addRequestProperty("Referer", "google.com");
                connection.connect();
                responseCode = connection.getResponseCode();
                System.out.println("REDIRECT URL CODE IS NOW " + responseCode);
            }
            return responseCode;
        }catch(Exception e)
        {
            System.out.println("Exception occurred: " + e.getMessage() + ", setting code to 999");
            return 999;
        }
    }
}
