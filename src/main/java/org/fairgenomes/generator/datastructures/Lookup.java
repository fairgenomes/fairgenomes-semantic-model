package org.fairgenomes.generator.datastructures;

public class Lookup {

    public String value;
    public String description;
    public String codesystem;
    public String code;
    public String iri;

    /**
     * Checks:
     * - input lines split by TAB have 6 elements
     * - there are no empty values
     * - iri starts with 'http'
     * - value_en equal to description_en
     * @param lineFromFile
     */
    public Lookup(String lineFromFile) throws Exception {
        String[] s = lineFromFile.split("\t", -1);
        if(s.length != 5)
        {
            throw new Exception("Expected lines with 5 tab-separated elements each");
        }

        this.value = s[0];
        this.description = s[1];
        this.codesystem = s[2];
        this.code = s[3];
        this.iri = s[4];

        /*
        check for empty values
         */
        if(value.isEmpty())
        {
            throw new Exception("value cannot be empty");
        }
        if(description.isEmpty())
        {
            throw new Exception("description cannot be empty");
        }
        if(codesystem.isEmpty())
        {
            throw new Exception("codesystem cannot be empty");
        }
        if(code.isEmpty())
        {
            throw new Exception("code cannot be empty");
        }
        if(iri.isEmpty())
        {
            throw new Exception("iri cannot be empty");
        }

        /*
        check if iri starts with 'http'
         */
        if(!iri.startsWith("http"))
        {
            throw new Exception("iri should start with 'http'");
        }

    }

}
