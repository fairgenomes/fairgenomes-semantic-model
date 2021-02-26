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
 * Links a concept to a codesystem and code via the terminology association tag
 */
public class ArtDecorTerminologyConcept {
    private String artdecorConceptId;
    private String conceptFlexibility;
    private String code;
    private String codeSystemName;
    private String codeSystemId;
    private String displayName;
    private String effectiveDate;

    /**
     * constructor to create an ART-DECOR terminology concept
     * @param artdecorConceptId  identifier
     * @param conceptFlexibility currently same as effectiveDate
     * @param code               code of the concept
     * @param codeSystemName     name of the codesystem
     * @param displayName        name that will be shown
     * @param effectiveDate      effective date
     * @param codeSystemId       identifier of the codesystem
     */
    public ArtDecorTerminologyConcept(String artdecorConceptId, String conceptFlexibility, String code, String codeSystemName, String displayName, String effectiveDate, String codeSystemId){
        this.artdecorConceptId = artdecorConceptId;
        this.conceptFlexibility = conceptFlexibility;
        this.code = code;
        this.codeSystemName = codeSystemName;
        this.codeSystemId = codeSystemId;
        this.displayName = displayName;
        this.effectiveDate = effectiveDate;
    }

    /**
     * creates the xml representation, linking a concept with its codesystem and code
     * @return XML string
     */
    public String toXML(){
        return "<terminologyAssociation conceptId=\""+ artdecorConceptId +"\" conceptFlexibility=\""+conceptFlexibility+"\" code=\""+code+"\" codeSystem=\""+ codeSystemId+"\" codeSystemName=\""+codeSystemName+"\" displayName=\""+displayName+"\" effectiveDate=\""+effectiveDate+"\"/>\n";
    }
}
