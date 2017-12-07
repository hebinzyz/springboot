package com.tg.dao;

import com.tg.model.TPoweruserOrg;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 * 企业dao
 */
public interface TPoweruserOrgDao {

    public List<TPoweruserOrg> getAll();

    /**
     * 查询企业名称
     * @param orgId
     * @return
     */
    public TPoweruserOrg getTPoweruserOrg( @Param("orgId")BigInteger orgId);
}
