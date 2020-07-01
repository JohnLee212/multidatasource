package com.example.multidatasource.dao;

import com.example.multidatasource.domain.UserDomain;

import java.util.List;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 17:14
 */
public interface User2Dao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
