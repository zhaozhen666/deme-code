package com.zhao.id;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
@Slf4j
public class MyShardingId implements ShardingKeyGenerator {
    private SnowflakeShardingKeyGenerator shardingKeyGenerator = new SnowflakeShardingKeyGenerator();
    @Override
    public Comparable<?> generateKey() {
        log.info("执行了自定义的id生成器");

        return shardingKeyGenerator.generateKey();
    }

    @Override
    public String getType() {
        return "zhao-sharding-key";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
