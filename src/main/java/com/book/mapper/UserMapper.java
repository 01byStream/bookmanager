package com.book.mapper;

import com.book.entity.AuthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @version 1.0
 * @description: 用户表数据库操作映射方法
 * @date 2022/8/29 20:19
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    public AuthUser getUserByUsername(String username);
}
