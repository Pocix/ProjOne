/**
 * This file created at 2015-7-6.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.controller.business.salerAnalysis;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbetl.controller.base.BaseController;
import com.tbetl.service.business.salerAnalysis.SalerAnalysisServ;

/**
 * <code>{@link SalerAnalyseCtr}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value="/saler")
public class SalerAnalyseCtr extends BaseController{
	
	@Resource(name="salerAnalysisServ")
	private SalerAnalysisServ salerAnalysisSev;

	@RequestMapping(value="/Test_default")
	public ModelAndView queryAllProduct(){
		ModelAndView mv = this.getModelAndView();
		mv.addObject("productList", salerAnalysisSev.queryAllProduct());
		mv.setViewName("busi/productList");
		return mv;
	}
}
