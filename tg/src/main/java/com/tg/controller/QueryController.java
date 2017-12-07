package com.tg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tg.biz.*;
import com.tg.model.*;
import com.tg.util.Constants;
import com.tg.util.StreamConvert;
import com.tg.util.UtilTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/31.
 */
@RestController
@RequestMapping("/api")
public class QueryController {

    @Autowired
    private TPoweruserOrgBiz biz;
    @Autowired
    private TOnlineDataPowerBiz powerBiz;
    @Autowired
    private TMonitorBiz monitorBiz;
    @Autowired
    private TOnlineDataEnergyBiz dataEnergyBiz;
    @Autowired
    private TKeyDistributeBiz tKeyDistributeBiz;

    private final static Logger logger = LoggerFactory.getLogger(QueryController.class);

    @RequestMapping(value = "/query/list_all_ents", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAllEnts(String appid, String nonce, String timestamp, String secretkey) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int sum = 0;
        try {
            TKeyDistribute tKeyDistribute = tKeyDistributeBiz.queryTKeyDistribute(appid);
            String sppkey = tKeyDistribute.getAppKey();
            Boolean bool = UtilTools.checkSecretKey(sppkey, nonce, timestamp, secretkey);

            if (bool) {
                List<TPoweruserOrg> list = biz.getAll();
                resultMap.put("data", list);
                sum = list.size();
            } else {
                resultMap.put("ret", 1);
                resultMap.put("code", Constants.DECRYPT_FAILURE_CODE);
                resultMap.put("sum", 0);
                return resultMap;
            }

        } catch (Throwable e) {
            resultMap.put("ret", 1);
            resultMap.put("code", Constants.DECRYPT_FAILURE_CODE);
            resultMap.put("sum", 0);
            logger.error(e.getMessage(), e);
            return resultMap;
        }
        resultMap.put("ret", 0);
        resultMap.put("code", 200);
        resultMap.put("sum", sum);
        return resultMap;

    }

    @RequestMapping(value = "/query/power_load", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> powerLoad(String appid, String nonce, String timestamp, String secretkey, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int ret = 0;
        //企业个数
        int cnt = 0;
        try {
            TKeyDistribute tKeyDistribute = tKeyDistributeBiz.queryTKeyDistribute(appid);
            String sppkey = tKeyDistribute.getAppKey();
            Boolean flag = UtilTools.checkSecretKey(sppkey, nonce, timestamp, secretkey);
            if(!flag){
                resultMap.put("ret", 1);
                resultMap.put("code", Constants.DECRYPT_FAILURE_CODE);
                resultMap.put("cnt", 0);
                return resultMap;
            }
            ServletInputStream inputStream = request.getInputStream();
            String content = StreamConvert.getInputStreamString(inputStream);
            JSONObject obj = JSONObject.parseObject(content);
            JSONArray arry = obj.getJSONArray("id_list");
            String num = obj.getString("cnt");
            if(Integer.parseInt(num)!=arry.size()){
                resultMap.put("ret", 1);
                resultMap.put("code", Constants.INVALID_CNT);
                resultMap.put("cnt", 0);
                return resultMap;
            }
            boolean temp =false;
            String tempSize = "";
            for(int i=0;i<arry.size();i++){
                tempSize = arry.getString(i);
                for(int j=i+1;j<arry.size();j++){
                    if(tempSize.equals(arry.getString(j))){
                        temp = true;
                    }
                }
            }
            if(temp){
                resultMap.put("ret", 1);
                resultMap.put("code", Constants.INVALID_LIST);
                resultMap.put("cnt", 0);
                return resultMap;
            }
            JSONArray listmap = new JSONArray();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < arry.size(); i++) {
                String str = arry.get(i).toString();
                BigInteger m = new BigInteger(str);
                map = queryConsumptionPower(m);
                if (map != null && !map.isEmpty()) {
                    listmap.add(map);
                    cnt++;
                }

            }
            resultMap.put("data", listmap);
            resultMap.put("ret", 0);
            resultMap.put("cnt", cnt);
        } catch (Throwable e) {
            resultMap.put("ret", 1);
            resultMap.put("code", Constants.DECRYPT_FAILURE_CODE);
            resultMap.put("cnt", 0);
            logger.error(e.getMessage(), e);
        }
        return resultMap;
    }

