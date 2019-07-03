package com.miao.boot.blog.configuration;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.handler.CommonHandler;
import com.miao.boot.blog.handler.PermissionHandler;
import com.miao.boot.blog.handler.UserHandler;
import com.miao.boot.blog.handler.ViewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title: WebRouters
 * @description: 路由配置
 * @author: dengmiao
 * @create: 2019-06-10 13:35
 **/
@Component
public class WebRouters {

    @Bean
    public RouterFunction<?> iconResources() {
        return RouterFunctions
                .resources("/favicon.**", new ClassPathResource("static/favicon.ico"));
    }

    @Bean
    public RouterFunction<ServerResponse> viewRoutes(final ViewHandler view) {
        return RouterFunctions
                .route(RequestPredicates.GET("/login"),
                        /*view::login*/
                        req -> ServerResponse
                                .ok()
                                .contentType(MediaType.TEXT_HTML)
                                .render("login",
                                        req.exchange().getAttributes())
                )
                .andRoute(RequestPredicates.GET("/logout"),
                        req -> ServerResponse.ok().render("index")
                )
                .andRoute(RequestPredicates.GET("/welcome"),
                        req -> ServerResponse.ok().render("welcome")
                )
                /*.filter((req, resHandler) ->
                        req.exchange()
                                .getAttributeOrDefault(
                                        CsrfToken.class.getName(),
                                        Mono.empty().ofType(CsrfToken.class)
                                )
                                .flatMap(csrfToken -> {
                                    req.exchange()
                                            .getAttributes()
                                            .put(csrfToken.getParameterName(), csrfToken);
                                    return resHandler.handle(req);
                                })

                )*/
                .andRoute(RequestPredicates.GET("/"),
                        req -> req.principal()
                                .ofType(Authentication.class)
                                .flatMap(auth -> {
                                    User user = User.class.cast(auth.getPrincipal());
                                    List<Permission> resource = user.getPermissionList().stream()
                                            .filter(p -> p != null)
                                            .sorted(Comparator.comparing(p -> p.getSort())).collect(Collectors.toList());
                                    List<Permission> root = resource.stream()
                                            .filter(item -> "0".equals(item.getPid())).collect(Collectors.toList());
                                    List<Permission> permissions = recursiveRec(root, resource);
                                    String json = JSONUtil.formatJsonStr(JSONUtil.toJsonStr(permissions));
                                    System.out.println("菜单:\n" +json);
                                    Map<String, Object> attr = req.exchange().getAttributes();
                                    attr.putAll(Collections.singletonMap("user", user));
                                    attr.put("menu", permissions);
                                    return ServerResponse.ok().render("index",
                                            req.exchange().getAttributes());
                                })
                )
        ;
    }

    @Bean
    public RouterFunction<ServerResponse> webFluxRoutesRegister(final CommonHandler commonHandler
                                                                , final UserHandler userHandler
                                                                , final PermissionHandler permissionHandler) {
        /*return RouterFunctions.nest(
                // 相当于controller的 路由前缀 @RequestMapping("/user")
                RequestPredicates.path("/user"),
                // 相当于@RequestMapping
                RouterFunctions.route(RequestPredicates.GET("/list"), userHandler::list)
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::create)
                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::delete)
        );*/
        return RouterFunctions.route(RequestPredicates.GET("/logout123"), commonHandler::logout)
                // user相关
                .andRoute(RequestPredicates.GET("/user/list"), userHandler::list)
                .andRoute(RequestPredicates.POST("/user/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::create)
                .andRoute(RequestPredicates.DELETE("/user/{id}"), userHandler::delete)
                .andRoute(RequestPredicates.PUT("/user/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::update)
                .andRoute(RequestPredicates.GET("/user/{id}"), userHandler::retrieve)
                // permission相关
                .andRoute(RequestPredicates.POST("/permission/"), permissionHandler::create)
                ;
    }

    private List<Permission> recursiveRec(List<Permission> list, final List<Permission> resource) {
        return list.stream().map(item -> {
            List<Permission> children = resource.stream()
                    //.sorted(Comparator.comparing(Permission::getSort))
                    .filter(i -> i.getPid().equals(item.getId())).collect(Collectors.toList());
            if(children != null && children.size() > 0) {
                recursiveRec(children, resource);
            }
            item.setChildren(children);
            return item;
        }).collect(Collectors.toList());
    }
}
