//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.03 at 08:09:53 AM PDT 
//
package org.biodatagraphdb.alsdb.model.uniprot

import java.util.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlType

/**
 *
 * Describes the names for the protein and parts thereof. Equivalent to the flat file DE-line.
 *
 *
 *
 * Java class for proteinType complex type.
 *
 *
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="proteinType">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
 * &lt;element name="domain" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="component" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
</pre> *
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proteinType", propOrder = ["recommendedName", "alternativeName", "submittedName", "allergenName", "biotechName", "cdAntigenName", "innName", "domain", "component"])
class ProteinType {
    /**
     * Gets the value of the recommendedName property.
     *
     * @return
     * possible object is
     * [ProteinType.RecommendedName]
     */
    /**
     * Sets the value of the recommendedName property.
     *
     * @param value
     * allowed object is
     * [ProteinType.RecommendedName]
     */
    var recommendedName: RecommendedName? = null
    protected var alternativeName: List<AlternativeName>? = null
    protected var submittedName: List<SubmittedName>? = null
    /**
     * Gets the value of the allergenName property.
     *
     * @return
     * possible object is
     * [EvidencedStringType]
     */
    /**
     * Sets the value of the allergenName property.
     *
     * @param value
     * allowed object is
     * [EvidencedStringType]
     */
    var allergenName: EvidencedStringType? = null
    /**
     * Gets the value of the biotechName property.
     *
     * @return
     * possible object is
     * [EvidencedStringType]
     */
    /**
     * Sets the value of the biotechName property.
     *
     * @param value
     * allowed object is
     * [EvidencedStringType]
     */
    var biotechName: EvidencedStringType? = null
    protected var cdAntigenName: List<EvidencedStringType>? = null
    protected var innName: List<EvidencedStringType>? = null
    protected var domain: List<Domain>? = null
    protected var component: List<Component>? = null

    /**
     * Gets the value of the alternativeName property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternativeName property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getAlternativeName().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [ProteinType.AlternativeName]
     *
     *
     */
    fun getAlternativeNameList(): List<AlternativeName>? {
        if (alternativeName == null) {
            alternativeName = ArrayList()
        }
        return alternativeName
    }

    /**
     * Gets the value of the submittedName property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the submittedName property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getSubmittedName().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [ProteinType.SubmittedName]
     *
     *
     */
    fun getSubmittedNameList(): List<SubmittedName>? {
        if (submittedName == null) {
            submittedName = ArrayList()
        }
        return submittedName
    }

    /**
     * Gets the value of the cdAntigenName property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cdAntigenName property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getCdAntigenName().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [EvidencedStringType]
     *
     *
     */
    fun getCdAntigenNameList(): List<EvidencedStringType>? {
        if (cdAntigenName == null) {
            cdAntigenName = ArrayList()
        }
        return cdAntigenName
    }

    /**
     * Gets the value of the innName property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the innName property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getInnName().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [EvidencedStringType]
     *
     *
     */
    fun getInnNameList(): List<EvidencedStringType>? {
        if (innName == null) {
            innName = ArrayList()
        }
        return innName
    }

    /**
     * Gets the value of the domain property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domain property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getDomain().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [ProteinType.Domain]
     *
     *
     */
    fun getDomainList(): List<Domain>? {
        if (domain == null) {
            domain = ArrayList()
        }
        return domain
    }

    /**
     * Gets the value of the component property.
     *
     *
     *
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the component property.
     *
     *
     *
     * For example, to add a new item, do as follows:
     * <pre>
     * getComponent().add(newItem);
    </pre> *
     *
     *
     *
     *
     * Objects of the following type(s) are allowed in the list
     * [ProteinType.Component]
     *
     *
     */
    fun getComponentList(): List<Component>? {
        if (component == null) {
            component = ArrayList()
        }
        return component
    }

    /**
     *
     * Java class for anonymous complex type.
     *
     *
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="fullName" type="{http://uniprot.org/uniprot}evidencedStringType" minOccurs="0"/>
     * &lt;element name="shortName" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="ecNumber" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
    </pre> *
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["fullName", "shortName", "ecNumber"])
    class AlternativeName {
        /**
         * Gets the value of the fullName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the fullName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        var fullName: EvidencedStringType? = null
        protected var shortName: List<EvidencedStringType>? = null
        protected var ecNumber: List<EvidencedStringType>? = null

        /**
         * Gets the value of the shortName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the shortName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getShortName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getShortNameList(): List<EvidencedStringType>? {
            if (shortName == null) {
                shortName = ArrayList()
            }
            return shortName
        }

        /**
         * Gets the value of the ecNumber property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ecNumber property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getEcNumber().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getEcNumberList(): List<EvidencedStringType>? {
            if (ecNumber == null) {
                ecNumber = ArrayList()
            }
            return ecNumber
        }
    }

    /**
     *
     * Java class for anonymous complex type.
     *
     *
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
    </pre> *
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["recommendedName", "alternativeName", "submittedName", "allergenName", "biotechName", "cdAntigenName", "innName"])
    class Component {
        /**
         * Gets the value of the recommendedName property.
         *
         * @return
         * possible object is
         * [ProteinType.RecommendedName]
         */
        /**
         * Sets the value of the recommendedName property.
         *
         * @param value
         * allowed object is
         * [ProteinType.RecommendedName]
         */
        var recommendedName: RecommendedName? = null
        protected var alternativeName: List<AlternativeName>? = null
        protected var submittedName: List<SubmittedName>? = null
        /**
         * Gets the value of the allergenName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the allergenName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        var allergenName: EvidencedStringType? = null
        /**
         * Gets the value of the biotechName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the biotechName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        var biotechName: EvidencedStringType? = null
        protected var cdAntigenName: List<EvidencedStringType>? = null
        protected var innName: List<EvidencedStringType>? = null

        /**
         * Gets the value of the alternativeName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alternativeName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getAlternativeName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [ProteinType.AlternativeName]
         *
         *
         */
        fun getAlternativeNameList(): List<AlternativeName>? {
            if (alternativeName == null) {
                alternativeName = ArrayList()
            }
            return alternativeName
        }

        /**
         * Gets the value of the submittedName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the submittedName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getSubmittedName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [ProteinType.SubmittedName]
         *
         *
         */
        fun getSubmittedNameList(): List<SubmittedName>? {
            if (submittedName == null) {
                submittedName = ArrayList()
            }
            return submittedName
        }

        /**
         * Gets the value of the cdAntigenName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cdAntigenName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getCdAntigenName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getCdAntigenNameList(): List<EvidencedStringType>? {
            if (cdAntigenName == null) {
                cdAntigenName = ArrayList()
            }
            return cdAntigenName
        }

        /**
         * Gets the value of the innName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the innName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getInnName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getInnNameList(): List<EvidencedStringType>? {
            if (innName == null) {
                innName = ArrayList()
            }
            return innName
        }
    }

    /**
     *
     * Java class for anonymous complex type.
     *
     *
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
    </pre> *
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["recommendedName", "alternativeName", "submittedName", "allergenName", "biotechName", "cdAntigenName", "innName"])
    class Domain {
        /**
         * Gets the value of the recommendedName property.
         *
         * @return
         * possible object is
         * [ProteinType.RecommendedName]
         */
        /**
         * Sets the value of the recommendedName property.
         *
         * @param value
         * allowed object is
         * [ProteinType.RecommendedName]
         */
        var recommendedName: RecommendedName? = null
        protected var alternativeName: List<AlternativeName>? = null
        protected var submittedName: List<SubmittedName>? = null
        /**
         * Gets the value of the allergenName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the allergenName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        var allergenName: EvidencedStringType? = null
        /**
         * Gets the value of the biotechName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the biotechName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        var biotechName: EvidencedStringType? = null
        protected var cdAntigenName: List<EvidencedStringType>? = null
        protected var innName: List<EvidencedStringType>? = null

        /**
         * Gets the value of the alternativeName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alternativeName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getAlternativeName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [ProteinType.AlternativeName]
         *
         *
         */
        fun getAlternativeNameList(): List<AlternativeName>? {
            if (alternativeName == null) {
                alternativeName = ArrayList()
            }
            return alternativeName
        }

        /**
         * Gets the value of the submittedName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the submittedName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getSubmittedName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [ProteinType.SubmittedName]
         *
         *
         */
        fun getSubmittedNameList(): List<SubmittedName>? {
            if (submittedName == null) {
                submittedName = ArrayList()
            }
            return submittedName
        }

        /**
         * Gets the value of the cdAntigenName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cdAntigenName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getCdAntigenName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getCdAntigenNameList(): List<EvidencedStringType>? {
            if (cdAntigenName == null) {
                cdAntigenName = ArrayList()
            }
            return cdAntigenName
        }

        /**
         * Gets the value of the innName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the innName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getInnName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getInnNameList(): List<EvidencedStringType>? {
            if (innName == null) {
                innName = ArrayList()
            }
            return innName
        }
    }

    /**
     *
     * Java class for anonymous complex type.
     *
     *
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="fullName" type="{http://uniprot.org/uniprot}evidencedStringType"/>
     * &lt;element name="shortName" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="ecNumber" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
    </pre> *
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["fullName", "shortName", "ecNumber"])
    class RecommendedName {
        /**
         * Gets the value of the fullName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the fullName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        @XmlElement(required = true)
        var fullName: EvidencedStringType? = null
        protected var shortName: List<EvidencedStringType>? = null
        protected var ecNumber: List<EvidencedStringType>? = null

        /**
         * Gets the value of the shortName property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the shortName property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getShortName().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getShortNameList(): List<EvidencedStringType>? {
            if (shortName == null) {
                shortName = ArrayList()
            }
            return shortName
        }

        /**
         * Gets the value of the ecNumber property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ecNumber property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getEcNumber().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getEcNumberList(): List<EvidencedStringType>? {
            if (ecNumber == null) {
                ecNumber = ArrayList()
            }
            return ecNumber
        }
    }

    /**
     *
     * Java class for anonymous complex type.
     *
     *
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="fullName" type="{http://uniprot.org/uniprot}evidencedStringType"/>
     * &lt;element name="ecNumber" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
    </pre> *
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["fullName", "ecNumber"])
    class SubmittedName {
        /**
         * Gets the value of the fullName property.
         *
         * @return
         * possible object is
         * [EvidencedStringType]
         */
        /**
         * Sets the value of the fullName property.
         *
         * @param value
         * allowed object is
         * [EvidencedStringType]
         */
        @XmlElement(required = true)
        var fullName: EvidencedStringType? = null
        protected var ecNumber: List<EvidencedStringType>? = null

        /**
         * Gets the value of the ecNumber property.
         *
         *
         *
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ecNumber property.
         *
         *
         *
         * For example, to add a new item, do as follows:
         * <pre>
         * getEcNumber().add(newItem);
        </pre> *
         *
         *
         *
         *
         * Objects of the following type(s) are allowed in the list
         * [EvidencedStringType]
         *
         *
         */
        fun getEcNumberList(): List<EvidencedStringType>? {
            if (ecNumber == null) {
                ecNumber = ArrayList()
            }
            return ecNumber
        }
    }
}