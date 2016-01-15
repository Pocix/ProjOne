package com.tbetl.scheduler.job.thread;

import java.util.Calendar;

import com.tbetl.dao.DaoSupport;
import com.tbetl.util.UuidUtil;

public abstract class AbstractThread implements Runnable{

	
	private DaoSupport dao;
	
	public DaoSupport getDao() {
		return dao;
	}

	public void setDao(DaoSupport dao) {
		this.dao = dao;
	}
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	private Calendar calendar;
	
	public Calendar getCalendar(){
		return Calendar.getInstance();
	}
}
