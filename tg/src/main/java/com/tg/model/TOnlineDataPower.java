package com.tg.model;
import  	java.math.BigDecimal;
import java.math.BigInteger;

import com.tg.util.Constants;
import com.tg.util.DataSourceContextHolder;

/**
 * Created by Administrator on 2017/7/19.
 * 电力实时表
 */
public class TOnlineDataPower implements IShardable<Integer>,IBatchAble
{
    //自增编号
    private BigInteger id;
    //监测仪编号
    private String terminalId;
    //时间
    private BigInteger getTime;
    //A相负荷
    private BigDecimal pa;
    //B相负荷
    private BigDecimal pb;
    //C相负荷
    private BigDecimal pc;
    //总负荷
    private BigDecimal p;
    //A相无功
    private BigDecimal qa;
    //B相无功
    private BigDecimal qb;
    //C相无功
    private BigDecimal qc;
    //总无功
    private BigDecimal q;
    //A相功率因数
    private BigDecimal pfa;
    //B相功率因数
    private BigDecimal pfb;
    //C相功率因数
    private BigDecimal pfc;
    //总功率因数
    private BigDecimal pf;
    //a相电流
    private BigDecimal ia;
    //b相电流
    private BigDecimal ib;
    //c相电流
    private BigDecimal ic;
    //零序电流
    private BigDecimal iz;
    //A相电压
    private BigDecimal ua;
    //B相电压
    private BigDecimal ub;
    //C相电压
    private BigDecimal uc;
    //Uab线电压
    private BigDecimal uab;
    //Ubc线电压
    private BigDecimal ubc;
    //Uca线电压
    private BigDecimal uca;
    //需量负荷
    private  BigDecimal dp;
    //负荷率
    private BigDecimal pv;

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

    public BigDecimal getPa() {
        return pa;
    }

    public void setPa(BigDecimal pa) {
        this.pa = pa;
    }

    public BigDecimal getPb() {
        return pb;
    }

    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    public BigDecimal getPc() {
        return pc;
    }

    public void setPc(BigDecimal pc) {
        this.pc = pc;
    }

    public BigDecimal getP() {
        return p;
    }

    public void setP(BigDecimal p) {
        this.p = p;
    }

    public BigDecimal getQa() {
        return qa;
    }

    public void setQa(BigDecimal qa) {
        this.qa = qa;
    }

    public BigDecimal getQb() {
        return qb;
    }

    public void setQb(BigDecimal qb) {
        this.qb = qb;
    }

    public BigDecimal getQc() {
        return qc;
    }

    public void setQc(BigDecimal qc) {
        this.qc = qc;
    }

    public BigDecimal getQ() {
        return q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }

    public BigDecimal getPfa() {
        return pfa;
    }

    public void setPfa(BigDecimal pfa) {
        this.pfa = pfa;
    }

    public BigDecimal getPfb() {
        return pfb;
    }

    public void setPfb(BigDecimal pfb) {
        this.pfb = pfb;
    }

    public BigDecimal getPfc() {
        return pfc;
    }

    public void setPfc(BigDecimal pfc) {
        this.pfc = pfc;
    }

    public BigDecimal getPf() {
        return pf;
    }

    public void setPf(BigDecimal pf) {
        this.pf = pf;
    }

    public BigDecimal getIa() {
        return ia;
    }

    public void setIa(BigDecimal ia) {
        this.ia = ia;
    }

    public BigDecimal getIb() {
        return ib;
    }

    public void setIb(BigDecimal ib) {
        this.ib = ib;
    }

    public BigDecimal getIc() {
        return ic;
    }

    public void setIc(BigDecimal ic) {
        this.ic = ic;
    }

    public BigDecimal getIz() {
        return iz;
    }

    public void setIz(BigDecimal iz) {
        this.iz = iz;
    }

    public BigDecimal getUa() {
        return ua;
    }

    public void setUa(BigDecimal ua) {
        this.ua = ua;
    }

    public BigDecimal getUb() {
        return ub;
    }

    public void setUb(BigDecimal ub) {
        this.ub = ub;
    }

    public BigDecimal getUc() {
        return uc;
    }

    public void setUc(BigDecimal uc) {
        this.uc = uc;
    }

    public BigDecimal getUab() {
        return uab;
    }

    public void setUab(BigDecimal uab) {
        this.uab = uab;
    }

    public BigDecimal getUbc() {
        return ubc;
    }

    public void setUbc(BigDecimal ubc) {
        this.ubc = ubc;
    }

    public BigDecimal getUca() {
        return uca;
    }

    public void setUca(BigDecimal uca) {
        this.uca = uca;
    }

    public BigDecimal getDp() {
        return dp;
    }

    public void setDp(BigDecimal dp) {
        this.dp = dp;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    
    private static final String tableName = "t_online_data_power";
    
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
