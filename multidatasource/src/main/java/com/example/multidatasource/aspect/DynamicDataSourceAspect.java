package com.example.multidatasource.aspect;

import com.example.multidatasource.annotation.DataSource;
import com.example.multidatasource.config.datasourceconfig.DataSourceContextHolder;
import com.example.multidatasource.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 15:56
 */
@Aspect
@Component
@Order(-10)
@Slf4j
//@Order 值越小，优先级越高
public class DynamicDataSourceAspect {

    @SuppressWarnings("rawtypes")
    @Before("@annotation(com.example.multidatasource.annotation.DataSource)")
    public void before(JoinPoint point) {

        //获取当前访问的类名
        Class<?> className = point.getTarget().getClass();

        //获取当前访问的方法名
        String methodName = point.getSignature().getName();

        //获取方法的参数类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();

        //默认数据源
        String dataSource = DataSourceContextHolder.DEFAULT_DB;

        try {
            //获取访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            //判断是否存在@DataSource注解
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                //获取注解中指定的数据源
                DataSourceType dataSourceType = annotation.value();
                dataSource = dataSourceType.getName();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        //切换到指定的数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(com.example.multidatasource.annotation.DataSource)")
    public void after(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }

}
