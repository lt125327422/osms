package com.itecheasy.core.system;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wanghw
 * @date 2015-3-23
 * @description TODO
 * @version
 */
public class Shop {
	private int id;
	private String name;
	private int type;
	private int language;
	private Integer department;
	private Date lastUpdatedAfter;
	/**
	 * 品牌
	 */
	private boolean notUPC;

	private Integer shopDevInfoId;
	private boolean enable;

	private String merchandiserId;

	private String marketplaceID;

	private int currency;

	private int firstWayCountry;
	
	/**
	 * 是否填充省
	 * 省为空时自动将城市填充到省中
	 */
	private boolean fillState;
	
	private Integer customerId;
	
	/**
	 * 海运安全库存周期
	 */
	private Double seaTransSecurityStockPeriod;
	
	/**
	 * 海运预计货运天数
	 */
	private Integer seaTransDays;


	/**
	 * 是否同步亚马逊库存
	 * 0不同步
	 * 1同步
	 */
	private Integer isSyncAmazonStockReport;


	/**
	 * 不同的店铺不同的计算底价方式
	 */
	private BigDecimal calculateBasePrice;

	public Integer getIsSyncAmazonStockReport() {
		return isSyncAmazonStockReport;
	}

	public void setIsSyncAmazonStockReport(Integer isSyncAmazonStockReport) {
		this.isSyncAmazonStockReport = isSyncAmazonStockReport;
	}

	public BigDecimal getCalculateBasePrice() {
		return calculateBasePrice;
	}

	public void setCalculateBasePrice(BigDecimal calculateBasePrice) {
		this.calculateBasePrice = calculateBasePrice;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type=" + type +
				", language=" + language +
				", department=" + department +
				", lastUpdatedAfter=" + lastUpdatedAfter +
				", notUPC=" + notUPC +
				", shopDevInfoId=" + shopDevInfoId +
				", enable=" + enable +
				", merchandiserId='" + merchandiserId + '\'' +
				", marketplaceID='" + marketplaceID + '\'' +
				", currency=" + currency +
				", firstWayCountry=" + firstWayCountry +
				", fillState=" + fillState +
				", customerId=" + customerId +
				", seaTransSecurityStockPeriod=" + seaTransSecurityStockPeriod +
				", seaTransDays=" + seaTransDays +
				'}';
	}

	public int getFirstWayCountry() {
		return firstWayCountry;
	}

	public void setFirstWayCountry(int firstWayCountry) {
		this.firstWayCountry = firstWayCountry;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public String getMarketplaceID() {
		return marketplaceID;
	}

	public void setMarketplaceID(String marketplaceID) {
		this.marketplaceID = marketplaceID;
	}

	public boolean isNotUPC() {
		return notUPC;
	}

	public void setNotUPC(boolean notUPC) {
		this.notUPC = notUPC;
	}

	public String getMerchandiserId() {
		return merchandiserId;
	}

	public void setMerchandiserId(String merchandiserId) {
		this.merchandiserId = merchandiserId;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public Integer getShopDevInfoId() {
		return shopDevInfoId;
	}

	public void setShopDevInfoId(Integer shopDevInfoId) {
		this.shopDevInfoId = shopDevInfoId;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getLastUpdatedAfter() {
		return lastUpdatedAfter;
	}

	public void setLastUpdatedAfter(Date lastUpdatedAfter) {
		this.lastUpdatedAfter = lastUpdatedAfter;
	}

	public boolean isFillState() {
		return fillState;
	}

	public void setFillState(boolean fillState) {
		this.fillState = fillState;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getSeaTransSecurityStockPeriod() {
		return seaTransSecurityStockPeriod;
	}

	public void setSeaTransSecurityStockPeriod(Double seaTransSecurityStockPeriod) {
		this.seaTransSecurityStockPeriod = seaTransSecurityStockPeriod;
	}

	public Integer getSeaTransDays() {
		return seaTransDays;
	}

	public void setSeaTransDays(Integer seaTransDays) {
		this.seaTransDays = seaTransDays;
	}
}
