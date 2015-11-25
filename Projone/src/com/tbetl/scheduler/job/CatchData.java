/**
 * This file created at 2015-7-21.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.scheduler.job;

import org.springframework.stereotype.Repository;

/**
 * <code>{@link CatchData}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */

@Repository("catchData")
public class CatchData {

	public void execute(){
		System.out.println("CatchData");
	}
}
