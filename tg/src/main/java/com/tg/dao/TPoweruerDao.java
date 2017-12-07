package com.tg.dao;

import com.tg.model.TPoweruser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
public interface TPoweruerDao {

    public TPoweruser getTPoweruser( @Param("orgId")String orgId);
}
