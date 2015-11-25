/**
 * This file created at 2015-7-6.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.service.business.salerAnalysis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tbetl.entity.business.Product;
import com.tbetl.service.AbstractService;

/**
 * <code>{@link SalerAnalysisServ}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
@Service("salerAnalysisServ")
public class SalerAnalysisServ extends AbstractService{

	public List<Product> queryAllProduct(){
		return dao2mongo.queryALLPro();
	}
}
