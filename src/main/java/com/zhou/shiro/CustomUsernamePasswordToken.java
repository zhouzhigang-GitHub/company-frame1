package com.zhou.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.shiro
 * @ClassName: CustomUsernamePasswordToken
 * @Author: 周志刚
 * @CreateDate: 2021/5/8 9:35
 * @Version: 0.0.1
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    private String jwtToken;

    public CustomUsernamePasswordToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal(){
        return jwtToken;
    }


    @Override
    public Object getCredentials(){
        return jwtToken;
    }
}
