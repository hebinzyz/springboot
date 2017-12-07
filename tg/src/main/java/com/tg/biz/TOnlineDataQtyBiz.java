package com.tg.biz;

import com.tg.model.TOnlineDataQty;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface TOnlineDataQtyBiz {

    public void addTOnlineDataQty(TOnlineDataQty tOnlineDataQty);

    public void addTOnlineDataQtyBatch(List<TOnlineDataQty> tOnlineDataQty);
}
