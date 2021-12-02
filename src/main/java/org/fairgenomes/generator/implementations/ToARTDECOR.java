package org.fairgenomes.generator.implementations;

import org.fairgenomes.generator.AbstractGenerator;
import org.fairgenomes.generator.datastructures.Author;
import org.fairgenomes.generator.datastructures.YamlModel;
import palgacodebooktoartdecor.codebook.CodebookManager;
import palgacodebooktoartdecor.codebook.CodebookToArtDecorConvertor;
import palgacodebooktoartdecor.settings.IdentifierManager;
import palgacodebooktoartdecor.settings.RunParameters;

import java.io.File;

public class ToARTDECOR extends AbstractGenerator {

    public ToARTDECOR(YamlModel fg, File outputFolder) throws Exception {
        super(fg, outputFolder);
    }

    @Override
    public void start() throws Exception {

        RunParameters runParameters = new RunParameters(
                outputFolder.getParent().toString() + "/palga-codebook",          // codebookDirectory
                fg.technical.artDecorId,                                                    // projectId
                fg.fileName.replace("-",""),                   // projectPrefix
                "false",                                                          // experimental
                getAuthors(),                                                     // authorString
                fg.copyright.holder + ";"+fg.copyright.years+";author",           // copyrightString
                "draft");                                                         // statusCode
        File output = new File(outputFolder, fg.fileName + "_en-US.xml");
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
