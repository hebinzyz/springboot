package com.tg.biz.impl;

import java.util.List;

import com.tg.model.IBatchAble;

/**
 * @author liyu
 * 批量处理接口
 * @param <T> 批量处理实体类 
 */
public interface IBatchUpdateService<T extends IBatchAble>
{
	/**
	 * 批量插入
	 * @param insertEntities 插入的实体
	 * @param shard 是否分片
	 * @return
	 */
	int batchInsert(List<T> insertEntities, boolean shard);
}
