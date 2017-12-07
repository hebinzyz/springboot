package com.tg.dao;

import com.tg.model.TOnlineDataQty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface TOnlineDataQtyDao {

    public void addTOnlineDataQty(TOnlineDataQty tOnlineDataQty);

    public int batchInsert(@Param("list") List<TOnlineDataQty> list , @Param("tableName")String tableName);
}
