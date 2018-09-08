package com.itecheasy.core.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.itecheasy.core.PackageStatus.PackageTraceStatus;
import com.itecheasy.core.system.Currency;

/**
 * @author wanghw
 * @date 2015-5-6
 * @description 订单详细
 * @version 1.1
 */
public class OrderDetail {
	private OrderAddress orderAddress;
	private List<OrderProduct> orderItems;

	private int id;
	/**
	 * 订单编号
	 * 
	 */
	private String code;
	/**
	 * 国家
	 * 
	 */
	private Integer country;
	/**
	 * 网站客户账号
	 * 
	 */
	private String customerAccount;
	/**
	 * 客户名称
	 * 
	 */
	private String customerName;
	/**
	 * 是否可发货
	 * 
	 */
	private Boolean isSend;
	/**
	 * 订单状态：准备货物(1), 已备货(31), 待校对(40), 已打包(55), 已出库(60), 已发货(64), 完成(128),
	 * 取消(132);
	 * 
	 */
	private int status;

	//平台货运方式
	private String shopShippingMethod;
	/**
	 * 货运方式
	 * 
	 */
	private int shippingMethod;

	/**
	 * 店铺id
	 * 1	Amazon_US
	 * 2	Amazon_FR
	 * 5	Amazon_CA
	 * 6	Amazon_ES
	 * 7	Amazon_IT
	 * 8	Amazon_DE
	 * 9	Amazon_UK
	 * 10	Amazon_JP
	 * 18	ebay1
	 * 19	速卖通
	 * 20	BBC
	 * 21	CurrencyTest
	 *
	 *
	 */
	private int shopId;
	/**
	 * 订单金额
	 * 
	 */
	private double orderAmount;
	/**
	 * dms发货成本 rmb
	 * 
	 */
	private double costFreight;
	/**
	 * 成本价(订单中实际出库的商品基准成本价之和 )
	 * 
	 */
	private double costPrice;
	/**
	 * 同票id
	 * 
	 */
	private Integer sendGroupId;
	/**
	 * 跟踪单号
	 * 
	 */
	private String trackingCode;
	/**
	 * 订单备注
	 * 
	 */
	private String remark;
	/**
	 * cms出库时间
	 * 
	 */
	private Date outDate;
	/**
	 * dms发货时间
	 * 
	 */
	private Date deliveryDate;
	/**
	 * 网店订单编号
	 * 
	 */
	private String shopOrderCode;
	/**
	 * 网店订单状态
	 * 
	 */
	private String shopOrderStatus;
	/**
	 * 网店下单时间
	 * 
	 */
	private Date shopOrderDate;
	/**
	 * 订单项总数
	 * 
	 */
	private int orderItemNum;
	/**
	 * 订单项备货总数
	 * 
	 */
	private int orderItemPrepareNum;
	/**
	 * 网店平台
	 * 
	 */
	private int shopType;
	/**
	 * 订单币种
	 * 
	 */
	private int orderCurrency;
	/**
	 * 预计交货日期最后一天
	 * 
	 */
	private Date latestDeliveryDate;
	/**
	 * 预计交货日期第一天
	 * 
	 */
	private Date earliestDeliveryDate;
	/**
	 * 预计发货日期最后一天
	 * 
	 */
	private Date latestShipDate;
	/**
	 * 预计发货日期第一天
	 * 
	 */
	private Date earliestShippingDate;

	/**
	 * amazon订单表id
	 */
	private Integer orderAmazonId;

	/**
	 * 订单运费
	 */
	private Double shopShippingPrice;

	/**
	 * 税号
	 */
	private String CPFCode;

	/**
	 * 是否第一次下单
	 */
	private boolean firstWebOrder;

	/**
	 * 是否质检
	 */
	private int isQualityInspection;

	/**
	 * 报关运费百分比
	 */
	private BigDecimal reimburseFreightPercent;
	
	/**
	 * 发票金额百分比
	 */
	private BigDecimal subTotalPercent;
	

	/**
	 * 跟单员
	 */
	private Integer merchandiserId;

	/**
	 * 是否偏远
	 */
	private Integer isRemote;

	/**
	 * cms订单货运部门
	 */
	private Integer Logistics;

	/**
	 * 网站重量
	 */
	private Integer webWeight;

	/**
	 * 税号类型
	 */
	private Integer CPFType;

	/**
	 * 是否cms备货
	 */
	private int cmsPrepare;

	/**
	 * 问题状态
	 */
	private int problemStatus;

	/**
	 * 问题类型
	 */
	private int problemType;

	/**
	 * 问题总值
	 */
	private int problemVal;

	/**
	 * AMAZON FBA订单类型:<br>
	 * <em>AFN、MFN<em>
	 */
	private String fulfillmentChannel;

	private String deliveryCustomerService;

	private boolean electronic;

	/**
	 * 重发 待确认
	 */
	private boolean awaitConfirmResend;