    /**
     * 前推时间
     *
     * @return
     */
    public static BigInteger formatGetTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar beforeTime = Calendar.getInstance();
        Calendar entTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);// 10分钟之前的时间
        entTime.add(Calendar.MINUTE, -15);
        Date beforeD = beforeTime.getTime();
        Date endD = entTime.getTime();
        String time = df.format(beforeD);
        String endTime = df.format(endD);
        String last = time.substring(time.length() - 1);
        String lastchar = "";
        if (Integer.parseInt(last) >= 5) {
            lastchar = "5";
        } else {
            lastchar = "0";
        }
        String getTime = time.substring(0, time.length() - 1) + lastchar;
        long m = Long.parseLong(endTime);
        if (m % 5 == 0) {
            getTime = endTime;
        }
        getTime = getTime + "00";
        return new BigInteger(getTime);
    }

    /**
     * 企业用电量查询
     *
     * @param compandId
     */
    public Map<String, Object> queryConsumptionPower(BigInteger compandId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //企业名称name
        TPoweruserOrg tPoweruserOrg = biz.getTPoweruserOrg(compandId);
        if (tPoweruserOrg != null) {
            String name = tPoweruserOrg.getName();
            resultMap.put("name", name);
            // 瞬时负荷
            TMonitor tMonitor = new TMonitor();
            BigInteger parentId = new BigInteger("-1");
            tMonitor.setParentId(parentId);
            tMonitor.setCompanyId(compandId);
            List<TMonitor> list = monitorBiz.queryTMonitor(tMonitor);
            BigInteger getTime = formatGetTime();
            BigDecimal lastp = new BigDecimal("0");
            List<String> terminalIdlist = new ArrayList<>();
            for (TMonitor tmonitor : list) {
                terminalIdlist.add(tmonitor.getTerminalId());
            }
            List<TOnlineDataPower> powerList = powerBiz.queryDataPower(terminalIdlist, getTime);
            //有记录的terminalId
            List<String> recordTerminalId = new ArrayList<>();
            //处理结果
            for (TOnlineDataPower dataPower : powerList) {
                //找到tmonitor
                recordTerminalId.add(dataPower.getTerminalId());
                TMonitor monitor = new TMonitor();
                for (TMonitor tmonitor : list) {
                    if (tmonitor.getTerminalId().equals(dataPower.getTerminalId())) {
                        monitor = tMonitor;
                        break;
                    }
                }
                BigDecimal p = dataPower.getP();
                int useCt = monitor.getUseCt();
                int userPt = monitor.getUsePt();
                if (useCt == 1) {
                    double d = monitor.getCt();
                    BigDecimal ct = new BigDecimal(Double.toString(d));
                    p = p.multiply(ct);
                }
                if (userPt == 1) {
                    double d = monitor.getPt();
                    BigDecimal ct = new BigDecimal(Double.toString(d));
                    p = p.multiply(ct);
                }
                lastp =lastp.add(p);
            }
            resultMap.put("pt", getTime);
            if (powerList.size() == 0) {
                resultMap.put("pload", "无数据");
            } else {
                resultMap.put("pload", lastp);
            }

            // List<String> allTerminalId = new ArrayList<>();

            List<TMonitor> restTmonitor = new ArrayList<>();
            BigInteger st = new BigInteger("0");
            for (TMonitor tmonitor : list) {
                boolean flag = false;
                for (String recordId : terminalIdlist) {
                    if (recordId.equals(tmonitor.getTerminalId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    restTmonitor.add(tmonitor);
                }
            }

            //allTerminalId.remove(recordTerminalId);
            BigDecimal lastTps = new BigDecimal("0");
            st = getTime;
            boolean falgTps = false;
            for (String terId : recordTerminalId) {
                TOnlineDataEnergy tOnlineDataEnergy = dataEnergyBiz.queryDataEnergyByGetTime(terId, getTime);
                if (tOnlineDataEnergy != null) {
                    lastTps =lastTps.add(tOnlineDataEnergy.getTps());
                    st = getTime;
                    falgTps = true;
                }
            }
            for (TMonitor restmonitor : restTmonitor) {
                BigInteger onlineTime = restmonitor.getOnlineTime();
                if (onlineTime.compareTo(getTime) > 0) {
                    TOnlineDataEnergy tOnlineDataEnergy = dataEnergyBiz.queryDataEnergy(restmonitor.getTerminalId(), getTime, onlineTime);
                    if (tOnlineDataEnergy != null) {
                        lastTps =lastTps.add(tOnlineDataEnergy.getTps());
                        st = onlineTime;
                        falgTps = true;
                    }
                } else {
                    TOnlineDataEnergy tOnlineDataEnergy = dataEnergyBiz.queryDataEnergyByGetTime(restmonitor.getTerminalId(), onlineTime);
                    if (tOnlineDataEnergy != null) {
                        lastTps =lastTps.add(tOnlineDataEnergy.getTps());
                        st = getTime;
                        falgTps = true;
                    }
                }
            }
            //selec 累计电量
            if (falgTps == false) {
                resultMap.put("selec", "无数据");
            } else {
                resultMap.put("selec", lastTps);
            }
            //st电量采集时间
            resultMap.put("st", st);
        }
        return resultMap;
    }


}
