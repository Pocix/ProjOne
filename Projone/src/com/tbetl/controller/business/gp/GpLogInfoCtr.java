/**
 * This file created at 2015-7-28.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.controller.business.gp;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbetl.controller.base.BaseController;
import com.tbetl.entity.system.Role;
import com.tbetl.service.business.gp.GpLogSrv;
import com.tbetl.util.MD5;
import com.tbetl.util.PageData;

/**
 * <code>{@link GpLogInfoCtr}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value="/gp")
public class GpLogInfoCtr extends BaseController{

	@Resource(name="gpLogSrv")
	private GpLogSrv gpLogSrv;
	
	@RequestMapping(value="/GPLog_list")
	public ModelAndView getList(){
		ModelAndView mv = this.getModelAndView();
		mv.addObject("gplogList", null);
		mv.setViewName("busi/gpLog_List");
		return mv;
	}
	
	/**
	 * 去新增用户页面
	 */
	@RequestMapping(value="/addLog")
	public ModelAndView addLog(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("busi/gpLog_edit");
			mv.addObject("msg", "saveL");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping(value="/saveL")
	public ModelAndView saveL(PrintWriter out) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("id", pd.getString("buyDate")+pd.getString("code"));	//ID
		
		if(null == gpLogSrv.findById(pd)){
			gpLogSrv.saveL(pd);
			mv.addObject("msg","success");
		}else{
			mv.addObject("msg","failed");
		}
		mv.addObject("gplogList", null);
		mv.setViewName("busi/gpLog_List");
		return mv;
	}
}
