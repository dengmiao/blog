package com.miao.boot.blog.security;

import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.domain.Role;
import com.miao.boot.blog.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

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

        List<Permission> root = permissionList.stream()
                //.sorted(Comparator.comparing(Permission::getSort))
                .filter(item -> "0".equals(item.getPid())).collect(Collectors.toList());
        System.out.println(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(recursiveRec(root))));
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

    private List<Permission> recursiveRec(List<Permission> list) {
        return list.stream().map(item -> {
            List<Permission> children = this.getPermissionList().stream()
                    //.sorted(Comparator.comparing(Permission::getSort))
                    .filter(i -> i.getPid().equals(item.getId())).collect(Collectors.toList());
            if(children != null && children.size() > 0) {
                recursiveRec(children);
            }
            item.setChildren(children);
            return item;
        }).collect(Collectors.toList());
    }
}
