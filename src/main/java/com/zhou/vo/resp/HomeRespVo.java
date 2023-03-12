package com.zhou.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.vo.resp
 * @ClassName: HomeRespVo
 * @Author: 周志刚
 * @CreateDate: 2021/5/25 15:32
 * @Version: 0.0.1
 */
@Data
public class HomeRespVo {

    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVo userInfoRespVo;

    @ApiModelProperty(value = "首页菜单导航数据")
    private List<PermissionRespNodeVo> menus;
}
