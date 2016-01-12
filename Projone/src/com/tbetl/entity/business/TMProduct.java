/**
 * This file created at 2015-6-29.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.entity.business;

import java.util.Date;

/**
 * <code>{@link TMProduct}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 * @description TBcollection表
 */
public class TMProduct{

	private String uid;
	private String name;
	private String pid;
	private Date createdate;
	private String remark;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minutes;
	private String second;
	/** 销售价 */
	private String price;
	/** 促销价*/
	private String tbprice;
	/** 收藏 */
	private String trove;
	/** 累计评价 */
	private String evaluateCount;
	/** 成交记录 */
	private String tradeCount;
	/**  运费 */
	private String postageToggleCount;
	/** 积分 */
	private String itemRates;
	/**  库存 */
	private String emStock;
	/** 月销量 */
	private String tradeCountForM;
	/** 当日销量 */
	private String tradeCountForD;
	/** 链接 */
	private String url;

	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createdate
	 */
	public Date getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the sales
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the tbprice
	 */
	public String getTbprice() {
		return tbprice;
	}

	/**
	 * @param tbprice the tbprice to set
	 */
	public void setTbprice(String tbprice) {
		this.tbprice = tbprice;
	}

	/**
	 * @return the trove
	 */
	public String getTrove() {
		return trove;
	}

	/**
	 * @param trove the trove to set
	 */
	public void setTrove(String trove) {
		this.trove = trove;
	}

	/**
	 * @return the evaluateCount
	 */
	public String getEvaluateCount() {
		return evaluateCount;
	}

	/**
	 * @param evaluateCount the evaluateCount to set
	 */
	public void setEvaluateCount(String evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	/**
	 * @return the tradeCount
	 */
	public String getTradeCount() {
		return tradeCount;
	}

	/**
	 * @param tradeCount the tradeCount to set
	 */
	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}

	/**
	 * @return the tradeCountFor30
	 */
	public String getTradeCountForM() {
		return tradeCountForM;
	}

	/**
	 * @param tradeCountFor30 the tradeCountFor30 to set
	 */
	public void setTradeCountForM(String tradeCountForM) {
		this.tradeCountForM = tradeCountForM;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getPostageToggleCount() {
		return postageToggleCount;
	}

	public void setPostageToggleCount(String postageToggleCount) {
		this.postageToggleCount = postageToggleCount;
	}

	public String getItemRates() {
		return itemRates;
	}

	public void setItemRates(String itemRates) {
		this.itemRates = itemRates;
	}

	public String getEmStock() {
		return emStock;
	}

	public void setEmStock(String emStock) {
		this.emStock = emStock;
	}

	public String getTradeCountForD() {
		return tradeCountForD;
	}

	public void setTradeCountForD(String tradeCountForD) {
		this.tradeCountForD = tradeCountForD;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}
}
