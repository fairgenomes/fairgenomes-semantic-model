package org.fairgenomes.transformer;

public class Main {

    public static void main(String args[]) throws Exception {
        System.out.println("Starting...");
        long start = System.nanoTime();
        TransformFGToAllFormats s = new TransformFGToAllFormats();
        s.generateResources();
        System.out.println("Done! Completed in " + ((System.nanoTime()-start)/1000000)+"ms.");
    }
}