package org.fairgenomes.generator;

public class Main {

    public static void main(String args[]) throws Exception {
        System.out.println("Starting...");
        long start = System.nanoTime();
        GenerateOutputs s = new GenerateOutputs();
        s.generateResources();
        System.out.println("Done! Completed in " + ((System.nanoTime()-start)/1000000)+"ms.");
    }
}