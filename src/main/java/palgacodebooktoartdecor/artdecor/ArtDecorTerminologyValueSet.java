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

/**
 * Links a Concept to its ValueSet in the via the terminology association tag
 */
public class ArtDecorTerminologyValueSet {
    private String artdecorConceptListId;
    private String artdecorValueSetId;
    private String flexibilityDate;
    private String effectiveDate;

    /**
     * constructor to create an ART-DECOR terminology value set
     * @param artdecorConceptListId identifier of the concept list
     * @param artdecorValueSetId    identifier of the value set
     * @param flexibilityDate       currently same as effectiveDate
     * @param effectiveDate         effective date
     */
    public ArtDecorTerminologyValueSet(String artdecorConceptListId, String artdecorValueSetId, String flexibilityDate, String effectiveDate){
        this.artdecorConceptListId = artdecorConceptListId;
        this.artdecorValueSetId = artdecorValueSetId;
        this.flexibilityDate = flexibilityDate;
        this.effectiveDate = effectiveDate;
    }

    /**
     * creates the xml representation, linking a concept with its value set
     * @return
     */
    public String toXML(){
        return "<terminologyAssociation conceptId=\""+artdecorConceptListId+"\" valueSet=\""+artdecorValueSetId+"\" flexibility=\""+flexibilityDate+"\" effectiveDate=\""+effectiveDate+"\"/>\n";
    }
}
