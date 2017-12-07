package com.tg.dao;

import java.util.List;

import com.tg.model.TMonitor;
import com.tg.model.TMonitorCacheEntry;
import org.apache.ibatis.annotations.Param;

/**
 * @author liyu
 * 检测点档案表dao
 */
public interface TMonitorDao 
{
	/**
	 * 查询所有监测点id,companyId,terminalId,order by companyId asc, id asc
	 * @return 监测点id,companyId,terminalId列表
	 */
	List<TMonitorCacheEntry> queryTmoitorCache();

	public List<TMonitor> queryTMonitor( TMonitor tMonitor );

	public void updateTmonitor(TMonitorCacheEntry tMonitor);
}
