package com.zhou.mapper;

import com.zhou.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component

public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
}