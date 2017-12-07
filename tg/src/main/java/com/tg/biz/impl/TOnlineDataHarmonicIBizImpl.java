package com.tg.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.biz.TOnlineDataHarmonicIBiz;
import com.tg.dao.TOnlineDataHarmonicIDao;
import com.tg.model.TOnlineDataHarmonicI;
import com.tg.persisten.queue.PersistanceTaskDispatcher;
import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class TOnlineDataHarmonicIBizImpl implements TOnlineDataHarmonicIBiz,IBatchUpdateService<TOnlineDataHarmonicI> 
{

    @Autowired
    private TOnlineDataHarmonicIDao dao;
    
    @Autowired
    private PersistanceTaskDispatcher dispatcher;
    
    @Override
    public void addTOnlineDataHarmonicI(TOnlineDataHarmonicI tOnlineDataHarmonicI) 
    {
    	if (!dispatcher.checkBatch(tOnlineDataHarmonicI, false, Constants.ONLINE_DATA_HARMONICI_INDEX))
    	{
	    	DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
	    	dao.addTOnlineDataHarmonicI(tOnlineDataHarmonicI);
    	}
    	if (!dispatcher.checkBatch(tOnlineDataHarmonicI, true, Constants.ONLINE_DATA_HARMONICI_INDEX))
    	{
	    	DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
	    	dao.addTOnlineDataHarmonicI(tOnlineDataHarmonicI);
    	}
    	DataSourceContextHolder.clearDataSourceType();
    }

	@Override
	public void addTOnlineDataHarmonicIBatch(List<TOnlineDataHarmonicI> list) {
		batchInsert(list,false);
		batchInsert(list,true);
	}

	@Override
	public int batchInsert(List<TOnlineDataHarmonicI> insertEntities, boolean shard) 
	{
		int result  = 0;
		if (insertEntities.size() > 0)
		{
			TOnlineDataHarmonicI dataHarmonicl = insertEntities.get(0);
			if (shard)
			{
				DataSourceContextHolder.setDataSourceType(dataHarmonicl.getActualDBName());
				result =  dao.batchInsert(insertEntities, dataHarmonicl.getActualTableName());
			}
			else
			{
				DataSourceContextHolder.setDataSourceType(dataHarmonicl.getDBName());
				result =  dao.batchInsert(insertEntities, dataHarmonicl.getTableName());
			}
			DataSourceContextHolder.clearDataSourceType();
		}
		return result;
	}
}
