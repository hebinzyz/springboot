package com.tg.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tg.biz.TMonitorBiz;

/**
 * @author liyu
 *	定时器任务：加载监测点档案缓存，每天中午12点进行同步
 */
@Component("loadMonitorCacheTask")
public class LoadMonitorCacheTask 
{
	@Autowired
	private TMonitorBiz biz;
	
	@PostConstruct
	public void init()
	{
		biz.initTMonitorCache();
	}
	
	public void domain()
	{
		biz.updateTMonitorCache();
	}
}
