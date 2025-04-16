package com.wordscool.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TPermissionMapper {
    List<String> getPermissionCodeByUserId(@Param("id")Integer id);
}