	/**
	 * 取消 待确认
	 */
	private boolean awaitConfirmCancel;

	/**
	 * 订单物流状态
	 */
	private PackageTraceStatus packageStatus;

	/**
	 * 系统增加订单时间
	 */
	private Date addOrderDate;

	private boolean awaitPutaway;

	private String shopBuyerId;

	private String shopOrderId;

	private String shopSellRecordNumber;
	private String shopOrderLineItemId;

	private String shopBuyerEmail;
	private String shopPaymentMethod;

	private String trackingUrl;
	
	private Boolean remote;

	/**
	 * fba的复制单
	 */
	private Boolean copy;
	
	/**
	 * 手动录单
	 * v 1.2.8
	 */
	private Boolean manual;
	/**
	 * 其他系统订单
	 */
	private Boolean isOtherSystem;


	private String labelRemark;

	public String getLabelRemark() {
		return labelRemark;
	}

	public void setLabelRemark(String labelRemark) {
		this.labelRemark = labelRemark;
	}


	//	private Currency currency;
//
//
//	public Currency getCurrency() {
//		return currency;
//	}
//
//	public void setCurrency(Currency currency) {
//		this.currency = currency;
//	}

	public String getShopPaymentMethod() {
		return shopPaymentMethod;
	}

	public void setShopPaymentMethod(String shopPaymentMethod) {
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public String getShopBuyerEmail() {
		return shopBuyerEmail;
	}

	public void setShopBuyerEmail(String shopBuyerEmail) {
		this.shopBuyerEmail = shopBuyerEmail;
	}

	public String getShopSellRecordNumber() {
		return shopSellRecordNumber;
	}

	public void setShopSellRecordNumber(String shopSellRecordNumber) {
		this.shopSellRecordNumber = shopSellRecordNumber;
	}

	public String getShopOrderLineItemId() {
		return shopOrderLineItemId;
	}

	public void setShopOrderLineItemId(String shopOrderLineItemId) {
		this.shopOrderLineItemId = shopOrderLineItemId;
	}

	public String getShopOrderId() {
		return shopOrderId;
	}

	public void setShopOrderId(String shopOrderId) {
		this.shopOrderId = shopOrderId;
	}

	public String getShopBuyerId() {
		return shopBuyerId;
	}

	public void setShopBuyerId(String shopBuyerId) {
		this.shopBuyerId = shopBuyerId;
	}

	public boolean isAwaitPutaway() {
		return awaitPutaway;
	}

	public void setAwaitPutaway(boolean awaitPutaway) {
		this.awaitPutaway = awaitPutaway;
	}

	public Date getAddOrderDate() {
		return addOrderDate;
	}

	public void setAddOrderDate(Date addOrderDate) {
		this.addOrderDate = addOrderDate;
	}

	public boolean isAwaitConfirmResend() {
		return awaitConfirmResend;
	}

	public void setAwaitConfirmResend(boolean awaitConfirmResend) {
		this.awaitConfirmResend = awaitConfirmResend;
	}

	public boolean isAwaitConfirmCancel() {
		return awaitConfirmCancel;
	}

	public void setAwaitConfirmCancel(boolean awaitConfirmCancel) {
		this.awaitConfirmCancel = awaitConfirmCancel;
	}

	public PackageTraceStatus getPackageStatus() {
		return packageStatus;
	}

	public void setPackageStatus(PackageTraceStatus packageStatus) {
		this.packageStatus = packageStatus;
	}

	public boolean isElectronic() {
		return electronic;
	}

	public void setElectronic(boolean electronic) {
		this.electronic = electronic;
	}

	public String getDeliveryCustomerService() {
		return deliveryCustomerService;
	}

	public void setDeliveryCustomerService(String deliveryCustomerService) {
		this.deliveryCustomerService = deliveryCustomerService;
	}

	public String getFulfillmentChannel() {
		return fulfillmentChannel;
	}

	public void setFulfillmentChannel(String fulfillmentChannel) {
		this.fulfillmentChannel = fulfillmentChannel;
	}

	public int getProblemVal() {
		return problemVal;
	}

	public void setProblemVal(int problemVal) {
		this.problemVal = problemVal;
	}

	public int getProblemStatus() {
		return problemStatus;
	}

	public void setProblemStatus(int problemStatus) {
		this.problemStatus = problemStatus;
	}

	public int getProblemType() {
		return problemType;
	}

	public void setProblemType(int problemType) {
		this.problemType = problemType;
	}

	public int getCmsPrepare() {
		return cmsPrepare;
	}

	public void setCmsPrepare(int cmsPrepare) {
		this.cmsPrepare = cmsPrepare;
	}

	public Integer getCPFType() {
		return CPFType;
	}

	public void setCPFType(Integer cPFType) {
		CPFType = cPFType;
	}

	public Integer getWebWeight() {
		return webWeight;
	}

	public void setWebWeight(Integer webWeight) {
		this.webWeight = webWeight;
	}

	public Integer getLogistics() {
		return Logistics;
	}

	public void setLogistics(Integer logistics) {
		Logistics = logistics;
	}

	public Integer getIsRemote() {
		return isRemote;
	}

	public void setIsRemote(Integer isRemote) {
		this.isRemote = isRemote;
	}

	public String getCPFCode() {
		return CPFCode;
	}

	public void setCPFCode(String cPFCode) {
		CPFCode = cPFCode;
	}

	public boolean isFirstWebOrder() {
		return firstWebOrder;
	}

	public void setFirstWebOrder(boolean firstWebOrder) {
		this.firstWebOrder = firstWebOrder;
	}

	public int getIsQualityInspection() {
		return isQualityInspection;
	}

	public void setIsQualityInspection(int isQualityInspection) {
		this.isQualityInspection = isQualityInspection;
	}

	public BigDecimal getReimburseFreightPercent() {
		return reimburseFreightPercent;
	}

	public void setReimburseFreightPercent(BigDecimal reimburseFreightPercent) {
		this.reimburseFreightPercent = reimburseFreightPercent;
	}

	public Integer getMerchandiserId() {
		return merchandiserId;
	}

	public void setMerchandiserId(Integer merchandiserId) {
		this.merchandiserId = merchandiserId;
	}

	public Double getShopShippingPrice() {
		return shopShippingPrice;
	}

	public void setShopShippingPrice(Double shopShippingPrice) {
		this.shopShippingPrice = shopShippingPrice;
	}

	public Integer getOrderAmazonId() {
		return orderAmazonId;
	}

	public void setOrderAmazonId(Integer orderAmazonId) {
		this.orderAmazonId = orderAmazonId;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public List<OrderProduct> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderProduct> orderItems) {
		this.orderItems = orderItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Boolean getIsSend() {
		return isSend;
	}

	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(int shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public double getCostFreight() {
		return costFreight;
	}

	public void setCostFreight(double costFreight) {
		this.costFreight = costFreight;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getSendGroupId() {
		return sendGroupId;
	}

	public void setSendGroupId(Integer sendGroupId) {
		this.sendGroupId = sendGroupId;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getShopOrderCode() {
		return shopOrderCode;
	}

	public void setShopOrderCode(String shopOrderCode) {
		this.shopOrderCode = shopOrderCode;
	}

	public String getShopOrderStatus() {
		return shopOrderStatus;
	}

	public void setShopOrderStatus(String shopOrderStatus) {
		this.shopOrderStatus = shopOrderStatus;
	}

	public Date getShopOrderDate() {
		return shopOrderDate;
	}

	public void setShopOrderDate(Date shopOrderDate) {
		this.shopOrderDate = shopOrderDate;
	}

	public int getOrderItemNum() {
		return orderItemNum;
	}

	public void setOrderItemNum(int orderItemNum) {
		this.orderItemNum = orderItemNum;
	}

	public int getOrderItemPrepareNum() {
		return orderItemPrepareNum;
	}

	public void setOrderItemPrepareNum(int orderItemPrepareNum) {
		this.orderItemPrepareNum = orderItemPrepareNum;
	}

	public int getShopType() {
		return shopType;
	}

	public void setShopType(int shopType) {
		this.shopType = shopType;
	}

	public int getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(int orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public Date getLatestDeliveryDate() {
		return latestDeliveryDate;
	}

	public void setLatestDeliveryDate(Date latestDeliveryDate) {
		this.latestDeliveryDate = latestDeliveryDate;
	}

	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}

	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}

	public Date getLatestShipDate() {
		return latestShipDate;
	}

	public void setLatestShipDate(Date latestShipDate) {
		this.latestShipDate = latestShipDate;
	}

	public Date getEarliestShippingDate() {
		return earliestShippingDate;
	}

	public void setEarliestShippingDate(Date earliestShippingDate) {
		this.earliestShippingDate = earliestShippingDate;
	}

	public String getShopShippingMethod() {
		return shopShippingMethod;
	}

	public void setShopShippingMethod(String shopShippingMethod) {
		this.shopShippingMethod = shopShippingMethod;
	}

	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}
	
	
	public Boolean getRemote() {
		return remote;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public Boolean getCopy() {
		return copy;
	}

	public void setCopy(Boolean copy) {
		this.copy = copy;
	}

	public Boolean getManual() {
		return manual;
	}

	public void setManual(Boolean manual) {
		this.manual = manual;
	}

	public BigDecimal getSubTotalPercent() {
		return subTotalPercent;
	}

	public void setSubTotalPercent(BigDecimal subTotalPercent) {
		this.subTotalPercent = subTotalPercent;
	}

	public Boolean getIsOtherSystem() {
		return isOtherSystem;
	}

	public void setIsOtherSystem(Boolean isOtherSystem) {
		this.isOtherSystem = isOtherSystem;
	}
	
}
