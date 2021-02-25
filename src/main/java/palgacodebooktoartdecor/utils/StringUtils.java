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

package palgacodebooktoartdecor.utils;

/**
 * String stuff
 */
public class StringUtils {
    /**
     * does some string replacements to ensure the value does not interfere with XML syntax
     * @param value the value to check
     * @return an acceptable xml value
     */
    static String prepareValueForXML(String value){
        value = value.replaceAll("&", "&amp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("'", "&apos;");
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("≥", "&gt;=");
        value = value.replaceAll("≤", "&lt;=");
        // replace all whitespace characters with a normal space
        // this due to an a0 character appearing
        value = value.replaceAll("[\\p{Zs}\\s]+", " ");
        return value.trim();
    }

    public static String removeNonAlphanumericCharacters(String value){
        value = value.replaceAll("&amp;","");
        value = value.replaceAll("&lt;", "");
        value = value.replaceAll("&gt;", "");
        value = value.replaceAll("&apos;", "");
        value = value.replaceAll("&quot;", "");
        value = value.replaceAll("&gt;=", "");
        value = value.replaceAll("&lt;=", "");
        value = value.replaceAll("[^a-zA-Z0-9]", "");
        return value;
    }
}
