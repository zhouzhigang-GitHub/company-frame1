package com.zhou.shiro;

import com.zhou.Constants.Constant;
import com.zhou.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.shiro
 * @ClassName: CustomRealm
 * @Author: 周志刚
 * @CreateDate: 2021/5/11 10:00
 * @Version: 0.0.1
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String accessToken = (String) principals.getPrimaryPrincipal();
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY) != null){
            info.addStringPermissions((Collection<String>) claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY));
        }
        if (claimsFromToken.get(Constant.ROLES_INFOS_KEY) !=null){
            info.addRoles((Collection<String>) claimsFromToken.get(Constant.ROLES_INFOS_KEY));
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customUsernamePasswordToken.getPrincipal(),customUsernamePasswordToken.getCredentials(),this.getName());
        return info;
    }
}
