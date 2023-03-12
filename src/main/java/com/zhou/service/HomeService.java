package com.zhou.service;

import com.zhou.vo.resp.HomeRespVo;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.service
 * @ClassName: HomeService
 * @Author: 周志刚
 * @CreateDate: 2021/5/25 15:41
 * @Version: 0.0.1
 */
public interface HomeService {
    HomeRespVo getHome(String id);
}
