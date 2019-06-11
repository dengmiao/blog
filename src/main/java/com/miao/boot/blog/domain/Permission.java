package com.miao.boot.blog.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @title: Permission
 * @description:
 * @author: dengmiao
 * @create: 2019-06-11 14:32
 **/
@Data
@Document(collection = "permission")
public class Permission {

    private String id;

    private String name;
}
