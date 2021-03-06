/*******************************************************************************
 * Copyright � 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.03 at 03:03:22 PM IST 
//


package com.progress.cloudservices.CommonTenantConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mailServiceConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mailServiceConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="replyToAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bccAddresses" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultTenantTemplateName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mailServiceConfig", propOrder = {
    "fromAddress",
    "replyToAddress",
    "bccAddresses",
    "defaultTenantTemplateName"
})
public class MailServiceConfig {

    @XmlElement(required = true)
    protected String fromAddress;
    protected String replyToAddress;
    protected String bccAddresses;
    @XmlElement(required = true)
    protected String defaultTenantTemplateName;

    /**
     * Gets the value of the fromAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Sets the value of the fromAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromAddress(String value) {
        this.fromAddress = value;
    }

    /**
     * Gets the value of the replyToAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyToAddress() {
        return replyToAddress;
    }

    /**
     * Sets the value of the replyToAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyToAddress(String value) {
        this.replyToAddress = value;
    }

    /**
     * Gets the value of the bccAddresses property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBccAddresses() {
        return bccAddresses;
    }

    /**
     * Sets the value of the bccAddresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBccAddresses(String value) {
        this.bccAddresses = value;
    }

    /**
     * Gets the value of the defaultTenantTemplateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultTenantTemplateName() {
        return defaultTenantTemplateName;
    }

    /**
     * Sets the value of the defaultTenantTemplateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultTenantTemplateName(String value) {
        this.defaultTenantTemplateName = value;
    }

}
