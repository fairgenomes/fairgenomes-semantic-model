package org.fairgenomes.comparator;

import org.fairgenomes.generator.datastructures.YamlModel;

public class CompareSchemas {


    public CompareSchemas(){ super(); }

    public void compareSchemas() throws Exception {
        System.out.println("Comparing schemas..");

        YamlModel fairgenomes = new org.fairgenomes.generator.GenerateOutputs("fair-genomes.yml").readYamlModel();
        YamlModel palga = new org.fairgenomes.generator.GenerateOutputs("extensions/palga-protocol-molecular-testing.yml").readYamlModel();
        YamlModel intersect = fairgenomes.intersectWith(palga);
    }

}
