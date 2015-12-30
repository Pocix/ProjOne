/**
 * This file created at 2015-6-27.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.util;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tbetl.entity.business.Product;

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
	public static final String REF_TM_DETAIL_ITEMRATES="J_ItemRates";//送天猫积分
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
		String url = "https://baihuasp.tmall.com";
		Product pro = new Product();
		tbhu.translateForTM("http://detail.tmall.com/item.htm?id=42332489806&rn=0925e05b7a955d6d68d30d7ab5b52a3e&abbucket=1",pro);
//		System.out.println(tbhu.taobaoCodeSrc(url+tbhu.SEARCHALL));
//		List<Product> list = tbhu.getProList2shop(url+tbhu.getDetailListUrl2shop(url+tbhu.SEARCHALL));
//		if(list != null){
//			for(Product pro : list){
//				System.out.println(pro.url);
//				tbhu.translateForTaob(tbhu.translateForTM(pro.url),pro);
//				System.out.println("name:"+pro.getName());
//				System.out.println("price:"+pro.getPrice());
//				System.out.println("tbprice:"+pro.getTbprice());
//				System.out.println("tradeCount:"+pro.getTradeCount());
//				System.out.println("evaluateCount:"+pro.getEvaluateCount());
//				System.out.println("trove:"+pro.getTrove());
//			}
//		}
		tbhu.destroy();
	}

	/**
	 * Get all item list url for TMShop.
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getDetailListUrl2shop(String url) throws Exception{
		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url);
		// 该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000 * 3);
		webClient.setJavaScriptTimeout(0);
		return page.getElementById(REF_TM_DETAIL_URL).getAttribute("value");
	}
	
	public List<Product> getProList2shop(String url) throws Exception{

		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url);
		// 该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000 * 3);
		webClient.setJavaScriptTimeout(0);
		
		List<HtmlAnchor> achList=page.getAnchors();
		List<Product> listPro = new ArrayList<Product>();
		//TODO There has repeat url !!!
		Set<String> urlSet = new HashSet<String>();
		for(HtmlAnchor ach:achList){
			if(ach.getHrefAttribute().split("detail.tmall.com/item.htm").length >1 && ach.getTextContent().trim() != null && "".equals(ach.getTextContent().trim())){
				urlSet.add("http:"+ach.getHrefAttribute().substring(1).replaceAll("\"", ""));
			}
		}
		
		for(String _tmp : urlSet){
			Product pro = new Product();
			pro.url = _tmp;
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
	public String translateForTM(String url,Product pro) throws Exception {
		try {
			// 模拟浏览器打开一个目标网址
			this.init();
			final HtmlPage page = webClient.getPage(url);
			// 该方法在getPage()方法之后调用才能生效
			webClient.waitForBackgroundJavaScript(1000 * 3);
			webClient.setJavaScriptTimeout(0);
			
			System.out.println(page.getTitleText());
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_STRPRICE).asText());
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_PROMOPRICE).asText());
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_POSTAGETOGGLECONT).asText());
			for(DomElement e : page.getElementsByTagName("li")){
				if(e.getAttributes().getNamedItem("data-label") != null){
					System.out.println(e.asText());
				}
			}
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_ITEMRATES).asText());
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_EMSTOCK).asText());
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_COLLECTCOUNT).asText());
			System.out.println(page.getHtmlElementById(REF_TM_DETAIL_ATTRLIST).asText());
		} catch (Exception e) {
			System.out.println("Exception:"+e.getCause());
		}
		return null;
	}
	
	public void translateForTM1(String str,Product pro){
		try{
			StringReader sr = new StringReader(str);
			BufferedReader br=new BufferedReader(sr);
			pro.setRemark(str);
			String previous = "";
			boolean isNext = false;
			String tmp = "";
			int idx = 0;
			while((tmp = br.readLine()) != null){
				tmp = tmp.trim();
				if(idx == 0){
					pro.setName(tmp);
					idx = -1;
				}
				if(tmp.split("收藏宝贝").length > 1){
					pro.setTrove(Pattern.compile("[^0-9]").matcher(tmp).replaceAll(""));
				}else if("价格".equals(tmp)){
					previous = tmp;
					isNext = true;
				}else if("价格".equals(previous) && isNext){
					pro.setPrice(Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$").matcher(tmp).replaceAll(""));
					isNext = false;
					previous = "";
				}else if("淘宝价".equals(tmp)){
					previous = tmp;
					isNext = true;
				}else if("淘宝价".equals(previous) && isNext){
					pro.setTbprice(Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$").matcher(tmp).replaceAll(""));
					isNext = false;
					previous = "";
				}else if(tmp.split("累计评论").length > 1){
					pro.setEvaluateCount(Pattern.compile("[^0-9]").matcher(tmp).replaceAll(""));
				}else if(tmp.split("最近30天成交记录").length > 1){
					pro.setTradeCountFor30(Pattern.compile("[^0-9]").matcher(tmp.split("最近30天成交记录")[1]).replaceAll(""));
				}else if(tmp.split("成交记录").length > 1){
					pro.setTradeCount(Pattern.compile("[^0-9]").matcher(tmp).replaceAll(""));
				}
			}
			
		}catch(Exception e){
			
		}
	}
	
}
