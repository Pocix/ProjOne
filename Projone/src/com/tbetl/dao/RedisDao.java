/**
 * This file created at 2015-6-25.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.dao;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * <code>{@link RedisDao}</code>
 * 
 * TODO : document me
 * 
 * @author Administrator
 */
public abstract class RedisDao<T> {
	
	@Resource(name = "redisTemplate")
	protected RedisTemplate redisTemplate;
	
	
	public <T> T getObject(String key){
		return null;
	}

}
