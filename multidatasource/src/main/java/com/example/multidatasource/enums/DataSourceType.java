package com.example.multidatasource.enums;

import lombok.Getter;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 15:52
 */
@Getter
public enum DataSourceType {
    /**
     * 配置数据源
     */
    MASTER("testMASTER", "测试主数据库"),
    SLAVE("testSlave", "测试从数据库"),
    SLAVE_TWO("testSlaveTwo", "测试从数据库");

    private String name;
    private String description;

    DataSourceType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
