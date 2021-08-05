package com.zhao.hint;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class MyHint implements HintShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> targetNames, HintShardingValue<Long> hintShardingValue) {
        Collection<String> results = new ArrayList<>();
        for (String each : targetNames) {
            for (Long value : hintShardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    results.add(each);
                }
            }
        }
        return results;
    }
}
