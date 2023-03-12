package com.zhou.utils;

import com.alibaba.druid.util.StringUtils;
import com.zhou.Constants.Constant;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.utils
 * @ClassName: JwtTokenUtil
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 17:42
 * @Version: 0.0.1
 */
@Slf4j
public class JwtTokenUtil {

    private static String secretKey;
    private static Duration accessTokenExpireTime;
    private static Duration refreshTokenExpireTime;
    private static Duration refreshTokenExpireAppTime;
    private static String issuer;

    public static void setJwtProperties(TokenSetting tokenSetting){
        secretKey = tokenSetting.getSecretKey();
        accessTokenExpireTime = tokenSetting.getAccessTokenExpireTime();
        refreshTokenExpireAppTime = tokenSetting.getRefreshTokenExpireAppTime();
        refreshTokenExpireTime = tokenSetting.getRefreshTokenExpireTime();
        issuer = tokenSetting.getIssuer();
    }

    /**
     * 生成 access_token
     * @param subject
     * @param claims
     * @return       java.lang.String
     * @throws
     */
    public static String getAccessToken(String subject, Map<String,Object> claims){
        return generateToken(issuer,subject,claims,accessTokenExpireTime.toMillis(),secretKey);
    }
    /**
     * 签发token
     * @param issuer 签发人
     * @param subject 代表这个JWT的主体，即它的所有人 一般是用户id
     * @param claims 存储在JWT里面的信息 一般放些用户的权限/角色信息
     * @param ttlMillis 有效时间(毫秒)
     * @return       java.lang.String
     * @throws
     */
    public static String generateToken(String issuer, String subject,Map<String, Object> claims, long
            ttlMillis,String secret) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] signingKey = DatatypeConverter.parseBase64Binary(secret);
        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ","JWT");
        if(null!=claims){
            builder.setClaims(claims);
        }
        if (!StringUtils.isEmpty(subject)) {
            builder.setSubject(subject);
        }
        if (!StringUtils.isEmpty(issuer)) {
            builder.setIssuer(issuer);
        }
        builder.setIssuedAt(now);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        builder.signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    // 上面我们已经有生成 access_token 的方法，下面加入生成 refresh_token 的方法(PC 端过期时间短一些)
    /**
     * 生产 PC refresh_token
     * @param subject
     * @param claims
     * @return       java.lang.String
     * @throws
     */
    public static String getRefreshToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,refreshTokenExpireTime.toMillis(),secretKey);
    }
    //上面我们已经有生成 access_token 的方法，下面加入生成 refresh_token 的方法(APP 端过期时间长一些)
    /**
     * 生产 App端 refresh_token
     * @param subject
     * @param claims
     * @return       java.lang.String
     * @throws
     */
    public static String getRefreshAppToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,refreshTokenExpireAppTime.toMillis(),secretKey);
    }

    /**
     * 从令牌中获取数据声明
     * @param token
     * @return       io.jsonwebtoken.Claims
     * @throws
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            if(e instanceof ClaimJwtException){
                claims=((ClaimJwtException) e).getClaims();
            }
        }
        return claims;
    }


    /**
     * 获取用户id
    12.7 创建公有的静态常量类
    12.8 通过token获取用户名静态方法
    12.9 token 是否过期方法
     * @Version:     0.0.1
     * @param token
     * @return       java.lang.String
     * @throws
     */
    public static String getUserId(String token){
        String userId=null;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return userId;
    }

    /**
     * 获取用户名
     * @param token
     * @return       java.lang.String
     * @throws
     */
    public static String getUserName(String token){
        String username=null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims .get(Constant.JWT_USER_NAME);
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return username;
    }

    /**
     * 验证token 是否过期(true:已过期 false:未过期)
     * @param token
    • * @param secretKey
     * @return       java.lang.Boolean
     * @throws
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.error("error={}",e);
            return true;
        }
    }

    /**
     * 校验令牌(true：验证通过 false：验证失败)
     * @param token
     * @return       java.lang.Boolean
     * @throws
     */
    public static Boolean validateToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return (null!=claimsFromToken && !isTokenExpired(token));
    }
    /**
     * 获取token的剩余过期时间
     * @param token
    • * @param secretKey
     * @return       long
     * @throws
     */
    public static long getRemainingTime(String token){
        long result=0;
        try {
            long nowMillis = System.currentTimeMillis();
            result= getClaimsFromToken(token).getExpiration().getTime()-nowMillis;
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return result;
    }
}

