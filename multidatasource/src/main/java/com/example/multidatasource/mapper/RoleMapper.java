package com.example.multidatasource.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 16:41
 */
public interface RoleMapper {

    @Insert("INSERT INTO ROLE(NAME, ROLE) VALUES(#{name}, #{role})")
    int insert(@Param("name") String name, @Param("role") String age);
}
