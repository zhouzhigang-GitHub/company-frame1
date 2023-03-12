package com.zhou.shiro;

import com.zhou.Constants.Constant;
import com.zhou.exception.BusinessException;
import com.zhou.exception.code.BaseResponseCode;
import com.zhou.service.RedisService;
import com.zhou.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.shiro
 * @ClassName: CustomHashedCredentialsMatcher
 * @Author: 周志刚
 * @CreateDate: 2021/5/11 10:23
 * @Version: 0.0.1
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        String accessToken = (String) customUsernamePasswordToken.getCredentials();
        String userId = JwtTokenUtil.getUserId(accessToken);
        log.info("doCredentialsMatch...userId = ",userId);
        //判断用户是否被删除
        if (redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        //是否锁定
        if (redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        //校验Token
        if (!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        return true;
    }
}
