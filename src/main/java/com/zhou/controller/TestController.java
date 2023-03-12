package com.zhou.controller;

import com.zhou.entity.SysUser;
import com.zhou.exception.BusinessException;
import com.zhou.exception.code.BaseResponseCode;
import com.zhou.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.controller
 * @ClassName: TestController
 * @Author: 周志刚
 * @CreateDate: 2021/4/28 16:31
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/swagger")
@Api(tags = "测试接口模块")
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "测试接口")
    public String testSwagger(){
        return "测试成功";
    }

    @GetMapping("/test/data")
    @ApiOperation(value = "统一的响应式测试接口")
    public DataResult<SysUser> test(){
//        DataResult result = DataResult.success("统一的响应式测试接口");
        SysUser sysUser = new SysUser();
        sysUser.setUsername("张三");
        SysUser sysUser1 = new SysUser();
        sysUser1.setUsername("张si");
        List<SysUser> list = new ArrayList<>();
        list.add(sysUser1);
        list.add(sysUser);
        DataResult result = DataResult.success(list);
        int i = 1/0;
        return result;
    }

    @GetMapping("/type")
    public DataResult testBusinessException(@RequestParam String tye){

        if (!tye.equals("1")){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        DataResult result = DataResult.success(tye);
        return result;
    }

}
