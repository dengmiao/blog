package com.miao.boot.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @title: User
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 10:35
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String realName;

    private String email;

    private Integer status;

    /**
     * 角色id集合
     */
    private List<String> roles;

    /**
     * 用户角色集合
     */
    @Transient
    private Set<Role> roleList;

    /**
     * 用户权限集合
     */
    @Transient
    private List<Permission> permissionList;
}
