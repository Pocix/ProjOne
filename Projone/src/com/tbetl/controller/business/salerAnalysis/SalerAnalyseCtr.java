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
import com.tbetl.util.PageData;

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

	@RequestMapping(value="/queryAllPro")
	public ModelAndView queryAllPro(PageData pd){
		ModelAndView mv = this.getModelAndView();
		mv.addObject("productList", salerAnalysisSev.queryAllProduct(getCurrentUser()));
		mv.setViewName("busi/task/ShoptaskList");
		return mv;
	}
}
