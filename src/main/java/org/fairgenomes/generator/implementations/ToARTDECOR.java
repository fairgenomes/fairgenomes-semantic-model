package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.Author;
import org.fairgenomes.generator.datastructures.FAIRGenomes;
import palgacodebooktoartdecor.codebook.CodebookManager;
import palgacodebooktoartdecor.codebook.CodebookToArtDecorConvertor;
import palgacodebooktoartdecor.settings.IdentifierManager;
import palgacodebooktoartdecor.settings.RunParameters;

import java.io.File;

public class ToARTDECOR extends AbstractGenerator {

    public ToARTDECOR(FAIRGenomes fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception {

        RunParameters runParameters = new RunParameters(
                "generated/palga-codebook",                           // codebookDirectory
                "2.16.840.1.113883.2.4.3.11.60.120",                              // projectId
                "fairgenomes",                                                    // projectPrefix
                "false",                                                          // experimental
                getAuthors(),                                                     // authorString
                fg.copyright.holder + ";"+fg.copyright.years+";author",           // copyrightString
                "draft");                                                         // statusCode
        File output = new File(outputFolder, "fair-genomes_en-US.xml");
        IdentifierManager.createIdentifierManager(runParameters);
        runParameters.addLanguageSettings("en", fg.description, fg.name);
        runParameters.setDefaultLanguage("en");
        CodebookManager codebookManager = CodebookManager.readCodebooksTSV(runParameters);
        CodebookToArtDecorConvertor codebookToArtDecorConvertor = new CodebookToArtDecorConvertor(codebookManager, runParameters);
        codebookToArtDecorConvertor.transformCodebooks();
        codebookToArtDecorConvertor.writeOutput(output.getAbsolutePath());
    }

    private String getAuthors(){
        StringBuilder sb = new StringBuilder();
        for(Author a : fg.authors)
        {
            sb.append(a.orcid + ";" + a.email + ";" + a.name + "\n");
        }
        return sb.toString();
    }
}
