package com.tbetl.scheduler.job;

import javax.annotation.Resource;

import com.tbetl.dao.DaoSupport;
import com.tbetl.util.UuidUtil;

public abstract class AbstractJob {

	@Resource(name = "daoSupport")
	public DaoSupport dao;
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
}
