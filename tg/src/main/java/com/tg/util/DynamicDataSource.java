package com.tg.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author liyu
 *	spring动态数据源
 */
public class DynamicDataSource  extends AbstractRoutingDataSource 
{  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceContextHolder.getDataSourceType();  
    }  
}  