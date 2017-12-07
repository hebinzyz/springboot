package com.tg.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.biz.TOnlineDataPowerBiz;
import com.tg.dao.TOnlineDataPowerDao;
import com.tg.model.TOnlineDataPower;
import com.tg.model.TOnlineDataPowerMonit;
import com.tg.persisten.queue.PersistanceTaskDispatcher;
import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class TOnlineDataPowerBizImpl implements TOnlineDataPowerBiz,IBatchUpdateService<TOnlineDataPower>
{

    @Autowired
    private TOnlineDataPowerDao dao;
    
    @Autowired
    private PersistanceTaskDispatcher dispatcher;

    @Override
    public void addTOnlineDataPower(TOnlineDataPower tOnlineDataPower) 
    {
    	if (!dispatcher.checkBatch(tOnlineDataPower, false, Constants.ONLINE_DATA_POWER_INDEX))
    	{
	    	DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
	        dao.addTOnlineDataPower(tOnlineDataPower);
    	}
    	if (!dispatcher.checkBatch(tOnlineDataPower, true, Constants.ONLINE_DATA_POWER_INDEX))
    	{
	        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
	        dao.addTOnlineDataPower(tOnlineDataPower);
    	}
        DataSourceContextHolder.clearDataSourceType();
    }

    @Override
    public List<TOnlineDataPower> queryDataPower(List<String> list, BigInteger getTime) {
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
        return dao.queryDataPower(list, getTime);
    }

	@Override
	public void addTOnlineDataPowerBatch(List<TOnlineDataPower> list) {
		batchInsert(list,false);
		batchInsert(list,true);
	}


	@Override
	public int batchInsert(List<TOnlineDataPower> insertEntities, boolean shard) 
	{
		int result  = 0;
		if (insertEntities.size() > 0)
		{
			TOnlineDataPower tOnlineDataPower = insertEntities.get(0);
			if (shard)
			{
				DataSourceContextHolder.setDataSourceType(tOnlineDataPower.getActualDBName());
				result =  dao.batchInsert(insertEntities, tOnlineDataPower.getActualTableName());
			}
			else
			{
				DataSourceContextHolder.setDataSourceType(tOnlineDataPower.getDBName());
				result =  dao.batchInsert(insertEntities, tOnlineDataPower.getTableName());
			}
			DataSourceContextHolder.clearDataSourceType();
		}
		return result;
	}
}
