package com.itecheasy.webservice.cms.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for orderTrackingItem complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="orderTrackingItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alarmTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="assignTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="assignUserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="auditCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auditTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="auditUserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="branchRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="branchRemarkOperaterTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="branchRemarkOperatorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="csContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="operatorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="orderTrackingAttachments" type="{http://osms.communication.itecheasy.com/}orderTrackingAttachment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderTrackingId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="predictDeliveryTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="productOperatorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="replyContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="replyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="replyUserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderTrackingItem", propOrder = { "alarmTime", "assignTime", "assignUserId", "auditCause",
		"auditTime", "auditUserId", "branchRemark", "branchRemarkOperaterTime", "branchRemarkOperatorId", "content",
		"csContent", "id", "operatorId", "orderTrackingAttachments", "orderTrackingId", "predictDeliveryTime",
		"productId", "productOperatorId", "remark", "replyContent", "replyTime", "replyUserId", "status" })
public class OrderTrackingItem {

	
	public void setOrderTrackingAttachments(List<OrderTrackingAttachment> orderTrackingAttachments) {
		this.orderTrackingAttachments = orderTrackingAttachments;
	}

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar alarmTime;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar assignTime;
	protected Integer assignUserId;
	protected String auditCause;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar auditTime;
	protected Integer auditUserId;
	protected String branchRemark;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar branchRemarkOperaterTime;
	protected Integer branchRemarkOperatorId;
	protected String content;
	protected String csContent;
	protected Integer id;
	protected Integer operatorId;
	@XmlElement(nillable = true)
	protected List<OrderTrackingAttachment> orderTrackingAttachments;
	protected Integer orderTrackingId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar predictDeliveryTime;
	protected Integer productId;
	protected Integer productOperatorId;
	protected String remark;
	protected String replyContent;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar replyTime;
	protected Integer replyUserId;
	protected Integer status;

	/**
	 * Gets the value of the alarmTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getAlarmTime() {
		return alarmTime;
	}

	/**
	 * Sets the value of the alarmTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setAlarmTime(XMLGregorianCalendar value) {
		this.alarmTime = value;
	}

	/**
	 * Gets the value of the assignTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getAssignTime() {
		return assignTime;
	}

	/**
	 * Sets the value of the assignTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setAssignTime(XMLGregorianCalendar value) {
		this.assignTime = value;
	}

	/**
	 * Gets the value of the assignUserId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAssignUserId() {
		return assignUserId;
	}

	/**
	 * Sets the value of the assignUserId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAssignUserId(Integer value) {
		this.assignUserId = value;
	}

	/**
	 * Gets the value of the auditCause property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuditCause() {
		return auditCause;
	}

	/**
	 * Sets the value of the auditCause property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuditCause(String value) {
		this.auditCause = value;
	}

	/**
	 * Gets the value of the auditTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getAuditTime() {
		return auditTime;
	}

	/**
	 * Sets the value of the auditTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setAuditTime(XMLGregorianCalendar value) {
		this.auditTime = value;
	}

	/**
	 * Gets the value of the auditUserId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAuditUserId() {
		return auditUserId;
	}

	/**
	 * Sets the value of the auditUserId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAuditUserId(Integer value) {
		this.auditUserId = value;
	}

	/**
	 * Gets the value of the branchRemark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBranchRemark() {
		return branchRemark;
	}

	/**
	 * Sets the value of the branchRemark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBranchRemark(String value) {
		this.branchRemark = value;
	}

	/**
	 * Gets the value of the branchRemarkOperaterTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getBranchRemarkOperaterTime() {
		return branchRemarkOperaterTime;
	}

	/**
	 * Sets the value of the branchRemarkOperaterTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setBranchRemarkOperaterTime(XMLGregorianCalendar value) {
		this.branchRemarkOperaterTime = value;
	}

	/**
	 * Gets the value of the branchRemarkOperatorId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getBranchRemarkOperatorId() {
		return branchRemarkOperatorId;
	}

	/**
	 * Sets the value of the branchRemarkOperatorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setBranchRemarkOperatorId(Integer value) {
		this.branchRemarkOperatorId = value;
	}

	/**
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the csContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCsContent() {
		return csContent;
	}

	/**
	 * Sets the value of the csContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCsContent(String value) {
		this.csContent = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setId(Integer value) {
		this.id = value;
	}

	/**
	 * Gets the value of the operatorId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getOperatorId() {
		return operatorId;
	}

	/**
	 * Sets the value of the operatorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setOperatorId(Integer value) {
		this.operatorId = value;
	}

	/**
	 * Gets the value of the orderTrackingAttachments property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the orderTrackingAttachments property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOrderTrackingAttachments().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OrderTrackingAttachment }
	 * 
	 * 
	 */
	public List<OrderTrackingAttachment> getOrderTrackingAttachments() {
		if (orderTrackingAttachments == null) {
			orderTrackingAttachments = new ArrayList<OrderTrackingAttachment>();
		}
		return this.orderTrackingAttachments;
	}

	/**
	 * Gets the value of the orderTrackingId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getOrderTrackingId() {
		return orderTrackingId;
	}

	/**
	 * Sets the value of the orderTrackingId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setOrderTrackingId(Integer value) {
		this.orderTrackingId = value;
	}

	/**
	 * Gets the value of the predictDeliveryTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getPredictDeliveryTime() {
		return predictDeliveryTime;
	}

	/**
	 * Sets the value of the predictDeliveryTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setPredictDeliveryTime(XMLGregorianCalendar value) {
		this.predictDeliveryTime = value;
	}

	/**
	 * Gets the value of the productId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Sets the value of the productId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setProductId(Integer value) {
		this.productId = value;
	}

	/**
	 * Gets the value of the productOperatorId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getProductOperatorId() {
		return productOperatorId;
	}

	/**
	 * Sets the value of the productOperatorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setProductOperatorId(Integer value) {
		this.productOperatorId = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemark(String value) {
		this.remark = value;
	}

	/**
	 * Gets the value of the replyContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReplyContent() {
		return replyContent;
	}

	/**
	 * Sets the value of the replyContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReplyContent(String value) {
		this.replyContent = value;
	}

	/**
	 * Gets the value of the replyTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getReplyTime() {
		return replyTime;
	}

	/**
	 * Sets the value of the replyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setReplyTime(XMLGregorianCalendar value) {
		this.replyTime = value;
	}

	/**
	 * Gets the value of the replyUserId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getReplyUserId() {
		return replyUserId;
	}

	/**
	 * Sets the value of the replyUserId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setReplyUserId(Integer value) {
		this.replyUserId = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setStatus(Integer value) {
		this.status = value;
	}

}