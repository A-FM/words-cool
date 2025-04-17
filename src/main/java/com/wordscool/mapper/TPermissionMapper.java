package com.wordscool.mapper;

import com.wordscool.entity.TPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TPermissionMapper {
    List<String> getPermissionCodeByUserId(@Param("id")Integer id);
    List<TPermission> selectAll();
}