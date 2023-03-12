package com.zhou.service;

import com.github.pagehelper.PageInfo;
import com.zhou.entity.SysUser;
import com.zhou.vo.req.LoginReqVo;
import com.zhou.vo.req.UserPageReqVo;
import com.zhou.vo.resp.LoginRespVo;
import com.zhou.vo.resp.PageRespVo;
import org.springframework.stereotype.Service;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.service
 * @ClassName: UserService
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 21:42
 * @Version: 0.0.1
 */

public interface UserService {
    LoginRespVo login(LoginReqVo vo);

    PageRespVo<SysUser> pageInfo(UserPageReqVo vo);
}
