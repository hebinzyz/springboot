package com.tg.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.biz.TOnlineDataHarmonicUBiz;
import com.tg.dao.TOnlineDataHarmonicUDao;
import com.tg.model.TOnlineDataHarmonicU;
import com.tg.persisten.queue.PersistanceTaskDispatcher;
import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class TOnlineDataHarmonicUBizImpl implements TOnlineDataHarmonicUBiz,IBatchUpdateService<TOnlineDataHarmonicU>
{

    @Autowired
    private TOnlineDataHarmonicUDao dao;
    
    @Autowired
    private PersistanceTaskDispatcher dispatcher;
    
    @Override
    public void addTOnlineDataHarmonicU(TOnlineDataHarmonicU tOnlineDataHarmonicU)
    {
    	if (!dispatcher.checkBatch(tOnlineDataHarmonicU, false, Constants.ONLINE_DATA_HARMONICU_INDEX))
    	{
	    	DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
	        dao.addTOnlineDataHarmonicU(tOnlineDataHarmonicU);
    	}
    	if (!dispatcher.checkBatch(tOnlineDataHarmonicU, true, Constants.ONLINE_DATA_HARMONICU_INDEX))
    	{
	        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
	        dao.addTOnlineDataHarmonicU(tOnlineDataHarmonicU);
    	}
        DataSourceContextHolder.clearDataSourceType();
    }

	@Override
	public void addTOnlineDataHarmonicUBatch(List<TOnlineDataHarmonicU> list) {
		batchInsert(list,false);
		batchInsert(list,true);
	}

	@Override
	public int batchInsert(List<TOnlineDataHarmonicU> insertEntities, boolean shard) 
	{

		int result  = 0;
		if (insertEntities.size() > 0)
		{
			TOnlineDataHarmonicU dataHarmonicu = insertEntities.get(0);
			if (shard)
			{
				DataSourceContextHolder.setDataSourceType(dataHarmonicu.getActualDBName());
				result =  dao.batchInsert(insertEntities, dataHarmonicu.getActualTableName());
			}
			else
			{
				DataSourceContextHolder.setDataSourceType(dataHarmonicu.getDBName());
				result =  dao.batchInsert(insertEntities, dataHarmonicu.getTableName());
			}
			DataSourceContextHolder.clearDataSourceType();
		}
		return result;
	}
}
