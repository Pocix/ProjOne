/**
 * This file created at 2015-7-21.
 *
 */
package com.tbetl.scheduler.job;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tbetl.entity.business.ShopItem;
import com.tbetl.entity.business.TMProduct;
import com.tbetl.util.TMHtmlUnit;

/**
 * <code>{@link CatchData}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */

@Repository("catchData")
public class CatchData extends AbstractJob{

	public CatchData() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public void catchShopItem(){
		TMHtmlUnit tmunit = new TMHtmlUnit();
		tmunit.init();
		ShopItem shop = new ShopItem();
		shop.setEffectivedate(new Date());
		shop.setIs_intask("1");
		List<ShopItem> list;
		try {
			list = (List<ShopItem>) dao.findForList("ShopItemMapper.getAllShop", shop);
			for(ShopItem s : list){
				List<TMProduct> listp = tmunit.getProList2shop(tmunit.getDetailListUrl2shop(s.getUrl()));
				System.out.println(listp.size());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
