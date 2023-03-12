package com.zhou.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.vo.resp
 * @ClassName: PageRespVo
 * @Author: 周志刚
 * @CreateDate: 2021/5/8 9:11
 * @Version: 0.0.1
 */
@Data
public class PageRespVo<T> {

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long totalRows;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPages;
    /**
     * 当前第几页
     */
    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;
    /**
     * 当前页记录数
     */
    @ApiModelProperty(value = "当前页记录数")
    private Integer curPageSize;
    /**
     * 数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> list;
}
