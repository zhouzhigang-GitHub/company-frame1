package com.zhou.mapper;

import com.zhou.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 北城
 */
@Component
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUsername(String username);

    List<SysUser> selectAll();
}