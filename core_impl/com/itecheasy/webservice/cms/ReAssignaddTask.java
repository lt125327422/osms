
package com.itecheasy.webservice.cms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>reAssignaddTask complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="reAssignaddTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operater" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reAssignaddTask", propOrder = {
    "taskId",
    "qty",
    "remark",
    "operater"
})
public class ReAssignaddTask {

    protected int taskId;
    protected int qty;
    protected String remark;
    protected String operater;

    /**
     * 获取taskId属性的值。
     * 
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * 设置taskId属性的值。
     * 
     */
    public void setTaskId(int value) {
        this.taskId = value;
    }

    /**
     * 获取qty属性的值。
     * 
     */
    public int getQty() {
        return qty;
    }

    /**
     * 设置qty属性的值。
     * 
     */
    public void setQty(int value) {
        this.qty = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取operater属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperater() {
        return operater;
    }

    /**
     * 设置operater属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperater(String value) {
        this.operater = value;
    }

}
