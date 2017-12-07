package com.tg.dao;

import com.tg.model.TOnlineDataEnergy;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface TOnlineDataEnergyDao {

    public void addTOnlineDataEnergy(TOnlineDataEnergy tOnlineDataEnergy);

    public int batchInsert(@Param("list") List<TOnlineDataEnergy> list , @Param("tableName")String tableName);

    public TOnlineDataEnergy queryDataEnergy(@Param("terminalId")String terminalId, @Param("beginTime")BigInteger beginTime, @Param("endTime")BigInteger endTime);

    public TOnlineDataEnergy queryDataEnergyByGetTime(@Param("terminalId")String terminalId,@Param("getTime")BigInteger getTime);
}
