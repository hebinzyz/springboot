package com.tg.biz.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tg.model.TMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.biz.TMonitorBiz;
import com.tg.dao.TMonitorDao;
import com.tg.model.TMonitorCacheEntry;
import com.tg.util.DataSourceContextHolder;

/**
 * @author liyu
 *	监测点档案业务层实现
 */
@Service
public class TMonitorBizImpl implements TMonitorBiz
{
	//检测点缓存
	//TODO 考虑放进redis中
	private Map<Long,Map<String,TMonitorCacheEntry>> monitorCache = null;
	
	@Autowired
	private TMonitorDao tMonitorDao; 
	
	@Override
	public void updateTMonitorCache() 
	{
		Map<Long, Map<String, TMonitorCacheEntry>> monitorCache = getCache();
		synchronized (this.monitorCache) 
		{
			this.monitorCache = monitorCache;
		}
	}

	private Map<Long, Map<String, TMonitorCacheEntry>> getCache() {
		DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
		List<TMonitorCacheEntry> cacheEntries = tMonitorDao.queryTmoitorCache();
		DataSourceContextHolder.clearDataSourceType();
		Map<Long,Map<String,TMonitorCacheEntry>> monitorCache= new HashMap<Long, Map<String,TMonitorCacheEntry>>();
		long companyId = -1;
		Map<String,TMonitorCacheEntry> tempMap = null;
		for (TMonitorCacheEntry cacheEntry : cacheEntries)
		{
			if (companyId != cacheEntry.getCompanyId())//当前企业编号和上一个监测点的编号不同，需要新建一个map
			{
				companyId = cacheEntry.getCompanyId();
				tempMap = new HashMap<String, TMonitorCacheEntry>();
				monitorCache.put(companyId, tempMap);
			}
			tempMap.put(cacheEntry.getTerminalId(), cacheEntry);
		}
		return monitorCache;
	}

	@Override
	public boolean checkMonitorExist(long companyId, String terminalId)
	{
		synchronized (this.monitorCache) 
		{
			Map<String, TMonitorCacheEntry> map = this.monitorCache.get(companyId);
			return (map != null && map.containsKey(terminalId));
		}
	}

	@Override
	public List<TMonitor> queryTMonitor(TMonitor tMonitor) {
		DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
		return tMonitorDao.queryTMonitor(tMonitor);
	}

	@Override
	public void updateTmonitor(TMonitorCacheEntry tMonitor) {
		DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
		tMonitorDao.updateTmonitor(tMonitor);
	}

	@Override
	public long getGetTime(long companyId, String terminalId) {

		synchronized (this.monitorCache)
		{
			Map<String, TMonitorCacheEntry> map = this.monitorCache.get(companyId);
			TMonitorCacheEntry entry= map.get(terminalId);
			return entry.getOnlineTime();
		}
	}

	@Override
	public void updatCache(TMonitorCacheEntry tMonitor) {
		synchronized (this.monitorCache)
		{
			Map<String, TMonitorCacheEntry> map = this.monitorCache.get(tMonitor.getCompanyId());
			//修改map中key是terminalId 值的TMonitorCacheEntry
			Iterator<String> it=map.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if(key.equals(tMonitor.getTerminalId())){
					map.put(key,tMonitor);
				}
			}

		}
	}

	@Override
	public void initTMonitorCache() 
	{
		this.monitorCache = getCache();
	}

}
