package com.wordscool.service;


import com.wordscool.entity.TUser;

public interface TUserService{

    TUser getUserByUsernameOrEmail(String username);

}
