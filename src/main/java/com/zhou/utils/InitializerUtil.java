package com.zhou.utils;

import org.springframework.stereotype.Component;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.utils
 * @ClassName: InitializerUtil
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 20:50
 * @Version: 0.0.1
 */
@Component
public class InitializerUtil {
    public InitializerUtil(TokenSetting tokenSetting){
        JwtTokenUtil.setJwtProperties(tokenSetting);
    }
}
