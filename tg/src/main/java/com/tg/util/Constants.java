package com.tg.util;

/**
 * Created by zyz on 2017/7/24.
 */
public class Constants {
    //解密错误
    public static final int DECRYPT_FAILURE_CODE = 300;
    //数据库错误
    public static final int DATAERROR_CODE = 301;
    //无效数据
    public static final int INVALID_DATA = 302;
    //宁夏查询个数不一致
    public static final int INVALID_CNT = 303;
    //list 里有重复的
    public static final int INVALID_LIST = 304;
    public static final String HTYPE_IA = "IA";
    public static final String HTYPE_IB = "IB";
    public static final String HTYPE_IC = "IC";
    public static final String HTYPE_UA = "UA";
    public static final String HTYPE_UB = "UB";
    public static final String HTYPE_UC = "UC";

    //getTimemod
    public static final long GETTIME_MOD_YEAR = 10000000000L;
    public static final long GETTIME_MOD_MONTH_AND_YEAR = 100000000L;


    //batch table index
    public static final int ONLINE_DATA_ENERGY_INDEX = 0;
    public static final int ONLINE_DATA_HARMONICI_INDEX = 1;
    public static final int ONLINE_DATA_HARMONICU_INDEX = 2;
    public static final int ONLINE_DATA_POWER_INDEX = 3;
    public static final int ONLINE_DATA_QTY_INDEX = 4;
}
