package com.example.multidatasource.domain;

import lombok.Data;

/**
 * @author John Lee
 * @Version 1.0
 * @Description
 * @date 2020/6/15 17:15
 */
@Data
public class UserDomain {

    private Integer userId;

    private String userName;

    private String password;

    private String phone;
}
