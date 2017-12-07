package com.tg.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Administrator on 2017/8/2.
 * 客户表
 */
public class TPoweruser {
    //主键ID
    private BigInteger id;
    //公司id
    private BigInteger orgId;
    //企业Logo
    private String logo;
    //过期日期
    private String expiredDate;
    //企业级别(0：普通、1：省级、2：市级、3：省级和市级，默认为0)
    private int rank;
    //行业一
    private BigInteger industry1;
    //行业二
    private BigInteger industry2;
    //行业三
    private BigInteger industry3;
    //报装容量
    private String applyLoad;
    //监测容量(KVA)
    private BigInteger jcrl;
    //主变数量
    private int zbsl;
    //电压等级（公用代码）
    private String voltageClass;
    //计费方式(1：容量、2：需量、3：单一电价，默认为1)
    private int jffs;
    //是否启用自定义用电时段(0：否、1：是，默认为0)
    private int fgpFlag;
    //供电电源（1：网供、 2：电厂、3：混合，默认为1）
    private int powerSupply;
    //企业类型(1：工况企业、2：办公楼宇、3：自备电厂、4：统调电厂、5：风电电厂、6：光伏电厂)
    private int style;
    //企业标识(与国家平台对应,上报数据用)
    private String cno;
    //基本电价(元)
    private String jbdj;
    //需量电价
    private String xldj;
    //容量电价
    private String rldj;
    //登录次数
    private BigInteger loginTimes;
    //自定义状态（0：手动；1：自动）
    private int customState;
    //状态（1：正常、2停产、3：半停）
    private int state;
    //正常生产负荷
    private BigDecimal normalLoad;
    //半停产起始负荷
    private BigDecimal halfLoadStart;
    //半停产结束负荷
    private BigDecimal halfLoadEnd;
    //停产负荷
    private BigDecimal stopLoad;
    //园区编号
    private BigInteger parkId;
    //单位总人数
    private long headCount;
    //单位总面积
    private double grossArea;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public BigInteger getIndustry1() {
        return industry1;
    }

    public void setIndustry1(BigInteger industry1) {
        this.industry1 = industry1;
    }

    public BigInteger getIndustry2() {
        return industry2;
    }

    public void setIndustry2(BigInteger industry2) {
        this.industry2 = industry2;
    }

    public BigInteger getIndustry3() {
        return industry3;
    }

    public void setIndustry3(BigInteger industry3) {
        this.industry3 = industry3;
    }

    public String getApplyLoad() {
        return applyLoad;
    }

    public void setApplyLoad(String applyLoad) {
        this.applyLoad = applyLoad;
    }

    public BigInteger getJcrl() {
        return jcrl;
    }

    public void setJcrl(BigInteger jcrl) {
        this.jcrl = jcrl;
    }

    public int getZbsl() {
        return zbsl;
    }

    public void setZbsl(int zbsl) {
        this.zbsl = zbsl;
    }

    public String getVoltageClass() {
        return voltageClass;
    }

    public void setVoltageClass(String voltageClass) {
        this.voltageClass = voltageClass;
    }

    public int getJffs() {
        return jffs;
    }

    public void setJffs(int jffs) {
        this.jffs = jffs;
    }

    public int getFgpFlag() {
        return fgpFlag;
    }

    public void setFgpFlag(int fgpFlag) {
        this.fgpFlag = fgpFlag;
    }

    public int getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(int powerSupply) {
        this.powerSupply = powerSupply;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getJbdj() {
        return jbdj;
    }

    public void setJbdj(String jbdj) {
        this.jbdj = jbdj;
    }

    public String getXldj() {
        return xldj;
    }

    public void setXldj(String xldj) {
        this.xldj = xldj;
    }

    public String getRldj() {
        return rldj;
    }

    public void setRldj(String rldj) {
        this.rldj = rldj;
    }

    public BigInteger getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(BigInteger loginTimes) {
        this.loginTimes = loginTimes;
    }

    public int getCustomState() {
        return customState;
    }

    public void setCustomState(int customState) {
        this.customState = customState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BigDecimal getNormalLoad() {
        return normalLoad;
    }

    public void setNormalLoad(BigDecimal normalLoad) {
        this.normalLoad = normalLoad;
    }

    public BigDecimal getHalfLoadStart() {
        return halfLoadStart;
    }

    public void setHalfLoadStart(BigDecimal halfLoadStart) {
        this.halfLoadStart = halfLoadStart;
    }

    public BigDecimal getHalfLoadEnd() {
        return halfLoadEnd;
    }

    public void setHalfLoadEnd(BigDecimal halfLoadEnd) {
        this.halfLoadEnd = halfLoadEnd;
    }

    public BigDecimal getStopLoad() {
        return stopLoad;
    }

    public void setStopLoad(BigDecimal stopLoad) {
        this.stopLoad = stopLoad;
    }

    public BigInteger getParkId() {
        return parkId;
    }

    public void setParkId(BigInteger parkId) {
        this.parkId = parkId;
    }

    public long getHeadCount() {
        return headCount;
    }

    public void setHeadCount(long headCount) {
        this.headCount = headCount;
    }

    public double getGrossArea() {
        return grossArea;
    }

    public void setGrossArea(double grossArea) {
        this.grossArea = grossArea;
    }
}
