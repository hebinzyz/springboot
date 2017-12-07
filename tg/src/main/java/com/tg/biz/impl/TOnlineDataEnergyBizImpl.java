package com.tg.biz.impl;

import com.tg.biz.TOnlineDataEnergyBiz;
import com.tg.dao.TOnlineDataEnergyDao;
import com.tg.model.TOnlineDataEnergy;
import com.tg.persisten.queue.PersistanceTaskDispatcher;
import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class TOnlineDataEnergyBizImpl implements TOnlineDataEnergyBiz,IBatchUpdateService<TOnlineDataEnergy>
{

    @Autowired
    private TOnlineDataEnergyDao dao;
    
    @Autowired
    private PersistanceTaskDispatcher dispatcher;
    
    @Override
    public void addTOnlineDataEnergy(TOnlineDataEnergy tOnlineDataEnergy) 
    {
    	if (!dispatcher.checkBatch(tOnlineDataEnergy, false, Constants.ONLINE_DATA_ENERGY_INDEX))
    	{
	    	DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
	        dao.addTOnlineDataEnergy(tOnlineDataEnergy);
    	}
    	if (!dispatcher.checkBatch(tOnlineDataEnergy, true, Constants.ONLINE_DATA_ENERGY_INDEX))
    	{
	        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
	        dao.addTOnlineDataEnergy(tOnlineDataEnergy);
    	}
    	DataSourceContextHolder.clearDataSourceType();
    }

    @Override
    public TOnlineDataEnergy queryDataEnergy(String terminalId, BigInteger beginTime, BigInteger endTime) {
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
        return dao.queryDataEnergy(terminalId,beginTime,endTime);
    }

    @Override
    public TOnlineDataEnergy queryDataEnergyByGetTime(String terminalId, BigInteger getTime) {
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
        return dao.queryDataEnergyByGetTime(terminalId,getTime);
    }

	@Override
	public void addTOnlineDataEnergyBatch(List<TOnlineDataEnergy> list) {
		batchInsert(list,false);
		batchInsert(list,true);
	}

	@Override
	public int batchInsert(List<TOnlineDataEnergy> insertEntities, boolean shard) 
	{
		int result  = 0;
		if (insertEntities.size() > 0)
		{
			TOnlineDataEnergy dataEnergy = insertEntities.get(0);
			if (shard)
			{
				DataSourceContextHolder.setDataSourceType(dataEnergy.getActualDBName());
				result =  dao.batchInsert(insertEntities, dataEnergy.getActualTableName());
			}
			else
			{
				DataSourceContextHolder.setDataSourceType(dataEnergy.getDBName());
				result =  dao.batchInsert(insertEntities, dataEnergy.getTableName());
			}
			DataSourceContextHolder.clearDataSourceType();
		}
		return result;
	}
}
