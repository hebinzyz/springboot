package com.tg.dao;

import com.tg.model.TKeyDistribute;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/8/11.
 * 密钥分配dao
 */
public interface TKeyDistributeDao {

    /**
     * 根据厂商id查询key
     * @param appid
     * @return
     */
    public TKeyDistribute queryTKeyDistribute(@Param("appid")String appid);

}
