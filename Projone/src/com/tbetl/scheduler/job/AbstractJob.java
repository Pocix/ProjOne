package com.tbetl.scheduler.job;

import javax.annotation.Resource;

import com.tbetl.dao.DaoSupport;

public abstract class AbstractJob {

	@Resource(name = "daoSupport")
	public DaoSupport dao;
}
