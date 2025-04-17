package com.wordscool.service;

import com.wordscool.entity.TPermission;

import java.util.List;

public interface TPermissionService {
    List<String> getPermissionCodeByUserId(Integer id);
    List<TPermission> selectAll();

}
