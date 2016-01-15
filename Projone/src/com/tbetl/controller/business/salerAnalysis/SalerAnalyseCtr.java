/**
 * This file created at 2015-7-6.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.controller.business.salerAnalysis;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbetl.controller.base.BaseController;
import com.tbetl.entity.RetObj;
import com.tbetl.entity.business.ShopItem;
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

	@RequestMapping(value="/queryAllPro")
	public ModelAndView queryAllPro(){
		ModelAndView mv = this.getModelAndView();
		ShopItem item = new ShopItem();
		item.setUser_uid(getCurrentUser().getUSER_ID());
		mv.addObject("shopList", salerAnalysisSev.getAllShop(item));
		Map param = new HashMap<String, String>();
		param.put("user_uid", getCurrentUser().getUSER_ID());
		param.put("year", getCalendar().get(Calendar.YEAR));
		mv.addObject("cur_sales_y", salerAnalysisSev.queryAllProduct(param));
		param.put("month", getCalendar().get(Calendar.MONTH)+1);
		mv.addObject("cur_sales_m", salerAnalysisSev.queryAllProduct(param));
		mv.setViewName("busi/task/ShoptaskList");
		return mv;
	}
	
	@RequestMapping(value="/updStatus")
	@ResponseBody
	public RetObj updStatus(HttpServletRequest request, String uid, String cmd,String type){
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			ShopItem item = new ShopItem();
			item.setUser_uid(getCurrentUser().getUSER_ID());
			item.setUid(uid);
			if("status".equals(type)){
				item.setStatus(cmd);
			}
			if("intask".equals(type)){
				item.setIs_intask(cmd);
			}
			salerAnalysisSev.changeStatus(item);
		} catch (Exception e) {
			retObj.setMsg("任务状态改变失败！");
			return retObj;
		}
		retObj.setFlag(true);
		return retObj;
	}
}
