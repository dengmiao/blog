package com.miao.boot.blog.base;

import cn.hutool.core.bean.BeanUtil;
import com.miao.boot.blog.vo.Page;
import com.miao.boot.blog.vo.PageResult;
import com.miao.boot.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Map;

/**
 * @title: BaseHandler
 * @description: 公有的handler
 * Create Retrieve Update Delete
 * 返回Publisher<Result<E>>
 * @author: dengmiao
 * @create: 2019-07-05 15:16
 **/
@Slf4j
public abstract class BaseHandler<E, ID extends Serializable> {

    private final MongoPageHelper mongoPageHelper;

    public BaseHandler(final MongoPageHelper mongoPageHelper) {
        this.mongoPageHelper = mongoPageHelper;
    }

    /**
     * 获取server实现
     * @return
     */
    public abstract BaseReactiveService getReactiveService();

    /**
     * 获取泛型的类模板对象
     * @return
     */
    private Class<E> getClazz() {
        Class<E> clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    /**
     * 拼装动态条件
     * @param clazz
     * @param bean
     * @return
     */
    private Criteria AssembleCriteria(Class clazz, final E bean) {
        Criteria criteria = Criteria.where("_id").exists(true);
        Arrays.stream(clazz.getDeclaredFields()).forEach(f -> {
            try {
                // setAccessible(true)破坏封装,PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz)对于链式风格（@Accessors(chain = true)）会出错;
                // 采用spring->BeanUtils.getPropertyDescriptor获取getter
                PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(clazz, f.getName());
                Object val = pd.getReadMethod().invoke(bean);
                if(val != null) {
                    // 需支持模糊匹配 范围匹配等 alike | lt | gt ..
                    criteria.and(f.getName()).is(val);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return criteria;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    public Mono<ServerResponse> page(ServerRequest request) {
        Class<E> clazz = getClazz();
        Map map = request.queryParams().toSingleValueMap();
        E bean = BeanUtil.mapToBean(map, clazz, true);
        Page page = BeanUtil.mapToBean(map, Page.class, true);
        final Query query = BeanUtil.isEmpty(bean) ? new Query() : new Query(AssembleCriteria(clazz, bean));

        PageResult<E> pageResult = mongoPageHelper.pageQuery(query, clazz, e -> e, page.getPageSize(),
                page.getPageNum(), page.getLastId());

        Mono resultMono = Mono.just(Result.ok(pageResult));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(resultMono, clazz);
    }

    /**
     * 新增
     * @param request
     * @return
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<E> e = request.bodyToMono(getClazz());
        // 数据校验？
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getReactiveService().create(e)
                        .flatMap(et -> Mono.just(Result.ok(et))), getClazz())
                ;
    }

    /**
     * 按id查询
     * @param request
     * @return
     */
    public Mono<ServerResponse> retrieve(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getReactiveService().retrieve(id)
                        .flatMap(et -> Mono.just(Result.ok(et)))
                        .switchIfEmpty(Mono.just(Result.notFound("对象不存在"))), getClazz())
                ;
    }

    /**
     * 修改
     * @param request
     * @return
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<E> e = request.bodyToMono(getClazz());
        // 数据校验？
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getReactiveService().update(id, e)
                        .flatMap(et -> Mono.just(Result.ok(et)))
                        // 不存在
                        , getClazz())
                ;
    }

    /**
     * 删除
     * @param request
     * @return
     */
    public Mono<ServerResponse> delete(ServerRequest request) {
        String ids = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(getReactiveService().delete(ids).then(Mono.just(Result.ok())), Object.class)
                ;
    }
}
