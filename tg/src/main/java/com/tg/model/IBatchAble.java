package com.tg.model;

/**
 * @author liyu
 *	批量接口
 */
public interface IBatchAble 
{
	/**
	 * 执行表的名称
	 * @return
	 */
	String getTableName();
	
	/**
	 * 执行db的名称（beanId）
	 * @return
	 */
	String getDBName();
}
