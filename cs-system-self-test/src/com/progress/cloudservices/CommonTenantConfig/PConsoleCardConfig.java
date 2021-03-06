/*******************************************************************************
 * Copyright � 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.21 at 12:47:31 PM IST 
//


package com.progress.cloudservices.CommonTenantConfig;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pConsoleCardConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pConsoleCardConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pConsoleFrontUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pConsoleBackUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageType" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="aggregateUsageFromParent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pConsoleCardConfig", propOrder = {
    "pConsoleFrontUrl",
    "pConsoleBackUrl",
    "usageType"
})
public class PConsoleCardConfig {

    protected String pConsoleFrontUrl;
    protected String pConsoleBackUrl;
    protected List<PConsoleCardConfig.UsageType> usageType;

    /**
     * Gets the value of the pConsoleFrontUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPConsoleFrontUrl() {
        return pConsoleFrontUrl;
    }

    /**
     * Sets the value of the pConsoleFrontUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPConsoleFrontUrl(String value) {
        this.pConsoleFrontUrl = value;
    }

    /**
     * Gets the value of the pConsoleBackUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPConsoleBackUrl() {
        return pConsoleBackUrl;
    }

    /**
     * Sets the value of the pConsoleBackUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPConsoleBackUrl(String value) {
        this.pConsoleBackUrl = value;
    }

    /**
     * Gets the value of the usageType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usageType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsageType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PConsoleCardConfig.UsageType }
     * 
     * 
     */
    public List<PConsoleCardConfig.UsageType> getUsageType() {
        if (usageType == null) {
            usageType = new ArrayList<PConsoleCardConfig.UsageType>();
        }
        return this.usageType;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="aggregateUsageFromParent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "displayName",
        "aggregateUsageFromParent"
    })
    public static class UsageType {

        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String displayName;
        protected String aggregateUsageFromParent;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the displayName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * Sets the value of the displayName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDisplayName(String value) {
            this.displayName = value;
        }

        /**
         * Gets the value of the aggregateUsageFromParent property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public boolean isAggregateUsageFromParent() {
            return Boolean.valueOf(aggregateUsageFromParent);
        }

        /**
         * Sets the value of the aggregateUsageFromParent property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAggregateUsageFromParent(boolean value) {
            this.aggregateUsageFromParent = String.valueOf(value).toUpperCase();  }

    }

}
