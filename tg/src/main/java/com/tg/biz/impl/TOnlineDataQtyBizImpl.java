package com.tg.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.biz.TOnlineDataQtyBiz;
import com.tg.dao.TOnlineDataQtyDao;
import com.tg.model.TOnlineDataQty;
import com.tg.persisten.queue.PersistanceTaskDispatcher;
import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service
public class TOnlineDataQtyBizImpl implements TOnlineDataQtyBiz, IBatchUpdateService<TOnlineDataQty>
{

    @Autowired
    private TOnlineDataQtyDao dao;
    
    @Autowired
    private PersistanceTaskDispatcher dispatcher;
    
    @Override
    public void addTOnlineDataQty(TOnlineDataQty tOnlineDataQty) 
    {
    	if (!dispatcher.checkBatch(tOnlineDataQty, false, Constants.ONLINE_DATA_QTY_INDEX))
    	{
	    	DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.anhui_dsm);
	        dao.addTOnlineDataQty(tOnlineDataQty);
    	}
    	if (!dispatcher.checkBatch(tOnlineDataQty, true, Constants.ONLINE_DATA_QTY_INDEX))
    	{
	        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.sharding);
	        dao.addTOnlineDataQty(tOnlineDataQty);
    	}
        DataSourceContextHolder.clearDataSourceType();
    }

	@Override
	public void addTOnlineDataQtyBatch(List<TOnlineDataQty> list) {
		batchInsert(list,false);
		batchInsert(list,true);
	}

	@Override
	public int batchInsert(List<TOnlineDataQty> insertEntities, boolean shard) 
	{
		int result  = 0;
		if (insertEntities.size() > 0)
		{
			TOnlineDataQty tOnlineDataQty = insertEntities.get(0);
			if (shard)
			{
				DataSourceContextHolder.setDataSourceType(tOnlineDataQty.getActualDBName());
				result =  dao.batchInsert(insertEntities, tOnlineDataQty.getActualTableName());
			}
			else
			{
				DataSourceContextHolder.setDataSourceType(tOnlineDataQty.getDBName());
				result =  dao.batchInsert(insertEntities, tOnlineDataQty.getTableName());
			}
			DataSourceContextHolder.clearDataSourceType();
		}
		return result;
	}
}
