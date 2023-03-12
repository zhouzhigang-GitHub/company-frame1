package com.zhou.exception.handler;

import com.zhou.exception.BusinessException;
import com.zhou.exception.code.BaseResponseCode;
import com.zhou.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.exception.handler
 * @ClassName: RestExceptionHandler
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 14:24
 * @Version: 0.0.1
 */

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public DataResult handlerException(Exception e){
        log.error("handlerException...{}"+e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public DataResult handlerBusinessException(BusinessException e){
        log.error("BusinessException...{}"+e);
        return DataResult.getResult(e.getMessageCode(),e.getDetailMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public DataResult unauthorizedException(UnauthorizedException e){
        log.error("UnauthorizedException:{}",e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }

}
