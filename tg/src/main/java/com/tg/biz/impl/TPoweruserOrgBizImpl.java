package com.tg.biz.impl;

import com.tg.biz.TPoweruserOrgBiz;
import com.tg.dao.TPoweruserOrgDao;
import com.tg.model.TPoweruserOrg;
import com.tg.util.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
public class TPoweruserOrgBizImpl implements TPoweruserOrgBiz {

    @Autowired
    private TPoweruserOrgDao dao;
    @Override
    public List<TPoweruserOrg> getAll() {
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.dsm_sysbase);
        return dao.getAll();
    }

    @Override
    public TPoweruserOrg getTPoweruserOrg(BigInteger orgId) {
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.dsm_sysbase);
        return dao.getTPoweruserOrg(orgId);
    }
}
