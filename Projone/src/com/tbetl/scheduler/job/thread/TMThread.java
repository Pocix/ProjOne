package com.tbetl.scheduler.job.thread;

import java.util.Date;
import java.util.List;

import com.tbetl.entity.business.ShopItem;
import com.tbetl.entity.business.TMProduct;
import com.tbetl.util.TMHtmlUnit;

public class TMThread extends AbstractThread{

	private TMHtmlUnit tmunit;
	
	private ShopItem item;
	
	private String threadType;
	
	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}

	public void setItem(ShopItem item) {
		this.item = item;
	}

	public void setTmunit(TMHtmlUnit tmunit) {
		this.tmunit = tmunit;
	}
	@Override
	public void run() {
		if(threadType != null && threadType != ""){
			if(threadType.equals("init")){
				initShopData();
			}
			if(threadType.equals("getAllShopItem")){
				getAllShopItem();
			}
		}
	}

	
	private void initShopData(){
		// TODO Auto-generated method stub
				try {
					if(item != null){
						System.out.println("初始化商店数据:\n任务执行开始........");
						List<TMProduct> listp = tmunit.getProList2shop(tmunit.getDetailListUrl2shop(item.getUrl()));
						for(TMProduct p : listp){
							tmunit.translateForTM(p.getUrl(), p);
							ShopItem _tmp = new ShopItem();
							_tmp.setP_uid(item.getUid());
							_tmp.setUid(get32UUID());
							_tmp.setName(p.getName());
							_tmp.setIs_intask("0");
							_tmp.setCreatetime(new Date());
							_tmp.setStatus("0");
							_tmp.setUrl(p.getUrl());
							getDao().save("ShopItemMapper.saveItem", _tmp);
						}

						System.out.println("任务执行结束........");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	private void getAllShopItem(){
		// TODO Auto-generated method stub
				try {
					if(item != null){
						System.out.println("获取销售数据:\n任务执行开始........");
						List<TMProduct> listp = tmunit.getProList2shop(tmunit.getDetailListUrl2shop(item.getUrl()));
						for(TMProduct p : listp){
							tmunit.translateForTM(p.getUrl(), p);
							p.setPid(item.getUid());
							getDao().save("TMProductMapper.saveItem", p);
						}

						System.out.println("任务执行结束........");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
