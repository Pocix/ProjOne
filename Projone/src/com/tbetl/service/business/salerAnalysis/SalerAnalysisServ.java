/**
 * This file created at 2015-7-6.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.service.business.salerAnalysis;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tbetl.entity.business.Product;
import com.tbetl.entity.business.ShopItem;
import com.tbetl.service.AbstractService;

import net.sf.json.JSONArray;

/**
 * <code>{@link SalerAnalysisServ}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
@Service("salerAnalysisServ")
public class SalerAnalysisServ extends AbstractService{

	public List<Product> testQueryAllProduct(){
		return dao2mongo.queryALLPro();
	}
	
	public List<ShopItem> getAllShop(ShopItem item){
		try {
			return (List<ShopItem>) dao.findForList("ShopItemMapper.getAllShop", item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void changeStatus(ShopItem item) throws Exception{
		dao.update("ShopItemMapper.updStatus", item);
	}
	
	public JSONArray queryAllProduct(Map map){
		try {
			List<Map> list = (List<Map>) dao.findForList("TMProductMapper.queryAllProduct", map);
			String[] dayTrade = getDayArr();
			if(map.get("month") == null){
				dayTrade = getMonthArr();
			}
			for(Map _tmp : list){
				dayTrade[Integer.parseInt(_tmp.get("pkey").toString())-1] = _tmp.get("pvalue").toString();
			}
			return JSONArray.fromObject(dayTrade);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String[] getDayArr(){
		Calendar calendar=Calendar.getInstance();
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month=calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		String[] dayTrade = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
		if(month == 2){
			if(((year%100==0)&&(year%400==0))||((year%100!=0)&&(year%4==0))){
				dayTrade = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			}else{
				dayTrade = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			}
		}else if(month == 4 || month == 6 || month == 9 || month == 11 ){
			dayTrade = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
		}
		return dayTrade;
	}
	
	private String[] getMonthArr(){
		return new String[]{"0","0","0","0","0","0","0","0","0","0","0","0"};
	}
}
