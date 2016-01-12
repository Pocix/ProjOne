/**
 * This file created at 2015-7-6.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.service.business.salerAnalysis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tbetl.entity.business.Product;
import com.tbetl.entity.business.ShopItem;
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
	
	public List<ShopItem> queryAllProduct(ShopItem item){
		try {
			return (List<ShopItem>) dao.findForList("TMProductMapper.getAllShopItem", item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
