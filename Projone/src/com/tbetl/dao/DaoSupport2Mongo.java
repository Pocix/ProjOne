package com.tbetl.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tbetl.entity.business.GPlogInfo;
import com.tbetl.entity.business.Product;
import com.tbetl.util.PageData;

@Repository("daoSupport2Mongo")
public class DaoSupport2Mongo extends MongoGenDao<Object> {

	
	public List queryALLPro() {
		List<Product> list = this.mongoTemplate.findAll(Product.class, "TBcollection");
		return list;
	}
	
	
	/**
	 * query all gp log
	 * @return
	 */
	public List queryAllGpLog() {
		List<GPlogInfo> list = this.mongoTemplate.findAll(GPlogInfo.class, "GPLogCollection");
		for(GPlogInfo p: list){
			System.out.println(p.getName());
		}
		return list;
	}
	
	/**
	 * insert GPlogInfo.
	 * @param inf
	 */
	public void insGpLog(GPlogInfo inf) {
		this.mongoTemplate.insert(inf, "GPLogCollection");
	}
	
	public GPlogInfo findById(PageData pd){
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(pd.get("id")));
		return this.mongoTemplate.findOne(query, GPlogInfo.class);
	}
	
	public void saveL(PageData pd){
		GPlogInfo inf = new GPlogInfo();
		try {
			inf.setBuyDate(new SimpleDateFormat("yyyy-MM-dd").parse(pd.getString("buyDate")));
			inf.setId(pd.getString("id"));
			inf.setCode(pd.getString("code"));
			inf.setName(pd.getString("name"));
			inf.setPrice(Double.parseDouble(pd.getString("price")));
			inf.setAmount(Integer.parseInt(pd.getString("amount")));
			inf.setDp(Double.parseDouble(pd.getString("dp")));
			inf.setSaleDate(new SimpleDateFormat("yyyy-MM-dd").parse(pd.getString("saleDate")));
			inf.setSales(Double.parseDouble(pd.getString("sales")));
			inf.setRemark(pd.getString("remark"));
			this.mongoTemplate.insert(inf, "GPLogCollection");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
