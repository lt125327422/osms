package com.itecheasy.core.report;

import java.util.Date;

/**
 * @author taozihao
 * @date 2018年9月3日 下午5:26:40
 * @description
 */
public class ReportAutotaskRecordVO {
	private Integer id;
	private Integer shopId;
	private Integer shopDevInfoId;
	/**
	 * 报告类型
	 */
	private String reportType;
	/**
	 * 报告限制下载日期
	 */
	private Integer limitDays;
	/**
	 * 上次触发时间
	 */
	private Date lastFireTime;
	/**
	 * 0待处理，1已处理，2异常
	 */
	private Integer status;
	/**
	 * 失败次数
	 */
	private Integer failedTimes;
	/**
	 * 创建日期
	 */
	private Date createTime;
	
	private String note; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopDevInfoId() {
		return shopDevInfoId;
	}

	public void setShopDevInfoId(Integer shopDevInfoId) {
		this.shopDevInfoId = shopDevInfoId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Integer getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}

	public Date getLastFireTime() {
		return lastFireTime;
	}

	public void setLastFireTime(Date lastFireTime) {
		this.lastFireTime = lastFireTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFailedTimes() {
		return failedTimes;
	}

	public void setFailedTimes(Integer failedTimes) {
		this.failedTimes = failedTimes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
