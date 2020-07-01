package com.example.multidatasource.config.datasourceconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 15:43
 */
@Configuration
public class DataSourceConfig {

    @Resource
    private DataSourceProperties dataSourceProperties;

    @Bean("testMaster")
    public DruidDataSource testMaster(){
        return dataSourceProperties.getMaster();
    }

    @Bean("testSlave")
    public DruidDataSource testSlave(){
        return dataSourceProperties.getSlave();
    }

    @Bean("testSlaveTwo")
    public DruidDataSource testSlaveTwo(){
        return dataSourceProperties.getSlaveTwo();
    }

    /**
     * 配置动态数据源，通过aop切换数据源
     * @return
     */
    @Primary
    @Bean(name="dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(testMaster());
        Map<Object, Object> dbMap = new HashMap<>(16);
        dbMap.put("testMaster", testMaster());
        dbMap.put("testSlave", testSlave());
        dbMap.put("testSlaveTwo",testSlaveTwo());
        dynamicDataSource.setTargetDataSources(dbMap);
        dynamicDataSource.setDefaultTargetDataSource(testMaster());
        return dynamicDataSource;
    }

    /**
     * 配置Transaction，统一管理
     * @return
     */
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
