package com.miao.boot.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @title: Permission
 * @description:
 * @author: dengmiao
 * @create: 2019-06-11 14:32
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(collection = "permission")
public class Permission {

    @Id
    private String id;

    private String pid;

    /**
     * 名称
     */
    private String name;

    /**
     * layUi图标
     */
    private String icon;

    /**
     * 资源类型（0 菜单 1 按钮）
     */
    private String type;

    /**
     * 跳转路由
     */
    private String routing;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 新页面打开
     */
    private Integer isBlank;

    @Transient
    private List<Permission> children;
}
