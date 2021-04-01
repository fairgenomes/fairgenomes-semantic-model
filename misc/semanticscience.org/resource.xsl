<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
    xmlns:dc="http://purl.org/dc/elements/1.1/"
    xmlns:dcterms="http://purl.org/dc/terms/"
    xmlns:ss="http://semanticscience.org/resource/"
    xmlns:obo="http://purl.obolibrary.org/obo/"
    xmlns:owl="http://www.w3.org/2002/07/owl#">

<xsl:output method="html" encoding="utf-8" indent="yes"/>

<xsl:template match="/">

 <xsl:for-each select="//rdf:Description">
  <xsl:if test="./rdf:type/@rdf:resource != 'http://www.w3.org/2002/07/owl#Ontology'">

  <xsl:call-template name="body">
   <xsl:with-param name="entity" select="."/>
  </xsl:call-template>

  </xsl:if>
 </xsl:for-each>

</xsl:template>


<xsl:template name="body">
  <xsl:param name="entity" />

<html>
<head>
 <title><xsl:value-of select="./rdfs:label"/> [<xsl:value-of select="./dcterms:identifier"/>]</title>
 <style type="text/css">
   body {font-family: Verdana,Arial,Helvetica, sans-serif; font-size: 11px; }
   table {vertical-align: top;}
   tr { vertical-align: top;}
   td { vertical-align: top; font-family: Verdana,Arial,Helvetica, sans-serif; font-size: 11px; }
   .header { text-align:right; text-decoration: ; font-weight:bold; color:rgb(0,0,0); }
 </style>
</head>
<body>

<h2><xsl:value-of select="$entity/rdfs:label"/> [<xsl:value-of select="$entity/dcterms:identifier"/>]</h2>

<xsl:element name="div">
<table>                         
 <tr>
  <td class="header">label</td>
  <td><xsl:value-of select="./rdfs:label"/></td>
 </tr>
 <tr>
  <td class="header">identifier</td>
  <td><xsl:value-of select="./dcterms:identifier"/></td>
 </tr>						
 <tr>
  <td class="header">description</td>
  <td width="600"><xsl:value-of select="./dcterms:description | ./obo:IAO_0000115 | ./dc:description"/></td>
 </tr>

 <tr><td><br/></td></tr>

 <tr>
  <td class="header">term type</td>
  <td> 
   <xsl:for-each select="./rdf:type">
    <xsl:variable name="type" select="./@rdf:resource"/>
    <xsl:variable name="nbsp"><br/></xsl:variable>
    <xsl:value-of select="substring-after($type,'#')"/>
    <xsl:copy-of select="$nbsp"/>
  </xsl:for-each>
  </td>
 </tr>

 <xsl:if test="./rdfs:subClassOf">
 <tr>
  <td class="header">subclass of</td>
  <td>

   <xsl:variable name="target" select="./rdfs:subClassOf"/>
   <xsl:for-each select="//rdf:Description">
    <xsl:if test="./@rdf:about = $target/@rdf:resource">
     <xsl:call-template name="makelink2">
      <xsl:with-param name="url" select="./@rdf:about"/>
      <xsl:with-param name="label" select="./rdfs:label" />
     </xsl:call-template>
    <xsl:variable name="br"><br/></xsl:variable>
    <xsl:copy-of select="$br"/>
    </xsl:if>
   </xsl:for-each>

  </td>
 </tr>
 </xsl:if>

 <xsl:if test="./rdfs:domain">
  <tr>
   <td class="header">domain</td>
   <td>

    <xsl:variable name="target" select="./rdfs:domain"/>
    <xsl:for-each select="//rdf:Description">
     <xsl:if test="./@rdf:about = $target/@rdf:resource">
      <xsl:call-template name="makelink2">
       <xsl:with-param name="url" select="./@rdf:about"/>
       <xsl:with-param name="label" select="./rdfs:label" />
      </xsl:call-template>
      <xsl:variable name="br"><br/></xsl:variable>
      <xsl:copy-of select="$br"/>
     </xsl:if>
    </xsl:for-each>

   </td>
  </tr>
 </xsl:if>

 <xsl:if test="./rdfs:subPropertyOf">
 <tr>
  <td class="header">subproperty of</td>
  <td>

   <xsl:variable name="target" select="./rdfs:subPropertyOf"/>
   <xsl:for-each select="//rdf:Description">
    <xsl:if test="./@rdf:about = $target/@rdf:resource">
     <xsl:call-template name="makelink2">
      <xsl:with-param name="url" select="./@rdf:about"/>
      <xsl:with-param name="label" select="./rdfs:label" />
     </xsl:call-template>
    <xsl:variable name="br"><br/></xsl:variable>
    <xsl:copy-of select="$br"/>
    </xsl:if>
   </xsl:for-each>

  </td>
 </tr>
 </xsl:if>


 <xsl:if test="//rdf:Description[*]/rdfs:subClassOf/@rdf:resource = $entity/@rdf:about">
 <tr>
  <td class="header">superclass of</td>
  <td> 
   <xsl:for-each select="//rdf:Description">
    <xsl:if test="./rdfs:subClassOf/@rdf:resource = $entity/@rdf:about">   

    <xsl:call-template name="makelink2">
     <xsl:with-param name="url" select="./@rdf:about"/>
     <xsl:with-param name="label" select="./rdfs:label"/>
    </xsl:call-template>
    <xsl:variable name="br"><br/></xsl:variable>
    <xsl:copy-of select="$br"/>
   </xsl:if>
  </xsl:for-each>
  </td>
 </tr>
 </xsl:if>

 <xsl:if test="//rdf:Description[*]/rdfs:subPropertyOf/@rdf:resource = $entity/@rdf:about">
 <tr>
  <td class="header">superproperty of</td>
  <td> 
   <xsl:for-each select="//rdf:Description">
    <xsl:if test="./rdfs:subPropertyOf/@rdf:resource = $entity/@rdf:about">   

    <xsl:call-template name="makelink2">
     <xsl:with-param name="url" select="./@rdf:about"/>
     <xsl:with-param name="label" select="./rdfs:label"/>
    </xsl:call-template>
    <xsl:variable name="br"><br/></xsl:variable>
    <xsl:copy-of select="$br"/>
   </xsl:if>
  </xsl:for-each>
  </td>
 </tr>
 </xsl:if>

 <xsl:if test="//rdf:Description[*]/owl:inverseOf/@rdf:resource = $entity/@rdf:about">
 <tr>
  <td class="header">inverse of</td>
  <td>
   <xsl:for-each select="//rdf:Description">
    <xsl:if test="./owl:inverseOf/@rdf:resource = $entity/@rdf:about">
    <xsl:call-template name="makelink2">
     <xsl:with-param name="url" select="./@rdf:about"/>
     <xsl:with-param name="label" select="./rdfs:label"/>
    </xsl:call-template>
    <xsl:variable name="br"><br/></xsl:variable>
    <xsl:copy-of select="$br"/>
   </xsl:if>
  </xsl:for-each>
  </td>
 </tr>
 </xsl:if>

