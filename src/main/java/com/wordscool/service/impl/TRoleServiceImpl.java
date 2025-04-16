package com.wordscool.service.impl;

import com.wordscool.mapper.TRoleMapper;
import com.wordscool.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TRoleServiceImpl implements TRoleService {

    private final TRoleMapper tRoleMapper;
    @Autowired
    public TRoleServiceImpl(TRoleMapper tRoleMapper) {
        this.tRoleMapper=tRoleMapper;
    }
}
