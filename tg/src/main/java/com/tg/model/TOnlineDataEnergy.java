package com.tg.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/19.
 * 电量实时表
 */
public class TOnlineDataEnergy implements IShardable<Integer>,IBatchAble
{
    //编号
    private BigInteger id;
    //监测仪编号
    private String terminalId;
    //时间
    private BigInteger getTime;
    //正相有功电量
    private BigDecimal tpe;
    //正相无功电量
    private BigDecimal tqe;
    //反相有功电量
    private BigDecimal fpe;
    //反相无功电量
    private BigDecimal fqe;
    //正相有功电度示值
    private BigDecimal tps;
    //正相无功电度示值
    private BigDecimal tqs;
    //反相有功电度示值
    private BigDecimal fps;
    //反相无功电度示值
    private BigDecimal fqs;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getGetTime() {
        return getTime;
    }

    public void setGetTime(BigInteger getTime) {
        this.getTime = getTime;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }



    public BigDecimal getTpe() {
        return tpe;
    }

    public void setTpe(BigDecimal tpe) {
        this.tpe = tpe;
    }

    public BigDecimal getTqe() {
        return tqe;
    }

    public void setTqe(BigDecimal tqe) {
        this.tqe = tqe;
    }

    public BigDecimal getFpe() {
        return fpe;
    }

    public void setFpe(BigDecimal fpe) {
        this.fpe = fpe;
    }

    public BigDecimal getFqe() {
        return fqe;
    }

    public void setFqe(BigDecimal fqe) {
        this.fqe = fqe;
    }

    public BigDecimal getTps() {
        return tps;
    }

    public void setTps(BigDecimal tps) {
        this.tps = tps;
    }

    public BigDecimal getTqs() {
        return tqs;
    }

    public void setTqs(BigDecimal tqs) {
        this.tqs = tqs;
    }

    public BigDecimal getFps() {
        return fps;
    }

    public void setFps(BigDecimal fps) {
        this.fps = fps;
    }

    public BigDecimal getFqs() {
        return fqs;
    }

    public void setFqs(BigDecimal fqs) {
        this.fqs = fqs;
    }

    private static final String tableName = "t_online_data_energy";
    
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
