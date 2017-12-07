package com.tg.model;

import java.math.BigInteger;

/**
 * Created by Administrator on 2017/8/2.
 * 监测点档案表
 */
public class TMonitor {
    //编号
    private BigInteger id;
    //名称
    private String name;
    //企业编号
    private BigInteger companyId;
    //上级编号
    private BigInteger parentId;
    //自定义编号
    private String customizeNo;
    //接线方式
    private int wiringWay;
    //配网类型（0：配网；1：变压器；2：虚拟节点）
    private int seeType;
    //监测对象
    private int monitorObj;
    //CT变比
    private double ct;
    //CT是否启用
    private int useCt;
    //PT变比
    private double pt;
    //PT是否启用
    private int usePt;
    //额定功率（容量）
    private long pe;
    //额定电压
    private long ue;
    //监测仪编号
    private String terminalId;
    //省平台编号
    private String tid;
    //在线时间
    private BigInteger onlineTime;
    //是否在线（0：离线；1：在线）
    private int online;
    //排序号
    private long orderNum;
    //一次接线图
    private String pic;
    //电价类型（1：动力；2：照明，默认为1）
    private int priceType;
    //手机显示(0不显示、1显示，默认为0)
    private int hasMobile;
    //是否显示在网页（0：不显示；1：显示）
    private int displayWeb;
    //描述
    private String description;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getCompanyId() {
        return companyId;
    }

    public void setCompanyId(BigInteger companyId) {
        this.companyId = companyId;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public String getCustomizeNo() {
        return customizeNo;
    }

    public void setCustomizeNo(String customizeNo) {
        this.customizeNo = customizeNo;
    }

    public int getWiringWay() {
        return wiringWay;
    }

    public void setWiringWay(int wiringWay) {
        this.wiringWay = wiringWay;
    }

    public int getSeeType() {
        return seeType;
    }

    public void setSeeType(int seeType) {
        this.seeType = seeType;
    }

    public int getMonitorObj() {
        return monitorObj;
    }

    public void setMonitorObj(int monitorObj) {
        this.monitorObj = monitorObj;
    }

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

    public long getPe() {
        return pe;
    }

    public void setPe(long pe) {
        this.pe = pe;
    }

    public long getUe() {
        return ue;
    }

    public void setUe(long ue) {
        this.ue = ue;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public BigInteger getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(BigInteger onlineTime) {
        this.onlineTime = onlineTime;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public int getHasMobile() {
        return hasMobile;
    }

    public void setHasMobile(int hasMobile) {
        this.hasMobile = hasMobile;
    }

    public int getDisplayWeb() {
        return displayWeb;
    }

    public void setDisplayWeb(int displayWeb) {
        this.displayWeb = displayWeb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
