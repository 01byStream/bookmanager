package com.book.service;

import com.book.entity.AuthUser;
import com.book.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: 用户验证服务
 * @date 2022/8/29 20:24
 */
@Service
public class AuthUserService implements UserDetailsService {
    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = mapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户:" + username + " not found");
        }
        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
