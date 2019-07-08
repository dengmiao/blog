package com.miao.boot.blog.handler;

import com.miao.boot.blog.base.BaseReactiveHandler;
import com.miao.boot.blog.base.BaseReactiveService;
import com.miao.boot.blog.base.MongoPageHelper;
import com.miao.boot.blog.domain.Permission;
import com.miao.boot.blog.service.PermissionReactiveService;
import org.springframework.stereotype.Component;

/**
 * @title: TestHandler
 * @description:
 * @author: dengmiao
 * @create: 2019-07-05 15:14
 **/
@Component
public class TestHandler implements BaseReactiveHandler<Permission, String> {

    private final MongoPageHelper mongoPageHelper;

    private final PermissionReactiveService permissionReactiveService;

    public TestHandler(
            final MongoPageHelper mongoPageHelper
            , final PermissionReactiveService permissionReactiveService
    ) {
        this.mongoPageHelper = mongoPageHelper;
        this.permissionReactiveService = permissionReactiveService;
    }

    @Override
    public MongoPageHelper getMongoPageHelper() {
        return this.mongoPageHelper;
    }

    @Override
    public BaseReactiveService getReactiveService() {
        return this.permissionReactiveService;
    }

}
