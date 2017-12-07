package com.tg.util;

/**
 * @author liyu
 * spring动态数据源线程变量
 */
public class DataSourceContextHolder 
{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	
	public static final String anhui_dsm = "anhui_dsm";
	
	public static final String sharding = "sharding";

	public static final String dsm_sysbase="dsm_sysbase";
    /** 
     * @Description: 设置数据源类型 
     * @param dataSourceType  数据库类型 
     * @return void 
     * @throws 
     */  
    public static void setDataSourceType(String dataSourceType) {  
        contextHolder.set(dataSourceType);  
    }  
      
    /** 
     * @Description: 获取数据源类型 
     * @param  
     * @return String 
     * @throws 
     */  
    public static String getDataSourceType() {  
        return contextHolder.get();  
    }  
      
    /** 
     * @Description: 清除数据源类型 
     * @param  
     * @return void 
     * @throws 
     */  
    public static void clearDataSourceType() {  
        contextHolder.remove();  
    } 
}
