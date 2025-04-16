package com.wordscool.service.impl;

import com.wordscool.mapper.TUserRoleMapper;
import com.wordscool.service.TUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TUserRoleServiceImpl implements TUserRoleService {

    private final TUserRoleMapper tUserRoleMapper;
    @Autowired
    public TUserRoleServiceImpl(TUserRoleMapper tUserRoleMapper) {
        this.tUserRoleMapper=tUserRoleMapper;
    }
}
