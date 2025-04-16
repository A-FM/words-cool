package com.wordscool.service.impl;

import com.wordscool.entity.TUser;
import com.wordscool.mapper.TUserMapper;
import com.wordscool.service.TUserService;
import org.springframework.stereotype.Service;

@Service
public class TUserServiceImpl implements TUserService {

    private final TUserMapper tUserMapper;
    public TUserServiceImpl(TUserMapper tUserMapper) {
        this.tUserMapper=tUserMapper;
    }

    @Override
    public TUser getUserByUsernameOrEmail(String username) {
        return tUserMapper.getUserByUsernameOrEmail(username);
    }
}
