package com.yyds.intelligentcustomerservicesystem.mapper;

import com.yyds.intelligentcustomerservicesystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {

    @Select("select * from user where email = #{email}")
    User selectByEmail(String email);

    void insert(User user);

    @Select("select * from user where userId = #{userId}")
    User selectById(Long userId);
}
