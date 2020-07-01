package com.example.multidatasource.annotation;

import com.example.multidatasource.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 15:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSource {
    DataSourceType value() default DataSourceType.MASTER;
}
