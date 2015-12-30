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

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tbetl.entity.business.Product;

/**
 * <code>{@link TBHtmlUnit}</code>
 *
 * TODO : 淘宝抓取
 *
 * @author Administrator
 */
public class TBHtmlUnit {

	public static void main(String[] s) throws Exception {
		TBHtmlUnit tbhu = new TBHtmlUnit();
//		String url = "http://item.taobao.com/item.htm?spm=a219r.lm895.14.1.tnOrnb&id=45522540668&ns=1&abbucket=16";
//		String url = "http://item.taobao.com/item.htm?spm=a1z10.1-c.w4004-8487258215.3.YROPfx&id=45522540668";
		String url = "http://shop113118231.taobao.com/";
//		translateForTaob(taobaoCodeSrc(url));
		System.out.println(tbhu.taobaoCodeSrc(url));
//		List<Product> list = tbhu.getProList2shop(url);
//		if(list != null){
//			for(Product pro : list){
//				tbhu.translateForTaob(tbhu.taobaoCodeSrc(pro.url),pro);
//				System.out.println("name:"+pro.getName());
//				System.out.println("price:"+pro.getPrice());
//				System.out.println("tbprice:"+pro.getTbprice());
//				System.out.println("tradeCount:"+pro.getTradeCount());
//				System.out.println("evaluateCount:"+pro.getEvaluateCount());
//				System.out.println("trove:"+pro.getTrove());
//			}
//		}
	}

	public List<Product> getProList2shop(String url) throws Exception{
		// 模拟一个浏览器
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// 设置webClient的相关参数
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

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
			if(ach.getHrefAttribute().split("item.taobao.com/item.htm").length >1 && ach.getTextContent().trim() != null && "".equals(ach.getTextContent().trim())){
				urlSet.add("http:"+ach.getHrefAttribute());
			}
		}
		
		for(String _tmp : urlSet){
			Product pro = new Product();
			pro.url = _tmp;
			listPro.add(pro);
		}
		webClient.close();
//		return listPro;
		return null;
	}
	
	public String taobaoCodeSrc(String url) throws Exception {
		// 模拟一个浏览器
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);

//		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
//		java.util.logging.Logger.getLogger("net.sourceforge.htmlunit").setLevel(java.util.logging.Level.OFF);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// 设置webClient的相关参数
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(url);
		// 该方法在getPage()方法之后调用才能生效
		webClient.waitForBackgroundJavaScript(1000 * 3);
		webClient.setJavaScriptTimeout(0);
		webClient.close();
		return page.asText();
	}
	
	
	public void translateForTaob(String str,Product pro){
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
