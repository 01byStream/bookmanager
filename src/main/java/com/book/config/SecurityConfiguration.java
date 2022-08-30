package com.book.config;

import com.book.repo.RedisTokenRepository;
import com.book.service.AuthUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: SpringSecurity配置类
 * @date 2022/8/29 20:02
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //使用Redis存储库存储token
    @Resource
    RedisTokenRepository repository;

    @Resource
    AuthUserService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder()); // BCryptPasswordEncoder加密方式
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**", "/register").permitAll() //静态文件
                .anyRequest().hasAnyRole("user", "admin")
                .and() //自定义登录界面
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and() //记住我
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(repository)
                .tokenValiditySeconds(60 * 60 * 24 * 30)
                .and() //退出登录
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout");
    }
}
