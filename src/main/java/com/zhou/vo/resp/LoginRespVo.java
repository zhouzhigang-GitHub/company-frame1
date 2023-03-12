package com.zhou.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.vo.resp
 * @ClassName: LoginRespVo
 * @Author: 周志刚
 * @CreateDate: 2021/5/6 21:15
 * @Version: 0.0.1
 */
@Data
public class LoginRespVo {
    @ApiModelProperty(value = "业务token")
    private String accessToken;
    @ApiModelProperty(value = "业务token刷新凭证")
    private String refreshToken;
    @ApiModelProperty(value = "用户id")
    private String userId;
}
