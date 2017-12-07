package com.tg.dao;

import com.tg.model.TOnlineDataHarmonicI;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface TOnlineDataHarmonicIDao {

    public void addTOnlineDataHarmonicI(TOnlineDataHarmonicI tOnlineDataHarmonicI);

    public int batchInsert(@Param("list") List<TOnlineDataHarmonicI> list , @Param("tableName")String tableName);
}
