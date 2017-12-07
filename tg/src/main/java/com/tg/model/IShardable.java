package com.tg.model;

/**
 * @author liyu
 * 分片接口
 * @param <T> 分片接口类型
 */
public interface IShardable<T> extends IBatchAble
{
	/**
	 * 返回该数据分片后的数据表
	 * @return
	 */
	String getActualTableName();
	
	/**
	 * 返回该数据分片后的数据库（beanId）
	 * @return
	 */
	String getActualDBName();
	
	/**
	 * 返回分类条件
	 * @return
	 */
	T getShardFlag();
}
