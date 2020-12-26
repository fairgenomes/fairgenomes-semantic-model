package org.molgenis.generator;

public class Main {

    public static void main(String args[]) throws Exception {
//        if(args.length != 2)
//        {
//            System.out.println("Please supply these 2 arguments:");
//            System.out.println("- Input file location of YAML");
//            System.out.println("- Output file location. May not exist yet.");
//            System.exit(0);
//        }
//
//        File inputF = new File(args[0]);
//        if(!inputF.exists())
//        {
//            System.out.println("Input YAML file not found at " + inputF.getAbsolutePath() + ".");
//            System.exit(0);
//        }
//
//        File outputF = new File(args[1]);
//        if(outputF.exists())
//        {
//            System.out.println("Output file already exists at " + outputF.getAbsolutePath()+". Please delete it first, or supply a different output file name.");
//            System.exit(0);
//        }
//
        System.out.println("Arguments OK. Starting...");
        long start = System.nanoTime();

        //Generator s = new Generator(inputF, outputF);
        Generator s = new Generator();
        s.parse();

        System.out.println("...completed in " + ((System.nanoTime()-start)/1000000)+"ms.");

    }
}