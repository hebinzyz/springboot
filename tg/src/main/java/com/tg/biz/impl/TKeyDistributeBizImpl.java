package com.tg.biz.impl;

import com.tg.biz.TKeyDistributeBiz;
import com.tg.dao.TKeyDistributeDao;
import com.tg.model.TKeyDistribute;
import com.tg.util.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/11.
 * 密钥分配
 */
@Service
public class TKeyDistributeBizImpl implements TKeyDistributeBiz {
    @Autowired
    private TKeyDistributeDao dao;

    @Override
    public TKeyDistribute queryTKeyDistribute(String appid) {
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.dsm_sysbase);
        return dao.queryTKeyDistribute(appid);
    }
}
