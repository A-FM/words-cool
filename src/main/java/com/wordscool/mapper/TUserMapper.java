package com.wordscool.mapper;


import com.wordscool.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserMapper {
    TUser getUserByUsernameOrEmail(String username);
}