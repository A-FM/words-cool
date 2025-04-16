package com.wordscool.service.impl;

import com.wordscool.mapper.TRolePermissionMapper;
import com.wordscool.service.TRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TRolePermissionServiceImpl implements TRolePermissionService {

    private final TRolePermissionMapper tRolePermissionMapper;
    @Autowired
    public TRolePermissionServiceImpl(TRolePermissionMapper tRolePermissionMapper) {
        this.tRolePermissionMapper=tRolePermissionMapper;
    }
}
