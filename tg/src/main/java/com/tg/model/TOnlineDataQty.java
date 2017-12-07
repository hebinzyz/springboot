package com.tg.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/21.
 * 电能质量实时表
 */
public class TOnlineDataQty implements IShardable<Integer>,IBatchAble
{
    //自增编号
    private BigInteger id;
    //监测仪编号
    private String terminalId;
    //时间
    private BigInteger getTime;
    //A相电压偏差
    private BigDecimal uaw;
    //B相电压偏差
    private BigDecimal ubw;
    //C相电压偏差
    private BigDecimal ucw;
    //Uab线电压偏差
    private BigDecimal uabw;
    //Ubc线电压偏差
    private BigDecimal ubcw;
    //Uca线电压偏差
    private BigDecimal ucaw;
    //频率偏差
    private BigDecimal fw;
    //频率
    private BigDecimal f;
    //三相电流不平衡度
    private BigDecimal inbalance;
    //三相电压不平衡度
    private BigDecimal unbalance;
    //温度一
    private BigDecimal t;
    //温度二
    private BigDecimal t2;
    //温度三
    private BigDecimal t3;
    //温度四
    private BigDecimal t4;
    //温度五
    private BigDecimal t5;
    //温度六
    private BigDecimal t6;
    //温度七
    private BigDecimal t7;
    //温度八
    private BigDecimal t8;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public BigInteger getGetTime() {
        return getTime;
    }

    public void setGetTime(BigInteger getTime) {
        this.getTime = getTime;
    }

    public BigDecimal getUaw() {
        return uaw;
    }

    public void setUaw(BigDecimal uaw) {
        this.uaw = uaw;
    }

    public BigDecimal getUbw() {
        return ubw;
    }

    public void setUbw(BigDecimal ubw) {
        this.ubw = ubw;
    }

    public BigDecimal getUcw() {
        return ucw;
    }

    public void setUcw(BigDecimal ucw) {
        this.ucw = ucw;
    }

    public BigDecimal getUabw() {
        return uabw;
    }

    public void setUabw(BigDecimal uabw) {
        this.uabw = uabw;
    }

    public BigDecimal getUbcw() {
        return ubcw;
    }

    public void setUbcw(BigDecimal ubcw) {
        this.ubcw = ubcw;
    }

    public BigDecimal getUcaw() {
        return ucaw;
    }

    public void setUcaw(BigDecimal ucaw) {
        this.ucaw = ucaw;
    }

    public BigDecimal getFw() {
        return fw;
    }

    public void setFw(BigDecimal fw) {
        this.fw = fw;
    }

    public BigDecimal getF() {
        return f;
    }

    public void setF(BigDecimal f) {
        this.f = f;
    }

    public BigDecimal getInbalance() {
        return inbalance;
    }

    public void setInbalance(BigDecimal inbalance) {
        this.inbalance = inbalance;
    }

    public BigDecimal getUnbalance() {
        return unbalance;
    }

    public void setUnbalance(BigDecimal unbalance) {
        this.unbalance = unbalance;
    }

    public BigDecimal getT() {
        return t;
    }

    public void setT(BigDecimal t) {
        this.t = t;
    }

    public BigDecimal getT2() {
        return t2;
    }

    public void setT2(BigDecimal t2) {
        this.t2 = t2;
    }

    public BigDecimal getT3() {
        return t3;
    }

    public void setT3(BigDecimal t3) {
        this.t3 = t3;
    }

    public BigDecimal getT4() {
        return t4;
    }

    public void setT4(BigDecimal t4) {
        this.t4 = t4;
    }

    public BigDecimal getT5() {
        return t5;
    }

    public void setT5(BigDecimal t5) {
        this.t5 = t5;
    }

    public BigDecimal getT6() {
        return t6;
    }

    public void setT6(BigDecimal t6) {
        this.t6 = t6;
    }

    public BigDecimal getT7() {
        return t7;
    }

    public void setT7(BigDecimal t7) {
        this.t7 = t7;
    }

    public BigDecimal getT8() {
        return t8;
    }

    public void setT8(BigDecimal t8) {
        this.t8 = t8;
    }

    private static final String tableName = "t_online_data_qty";
    
	@Override
	public String getActualTableName() 
	{
        int index = (int) ((getTime.longValue()/Constants.GETTIME_MOD_MONTH_AND_YEAR)%100);
        if (index < 10)
        {
            return tableName +"_0"+ index;
        }
        return tableName +"_"+ index;
	}

	@Override
	public String getActualDBName() 
	{
		return "dsm"+(getTime.longValue()/Constants.GETTIME_MOD_YEAR);
	}
	
	@Override
	public String getTableName() 
	{
		return tableName;
	}

	@Override
	public String getDBName() 
	{
		return DataSourceContextHolder.anhui_dsm;
	}
	
	@Override
	public Integer getShardFlag() 
	{
		return (int) ((getTime.longValue()/Constants.GETTIME_MOD_MONTH_AND_YEAR)%100);
	}
}
