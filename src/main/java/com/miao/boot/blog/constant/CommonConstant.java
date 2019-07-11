package com.miao.boot.blog.constant;

/**
 * @title: CommonConstant
 * @description: 常量
 * @author: dengmiao
 * @create: 2019-07-09 12:50
 **/
public interface CommonConstant {

    interface LoginError {
        String SYSTEM_ERROR = "系统错误";
        String USER_NOT_EXISTS = "用户不存在";
        String USERNAME_PASSWORD_ERROR = "密码错误";
        String USER_LOCKED = "账号被锁定";
    }

    interface Security {
        /**
         * token分割
         */
        String TOKEN_SPLIT = "Bearer ";

        /**
         * JWT签名加密key
         */
        String JWT_SIGN_KEY = "xxBlog";

        /**
         * token参数头
         */
        String HEADER = "XBlog-Access-Token";

        /**
         * 权限参数头
         */
        String AUTHORITIES = "authorities";
    }
}
