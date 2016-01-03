/**
 * This file created at 2015-6-25.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <code>{@link MongoGenDao}</code>
 * 
 * TODO : document me
 * 
 * @author Administrator
 */
public abstract class MongoGenDao<T> {
	
	//@Resource(name = "mongoTemplate")
	protected MongoTemplate mongoTemplate;

	/**
	 * 保存一个对象
	 * 
	 * @param t
	 * @return
	 */
	public void save(T t) {
		this.mongoTemplate.save(t);
	}

	/**
	 * 为属性自动注入bean服务
	 * 
	 * @param mongoTemplate
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
