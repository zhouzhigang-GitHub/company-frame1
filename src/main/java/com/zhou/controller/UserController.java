package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.entity.SysUser;
import com.zhou.service.UserService;
import com.zhou.utils.DataResult;
import com.zhou.vo.req.LoginReqVo;
import com.zhou.vo.req.UserPageReqVo;
import com.zhou.vo.resp.LoginRespVo;
import com.zhou.vo.resp.PageRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.controller
 * @ClassName: UserController
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 21:39
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户模块相关接口")
public class UserController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "用户登录接口")
    @PostMapping("/user/login")
    public DataResult<LoginRespVo> login(@RequestBody @Valid LoginReqVo vo){
        DataResult result=DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页查询用户接口")
    @RequiresPermissions("sys:user:list")
    public DataResult<PageRespVo<SysUser>> pageInfoDataResult(@RequestBody UserPageReqVo vo){
        DataResult result = DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

}
