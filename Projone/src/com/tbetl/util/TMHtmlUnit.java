/**
 * This file created at 2015-6-27.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tbetl.entity.business.TMProduct;

/**
 * <code>{@link TMHtmlUnit}</code>
 *
 * TODO : 天猫抓取
 *
 * @author Poci
 */
public class TMHtmlUnit {

	public static final String SEARCHALL="/search.htm?search=y";
	public static final String REF_TM_DETAIL_URL="J_ShopAsynSearchURL";//明细链接
	public static final String REF_TM_DETAIL_STRPRICE="J_StrPriceModBox";//价格
	public static final String REF_TM_DETAIL_PROMOPRICE="J_PromoPrice";//促销价
	public static final String REF_TM_DETAIL_POSTAGETOGGLECONT="J_PostageToggleCont";//运费
	public static final String REF_TM_DETAIL_SS="J_SaleCombo";//月销量
	public static final String REF_TM_DETAIL_ITEMRATES="J_ItemRates";//送天猫积分 但实际获取的为累计总评价
	public static final String REF_TM_DETAIL_EMSTOCK="J_EmStock";//库存
	public static final String REF_TM_DETAIL_COLLECTCOUNT="J_CollectCount";//收藏商品
	public static final String REF_TM_DETAIL_ATTRLIST="J_AttrList";//产品参数
	
	WebClient webClient = null;
	
	public void init(){
		// 模拟一个浏览器
		webClient = new WebClient(BrowserVersion.CHROME);
		
		// 设置webClient的相关参数
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	}
	
	public void destroy(){
		webClient.close();
	}
	
	public static void main(String[] s) throws Exception {
		TMHtmlUnit tbhu = new TMHtmlUnit();
		tbhu.init();
//		String url = "https://yifengshangsp.tmall.com";
//		String url = "https://baihuasp.tmall.com";
		/**/
		TMProduct pro = new TMProduct();
		pro.setUrl("https://detail.tmall.com/item.htm?id=45353225588&rn=1b6baec6e46f3c732851ea6c2566eb82");
		tbhu.translateForTM(pro.getUrl(),pro);
		System.out.println("name:"+pro.getName());
		System.out.println("price:"+pro.getPrice());
		System.out.println("tbprice:"+pro.getTbprice());
		System.out.println("tradeCount:"+pro.getTradeCountForM());
		System.out.println("evaluateCount:"+pro.getEvaluateCount());
		System.out.println("trove:"+pro.getTrove());
		
		/*
		List<TMProduct> list = tbhu.getProList2shop(tbhu.getDetailListUrl2shop(url));
		if(list != null){
			for(TMProduct pro : list){
				tbhu.translateForTM(pro.getUrl(),pro);
				System.out.println("name:"+pro.getName());
				System.out.println("price:"+pro.getPrice());
				System.out.println("tbprice:"+pro.getTbprice());
				System.out.println("tradeCount:"+pro.getTradeCount());
				System.out.println("evaluateCount:"+pro.getEvaluateCount());
				System.out.println("trove:"+pro.getTrove());
			}
		}
		*/
		tbhu.destroy();
	}

	/**
	 * Get all item list url for TMShop.
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getDetailListUrl2shop(String url) throws Exception{
		// 模拟一个浏览器
		webClient = new WebClient(BrowserVersion.CHROME);
		
		// 设置webClient的相关参数
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url+SEARCHALL);
		// 该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000 * 3);
		webClient.setJavaScriptTimeout(0);
		return url+page.getElementById(REF_TM_DETAIL_URL).getAttribute("value");
	}
	
	public List<TMProduct> getProList2shop(String url) throws Exception{

		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url);
		// 该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000 * 3);
		webClient.setJavaScriptTimeout(0);
		
		List<HtmlAnchor> achList=page.getAnchors();
		List<TMProduct> listPro = new ArrayList<TMProduct>();
		//TODO There has repeat url !!!
		Map<String,String> map = new HashMap<String,String>();
		for(HtmlAnchor ach:achList){
			if(ach.getHrefAttribute().split("detail.tmall.com/item.htm").length >1 && ach.getTextContent().trim() != null && "".equals(ach.getTextContent().trim())){
				map.put(ach.getHrefAttribute().split("&")[0].substring(ach.getHrefAttribute().split("&")[0].indexOf("id=")+3),"http:"+ach.getHrefAttribute().substring(1).replaceAll("\"", ""));
			}
		}
		
		for(String _tmp : map.keySet()){
			TMProduct pro = new TMProduct();
			pro.setUrl(map.get(_tmp));
			listPro.add(pro);
		}
		webClient.close();
		return listPro;
	}
	
	/**
	 * Get detail data from item.
	 * @param url
	 * @param pro
	 * @return
	 * @throws Exception
	 */
	public String translateForTM(String url,TMProduct pro) throws Exception {
		try {
			final HtmlPage page = webClient.getPage(url);
			// 该方法在getPage()方法之后调用才能生效
			webClient.waitForBackgroundJavaScript(1000 * 3);
			webClient.setJavaScriptTimeout(0);
			if(pro != null){
				pro.setName(page.getTitleText());
				pro.setPrice(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(page.getHtmlElementById(REF_TM_DETAIL_STRPRICE).asText()).replaceAll(""));
				pro.setTbprice(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(page.getHtmlElementById(REF_TM_DETAIL_PROMOPRICE).asText()).replaceAll(""));
				pro.setPostageToggleCont(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(page.getHtmlElementById(REF_TM_DETAIL_POSTAGETOGGLECONT).asText()).replaceAll(""));
				for(DomElement e : page.getElementsByTagName("li")){
					if(e.getAttributes().getNamedItem("data-label") != null){
						pro.setTradeCountForM(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(e.asText()).replaceAll(""));
					}
				}
				pro.setEvaluateCount(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(page.getHtmlElementById(REF_TM_DETAIL_ITEMRATES).asText()).replaceAll(""));
				pro.setEmStock(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(page.getHtmlElementById(REF_TM_DETAIL_EMSTOCK).asText()).replaceAll(""));
				pro.setTrove(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s\\n").matcher(page.getHtmlElementById(REF_TM_DETAIL_COLLECTCOUNT).asText()).replaceAll(""));
				pro.setRemark((page.getHtmlElementById(REF_TM_DETAIL_ATTRLIST).asText()));
				 
			}
		} catch (Exception e) {
			System.out.println("Exception:"+e.getCause());
		}
		return null;
	}
	
}
