package org.molgenis.generator.datastructures;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class LookupList {

    public String fileName;
    public HashMap<String, Lookup> lookups;
    private static final String HEADER = "value\tdescription\tcodesystem\tcode\tiri";

    /**
     * Checks:
     * - value_en is unique in file
     * @param lookupListFile
     */
    public LookupList(File lookupListFile) throws Exception {
        this.fileName = lookupListFile.getName();
        lookups = new HashMap<>();

        Scanner s = new Scanner(lookupListFile);
        boolean firstLine = true;
        while(s.hasNextLine())
        {
            if(firstLine)
            {
                String firstLineStr = s.nextLine();
                if(!firstLineStr.equals(HEADER))
                {
                    System.out.println("Bad lookup file header: " + firstLineStr + "must be: " + HEADER);
                }
                firstLine = false;
                continue;
            }
            String nextLine = s.nextLine();
            Lookup l = new Lookup(nextLine);

            if(lookups.containsKey(l.value))
            {
                throw new Exception("Lookups already contains value: " + l.value);
            }
            lookups.put(l.value, l);
        }

    }
}
