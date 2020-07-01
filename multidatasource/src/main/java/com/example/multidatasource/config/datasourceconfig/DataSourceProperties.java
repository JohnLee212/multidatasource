package com.example.multidatasource.config.datasourceconfig;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 15:37
 */
@Component
@Data
@ConfigurationProperties("mysql.datasource")
public class DataSourceProperties {

    private DruidDataSource master;

    private DruidDataSource slave;

    private DruidDataSource slaveTwo;
}
