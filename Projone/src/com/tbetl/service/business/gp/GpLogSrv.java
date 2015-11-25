/**
 * This file created at 2015-7-28.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.service.business.gp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tbetl.entity.business.GPlogInfo;
import com.tbetl.service.AbstractService;
import com.tbetl.util.PageData;

/**
 * <code>{@link GpLogSrv}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
@Service("gpLogSrv")
public class GpLogSrv extends AbstractService{

	public List<GPlogInfo> queryAll(){
		return dao2mongo.queryAllGpLog();
	}
	
	public void insLog(GPlogInfo inf){
		dao2mongo.insGpLog(inf);
	}
	
	public GPlogInfo findById(PageData pd){
		return dao2mongo.findById(pd);
	}
	
	public void saveL(PageData pd){
		dao2mongo.saveL(pd);
	}
}
