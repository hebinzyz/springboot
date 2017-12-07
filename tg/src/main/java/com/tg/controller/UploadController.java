package com.tg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tg.biz.*;
import com.tg.model.*;
import com.tg.util.AESCrptography;
import com.tg.util.Constants;
import com.tg.util.PropertiesUtil;
import com.tg.util.StreamConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by n008566 on 2017/7/4.
 */
@Controller
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private TOnlineDataEnergyBiz energyBiz;
    @Autowired
    private TOnlineDataHarmonicIBiz harmonicIBiz;
    @Autowired
    private TOnlineDataHarmonicUBiz harmonicUBiz;
    @Autowired
    private TOnlineDataPowerBiz powerBiz;
    @Autowired
    private TOnlineDataQtyBiz qtyBiz;
    @Autowired
    private TMonitorBiz tMonitorBiz;
    @Autowired
    private TKeyDistributeBiz tKeyDistributeBiz;
    
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);
    private  static  Properties prop =  PropertiesUtil.loadClassLoader("global.properties");
    @RequestMapping(value = "/uploadAllData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadAllData(String appid,HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject obj = new JSONObject();
        String  companyId = "";
        try {
            //prop = PropertiesUtil.loadClassLoader("global.properties");
            String flag = prop.getProperty("ae.encrypt");

            ServletInputStream inputStream = request.getInputStream();
            String content = StreamConvert.getInputStreamString(inputStream);
            if("true".equals(flag)){
                TKeyDistribute tKeyDistribute = tKeyDistributeBiz.queryTKeyDistribute(appid);
                String appkey =  tKeyDistribute.getAppKey();
                content = AESCrptography.Decrypt(content, appkey);
                if(content ==null){
                    resultMap.put("code", Constants.DECRYPT_FAILURE_CODE);
                    return resultMap;
                }
            }
             obj = JSONObject.parseObject(content);
             companyId = obj.getString("companyId");
            logger.info(companyId);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            logger.error("cw:"+companyId);
            resultMap.put("code", Constants.DECRYPT_FAILURE_CODE);
        }
        try{
            if (obj.get("dataType") != null) {
                String type = obj.get("dataType").toString();
                switch (type) {
                    case "01":
                        resultMap = toTOnlineDataPower(obj);
                        break;
                    case "02":
                        resultMap = toHarmonicU(obj);
                        break;
                    case "03":
                        resultMap = toHarmonicI(obj);
                        break;
                    default:
                        resultMap.put("code",Constants.INVALID_DATA);

                }
                //上传数据后更新缓存和表onlineTime
                TMonitorCacheEntry cacheEntry = new TMonitorCacheEntry();
                cacheEntry.setCompanyId(obj.getLong("companyId"));

            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            logger.error("cw:"+companyId);
            resultMap.put("code", Constants.DATAERROR_CODE);
        }

        return resultMap;
    }

    public Map<String, Object> toHarmonicU(JSONObject jsStr) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int n = 0;
        JSONArray JSONArray = jsStr.getJSONArray("dataList");
        int num = JSONArray.size() ;
        long companyId = jsStr.getLong("companyId");
        List<String> terminalId = new ArrayList<>();
        List<String> lastgetTime =new ArrayList<String>();
        List<TOnlineDataHarmonicU> tOnlineDataHarmonicUList = new ArrayList<>();
        for (int i = 0; i < JSONArray.size(); i++) {
            JSONObject obj = (JSONObject) JSONArray.get(i);
            Boolean flag = tMonitorBiz.checkMonitorExist(companyId, obj.get("terminalId").toString());
            if (flag) {
                terminalId.add(obj.get("terminalId").toString());
                lastgetTime.add(obj.getString("getTime"));
                TOnlineDataHarmonicU entity = new TOnlineDataHarmonicU();
                entity.setTerminalId(obj.getString("terminalId"));
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setHtype(Constants.HTYPE_UA);
                entity.setBaseU(obj.getBigDecimal("01"));
                entity.setHall(obj.getBigDecimal("02"));
                entity.setH3(obj.getBigDecimal("03"));
                entity.setH5(obj.getBigDecimal("04"));
                entity.setH7(obj.getBigDecimal("05"));
                entity.setH9(obj.getBigDecimal("06"));
                entity.setH11(obj.getBigDecimal("07"));
                entity.setH13(obj.getBigDecimal("08"));
                entity.setH15(obj.getBigDecimal("09"));
                entity.setH17(obj.getBigDecimal("10"));
                entity.setH19(obj.getBigDecimal("11"));
                entity.setH21(obj.getBigDecimal("12"));
                entity.setH23(obj.getBigDecimal("13"));
                entity.setH25(obj.getBigDecimal("14"));
                entity.setH27(obj.getBigDecimal("15"));
                entity.setH29(obj.getBigDecimal("16"));
                entity.setH31(obj.getBigDecimal("17"));
                tOnlineDataHarmonicUList.add(entity);
               // harmonicUBiz.addTOnlineDataHarmonicU(entity);
                //记录日记
                //logger.info("插入谐波电压实时表" + AESCrptography.objectToString(entity));
                entity = new TOnlineDataHarmonicU();
                entity.setTerminalId(obj.getString("terminalId"));
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setHtype(Constants.HTYPE_UB);
                entity.setBaseU(obj.getBigDecimal("18"));
                entity.setHall(obj.getBigDecimal("19"));
                entity.setH3(obj.getBigDecimal("20"));
                entity.setH5(obj.getBigDecimal("21"));
                entity.setH7(obj.getBigDecimal("22"));
                entity.setH9(obj.getBigDecimal("23"));
                entity.setH11(obj.getBigDecimal("24"));
                entity.setH13(obj.getBigDecimal("25"));
                entity.setH15(obj.getBigDecimal("26"));
                entity.setH17(obj.getBigDecimal("27"));
                entity.setH19(obj.getBigDecimal("28"));
                entity.setH21(obj.getBigDecimal("29"));
                entity.setH23(obj.getBigDecimal("30"));
                entity.setH25(obj.getBigDecimal("31"));
                entity.setH27(obj.getBigDecimal("32"));
                entity.setH29(obj.getBigDecimal("33"));
                entity.setH31(obj.getBigDecimal("34"));
                tOnlineDataHarmonicUList.add(entity);
                //harmonicUBiz.addTOnlineDataHarmonicU(entity);
                //logger.info("插入谐波电压实时表" + AESCrptography.objectToString(entity));
                entity = new TOnlineDataHarmonicU();
                entity.setTerminalId(obj.getString("terminalId"));
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setHtype(Constants.HTYPE_UC);
                entity.setBaseU(obj.getBigDecimal("35"));
                entity.setHall(obj.getBigDecimal("36"));
                entity.setH3(obj.getBigDecimal("37"));
                entity.setH5(obj.getBigDecimal("38"));
                entity.setH7(obj.getBigDecimal("39"));
                entity.setH9(obj.getBigDecimal("40"));
                entity.setH11(obj.getBigDecimal("41"));
                entity.setH13(obj.getBigDecimal("42"));
                entity.setH15(obj.getBigDecimal("43"));
                entity.setH17(obj.getBigDecimal("44"));
                entity.setH19(obj.getBigDecimal("45"));
                entity.setH21(obj.getBigDecimal("46"));
                entity.setH23(obj.getBigDecimal("47"));
                entity.setH25(obj.getBigDecimal("48"));
                entity.setH27(obj.getBigDecimal("49"));
                entity.setH29(obj.getBigDecimal("50"));
                entity.setH31(obj.getBigDecimal("51"));
                tOnlineDataHarmonicUList.add(entity);
               // harmonicUBiz.addTOnlineDataHarmonicU(entity);
               // logger.info("插入谐波电压实时表" + AESCrptography.objectToString(entity));
                n++;
            }else{
                logger.info("not have companyId:"+companyId +" terminalId:"+obj.get("terminalId").toString());
            }
        }
        //harmonicUBiz.addTOnlineDataHarmonicUBatch(tOnlineDataHarmonicUList);
        Map<BigInteger ,List<TOnlineDataHarmonicU>> map =new HashMap<BigInteger ,List<TOnlineDataHarmonicU>>() ;
        Map<BigInteger,BigInteger>  getTime = new HashMap<>();
        for (TOnlineDataHarmonicU tOnlineDataHarmonicU:tOnlineDataHarmonicUList) {
            getTime.put(tOnlineDataHarmonicU.getGetTime(),tOnlineDataHarmonicU.getGetTime());
        }
        for(BigInteger timekey:getTime.keySet()){
            List<TOnlineDataHarmonicU> list = new ArrayList<>();
            for (TOnlineDataHarmonicU tOnlineDataHarmonicU:tOnlineDataHarmonicUList) {
                if(timekey.equals(tOnlineDataHarmonicU.getGetTime())){
                    list.add(tOnlineDataHarmonicU);
                }
            }
            map.put(timekey,list);
        }
        for(BigInteger key:map.keySet()){
            harmonicUBiz.addTOnlineDataHarmonicUBatch(map.get(key));
        }
//        for (TOnlineDataHarmonicU tOnlineDataHarmonicU:tOnlineDataHarmonicUList) {
//            logger.info(AESCrptography.objectToString(tOnlineDataHarmonicU));
//        }
        resultMap.put("total", num);
        resultMap.put("fail", num - n);
        resultMap.put("success", n);
        if(n>0){
            for(int i=0;i<terminalId.size();i++){
                updateGetTime(companyId,terminalId.get(i),Long.parseLong(lastgetTime.get(i)));
            }
        }
        return resultMap;
    }
    public void updateGetTime(long companyId,String terminalId,long onlineTime){
        long time = tMonitorBiz.getGetTime(companyId,terminalId);
        if(onlineTime>time){
            TMonitorCacheEntry tMonitor = new TMonitorCacheEntry();
            tMonitor.setCompanyId(companyId);
            tMonitor.setTerminalId(terminalId);
            tMonitor.setOnlineTime(onlineTime);
            tMonitorBiz.updateTmonitor(tMonitor);
            tMonitorBiz.updatCache(tMonitor);

        }

    }

    public Map<String, Object> toHarmonicI(JSONObject jsStr) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int n = 0;
        JSONArray JSONArray = jsStr.getJSONArray("dataList");
        int num = JSONArray.size() ;
        long companyId = jsStr.getLong("companyId");
        List<String> terminalId = new ArrayList<>();
        List<String> lastgetTime =new ArrayList<String>();
        List<TOnlineDataHarmonicI> tOnlineDataHarmonicIList = new ArrayList<>();
        for (int i = 0; i < JSONArray.size(); i++) {
            JSONObject obj = (JSONObject) JSONArray.get(i);
            Boolean flag = tMonitorBiz.checkMonitorExist(companyId, obj.get("terminalId").toString());
            if (flag) {
                terminalId.add(obj.get("terminalId").toString());
                lastgetTime.add(obj.getString("getTime"));
                TOnlineDataHarmonicI entity = new TOnlineDataHarmonicI();
                entity.setTerminalId(obj.getString("terminalId"));
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setHtype(Constants.HTYPE_IA);
                entity.setBaseI(obj.getBigDecimal("01"));
                entity.setHall(obj.getBigDecimal("02"));
                entity.setH3(obj.getBigDecimal("03"));
                entity.setH5(obj.getBigDecimal("04"));
                entity.setH7(obj.getBigDecimal("05"));
                entity.setH9(obj.getBigDecimal("06"));
                entity.setH11(obj.getBigDecimal("07"));
                entity.setH13(obj.getBigDecimal("08"));
                entity.setH15(obj.getBigDecimal("09"));
                entity.setH17(obj.getBigDecimal("10"));
                entity.setH19(obj.getBigDecimal("11"));
                entity.setH21(obj.getBigDecimal("12"));
                entity.setH23(obj.getBigDecimal("13"));
                entity.setH25(obj.getBigDecimal("14"));
                entity.setH27(obj.getBigDecimal("15"));
                entity.setH29(obj.getBigDecimal("16"));
                entity.setH31(obj.getBigDecimal("17"));
                tOnlineDataHarmonicIList.add(entity);
               // harmonicIBiz.addTOnlineDataHarmonicI(entity);
                //logger.info("插入谐波电流实时表" + AESCrptography.objectToString(entity));
                entity = new TOnlineDataHarmonicI();
                entity.setTerminalId(obj.getString("terminalId"));
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setHtype(Constants.HTYPE_IB);
                entity.setBaseI(obj.getBigDecimal("18"));
                entity.setHall(obj.getBigDecimal("19"));
                entity.setH3(obj.getBigDecimal("20"));
                entity.setH5(obj.getBigDecimal("21"));
                entity.setH7(obj.getBigDecimal("22"));
                entity.setH9(obj.getBigDecimal("23"));
                entity.setH11(obj.getBigDecimal("24"));
                entity.setH13(obj.getBigDecimal("25"));
                entity.setH15(obj.getBigDecimal("26"));
                entity.setH17(obj.getBigDecimal("27"));
                entity.setH19(obj.getBigDecimal("28"));
                entity.setH21(obj.getBigDecimal("29"));
                entity.setH23(obj.getBigDecimal("30"));
                entity.setH25(obj.getBigDecimal("31"));
                entity.setH27(obj.getBigDecimal("32"));
                entity.setH29(obj.getBigDecimal("33"));
                entity.setH31(obj.getBigDecimal("34"));
                tOnlineDataHarmonicIList.add(entity);
               // harmonicIBiz.addTOnlineDataHarmonicI(entity);
                //logger.info("插入谐波电流实时表" + AESCrptography.objectToString(entity));
                entity = new TOnlineDataHarmonicI();
                entity.setTerminalId(obj.getString("terminalId"));
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setHtype(Constants.HTYPE_IC);
                entity.setBaseI(obj.getBigDecimal("35"));
                entity.setHall(obj.getBigDecimal("36"));
                entity.setH3(obj.getBigDecimal("37"));
                entity.setH5(obj.getBigDecimal("38"));
                entity.setH7(obj.getBigDecimal("39"));
                entity.setH9(obj.getBigDecimal("40"));
                entity.setH11(obj.getBigDecimal("41"));
                entity.setH13(obj.getBigDecimal("42"));
                entity.setH15(obj.getBigDecimal("43"));
                entity.setH17(obj.getBigDecimal("44"));
                entity.setH19(obj.getBigDecimal("45"));
                entity.setH21(obj.getBigDecimal("46"));
                entity.setH23(obj.getBigDecimal("47"));
                entity.setH25(obj.getBigDecimal("48"));
                entity.setH27(obj.getBigDecimal("49"));
                entity.setH29(obj.getBigDecimal("50"));
                entity.setH31(obj.getBigDecimal("51"));
                tOnlineDataHarmonicIList.add(entity);
                //harmonicIBiz.addTOnlineDataHarmonicI(entity);
                //logger.info("插入谐波电流实时表" + AESCrptography.objectToString(entity));
                n++;
            }else{
                logger.info("not have companyId:"+companyId +" terminalId:"+obj.get("terminalId").toString());
            }
        }
       // harmonicIBiz.addTOnlineDataHarmonicIBatch(tOnlineDataHarmonicIList);
        Map<BigInteger ,List<TOnlineDataHarmonicI>> map =new HashMap<BigInteger ,List<TOnlineDataHarmonicI>>() ;
        Map<BigInteger,BigInteger>  getTime = new HashMap<>();
        for (TOnlineDataHarmonicI tOnlineDataHarmonicI:tOnlineDataHarmonicIList) {
            getTime.put(tOnlineDataHarmonicI.getGetTime(),tOnlineDataHarmonicI.getGetTime());
        }
        for(BigInteger timekey:getTime.keySet()){
            List<TOnlineDataHarmonicI> list = new ArrayList<>();
            for (TOnlineDataHarmonicI tOnlineDataHarmonicI:tOnlineDataHarmonicIList) {
                if(timekey.equals(tOnlineDataHarmonicI.getGetTime())){
                    list.add(tOnlineDataHarmonicI);
                }
            }
            map.put(timekey,list);
        }
        for(BigInteger key:map.keySet()){
            harmonicIBiz.addTOnlineDataHarmonicIBatch(map.get(key));
        }
