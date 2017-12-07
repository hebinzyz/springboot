package com.tg.biz;

import com.tg.model.TOnlineDataEnergy;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface TOnlineDataEnergyBiz {

    public void addTOnlineDataEnergy(TOnlineDataEnergy tOnlineDataEnergy);

    public TOnlineDataEnergy queryDataEnergy(String terminalId, BigInteger beginTime, BigInteger endTime);

    public TOnlineDataEnergy queryDataEnergyByGetTime(String terminalId, BigInteger getTime);

    public void addTOnlineDataEnergyBatch(List<TOnlineDataEnergy> list);
}
