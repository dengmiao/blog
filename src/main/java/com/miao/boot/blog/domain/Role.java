package com.miao.boot.blog.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @title: Role
 * @description:
 * @author: dengmiao
 * @create: 2019-06-11 14:18
 **/
@Data
@Document(collection = "role")
public class Role {

    private String id;

    private String code;

    private String name;
}
