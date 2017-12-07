package com.tg.model;

import java.math.BigInteger;

/**
 * Created by Administrator on 2017/8/11.
 * 密钥分配表
 */
public class TKeyDistribute {
    //编号
    private BigInteger id;
    //申请id
    private String appId;
    //申请key
    private String appKey;
    //厂商名称
    private String name;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
