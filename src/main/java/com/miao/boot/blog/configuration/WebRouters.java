package com.miao.boot.blog.configuration;

import cn.hutool.json.JSONUtil;
import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.handler.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WebRouters {

    private static final String ADMIN_ROOT_PATH = "";

    private static final String HOME_ROOT_PATH = "";

    @Bean
    public RouterFunction<?> iconResources() {
        return RouterFunctions
                .resources("/favicon.**", new ClassPathResource("static/favicon.ico"));
    }

    /**
     * 视图路由
     * @param view
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> viewRoutes(final ViewHandler view) {
        return RouterFunctions
                // 后台登录
                .route(RequestPredicates.GET("/login"),
                        /*view::login*/
                        req -> ServerResponse
                                .ok()
                                .contentType(MediaType.TEXT_HTML)
                                .render("login",
                                        req.exchange().getAttributes())
                )
                // 登出
                .andRoute(RequestPredicates.GET("/logout"),
                        req -> ServerResponse.ok().render("login")
                )
                // 后台欢迎页
                .andRoute(RequestPredicates.GET("/welcome"),
                        req -> {
                            Mono<User> userMono = req.principal().ofType(Authentication.class).map(authentication -> {
                                User user = User.class.cast(authentication.getPrincipal());
                                return user;
                            });
                            req.exchange().getAttributes().put("user", userMono);
                            return ServerResponse.ok().render("admin/welcome", req.exchange().getAttributes());
                        }
                )
                // 会员管理/统计页面
                .andRoute(
                        RequestPredicates.GET("/admin/memory/welcome1.html"),
                        req -> ServerResponse.ok().render("admin/memory/welcome1.html")
                )
                // 会员管理/静态表格
                .andRoute(
                        RequestPredicates.GET("/admin/memory/member-list.html"),
                        req -> ServerResponse.ok().render("admin/memory/member-list.html")
                )
                // 会员管理/动态表格
                .andRoute(
                        RequestPredicates.GET("/admin/memory/member-list1.html"),
                        req -> ServerResponse.ok().render("admin/memory/member-list1.html")
                )
                // 会员管理/会员删除
                .andRoute(
                        RequestPredicates.GET("/admin/memory/member-del.html"),
                        req -> ServerResponse.ok().render("admin/memory/member-del.html")
                )
                // 订单管理/订单列表
                .andRoute(
                        RequestPredicates.GET("/admin/order/order-list.html"),
                        req -> ServerResponse.ok().render("admin/order/order-list.html")
                )
                // 订单管理/订单列表1
                .andRoute(
                        RequestPredicates.GET("/admin/order/order-list1.html"),
                        req -> ServerResponse.ok().render("admin/order/order-list1.html")
                )
                // 分类管理/多级分类
                .andRoute(
                        RequestPredicates.GET("/admin/cate/cate.html"),
                        req -> ServerResponse.ok().render("admin/cate/cate.html")
                )
                // 城市联动/三级地区联动
                .andRoute(
                        RequestPredicates.GET("/admin/city/city.html"),
                        req -> ServerResponse.ok().render("admin/city/city.html")
                )
                // 管理员管理/管理员列表
                .andRoute(
                        RequestPredicates.GET("/admin/admin/admin-list.html"),
                        req -> ServerResponse.ok().render("admin/admin/admin-list.html")
                )
                // 管理员管理/角色管理
                .andRoute(
                        RequestPredicates.GET("/admin/admin/admin-role.html"),
                        req -> ServerResponse.ok().render("admin/admin/admin-role.html")
                )
                // 管理员管理/权限分类
                .andRoute(
                        RequestPredicates.GET("/admin/admin/admin-cate.html"),
                        req -> ServerResponse.ok().render("admin/admin/admin-cate.html")
                )
                // 管理员管理/权限管理
                .andRoute(
                        RequestPredicates.GET("/admin/admin/admin-rule.html"),
                        req -> ServerResponse.ok().render("admin/admin/admin-rule.html")
                )
                // 系统统计/折线图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts1.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts1.html")
                )
                // 系统统计/折线图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts2.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts2.html")
                )
                // 系统统计/地图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts3.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts3.html")
                )
                // 系统统计/饼图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts4.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts4.html")
                )
                // 系统统计/雷达图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts5.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts5.html")
                )
                // 系统统计/K线图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts6.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts6.html")
                )
                // 系统统计/热力图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts7.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts7.html")
                )
                // 系统统计/仪表图
                .andRoute(
                        RequestPredicates.GET("/admin/chart/echarts8.html"),
                        req -> ServerResponse.ok().render("admin/chart/echarts8.html")
                )
                // 图标字体/图标对应字体
                .andRoute(
                        RequestPredicates.GET("/admin/icon/unicode.html"),
                        req -> ServerResponse.ok().render("admin/icon/unicode.html")
                )
                // 其他页面/登录页面
                .andRoute(
                        RequestPredicates.GET("/admin/other/login.html"),
                        req -> ServerResponse.ok().render("admin/other/login.html")
                )
                // 其他页面/错误页面
                .andRoute(
                        RequestPredicates.GET("/admin/other/error.html"),
                        req -> ServerResponse.ok().render("admin/other/error.html")
                )
                // 其他页面/实例页面
                .andRoute(
                        RequestPredicates.GET("/admin/other/demo.html"),
                        req -> ServerResponse.ok().render("admin/other/demo.html")
                )
                // 其他页面/更新日志
                .andRoute(
                        RequestPredicates.GET("/admin/other/log.html"),
                        req -> ServerResponse.ok().render("admin/other/log.html")
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
                // 首页
                .andRoute(RequestPredicates.GET("/"),
                        req -> req.principal()
                                .ofType(Authentication.class)
                                .flatMap(auth -> {
                                    User user = User.class.cast(auth.getPrincipal());
                                    List<Permission> permissionList = user.getPermissionList().stream().filter(p -> p != null)
                                            .sorted(Comparator.comparing(p -> p.getSort())).collect(Collectors.toList());
                                    List<Permission> resource = permissionList.stream()
                                            .collect(Collectors.toList());
                                    List<Permission> root = permissionList.stream()
                                            .filter(item -> "0".equals(item.getPid())).collect(Collectors.toList());
                                    List<Permission> permissions = recursiveRec(root, resource);
                                    log.info("菜单: 资源总数->{}, 根->{}", resource.size(), permissions.size());
                                    String json = JSONUtil.formatJsonStr(JSONUtil.toJsonStr(permissions));
                                    System.out.println(/*"菜单:\n" +json*/);
                                    Map<String, Object> attr = req.exchange().getAttributes();
                                    attr.putAll(Collections.singletonMap("user", user));
                                    attr.put("menu", permissions);
                                    return ServerResponse.ok().render("admin/index",
                                            req.exchange().getAttributes());
                                })
                )
        ;
    }

    /**
     * 方法路由
     * @param commonHandler
     * @param userHandler
     * @param permissionHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> webFluxRoutesRegister(final CommonHandler commonHandler
                                                                , final UserHandler userHandler
                                                                , final PermissionHandler permissionHandler
                                                                , final TestHandler testHandler
                                                                ) {
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
                .andRoute(RequestPredicates.GET(ADMIN_ROOT_PATH + "/user/list/"), userHandler::list)
                .andRoute(RequestPredicates.POST(ADMIN_ROOT_PATH + "/user/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::create)
                .andRoute(RequestPredicates.DELETE(ADMIN_ROOT_PATH + "/user/{id}"), userHandler::delete)
                .andRoute(RequestPredicates.PUT(ADMIN_ROOT_PATH + "/user/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::update)
                .andRoute(RequestPredicates.GET(ADMIN_ROOT_PATH + "/user/{id}"), userHandler::retrieve)
                // permission相关
                .andRoute(RequestPredicates.POST(ADMIN_ROOT_PATH + "/permission/"), permissionHandler::create)
                // test
                .andRoute(RequestPredicates.GET(ADMIN_ROOT_PATH + "/test/page/"), testHandler::page)
                .andRoute(RequestPredicates.GET(ADMIN_ROOT_PATH + "/test/{id}"), testHandler::retrieve)
                ;
    }

    /**
     * 递归处理菜单资源树结构
     * @param list
     * @param resource
     * @return
     */
    private List<Permission> recursiveRec(List<Permission> list, List<Permission> resource) {
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
