//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.03 at 08:09:53 AM PDT 
//
package org.biodatagraphdb.alsdb.model.uniprot

import java.util.*
import javax.xml.bind.annotation.*

/**
 *
 * Describes different types of sequence annotations. Equivalent to the flat file FT-line.
 *
 *
 *
 * Java class for featureType complex type.
 *
 *
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="featureType">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="original" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="variation" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="location" type="{http://uniprot.org/uniprot}locationType"/>
 * &lt;/sequence>
 * &lt;attribute name="type" use="required">
 * &lt;simpleType>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="active site"/>
 * &lt;enumeration value="binding site"/>
 * &lt;enumeration value="calcium-binding region"/>
 * &lt;enumeration value="chain"/>
 * &lt;enumeration value="coiled-coil region"/>
 * &lt;enumeration value="compositionally biased region"/>
 * &lt;enumeration value="cross-link"/>
 * &lt;enumeration value="disulfide bond"/>
 * &lt;enumeration value="DNA-binding region"/>
 * &lt;enumeration value="domain"/>
 * &lt;enumeration value="glycosylation site"/>
 * &lt;enumeration value="helix"/>
 * &lt;enumeration value="initiator methionine"/>
 * &lt;enumeration value="lipid moiety-binding region"/>
 * &lt;enumeration value="metal ion-binding site"/>
 * &lt;enumeration value="modified residue"/>
 * &lt;enumeration value="mutagenesis site"/>
 * &lt;enumeration value="non-consecutive residues"/>
 * &lt;enumeration value="non-terminal residue"/>
 * &lt;enumeration value="nucleotide phosphate-binding region"/>
 * &lt;enumeration value="peptide"/>
 * &lt;enumeration value="propeptide"/>
 * &lt;enumeration value="region of interest"/>
 * &lt;enumeration value="repeat"/>
 * &lt;enumeration value="non-standard amino acid"/>
 * &lt;enumeration value="sequence conflict"/>
 * &lt;enumeration value="sequence variant"/>
 * &lt;enumeration value="short sequence motif"/>
 * &lt;enumeration value="signal peptide"/>
 * &lt;enumeration value="site"/>
 * &lt;enumeration value="splice variant"/>
 * &lt;enumeration value="strand"/>
 * &lt;enumeration value="topological domain"/>
 * &lt;enumeration value="transit peptide"/>
 * &lt;enumeration value="transmembrane region"/>
 * &lt;enumeration value="turn"/>
 * &lt;enumeration value="unsure residue"/>
 * &lt;enumeration value="zinc finger region"/>
 * &lt;enumeration value="intramembrane region"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * &lt;/attribute>
 * &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="evidence" type="{http://uniprot.org/uniprot}intListType" />
 * &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
</pre> *
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "featureType", propOrder = ["original", "variation", "location"])
class FeatureType {
    /**
     * Gets the value of the original property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the original property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    var original: String? = null
    protected var variation: List<String>? = null

    /**
     * Gets the value of the location property.
     *
     * @return
     * possible object is
     * [LocationType]
     */
    /**
     * Sets the value of the location property.
     *
     * @param value
     * allowed object is
     * [LocationType]
     */
    @XmlElement(required = true)
    var location: LocationType? = null

    /**
     * Gets the value of the type property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the type property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    @XmlAttribute(name = "type", required = true)
    var type: String? = null

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

    /**
     * Gets the value of the description property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the description property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    @XmlAttribute(name = "description")
    var description: String? = null

    @XmlAttribute(name = "evidence")
    protected var evidence: List<Int>? = null

    /**
     * Gets the value of the ref property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the ref property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    @XmlAttribute(name = "ref")
    var ref: String? = null

    /**
     * Gets the value of the variation property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variation property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getVariation().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [String]
     *
     *
     */
    fun getVariationList(): List<String>? {
        if (variation == null) {
            variation = ArrayList()
        }
        return variation
    }

    /**
     * Gets the value of the evidence property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evidence property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getEvidence().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [Integer]
     *
     *
     */
    fun getEvidenceList(): List<Int>? {
        if (evidence == null) {
            evidence = ArrayList()
        }
        return evidence
    }

}