package com.zhou.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.controller
 * @ClassName: IndexController
 * @Author: 周志刚
 * @CreateDate: 2021/5/12 14:47
 * @Version: 0.0.1
 */

@Controller
@RequestMapping("/index")
@Api(tags = "视图",description = "跳转视图的控制器")
public class IndexController {

    @GetMapping("/404")
    @ApiOperation(value = "跳转404页面")
    public String error404(){
        return "error/404";
    }

    @GetMapping("/login")
    @ApiOperation(value = "跳转登录界面")
    public String logout(){
        return "login";
    }
    @GetMapping("/home")
    @ApiOperation(value = "跳转首页")
    public String home(){
        return "home";
    }

    @GetMapping("/main")
    @ApiOperation(value = "跳转主页")
    public String main(){
        return "main";
    }

}
