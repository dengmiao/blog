package com.miao.boot.blog.security;

import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.domain.Role;
import com.miao.boot.blog.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

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
            BeanUtils.copyProperties(user, this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorityList = new ArrayList<>();
        // 用户持有的角色集合
        Set<Role> roleSet = this.getRoleList();

        // 获取角色对应权限
        List<Permission> permissionList = this.getPermissionList();
        if(roleSet != null && roleSet.size() != 0) {
            roleSet.forEach(item -> {
                if(item != null) {

                }
            });
        }

        // 添加请求权限
        permissionList.forEach(item -> {
            if(item != null) {
                authorityList.add(new SimpleGrantedAuthority(item.getName()));
            }
        });

        return authorityList;
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
