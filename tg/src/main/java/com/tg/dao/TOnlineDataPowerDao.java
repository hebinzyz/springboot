package com.tg.dao;

import com.tg.model.TOnlineDataPower;
import com.tg.model.TOnlineDataPowerMonit;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface TOnlineDataPowerDao {

    public void addTOnlineDataPower(TOnlineDataPower tOnlineDataPower);

    public int batchInsert(@Param("list") List<TOnlineDataPower> list , @Param("tableName")String tableName);

    public List<TOnlineDataPower> queryDataPower(@Param("list") List<String> list, @Param("getTime")BigInteger getTime );
}
