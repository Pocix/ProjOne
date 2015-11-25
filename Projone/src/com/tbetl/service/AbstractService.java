/**
 * This file created at 2015-7-16.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import com.tbetl.dao.DaoSupport2Mongo;

/**
 * <code>{@link AbstractService}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
public abstract class AbstractService {

	@Resource(name = "redisTemplate")
	protected RedisTemplate<?, ?> redisTemplate;
	
	@Resource(name="daoSupport2Mongo")
	protected DaoSupport2Mongo dao2mongo;
}
