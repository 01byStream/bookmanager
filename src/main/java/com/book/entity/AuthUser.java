package com.book.entity;

import lombok.Data;

/**
 * @author Administrator
 * @version 1.0
 * @description: 用户表对应的实体类
 * @date 2022/8/29 20:16
 */
@Data
public class AuthUser {
    private Integer id;
    private String username;
    private String password;
    private String role;
}
