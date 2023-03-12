package com.zhou.vo.resp;

import lombok.Data;

import java.util.List;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.vo.resp
 * @ClassName: PermissionRespNodeVo
 * @Author: 周志刚
 * @CreateDate: 2021/5/25 15:36
 * @Version: 0.0.1
 */
@Data
public class PermissionRespNodeVo {


    private String id;

    private String url;

    private String title;

    private List<?> children;
}
