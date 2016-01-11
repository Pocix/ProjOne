/**
 * This file created at 2015-7-21.
 *
 */
package com.tbetl.scheduler.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import com.tbetl.entity.business.ShopItem;
import com.tbetl.entity.business.TMProduct;
import com.tbetl.scheduler.job.thread.TMThread;
import com.tbetl.util.ReflectHelper;
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

	TMHtmlUnit tmunit = null;
	
	@Resource(name="taskExecutor")
	ThreadPoolTaskExecutor poolTaskExecutor;
	
	public CatchData() {
		super();
		init();
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		tmunit = new TMHtmlUnit();
		tmunit.init();
	}
	
	private List<ShopItem> getAllShopBySearch(ShopItem shop) throws Exception{
		return (List<ShopItem>) dao.findForList("ShopItemMapper.getAllShop", shop);
	}
	
	/**
	 * 获取商店基本数据
	 */
	public void catchShopItem(){
		try {
			ShopItem shop = new ShopItem();
			shop.setEffectivedate(new Date());
			shop.setIs_intask("1");
			shop.setIs_init("0");
			List<ShopItem> list = getAllShopBySearch(shop);
			if(list != null || list.size() > 0){
				//更新状态
				shop.setIs_init("1");
				dao.update("ShopItemMapper.updInitStatus", shop);
				
				for(ShopItem s : list){
					TMThread tmThread = new TMThread();
					tmThread.setDao(dao);
					tmThread.setTmunit(tmunit);
					tmThread.setItem(s);
					tmThread.setThreadType("init");
					poolTaskExecutor.execute(tmThread);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取每日数据,须先获取每天商品销售数据,以便计算每天销售值.
	 */
	public void catchItemForDay(){
		try{
			ShopItem shop = new ShopItem();
			shop.setEffectivedate(new Date());
			shop.setIs_intask("1");
			shop.setIs_init("1");
			List<ShopItem> list = getAllShopBySearch(shop);
			if(list != null || list.size() > 0){
				ReflectHelper.setObjectFieldsEmpty(shop);
				for(ShopItem s : list){
					TMThread tmThread = new TMThread();
					shop.setUid(s.getUid());
					shop.setIntaskThreadId(tmThread.hashCode()+"");
					Integer msg = (Integer) dao.update("ShopItemMapper.updThreadStatusIn", shop);
					if(msg == 0){
						continue;
					}
					tmThread.setDao(dao);
					tmThread.setTmunit(tmunit);
					tmThread.setItem(s);
					tmThread.setThreadType("getAllShopItem");
					poolTaskExecutor.execute(tmThread);
				}
			}
		}catch(Exception e){
			
		}finally{
			
		}
	}
}