//        for (TOnlineDataHarmonicI tOnlineDataHarmonicI:tOnlineDataHarmonicIList) {
//            logger.info("插入谐波电流实时表" + AESCrptography.objectToString(tOnlineDataHarmonicI));
//        }
        resultMap.put("total", num);
        resultMap.put("fail", num - n);
        resultMap.put("success", n);
        if(n>0){
            for(int i=0;i<terminalId.size();i++){
                updateGetTime(companyId,terminalId.get(i),Long.parseLong(lastgetTime.get(i)));
            }
        }
        return resultMap;
    }

    public Map<String, Object> toTOnlineDataPower(JSONObject jsStr) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int n = 0;
        JSONArray JSONArray = jsStr.getJSONArray("dataList");
        int num = JSONArray.size() ;
        long companyId = jsStr.getLong("companyId");
        List<String> terminalId = new ArrayList<>();
        List<String> lastgetTime =new ArrayList<String>();
        //组合list
        List<TOnlineDataPower>  tOnlineDataPowerList = new ArrayList<>();
        List<TOnlineDataQty> tOnlineDataQtyList = new ArrayList<>();
        List<TOnlineDataEnergy> tOnlineDataEnergyList = new ArrayList<>();
        for (int i = 0; i < JSONArray.size(); i++) {
            JSONObject obj = (JSONObject) JSONArray.get(i);
            //terminalId = obj.getString("terminalId");
            Boolean flag = tMonitorBiz.checkMonitorExist(companyId, obj.get("terminalId").toString());
            if (flag) {
                terminalId.add(obj.getString("terminalId"));
                lastgetTime.add(obj.getString("getTime"));
                TOnlineDataPower entity = new TOnlineDataPower();
                entity.setTerminalId(obj.get("terminalId").toString());
                entity.setGetTime(obj.getBigInteger("getTime"));
                entity.setP(obj.getBigDecimal("01"));
                entity.setPa(obj.getBigDecimal("02"));
                entity.setPb(obj.getBigDecimal("03"));
                entity.setPc(obj.getBigDecimal("04"));
                entity.setQ(obj.getBigDecimal("05"));
                entity.setQa(obj.getBigDecimal("06"));
                entity.setQb(obj.getBigDecimal("07"));
                entity.setQc(obj.getBigDecimal("08"));
                entity.setPf(obj.getBigDecimal("09"));
                entity.setPfa(obj.getBigDecimal("10"));
                entity.setPfb(obj.getBigDecimal("11"));
                entity.setPfc(obj.getBigDecimal("12"));
                entity.setIa(obj.getBigDecimal("13"));
                entity.setIb(obj.getBigDecimal("14"));
                entity.setIc(obj.getBigDecimal("15"));
                entity.setIz(obj.getBigDecimal("16"));
                entity.setUa(obj.getBigDecimal("17"));
                entity.setUb(obj.getBigDecimal("18"));
                entity.setUc(obj.getBigDecimal("19"));
                entity.setUab(obj.getBigDecimal("20"));
                entity.setUca(obj.getBigDecimal("21"));
                entity.setUbc(obj.getBigDecimal("22"));
                entity.setPv(obj.getBigDecimal("23"));
                entity.setDp(obj.getBigDecimal("50"));
                tOnlineDataPowerList.add(entity);
                //powerBiz.addTOnlineDataPower(entity);
                //logger.info("插入电力实时表" + AESCrptography.objectToString(entity));
                TOnlineDataQty dataQty = new TOnlineDataQty();
                dataQty.setTerminalId(obj.getString("terminalId"));
                dataQty.setGetTime(obj.getBigInteger("getTime"));
                dataQty.setF(obj.getBigDecimal("24"));
                dataQty.setInbalance(obj.getBigDecimal("25"));
                dataQty.setUnbalance(obj.getBigDecimal("26"));
                dataQty.setUaw(obj.getBigDecimal("27"));
                dataQty.setUbw(obj.getBigDecimal("28"));
                dataQty.setUcw(obj.getBigDecimal("29"));
                dataQty.setUabw(obj.getBigDecimal("30"));
                dataQty.setUbcw(obj.getBigDecimal("31"));
                dataQty.setUcaw(obj.getBigDecimal("32"));
                dataQty.setFw(obj.getBigDecimal("33"));
                dataQty.setT(obj.getBigDecimal("34"));
                dataQty.setT2(obj.getBigDecimal("35"));
                dataQty.setT3(obj.getBigDecimal("36"));
                dataQty.setT4(obj.getBigDecimal("37"));
                dataQty.setT5(obj.getBigDecimal("38"));
                dataQty.setT6(obj.getBigDecimal("39"));
                dataQty.setT7(obj.getBigDecimal("40"));
                dataQty.setT8(obj.getBigDecimal("41"));
                tOnlineDataQtyList.add(dataQty);
                //qtyBiz.addTOnlineDataQty(dataQty);
                //logger.info("插入电能质量实时表" + AESCrptography.objectToString(dataQty));
                TOnlineDataEnergy energy = new TOnlineDataEnergy();
                energy.setTerminalId(obj.getString("terminalId"));
                energy.setGetTime(obj.getBigInteger("getTime"));
                energy.setTpe(obj.getBigDecimal("42"));
                energy.setTqe(obj.getBigDecimal("43"));
                energy.setFpe(obj.getBigDecimal("44"));
                energy.setFqe(obj.getBigDecimal("45"));
                energy.setTps(obj.getBigDecimal("46"));
                energy.setTqs(obj.getBigDecimal("47"));
                energy.setFps(obj.getBigDecimal("48"));
                energy.setFqs(obj.getBigDecimal("49"));
                tOnlineDataEnergyList.add(energy);
                //energyBiz.addTOnlineDataEnergy(energy);
                // logger.info("插入电量实时表" + AESCrptography.objectToString(energy));
                n++;
            }else{
                logger.info("not have companyId:"+companyId +" terminalId:"+obj.get("terminalId").toString());
            }
        }
        Map<BigInteger,BigInteger> timeMap = new HashMap<>();
        for (TOnlineDataPower datapower:tOnlineDataPowerList) {
            timeMap.put(datapower.getGetTime(),datapower.getGetTime());
        }
        Map<BigInteger,List<TOnlineDataPower>>  mapPowerList= new HashMap<>();
        Map<BigInteger,List<TOnlineDataQty>>  mapQtyList = new HashMap<>();
        Map<BigInteger,List<TOnlineDataEnergy>>  energyList = new HashMap<>();
        for(BigInteger key:timeMap.keySet()){
            List<TOnlineDataPower>  powerList = new ArrayList<>();
            List<TOnlineDataQty> qtyList = new ArrayList<>();
            List<TOnlineDataEnergy> dataEnergyList = new ArrayList<>();
            for (TOnlineDataPower power:tOnlineDataPowerList ) {
                if(key.equals(power.getGetTime())){
                    powerList.add(power);
                }
            }
            for (TOnlineDataQty power:tOnlineDataQtyList ) {
                if(key.equals(power.getGetTime())){
                    qtyList.add(power);
                }
            }
            for (TOnlineDataEnergy power:tOnlineDataEnergyList ) {
                if(key.equals(power.getGetTime())){
                    dataEnergyList.add(power);
                }
            }
            mapPowerList.put(key,powerList);
            mapQtyList.put(key,qtyList);
            energyList.put(key,dataEnergyList);
        }
        for(BigInteger key:mapPowerList.keySet()){
            powerBiz.addTOnlineDataPowerBatch(mapPowerList.get(key));
        }
        for(BigInteger key:mapQtyList.keySet()){
            qtyBiz.addTOnlineDataQtyBatch(mapQtyList.get(key));
        }
        for(BigInteger key:energyList.keySet()){
            energyBiz.addTOnlineDataEnergyBatch(energyList.get(key));
        }
//        powerBiz.addTOnlineDataPowerBatch(tOnlineDataPowerList);
//        qtyBiz.addTOnlineDataQtyBatch(tOnlineDataQtyList);
//        energyBiz.addTOnlineDataEnergyBatch(tOnlineDataEnergyList);
//        for (TOnlineDataPower power:tOnlineDataPowerList ) {
//            logger.info("插入电力实时表" + AESCrptography.objectToString(power));
//        }
//        for (TOnlineDataQty power:tOnlineDataQtyList ) {
//            logger.info("插入电能质量实时表" + AESCrptography.objectToString(power));
//        }
//        for (TOnlineDataEnergy power:tOnlineDataEnergyList ) {
//             logger.info("插入电量实时表" + AESCrptography.objectToString(power));
//        }
        resultMap.put("total", num);
        resultMap.put("fail", num - n);
        resultMap.put("success", n);
        if(n>0){
            for(int i=0;i<terminalId.size();i++){
                updateGetTime(companyId,terminalId.get(i),Long.parseLong(lastgetTime.get(i)));
            }
        }
        return resultMap;
    }

}

