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

import palgacodebooktoartdecor.settings.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Representation of an ART-DECOR dataset (basically a codebook)
 */
public class ArtDecorDataset {
    private String effectiveDate;
    private String statusCode;
//    private int versionLabel;
    private double versionLabel;

    private String artdecorDatasetId;

    private List<LanguageDataset> languageDatasetList = new ArrayList<>();

    private Map<String, ArtDecorConcept> artDecorConcepMap = new TreeMap<>();
    private List<ArtDecorConcept> topArtDecorConceptList = new ArrayList<>();

    /**
     * Create a new ART-DECOR dataset
     * @param artdecorDatasetId identifier in Art-Decor
     * @param effectiveDate     effective date
     * @param versionLabel      version
     * @param statusCode        status of the dataset
     */
//    public ArtDecorDataset(String artdecorDatasetId, String effectiveDate, int versionLabel, String statusCode){
    public ArtDecorDataset(String artdecorDatasetId, String effectiveDate, double versionLabel, String statusCode){
        this.artdecorDatasetId = artdecorDatasetId;
        this.effectiveDate = effectiveDate;
        this.versionLabel = versionLabel;
        this.statusCode = statusCode;
    }

    /**
     * Adds name and description of the dataset for a certain language
     * @param language    the language
     * @param name        the name of the dataset in that language
     * @param description the description of the dataset in that language
     */
    public void addLanguageParameter(String language, String name, String description){
        languageDatasetList.add(new LanguageDataset(language, name, description));
    }

    /**
     * store an ART-DECOR concept for this dataset
     * @param artDecorConcept the concept
     */
    public void addArtDecorConcept(ArtDecorConcept artDecorConcept){
        artDecorConcepMap.put(artDecorConcept.getConceptId(), artDecorConcept);
    }

    /**
     * creates a concept tree by looking at concept parents
     */
    public void connectConcepts(){
        for(ArtDecorConcept artDecorConcept:artDecorConcepMap.values()){
            String parent = artDecorConcept.getParent();
            if(artDecorConcepMap.containsKey(parent)) {
                ArtDecorConcept parentConcept = artDecorConcepMap.get(parent);
                parentConcept.addChild(artDecorConcept);
            }
            else{
                topArtDecorConceptList.add(artDecorConcept);
            }
        }
    }

    /**
     * transforms the dataset into XML
     * @return String representation of the dataset
     * @throws Exception
     */
    public String toXML() throws Exception{
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<dataset id=\"" + artdecorDatasetId + "\" effectiveDate=\"" + effectiveDate + "\" statusCode=\"" + statusCode + "\" versionLabel=\"" + versionLabel + "\">\n");
            for (LanguageDataset languageDataset : languageDatasetList) {
                stringBuilder.append("<name language=\"" + languageDataset.language + "\">" + languageDataset.datasetName + "</name>\n");
            }

            for (LanguageDataset languageDataset : languageDatasetList) {
                stringBuilder.append("<desc language=\"" + languageDataset.language + "\">" + languageDataset.datasetDescription + "</desc>\n");
            }

            for (ArtDecorConcept artDecorConcept : topArtDecorConceptList) {
                stringBuilder.append(artDecorConcept.toXML());
            }

            stringBuilder.append("</dataset>\n");
            return stringBuilder.toString();
        } catch(Exception e){
            throw new Exception("codebook version: "+versionLabel+"; "+e.getMessage());
        }
    }

    /**
     * Class for keeping track of the name and description of the dataset in a language
     */
    private static class LanguageDataset{
        private String language;
        private String datasetName;
        private String datasetDescription;

        LanguageDataset(String language, String datasetName, String datasetDescription){
            this.language = Statics.getArtDecorLanguage(language);
            this.datasetName = datasetName;
            this.datasetDescription = datasetDescription;
        }
    }
}
