package com.itecheasy.core.order;

/**
 * @author wanghw
 * @date 2015-6-24
 * @description TODO
 * @version
 */
public class UpdateOrderProductForm {
	private int fbaShopProductId;	//商品与fba的对应表

	private int orderProductId;		//这个是商品的id
	private String updateProductCode;	//cms product code

	private double qty;
	private int unitQty;
	private String productRemark;
	private String sku;

	private String productChineseName;	//品名中文

	private String	productEnglishName;	//品名英文

//	private Integer	hadSpareCapacity;	//已备量

//	public Integer getHadSpareCapacity() {
//		return hadSpareCapacity;
//	}
//
//	public void setHadSpareCapacity(Integer hadSpareCapacity) {
//		this.hadSpareCapacity = hadSpareCapacity;
//	}


	/**
	 * 实际备货数   (已备量)
	 *
	 */
	private double prepareQty;


	public double getPrepareQty() {
		return prepareQty;
	}

	public void setPrepareQty(double prepareQty) {
		this.prepareQty = prepareQty;
	}

	public String getProductChineseName() {
		return productChineseName;
	}

	public void setProductChineseName(String productChineseName) {
		this.productChineseName = productChineseName;
	}

	public String getProductEnglishName() {
		return productEnglishName;
	}

	public void setProductEnglishName(String productEnglishName) {
		this.productEnglishName = productEnglishName;
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

	public int getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(int unitQty) {
		this.unitQty = unitQty;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public int getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}

	public String getUpdateProductCode() {
		return updateProductCode;
	}

	public void setUpdateProductCode(String updateProductCode) {
		this.updateProductCode = updateProductCode;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

}
