package com.tg.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author liyu
 * 数据库切片算法：按照getTime年份分库
 *
 */
public final class SingleKeyModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<BigInteger> 
{
    
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<BigInteger> shardingValue) 
    {
    	String database_index = String.valueOf(shardingValue.getValue()).substring(0,4);
        for (String each : availableTargetNames) 
        {
            if (each.endsWith(database_index)) 
            {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<BigInteger> shardingValue) 
    {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<BigInteger> values = shardingValue.getValues();
        for (BigInteger value : values) 
        {
        	String database_index = String.valueOf(value).substring(0,4);
            for (String each : availableTargetNames) 
            {
                if (each.endsWith(database_index)) 
                {
                    result.add(each);
                }
            }
        }
        return result;
    }
    
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<BigInteger> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<BigInteger> range = shardingValue.getValueRange();
        Integer lowerEndPoint = Integer.parseInt(String.valueOf(range.lowerEndpoint()).substring(0,4));
        Integer upperEndpoint = Integer.parseInt(String.valueOf(range.upperEndpoint()).substring(0,4));
        for (Integer value = lowerEndPoint; value <= upperEndpoint; value++)
        {
        	String database_index = String.valueOf(value);
            for (String each : availableTargetNames) {
                if (each.endsWith(database_index))
                {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
