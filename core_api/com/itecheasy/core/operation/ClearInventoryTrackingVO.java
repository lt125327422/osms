package com.itecheasy.core.operation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author taozihao
 * @date 2018年8月24日 下午3:45:00
 * @description	清仓跟踪
 */
public class ClearInventoryTrackingVO {
	private int id;
	private int shopId;
	private int fbaShopProductId;
	private String sku;
	private String cmsProductCode;
	private boolean selectable;
	/**
	 * 商品图片
	 */
	private String primaryPictureCode;
	/**
	 * 是否本周关注
	 */
	private int isThisWeekConcerned;
	/**
	 * 初始库存
	 */
	private BigDecimal initialInventory;
	/**
	 * 初始售价
	 */
	private BigDecimal initialSalePrice;
	/**
	 * 初始降价时间
	 */
	private Date firstPriceOffDate;
	/**
	 * 库存
	 */
	private BigDecimal inventory;
	/**
	 * 月销量
	 */
	private BigDecimal unitsShippedLast30Days;
	/**
	 * 售完时间
	 */
	private BigDecimal sellOutDays;
	/**
	 * 剩余时间
	 */
	private Integer daysLeft;
	/**
	 * 降价幅度
	 */
	private int priceOffPercent;
	/**
	 * 售完状态 0不能售完 1可以售完
	 */
	private Integer sellOutStatus;
	/**
	 * 降价次数
	 */
	private int priceOffTimes;
	/**
	 * 底价
	 */
	private BigDecimal basePrice;
	/**
	 * 货币单位
	 */
	private String currency;
	/**
	 * 库龄报告快照日期
	 */
	private Date snapshotDate;
	
	private String fnsku;
	private String asin;
	/**
	 * 降价时间，售价备注
	 */
	private List<ClearInventoryTrackingRecordVO> clearInventoryTrackingRecords;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getFbaShopProductId() {
		return fbaShopProductId;
	}

	public void setFbaShopProductId(int fbaShopProductId) {
		this.fbaShopProductId = fbaShopProductId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getIsThisWeekConcerned() {
		return isThisWeekConcerned;
	}

	public void setIsThisWeekConcerned(int isThisWeekConcerned) {
		this.isThisWeekConcerned = isThisWeekConcerned;
	}

	public String getCmsProductCode() {
		return cmsProductCode;
	}

	public void setCmsProductCode(String cmsProductCode) {
		this.cmsProductCode = cmsProductCode;
	}

	public String getPrimaryPictureCode() {
		return primaryPictureCode;
	}

	public void setPrimaryPictureCode(String primaryPictureCode) {
		this.primaryPictureCode = primaryPictureCode;
	}

	public BigDecimal getInitialInventory() {
		return initialInventory;
	}

	public void setInitialInventory(BigDecimal initialInventory) {
		this.initialInventory = initialInventory;
	}

	public BigDecimal getInitialSalePrice() {
		return initialSalePrice;
	}

	public void setInitialSalePrice(BigDecimal initialSalePrice) {
		this.initialSalePrice = initialSalePrice;
	}

	public Date getFirstPriceOffDate() {
		return firstPriceOffDate;
	}

	public void setFirstPriceOffDate(Date firstPriceOffDate) {
		this.firstPriceOffDate = firstPriceOffDate;
	}

	public BigDecimal getInventory() {
		return inventory;
	}

	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}

	public BigDecimal getUnitsShippedLast30Days() {
		return unitsShippedLast30Days;
	}

	public void setUnitsShippedLast30Days(BigDecimal unitsShippedLast30Days) {
		this.unitsShippedLast30Days = unitsShippedLast30Days;
	}

	public BigDecimal getSellOutDays() {
		return sellOutDays;
	}

	public void setSellOutDays(BigDecimal sellOutDays) {
		this.sellOutDays = sellOutDays;
	}

	public Integer getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(Integer daysLeft) {
		this.daysLeft = daysLeft;
	}

	public int getPriceOffPercent() {
		return priceOffPercent;
	}

	public void setPriceOffPercent(int priceOffPercent) {
		this.priceOffPercent = priceOffPercent;
	}

	public Integer getSellOutStatus() {
		return sellOutStatus;
	}

	public void setSellOutStatus(Integer sellOutStatus) {
		this.sellOutStatus = sellOutStatus;
	}

	public int getPriceOffTimes() {
		return priceOffTimes;
	}

	public void setPriceOffTimes(int priceOffTimes) {
		this.priceOffTimes = priceOffTimes;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<ClearInventoryTrackingRecordVO> getClearInventoryTrackingRecords() {
		return clearInventoryTrackingRecords;
	}

	public void setClearInventoryTrackingRecords(List<ClearInventoryTrackingRecordVO> clearInventoryTrackingRecords) {
		this.clearInventoryTrackingRecords = clearInventoryTrackingRecords;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public String getFnsku() {
		return fnsku;
	}

	public void setFnsku(String fnsku) {
		this.fnsku = fnsku;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

}
