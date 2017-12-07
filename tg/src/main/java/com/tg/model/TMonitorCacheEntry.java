package com.tg.model;

import java.math.BigInteger;

/**
 * @author liyu
 * 监测点档案缓存
 */
public class TMonitorCacheEntry 
{
	//编号
	private long id;
	//检测仪编号
	private String terminalId;
	//企业编号
	private long companyId;

	private long onlineTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}
}