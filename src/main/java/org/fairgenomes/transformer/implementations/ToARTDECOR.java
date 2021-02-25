package org.fairgenomes.transformer.implementations;

import org.fairgenomes.transformer.datastructures.FAIRGenomes;
import org.fairgenomes.transformer.datastructures.GenericTransformer;
import palgacodebooktoartdecor.codebook.CodebookManager;
import palgacodebooktoartdecor.codebook.CodebookToArtDecorConvertor;
import palgacodebooktoartdecor.settings.IdentifierManager;
import palgacodebooktoartdecor.settings.RunParameters;

import java.io.File;

public class ToARTDECOR extends GenericTransformer {

    public ToARTDECOR(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception {

        RunParameters runParameters = new RunParameters(
                "transformation-output/palga-codebook",                           // codebookDirectory
                "2.16.840.1.113883.2.4.3.11.60.120",                              // projectId
                "FG",                                                             // projectPrefix
                "true",                                                           // experimental
                "fairgenomes;consortium@fairgenomes.org;FAIR Genomes Consortium", // authorString
                "The FAIR Genomes Consortium;2019-2021;author",                   // copyrightString
                "draft");                                                         // statusCode
        File output = new File(outputFolder, "fair-genomes_en-US.xml");
        IdentifierManager.createIdentifierManager(runParameters);
        runParameters.addLanguageSettings("en", fg.description, fg.name);
        CodebookManager codebookManager = CodebookManager.readCodebooksTSV(runParameters);
        CodebookToArtDecorConvertor codebookToArtDecorConvertor = new CodebookToArtDecorConvertor(codebookManager, runParameters);
        codebookToArtDecorConvertor.transformCodebooks();
        codebookToArtDecorConvertor.writeOutput(output.getAbsolutePath());
    }
}
