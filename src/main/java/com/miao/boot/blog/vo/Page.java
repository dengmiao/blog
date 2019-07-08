package com.miao.boot.blog.vo;

import lombok.Data;

/**
 * @title: Page
 * @description: 分页请求参数
 * @author: dengmiao
 * @create: 2019-07-08 09:52
 **/
@Data
public class Page {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String lastId;
}
