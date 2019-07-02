package com.miao.boot.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @title: Role
 * @description:
 * @author: dengmiao
 * @create: 2019-06-11 14:18
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(collection = "role")
public class Role {

    private String id;

    private String code;

    private String name;

    /**
     * 权限id集合
     */
    private List<String> permissions;
}
