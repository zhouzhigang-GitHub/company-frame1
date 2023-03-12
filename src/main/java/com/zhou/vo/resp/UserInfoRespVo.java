package com.zhou.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.vo.resp
 * @ClassName: UserInfoRespVo
 * @Author: 周志刚
 * @CreateDate: 2021/5/25 15:29
 * @Version: 0.0.1
 */
@Data
public class UserInfoRespVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "所属部门名称")
    private String deptName;
}
