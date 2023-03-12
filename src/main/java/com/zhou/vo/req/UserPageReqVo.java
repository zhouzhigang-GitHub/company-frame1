package com.zhou.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.vo.req
 * @ClassName: UserPageReqVo
 * @Author: 周志刚
 * @CreateDate: 2021/5/8 8:42
 * @Version: 0.0.1
 */

@Data
public class UserPageReqVo {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "当前页的总数")
    private Integer pageSize = 10;
}
