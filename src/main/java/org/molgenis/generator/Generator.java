package org.molgenis.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.molgenis.generator.datastructures.FAIRGenomes;
import org.molgenis.generator.implementations.markdown.ToMD;

import java.io.*;

public class Generator {

    private File inputF;
   // private File outputF;

    public Generator()
    {
        this.inputF = new File("fair-genomes.yml");
    }

    public Generator(File inputF, File outputF) {
        this.inputF = inputF;
     //   this.outputF = outputF;
    }

    public void parse() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        FAIRGenomes fg = mapper.readValue(inputF, FAIRGenomes.class);
        fg.loadLookupGlobalOptions();
        fg.parseElementValueTypes();
        fg.loadElementLookups();

        /*
        Generate other representations
         */
        new ToMD(fg, new File("generated/markdown")).go();

    }
}
