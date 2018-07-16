
package com.itecheasy.webservice.amazon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for orderAmazon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderAmazon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amazonOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyerEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbaDisplayableShippingLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="earliestDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="earliestShipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fulfillmentChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="latestDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="latestShipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="marketplaceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfItemsShipped" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numberOfItemsUnshipped" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="orderChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItems" type="{http://amazon.core.itecheasy.com/}orderItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderTotal" type="{http://amazon.core.itecheasy.com/}money" minOccurs="0"/>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentExecutionDetail" type="{http://amazon.core.itecheasy.com/}paymentExecutionDetailItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paymentMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="salesChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellerOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipServiceLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shipmentServiceLevelCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shippedByAmazonTFM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="shippingAddress" type="{http://amazon.core.itecheasy.com/}address" minOccurs="0"/>
 *         &lt;element name="tfmShipmentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderAmazon", propOrder = {
    "amazonOrderId",
    "buyerEmail",
    "buyerName",
    "cbaDisplayableShippingLabel",
    "earliestDeliveryDate",
    "earliestShipDate",
    "fulfillmentChannel",
    "lastUpdateDate",
    "latestDeliveryDate",
    "latestShipDate",
    "marketplaceId",
    "numberOfItemsShipped",
    "numberOfItemsUnshipped",
    "orderChannel",
    "orderItems",
    "orderStatus",
    "orderTotal",
    "orderType",
    "paymentExecutionDetail",
    "paymentMethod",
    "purchaseDate",
    "salesChannel",
    "sellerOrderId",
    "shipServiceLevel",
    "shipmentServiceLevelCategory",
    "shippedByAmazonTFM",
    "shippingAddress",
    "tfmShipmentStatus"
})
public class OrderAmazon {
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setPaymentExecutionDetail(List<PaymentExecutionDetailItem> paymentExecutionDetail) {
        this.paymentExecutionDetail = paymentExecutionDetail;
    }

    public Boolean getShippedByAmazonTFM() {
        return shippedByAmazonTFM;
    }

    protected String amazonOrderId;
    protected String buyerEmail;
    protected String buyerName;
    protected String cbaDisplayableShippingLabel;
    protected XMLGregorianCalendar earliestDeliveryDate;
    protected XMLGregorianCalendar earliestShipDate;
    protected String fulfillmentChannel;
    protected XMLGregorianCalendar lastUpdateDate;
    protected XMLGregorianCalendar latestDeliveryDate;
    protected XMLGregorianCalendar latestShipDate;
    protected String marketplaceId;
    protected Integer numberOfItemsShipped;
    protected Integer numberOfItemsUnshipped;
    protected String orderChannel;
    @XmlElement(nillable = true)
    protected List<OrderItem> orderItems;
    protected String orderStatus;
    protected Money orderTotal;
    protected String orderType;
    @XmlElement(nillable = true)
    protected List<PaymentExecutionDetailItem> paymentExecutionDetail;
    protected String paymentMethod;
    protected XMLGregorianCalendar purchaseDate;
    protected String salesChannel;
    protected String sellerOrderId;
    protected String shipServiceLevel;
    protected String shipmentServiceLevelCategory;
    protected Boolean shippedByAmazonTFM;
    protected Address shippingAddress;
    protected String tfmShipmentStatus;

