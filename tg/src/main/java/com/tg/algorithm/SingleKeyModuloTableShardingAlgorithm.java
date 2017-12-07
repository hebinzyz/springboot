/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.tg.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashSet;

public final class SingleKeyModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<BigInteger> 
{
    
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<BigInteger> shardingValue) 
    {
    	String tableName = String.valueOf(shardingValue.getValue()).substring(4,6);
        for (String each : availableTargetNames) 
        {
            if (each.endsWith(tableName)) 
            {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<BigInteger> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<BigInteger> values = shardingValue.getValues();
        for (BigInteger value : values) 
        {
        	String tableName = String.valueOf(value).substring(4,6);
            for (String each : availableTargetNames) 
            {
                if (each.endsWith(tableName)) 
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
        Integer lowerEndpoint = Integer.parseInt(String.valueOf(range.lowerEndpoint()).substring(4,6));
        Integer upperEndpoint = Integer.parseInt(String.valueOf(range.upperEndpoint()).substring(4,6));
        for (int i = lowerEndpoint; i <= upperEndpoint; i++)
        {
        	String tableName = "";
        	if (i < 10)
        	{
        		tableName = "0";
        	}
        	tableName += String.valueOf(i);
            for (String each : availableTargetNames) 
            {
                if (each.endsWith(tableName)) 
                {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
