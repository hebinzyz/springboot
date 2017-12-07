package com.tg.model;


import java.math.BigDecimal;
import java.math.BigInteger;

import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/19.
 * 谐波电流实时表
 */
public class TOnlineDataHarmonicI implements IShardable<Integer>,IBatchAble
{
    //编号
    private BigInteger id;
    //自增编号
    private String terminalId ;
    //监测仪编号
    private BigInteger getTime;
    //得到数据时间
    private String htype;
    //谐波电流类型
    private BigDecimal h3;
    //3次谐波
    private BigDecimal h5;
    //5次谐波
    private BigDecimal h7;
    //9次谐波
    private BigDecimal h9;
    //11次谐波
    private BigDecimal h11;
    //13次谐波
    private BigDecimal h13;
    //15次谐波
    private BigDecimal h15;
    //17次谐波
    private BigDecimal h17;
    //19次谐波
    private BigDecimal h19;
    //21次谐波
    private BigDecimal h21;
    //23次谐波
    private BigDecimal h23;
    //25次谐波
    private BigDecimal h25;
    //27次谐波
    private BigDecimal h27;
    //29次谐波
    private BigDecimal h29;
    //31次谐波
    private BigDecimal h31;
    //
    private BigDecimal hall;
    //基波
    private BigDecimal baseI;


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



    public String getHtype() {
        return htype;
    }

    public void setHtype(String htype) {
        this.htype = htype;
    }

    public BigDecimal getH3() {
        return h3;
    }

    public void setH3(BigDecimal h3) {
        this.h3 = h3;
    }

    public BigDecimal getH5() {
        return h5;
    }

    public void setH5(BigDecimal h5) {
        this.h5 = h5;
    }

    public BigDecimal getH7() {
        return h7;
    }

    public void setH7(BigDecimal h7) {
        this.h7 = h7;
    }

    public BigDecimal getH9() {
        return h9;
    }

    public void setH9(BigDecimal h9) {
        this.h9 = h9;
    }

    public BigDecimal getH11() {
        return h11;
    }

    public void setH11(BigDecimal h11) {
        this.h11 = h11;
    }

    public BigDecimal getH13() {
        return h13;
    }

    public void setH13(BigDecimal h13) {
        this.h13 = h13;
    }

    public BigDecimal getH15() {
        return h15;
    }

    public void setH15(BigDecimal h15) {
        this.h15 = h15;
    }

    public BigDecimal getH17() {
        return h17;
    }

    public void setH17(BigDecimal h17) {
        this.h17 = h17;
    }

    public BigDecimal getH19() {
        return h19;
    }

    public void setH19(BigDecimal h19) {
        this.h19 = h19;
    }

    public BigDecimal getH21() {
        return h21;
    }

    public void setH21(BigDecimal h21) {
        this.h21 = h21;
    }

    public BigDecimal getH23() {
        return h23;
    }

    public void setH23(BigDecimal h23) {
        this.h23 = h23;
    }

    public BigDecimal getH25() {
        return h25;
    }

    public void setH25(BigDecimal h25) {
        this.h25 = h25;
    }

    public BigDecimal getH27() {
        return h27;
    }

    public void setH27(BigDecimal h27) {
        this.h27 = h27;
    }

    public BigDecimal getH29() {
        return h29;
    }

    public void setH29(BigDecimal h29) {
        this.h29 = h29;
    }

    public BigDecimal getH31() {
        return h31;
    }

    public void setH31(BigDecimal h31) {
        this.h31 = h31;
    }

    public BigDecimal getHall() {
        return hall;
    }

    public void setHall(BigDecimal hall) {
        this.hall = hall;
    }

    public BigDecimal getBaseI() {
        return baseI;
    }

    public void setBaseI(BigDecimal baseI) {
        this.baseI = baseI;
    }
    
    private static final String tableName = "t_online_data_harmonic_i";
    
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
