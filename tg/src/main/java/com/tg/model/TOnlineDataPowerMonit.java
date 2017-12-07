package com.tg.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Administrator on 2017/7/19.
 * 电力实时表
 */
public class TOnlineDataPowerMonit {
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
    private BigDecimal dp;
    //负荷率
    private BigDecimal pv;
    //CT变比
    private double ct;
    //CT是否启用
    private int useCt;
    //PT变比
    private double pt;
    //PT是否启用
    private int usePt;

    public double getCt() {
        return ct;
    }

    public void setCt(double ct) {
        this.ct = ct;
    }

    public int getUseCt() {
        return useCt;
    }

    public void setUseCt(int useCt) {
        this.useCt = useCt;
    }

    public double getPt() {
        return pt;
    }

    public void setPt(double pt) {
        this.pt = pt;
    }

    public int getUsePt() {
        return usePt;
    }

    public void setUsePt(int usePt) {
        this.usePt = usePt;
    }

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
}
