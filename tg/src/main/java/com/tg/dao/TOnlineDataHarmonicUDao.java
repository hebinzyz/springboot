package com.tg.dao;

import com.tg.model.TOnlineDataHarmonicU;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface TOnlineDataHarmonicUDao {

    public void addTOnlineDataHarmonicU(TOnlineDataHarmonicU tOnlineDataHarmonicU);

    public int batchInsert(@Param("list") List<TOnlineDataHarmonicU> list , @Param("tableName")String tableName);
}