<xsl:variable name="target" select="./owl:inverseOf"/>

<!-- 
 <xsl:if test="//rdf:Description[*]/owl:inverseOf/@rdf:resource = $target/@rdf:about">
-->
   <xsl:for-each select="//rdf:Description">
    <xsl:if test="./@rdf:about = $target/@rdf:resource">
 <tr>
  <td class="header">inverse of</td>
  <td>
 
    <xsl:call-template name="makelink2">
      <xsl:with-param name="url" select="./@rdf:about"/>
      <xsl:with-param name="label" select="./rdfs:label" />
     </xsl:call-template>
    <xsl:variable name="br"><br/></xsl:variable>
    <xsl:copy-of select="$br"/>
  </td>
 </tr>

    </xsl:if>
   </xsl:for-each>
<!--
 </xsl:if>
-->
 <tr><td><br/></td></tr>

 <tr>
  <td class="header">is defined by</td>
  <td>

 <xsl:for-each select="//rdf:Description">
  <xsl:if test="./rdf:type/@rdf:resource = 'http://www.w3.org/2002/07/owl#Ontology'">   
   <xsl:call-template name="makelink">
    <xsl:with-param name="url" select="./owl:imports/@rdf:resource"/>
   </xsl:call-template>
  </xsl:if>
 </xsl:for-each>

  </td>
 </tr>
<!-- <tr>
  <td class="header">is subject of</td>
  <td>
   <xsl:call-template name="makelink">
    <xsl:with-param name="url" select="./rdfs:isDefinedBy/@rdf:resource"/>
   </xsl:call-template>
  </td>
 </tr>
-->
 </table>
</xsl:element>

<hr />
<a href="http://www.w3.org/TR/rdf-sparql-query/"><img alt="W3C Semantic Web Technology" src="image/sw-sparql-blue.png" border="0"/></a> 
<a href="http://www.w3.org/RDF/" title="RDF data"><img border="0" src="image/sw-rdf-blue.png" alt="[RDF Data]"/></a> 
<br/><a href="http://www.opendefinition.org/"><img alt="This material is Open Knowledge" src="image/od_80x15_red_green.png" border="0"/></a> 
<a title="Creative Commons - By Attribution - Share-Alike" rel="license" href="http://creativecommons.org/licenses/by-sa/2.5/ca/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/2.5/ca/80x15.png" /></a> 

</body>
</html>
</xsl:template>

 <xsl:template name="makelink">
  <xsl:param name="url" />

  <xsl:element name="a">
   <xsl:attribute name="href"><xsl:value-of select="$url"/></xsl:attribute>
   <xsl:value-of select="$url"/>
  </xsl:element>
 </xsl:template>


 <xsl:template name="makelink2">
  <xsl:param name="url" />
  <xsl:param name="label" />
  <xsl:element name="a">
   <xsl:attribute name="href"><xsl:value-of select="$url"/></xsl:attribute>
   <xsl:value-of select="$label"/>
  </xsl:element>
 </xsl:template>

</xsl:stylesheet>
