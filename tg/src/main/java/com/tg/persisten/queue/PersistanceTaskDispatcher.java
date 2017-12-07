package com.tg.persisten.queue;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tg.biz.TOnlineDataEnergyBiz;
import com.tg.biz.TOnlineDataHarmonicIBiz;
import com.tg.biz.TOnlineDataHarmonicUBiz;
import com.tg.biz.TOnlineDataPowerBiz;
import com.tg.biz.TOnlineDataQtyBiz;
import com.tg.biz.impl.IBatchUpdateService;
import com.tg.model.IBatchAble;
import com.tg.util.Constants;

/**
 * @author liyu
 *	持久化任务分发器
 */
@Component
public class PersistanceTaskDispatcher 
{
	private Map<String,PersistanceIntKeyMainLoop> updateLoopMap = new HashMap<>();
	
	@Autowired
	private TOnlineDataEnergyBiz tOnlineDataEnergyBiz;
	
	@Autowired
	private TOnlineDataHarmonicIBiz tOnlineDataHarmonicIBiz; 
	
	@Autowired
	private TOnlineDataHarmonicUBiz tOnlinedataHarmonicUBizImpl;
	
	@Autowired
	private TOnlineDataPowerBiz tOnlineDataPowerBiz; 
	
	@Autowired
	private TOnlineDataQtyBiz tOnlineDataQtyBiz; 
	
	@SuppressWarnings("rawtypes")
	@PostConstruct
	public void init()
	{
		/*updateLoopMap.put(Constants.ONLINE_DATA_ENERGY_INDEX+ "_"+0, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataEnergyBiz,new NoCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_ENERGY_INDEX+ "_"+1, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataEnergyBiz,new GetTimeCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_HARMONICI_INDEX+ "_"+0, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataHarmonicIBiz,new NoCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_HARMONICI_INDEX+ "_"+1, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataHarmonicIBiz,new GetTimeCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_HARMONICU_INDEX+ "_"+0, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlinedataHarmonicUBizImpl,new NoCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_HARMONICU_INDEX+ "_"+1, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlinedataHarmonicUBizImpl,new GetTimeCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_POWER_INDEX+ "_"+0, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataPowerBiz,new NoCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_POWER_INDEX+ "_"+1, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataPowerBiz,new GetTimeCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_QTY_INDEX+ "_"+0, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataQtyBiz,new NoCheck()));
		updateLoopMap.put(Constants.ONLINE_DATA_QTY_INDEX+ "_"+1, new PersistanceIntKeyMainLoop((IBatchUpdateService) tOnlineDataQtyBiz,new GetTimeCheck()));*/
	}
	
	/**
	 * 是否进行批量操作
	 * @param vo 待插入的数据
	 * @param share 是否分片
	 * @param tableIndex 批量处理类序号,见Constants.
	 * @return
	 */
	public boolean checkBatch(IBatchAble vo,boolean share,int tableIndex)
	{
		PersistanceIntKeyMainLoop mainLoop = updateLoopMap.get(tableIndex+ "_"+(share?1:0));
		return mainLoop.checkBatchable(vo);
	}
}
