package com.tbetl.entity.business;

import java.util.Date;

public class ShopItem {

	private String uid;
	private String name;
	private String status;
	private Date createtime;
	private Date effectivedate;
	private String url;
	private String is_intask;
	private String p_uid;
	private String user_uid;
	private String is_init;
	
	public String getIs_init() {
		return is_init;
	}
	public void setIs_init(String is_init) {
		this.is_init = is_init;
	}
	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIs_intask() {
		return is_intask;
	}
	public void setIs_intask(String is_intask) {
		this.is_intask = is_intask;
	}
	public String getP_uid() {
		return p_uid;
	}
	public void setP_uid(String p_uid) {
		this.p_uid = p_uid;
	}
}
