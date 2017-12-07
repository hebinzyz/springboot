package com.tg.biz;

import com.tg.model.TOnlineDataPower;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface TOnlineDataPowerBiz {
    public void addTOnlineDataPower(TOnlineDataPower tOnlineDataPower);

    public List<TOnlineDataPower> queryDataPower(List<String> list, BigInteger getTime);

    public void addTOnlineDataPowerBatch(List<TOnlineDataPower> list);
}
