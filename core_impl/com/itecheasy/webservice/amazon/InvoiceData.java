
package com.itecheasy.webservice.amazon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>invoiceData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="invoiceData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://amazon.core.itecheasy.com/}abstractMwsObject">
 *       &lt;sequence>
 *         &lt;element name="buyerSelectedInvoiceCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceRequirement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoiceData", propOrder = {
    "buyerSelectedInvoiceCategory",
    "invoiceInformation",
    "invoiceRequirement",
    "invoiceTitle"
})
public class InvoiceData
    extends AbstractMwsObject
{

    protected String buyerSelectedInvoiceCategory;
    protected String invoiceInformation;
    protected String invoiceRequirement;
    protected String invoiceTitle;

    /**
     * 获取buyerSelectedInvoiceCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerSelectedInvoiceCategory() {
        return buyerSelectedInvoiceCategory;
    }

    /**
     * 设置buyerSelectedInvoiceCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerSelectedInvoiceCategory(String value) {
        this.buyerSelectedInvoiceCategory = value;
    }

    /**
     * 获取invoiceInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceInformation() {
        return invoiceInformation;
    }

    /**
     * 设置invoiceInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceInformation(String value) {
        this.invoiceInformation = value;
    }

    /**
     * 获取invoiceRequirement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceRequirement() {
        return invoiceRequirement;
    }

    /**
     * 设置invoiceRequirement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceRequirement(String value) {
        this.invoiceRequirement = value;
    }

    /**
     * 获取invoiceTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * 设置invoiceTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceTitle(String value) {
        this.invoiceTitle = value;
    }

}
