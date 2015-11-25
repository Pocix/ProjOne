/**
 * This file created at 2015-7-28.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.entity.business;

import java.util.Date;

/**
 * <code>{@link GPlogInfo}</code>
 * 
 * TODO : document me
 * 
 * @author Administrator
 * @description GPLogCollection表
 */
public class GPlogInfo {
	
	private String id;

	private String name;

	private String code;

	private Date buyDate;

	private Date SaleDate;

	private double price;

	private int amount;

	private double sales;

	private double dp;

	private String remark;

	/**
	 * @return the name
	 */
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the buyDate
	 */
	public Date getBuyDate() {
		return buyDate;
	}

	/**
	 * @param buyDate the buyDate to set
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * @return the saleDate
	 */
	public Date getSaleDate() {
		return SaleDate;
	}

	/**
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(Date saleDate) {
		SaleDate = saleDate;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	/**
	 * @return the sales
	 */
	public double getSales() {
		return sales;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}

	/**
	 * @return the dp
	 */
	public double getDp() {
		return dp;
	}

	/**
	 * @param dp the dp to set
	 */
	public void setDp(double dp) {
		this.dp = dp;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
}
