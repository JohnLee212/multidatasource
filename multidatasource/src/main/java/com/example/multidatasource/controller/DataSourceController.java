package com.example.multidatasource.controller;

import com.example.multidatasource.domain.User;
import com.example.multidatasource.domain.UserDomain;
import com.example.multidatasource.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 16:04
 */
@RestController
@Slf4j
public class DataSourceController {

    @Resource
    private UserService userService;

    @RequestMapping("/insert1")
    public Integer insert01(String name, Integer age) {
        return userService.insertUser1(name, age);
    }

    @RequestMapping("/insert2")
    public Integer insert02(String name, Integer age) {
        return userService.insertUser2(name, age);
    }

    @RequestMapping("/select")
    public void selectAll(){
        List<User> users = userService.selectAll();
        log.info("select success! user size  {}",users.size());
    }

    @RequestMapping("/select2")
    public void selectAll2(){
        List<User> users = userService.selectAll2();
        log.info("select success! user size {}",users.size());
    }

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
}
