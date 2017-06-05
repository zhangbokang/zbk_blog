package com.zbkblog.service.impl;

import com.zbkblog.dao.BlogUserDao;
import com.zbkblog.entity.BlogUser;
import com.zbkblog.service.BlogUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangbokang on 2017/6/3.
 */
@Service("blogUserService")
public class BlogUserServiceImpl implements BlogUserService {
    @Resource
    private BlogUserDao blogUserDao;

    @Override
    public BlogUser authBlogUser(BlogUser blogUser) {
        return blogUserDao.authBlogUser(blogUser);
    }
}
