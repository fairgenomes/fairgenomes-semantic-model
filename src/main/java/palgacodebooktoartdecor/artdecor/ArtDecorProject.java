/*
 * Copyright 2017 NKI/AvL; VUmc 2018/2019/2020
 *
 * This file is part of PALGA Protocol Codebook to XML.
 *
 * PALGA Protocol Codebook to XML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PALGA Protocol Codebook to XML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PALGA Protocol Codebook to XML. If not, see <http://www.gnu.org/licenses/>
 */

package palgacodebooktoartdecor.artdecor;

import palgacodebooktoartdecor.settings.RunParameters;
import palgacodebooktoartdecor.settings.Statics;

import java.util.Set;

/**
 * Information necessary to generate the information within the Project tag
 */
public class ArtDecorProject {
    private RunParameters runParameters;

    public ArtDecorProject(RunParameters runParameters){
        this.runParameters = runParameters;
    }

    /**
     * transforms the project information into XML
     * @return xml string
     */
    public String toXML(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<project id=\""+ runParameters.getProjectId()+"\" prefix=\""+ runParameters.getProjectPrefix()+"\" experimental=\""+ runParameters.getExperimental()+"\" defaultLanguage=\""+ Statics.getArtDecorLanguage(runParameters.getDefaultLanguage())+"\">\n");
        Set<String> languages = runParameters.getLanguages();

        for(String language:languages){
            String languageArtDecor = Statics.getArtDecorLanguage(language);
            stringBuilder.append("<name language=\""+ languageArtDecor+"\">"+ runParameters.getProjectName(language)+"</name>\n");
        }

        for(String language:languages){
            String languageArtDecor = Statics.getArtDecorLanguage(language);
            stringBuilder.append("<desc language=\""+ languageArtDecor+"\">"+ runParameters.getProjectDescription(language)+"</desc>\n");
        }

        // copyright by and years should probably become parameters as well...
        stringBuilder.append(
                        runParameters.getCopyrightStringFormatted()+"\n"+
                        runParameters.getAuthorsStringFormatted()+"\n"+
                        "<reference url=\"http://decor.nictiz.nl/pub/"+ runParameters.getProjectReference()+"/\"/>\n" +
                        "<defaultElementNamespace ns=\"hl7:\"/>\n" +
                        "</project>\n");
        return stringBuilder.toString();
    }
}
