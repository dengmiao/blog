package com.miao.boot.blog.security;

import com.miao.boot.blog.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @title: SecurityUserDetails
 * @description:
 * @author: dengmiao
 * @create: 2019-06-06 16:09
 **/
@Slf4j
public class SecurityUserDetails extends User implements UserDetails {

    public SecurityUserDetails(User user) {
        if(user!=null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setStatus(user.getStatus());
            this.setRealName(user.getRealName());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 添加请求权限

        // 添加角色
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