    /**
     * Gets the value of the amazonOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmazonOrderId() {
        return amazonOrderId;
    }

    /**
     * Sets the value of the amazonOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmazonOrderId(String value) {
        this.amazonOrderId = value;
    }

    /**
     * Gets the value of the buyerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * Sets the value of the buyerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerEmail(String value) {
        this.buyerEmail = value;
    }

    /**
     * Gets the value of the buyerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Sets the value of the buyerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerName(String value) {
        this.buyerName = value;
    }

    /**
     * Gets the value of the cbaDisplayableShippingLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbaDisplayableShippingLabel() {
        return cbaDisplayableShippingLabel;
    }

    /**
     * Sets the value of the cbaDisplayableShippingLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbaDisplayableShippingLabel(String value) {
        this.cbaDisplayableShippingLabel = value;
    }

    /**
     * Gets the value of the earliestDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestDeliveryDate() {
        return earliestDeliveryDate;
    }

    /**
     * Sets the value of the earliestDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestDeliveryDate(XMLGregorianCalendar value) {
        this.earliestDeliveryDate = value;
    }

    /**
     * Gets the value of the earliestShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestShipDate() {
        return earliestShipDate;
    }

    /**
     * Sets the value of the earliestShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestShipDate(XMLGregorianCalendar value) {
        this.earliestShipDate = value;
    }

    /**
     * Gets the value of the fulfillmentChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFulfillmentChannel() {
        return fulfillmentChannel;
    }

    /**
     * Sets the value of the fulfillmentChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFulfillmentChannel(String value) {
        this.fulfillmentChannel = value;
    }

    /**
     * Gets the value of the lastUpdateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the value of the lastUpdateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateDate(XMLGregorianCalendar value) {
        this.lastUpdateDate = value;
    }

    /**
     * Gets the value of the latestDeliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestDeliveryDate() {
        return latestDeliveryDate;
    }

    /**
     * Sets the value of the latestDeliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestDeliveryDate(XMLGregorianCalendar value) {
        this.latestDeliveryDate = value;
    }

    /**
     * Gets the value of the latestShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestShipDate() {
        return latestShipDate;
    }

    /**
     * Sets the value of the latestShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestShipDate(XMLGregorianCalendar value) {
        this.latestShipDate = value;
    }

    /**
     * Gets the value of the marketplaceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketplaceId() {
        return marketplaceId;
    }

    /**
     * Sets the value of the marketplaceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketplaceId(String value) {
        this.marketplaceId = value;
    }

    /**
     * Gets the value of the numberOfItemsShipped property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfItemsShipped() {
        return numberOfItemsShipped;
    }

    /**
     * Sets the value of the numberOfItemsShipped property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfItemsShipped(Integer value) {
        this.numberOfItemsShipped = value;
    }

    /**
     * Gets the value of the numberOfItemsUnshipped property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfItemsUnshipped() {
        return numberOfItemsUnshipped;
    }

    /**
     * Sets the value of the numberOfItemsUnshipped property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfItemsUnshipped(Integer value) {
        this.numberOfItemsUnshipped = value;
    }

    /**
     * Gets the value of the orderChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderChannel() {
        return orderChannel;
    }

    /**
     * Sets the value of the orderChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderChannel(String value) {
        this.orderChannel = value;
    }

    /**
     * Gets the value of the orderItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderItem }
     * 
     * 
     */
    public List<OrderItem> getOrderItems() {
        if (orderItems == null) {
            orderItems = new ArrayList<OrderItem>();
        }
        return this.orderItems;
    }

    /**
     * Gets the value of the orderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatus(String value) {
        this.orderStatus = value;
    }

    /**
     * Gets the value of the orderTotal property.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getOrderTotal() {
        return orderTotal;
    }

    /**
     * Sets the value of the orderTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setOrderTotal(Money value) {
        this.orderTotal = value;
    }

    /**
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the paymentExecutionDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentExecutionDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentExecutionDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentExecutionDetailItem }
     * 
     * 
     */
    public List<PaymentExecutionDetailItem> getPaymentExecutionDetail() {
        if (paymentExecutionDetail == null) {
            paymentExecutionDetail = new ArrayList<PaymentExecutionDetailItem>();
        }
        return this.paymentExecutionDetail;
    }

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethod(String value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPurchaseDate(XMLGregorianCalendar value) {
        this.purchaseDate = value;
    }

    /**
     * Gets the value of the salesChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesChannel() {
        return salesChannel;
    }

    /**
     * Sets the value of the salesChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesChannel(String value) {
        this.salesChannel = value;
    }

    /**
     * Gets the value of the sellerOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellerOrderId() {
        return sellerOrderId;
    }

    /**
     * Sets the value of the sellerOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellerOrderId(String value) {
        this.sellerOrderId = value;
    }

    /**
     * Gets the value of the shipServiceLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipServiceLevel() {
        return shipServiceLevel;
    }

    /**
     * Sets the value of the shipServiceLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipServiceLevel(String value) {
        this.shipServiceLevel = value;
    }

    /**
     * Gets the value of the shipmentServiceLevelCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentServiceLevelCategory() {
        return shipmentServiceLevelCategory;
    }

    /**
     * Sets the value of the shipmentServiceLevelCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentServiceLevelCategory(String value) {
        this.shipmentServiceLevelCategory = value;
    }

    /**
     * Gets the value of the shippedByAmazonTFM property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShippedByAmazonTFM() {
        return shippedByAmazonTFM;
    }

    /**
     * Sets the value of the shippedByAmazonTFM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShippedByAmazonTFM(Boolean value) {
        this.shippedByAmazonTFM = value;
    }

    /**
     * Gets the value of the shippingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Sets the value of the shippingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setShippingAddress(Address value) {
        this.shippingAddress = value;
    }

    /**
     * Gets the value of the tfmShipmentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTfmShipmentStatus() {
        return tfmShipmentStatus;
    }

    /**
     * Sets the value of the tfmShipmentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTfmShipmentStatus(String value) {
        this.tfmShipmentStatus = value;
    }

}