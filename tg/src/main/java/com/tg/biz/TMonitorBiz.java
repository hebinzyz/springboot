package com.tg.biz;

import com.tg.model.TMonitor;
import com.tg.model.TMonitorCacheEntry;

import java.math.BigInteger;
import java.util.List;

/**
 * @author liyu
 * 检测点档案表service
 */
public interface TMonitorBiz 
{
	/**
	 * 更新监测点档案缓存
	 */
	void updateTMonitorCache();
	
	/**
	 * 初始化监测点档案
	 */
	void initTMonitorCache();
	/**
	 * 检查上报数据公司编号、监测点编号是否合法
	 * @param companyId 公司编号
	 * @param terminalId 监测点编号
	 * @return true:数据合法，false:数据非法
	 */
	boolean checkMonitorExist(long companyId, String terminalId);

	/**
	 * 查询检测点根据内容
	 * @param tMonitor
	 * @return
	 */
	public List<TMonitor> queryTMonitor(TMonitor tMonitor );

	/**
	 * 更新onlineTime
	 * @param tMonitor
	 */
	public void updateTmonitor(TMonitorCacheEntry tMonitor);

	public long getGetTime(long companyId, String terminalId);

	public void updatCache(TMonitorCacheEntry tMonitor);
}
