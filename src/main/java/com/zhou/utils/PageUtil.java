package com.zhou.utils;

import com.github.pagehelper.Page;
import com.zhou.vo.resp.PageRespVo;

import java.util.List;

/**
 * @Product: IntelliJ IDEA
 * TODO:类文件描述
 * @PackageName: com.zhou.utils
 * @ClassName: PageUtil
 * @Author: 周志刚
 * @CreateDate: 2021/5/8 9:15
 * @Version: 0.0.1
 */
public class PageUtil {

    private PageUtil(){
    }
    public static <T> PageRespVo getPageRespVo(List<T> list){
        PageRespVo<T> pageRespVo = new PageRespVo<>();
        if (list instanceof Page){
            Page page = (Page) list;
            pageRespVo.setTotalRows(page.getTotal());
            pageRespVo.setList(page.getResult());
            pageRespVo.setTotalPages(page.getPages());
            pageRespVo.setCurPageSize(page.getPageSize());
            pageRespVo.setPageNum(page.getPageNum());
            pageRespVo.setPageSize(page.size());
        }
        return pageRespVo;
    }
}
