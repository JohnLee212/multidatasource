package com.example.multidatasource.mapper;

import com.example.multidatasource.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 16:02
 */
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    public User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Select("select * from user")
    List<User> selectAll();

}
