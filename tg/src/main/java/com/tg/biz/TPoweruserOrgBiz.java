package com.tg.biz;

import com.tg.model.TPoweruserOrg;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface TPoweruserOrgBiz {

    /**
     * 查询企业id和名称
     * @return
     */
    public List<TPoweruserOrg> getAll();

    /**
     * 查询企业名称
     * @param orgId
     * @return
     */
    public TPoweruserOrg getTPoweruserOrg(BigInteger orgId);
}
