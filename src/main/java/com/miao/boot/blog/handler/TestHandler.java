package com.miao.boot.blog.handler;

import com.miao.boot.blog.base.BaseHandler;
import com.miao.boot.blog.base.BaseReactiveService;
import com.miao.boot.blog.base.MongoPageHelper;
import com.miao.boot.blog.domain.User;
import com.miao.boot.blog.service.UserReactiveService;
import org.springframework.stereotype.Component;

/**
 * @title: TestHandler
 * @description:
 * @author: dengmiao
 * @create: 2019-07-05 15:14
 **/
@Component
public class TestHandler extends BaseHandler<User, String> {

    private final UserReactiveService userReactiveService;

    public TestHandler(final MongoPageHelper mongoPageHelper,
                       final UserReactiveService userReactiveService
    ) {
        super(mongoPageHelper);
        this.userReactiveService = userReactiveService;
    }

    @Override
    public BaseReactiveService getReactiveService() {
        return this.userReactiveService;
    }
}
