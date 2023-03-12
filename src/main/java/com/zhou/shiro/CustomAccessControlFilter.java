package com.zhou.shiro;

import com.alibaba.fastjson.JSON;
import com.zhou.Constants.Constant;
import com.zhou.exception.BusinessException;
import com.zhou.exception.code.BaseResponseCode;
import com.zhou.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.shiro
 * @ClassName: CustomAccessControlFilter
 * @Author: 周志刚
 * @CreateDate: 2021/5/8 9:42
 * @Version: 0.0.1
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {

    /**
     * 是否允许访问
     * true：允许，交下一个Filter处理
     * false：回往下执行onAccessDenied
    • * @param servletResponse
    • * @param o
     * @return       boolean
     * @throws
     */

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * 表示访问拒绝时是否自己处理，
     * 如果返回true表示自己不处理且继续拦截器链执行，
     * 返回false表示自己已经处理了（比如直接响应回前端）。
    • * @param servletResponse
     * @return       boolean
     * @throws
     */

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        log.info("request 接口地址：{}",request.getRequestURI());
        log.info("request 接口的请求方式{}",request.getMethod());
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        try {
            if(StringUtils.isEmpty(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
            }
            CustomUsernamePasswordToken customUsernamePasswordToken=new CustomUsernamePasswordToken(accessToken);

            getSubject(servletRequest,servletResponse).login(customUsernamePasswordToken);
        }catch (BusinessException e){
            customResponse(servletResponse,e.getMessageCode(),e.getDetailMessage());
            return false;
        } catch (AuthenticationException e) {
            if(e.getCause() instanceof BusinessException){
                BusinessException exception= (BusinessException) e.getCause();
                customResponse(servletResponse,exception.getMessageCode(),exception.getDetailMessage());
            }else {
                customResponse(servletResponse, BaseResponseCode.TOKEN_ERROR.getCode(), BaseResponseCode.TOKEN_ERROR.getMsg());
            }
            return false;
        }catch (Exception e){
            customResponse(servletResponse, BaseResponseCode.SYSTEM_ERROR.getCode(), BaseResponseCode.SYSTEM_ERROR.getMsg());
            return false;
        }
        return true;
    }

    /**
     * 自定义前端响应方法
     * @param response
     * @param code
     * @param msg
     */
    private void customResponse(ServletResponse response,int code ,String msg){
        try {
            DataResult result = DataResult.getResult(code, msg);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            String str = JSON.toJSONString(result);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
        } catch (IOException e) {
            log.error("customResponse...error:{}",e);
        }
    }
}
