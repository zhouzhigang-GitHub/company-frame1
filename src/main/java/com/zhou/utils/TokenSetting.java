package com.zhou.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.utils
 * @ClassName: TokenSetting
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 17:45
 * @Version: 0.0.1
 */

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class TokenSetting {

    private String secretKey;
    private Duration accessTokenExpireTime;
    private Duration refreshTokenExpireTime;
    private Duration refreshTokenExpireAppTime;
    private String issuer;


}
