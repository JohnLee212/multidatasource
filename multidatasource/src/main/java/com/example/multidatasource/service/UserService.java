package com.example.multidatasource.service;

import com.example.multidatasource.annotation.DataSource;
import com.example.multidatasource.dao.User2Dao;
import com.example.multidatasource.domain.User;
import com.example.multidatasource.domain.UserDomain;
import com.example.multidatasource.enums.DataSourceType;
import com.example.multidatasource.mapper.RoleMapper;
import com.example.multidatasource.mapper.UserMapper;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 16:03
 */
@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper mapper;

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Resource
    User2Dao userDao;

    @DataSource(DataSourceType.MASTER)
    @Transactional(rollbackFor = Exception.class)
    public int insertUser1(String name, Integer age){
        int result = mapper.insert(name, age);
        int i = 1 / age;
        int admin = roleMapper.insert(name, "admin");
        return result + admin;
    }

    @DataSource(DataSourceType.SLAVE)
    @Transactional(rollbackFor = Exception.class)
    public int insertUser2(String name, Integer age){
        int result = mapper.insert(name, age);
        int i = 1 / age;
        int user = roleMapper.insert(name, "user");
        return result;
    }

    @DataSource(DataSourceType.MASTER)
    @Transactional(rollbackFor = Exception.class)
    public int addUser(UserDomain user) {
        return userDao.insert(user);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @DataSource(DataSourceType.MASTER)
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        return new PageInfo<>(userDomains);
    }

    @DataSource(DataSourceType.MASTER)
    public List<User> selectAll() {
        List<User> userList = mapper.selectAll();
        return userList;
    }

    @DataSource(DataSourceType.SLAVE_TWO)
    public List<User> selectAll2() {
        List<User> userList = mapper.selectAll();
        return userList;
    }
}
