/**
 * This file created at 2015-6-30.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.entity.business;

/**
 * <code>{@link AbstractEntity}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
public class AbstractEntity {

	Oid _id;
	/**
	 * Mongodb会自动生成ObjectId
	 * @author fhp
	 *
	 */
	public class Oid{
		String $oid;
		public String get$oid() {
			return $oid;
		}
 
		public void set$oid(String $oid) {
			this.$oid = $oid;
		}
		
	}
}
