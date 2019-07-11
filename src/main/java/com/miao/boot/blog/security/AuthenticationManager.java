/*
package com.miao.boot.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.miao.boot.blog.constant.CommonConstant.Security;

*/
/**
 * @title: AuthenticationManager
 * @description:
 * @author: dengmiao
 * @create: 2019-07-11 15:17
 **//*

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            // 解析token
            Claims claims= Jwts.parser()
                    .setSigningKey(Security.JWT_SIGN_KEY)
                    .parseClaimsJws(authToken.replace(Security.TOKEN_SPLIT, ""))
                    .getBody();
            //todo 此处应该列出token中携带的角色表。
            List<String> roles=new ArrayList();
            roles.add("user");
            Authentication authentication1 = new UsernamePasswordAuthenticationToken(
                    claims.getId(),
                    null,
                    roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList())
            );
            return Mono.just(authentication1);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
*/
