package com.wordscool.service.impl;

import com.wordscool.mapper.TPermissionMapper;
import com.wordscool.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPermissionServiceImpl implements TPermissionService {

    private final TPermissionMapper tPermissionMapper;
    @Autowired
    public TPermissionServiceImpl(TPermissionMapper tPermissionMapper) {
        this.tPermissionMapper=tPermissionMapper;
    }

    @Override
    public List<String> getPermissionCodeByUserId(Integer id) {
        return tPermissionMapper.getPermissionCodeByUserId(id);
    }
}
