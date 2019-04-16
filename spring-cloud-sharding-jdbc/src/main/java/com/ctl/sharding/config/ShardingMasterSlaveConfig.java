package com.ctl.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMasterSlaveConfig {

    private Map<String, DruidDataSource> dataSources = new HashMap<>();

    private MasterSlaveRuleConfiguration masterSlaveRule;

    public Map<String, DruidDataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DruidDataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public MasterSlaveRuleConfiguration getMasterSlaveRule() {
        return masterSlaveRule;
    }

    public void setMasterSlaveRule(MasterSlaveRuleConfiguration masterSlaveRule) {
        this.masterSlaveRule = masterSlaveRule;
    }
}
