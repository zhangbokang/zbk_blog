package com.zbkblog.service.impl;

import com.zbkblog.dao.BlogUserDao;
import com.zbkblog.entity.BlogUser;
import com.zbkblog.service.BlogUserService;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("blogUser")
    public BlogUser authBlogUser(String username,String password) {
        return blogUserDao.authBlogUser(username,password);
    }
}
