
package com.itecheasy.webservice.cms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="storageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="��վ"/>
 *     &lt;enumeration value="fba���"/>
 *     &lt;enumeration value="���������"/>
 *     &lt;enumeration value="��ռ����"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "storageType")
@XmlEnum
public enum StorageType {

	网站("\u7f51\u7ad9"),
    @XmlEnumValue("fba\u5e93\u5b58")
	FBA_库存("fba\u5e93\u5b58"),
	自卖单库存("\u81ea\u5356\u5355\u5e93\u5b58"),
	抢占冻结("\u62a2\u5360\u51bb\u7ed3");
    private final String value;

    StorageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StorageType fromValue(String v) {
        for (StorageType c: StorageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}