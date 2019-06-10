package com.miao.boot.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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
public class User implements Serializable {

    @Id
    private String id;

    private String username;

    private String password;

    private String realName;

    private String email;

    private Integer status;
}
