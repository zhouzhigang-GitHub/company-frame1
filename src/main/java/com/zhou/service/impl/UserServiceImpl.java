package com.zhou.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhou.Constants.Constant;
import com.zhou.entity.SysUser;
import com.zhou.exception.BusinessException;
import com.zhou.exception.code.BaseResponseCode;
import com.zhou.mapper.SysUserMapper;
import com.zhou.service.UserService;
import com.zhou.utils.JwtTokenUtil;
import com.zhou.utils.PageUtil;
import com.zhou.utils.PasswordUtils;
import com.zhou.vo.req.LoginReqVo;
import com.zhou.vo.req.UserPageReqVo;
import com.zhou.vo.resp.LoginRespVo;
import com.zhou.vo.resp.PageRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.service.impl
 * @ClassName: UserServiceImpl
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 21:43
 * @Version: 0.0.1
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public LoginRespVo login(LoginReqVo vo) {
        SysUser sysUser = sysUserMapper.selectByUsername(vo.getUsername());
        if (sysUser == null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if (sysUser.getStatus() ==2){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        if (!PasswordUtils.matches(sysUser.getSalt(),vo.getPassword(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        claims.put(Constant.ROLES_INFOS_KEY,getRoleByUserId(sysUser.getId()));
        claims.put(Constant.PERMISSIONS_INFOS_KEY,getPermissionByUserId(sysUser.getId()));
        String accessToken = JwtTokenUtil.getAccessToken(sysUser.getId(),claims);
        log.info("accessToken={}",accessToken);
        Map<String, Object> refreshTokenClaims = new HashMap<>();
        refreshTokenClaims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        String refreshToken = null;
        if ("1".equals(vo.getType())){
            refreshToken = JwtTokenUtil.getRefreshToken(sysUser.getId(),refreshTokenClaims);
        }else {
            refreshToken = JwtTokenUtil.getRefreshAppToken(sysUser.getId(),refreshTokenClaims);
        }
        log.info("refreshToken={}",refreshToken);
        LoginRespVo loginRespVo = new LoginRespVo();
        loginRespVo.setUserId(sysUser.getId());
        loginRespVo.setRefreshToken(refreshToken);
        loginRespVo.setAccessToken(accessToken);
        return loginRespVo;
    }

    @Override
    public PageRespVo<SysUser> pageInfo(UserPageReqVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.selectAll();
        return PageUtil.getPageRespVo(sysUsers);
    }

    private List<String> getRoleByUserId(String userId){
        List<String> list = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            list.add("admin");
        }else {
            list.add("dev");
        }
        return list;
    }

    private List<String> getPermissionByUserId(String userId){
        List<String> list = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            list.add("sys:user:add");
            list.add("sys:user:update");
            list.add("sys:user:delete");
            list.add("sys:user:list");
        }else {
//            list.add("sys:user:list");
            list.add("sys:user:add");
        }
        return list;
    }
}
