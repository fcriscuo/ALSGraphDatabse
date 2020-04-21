//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.03 at 08:09:53 AM PDT 
//
package org.biodatagraphdb.alsdb.model.uniprot

import javax.xml.bind.annotation.*

/**
 * Describes a molecule by name or unique identifier.
 *
 *
 * Java class for moleculeType complex type.
 *
 *
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="moleculeType">
 * &lt;simpleContent>
 * &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 * &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/extension>
 * &lt;/simpleContent>
 * &lt;/complexType>
</pre> *
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moleculeType", propOrder = ["value"])
class MoleculeType {
    /**
     * Gets the value of the value property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the value property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    @XmlValue
    var value: String? = null

    /**
     * Gets the value of the id property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the id property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    @XmlAttribute(name = "id")
    var id: String? = null

}