package com.zbkblog.dao;

import com.zbkblog.entity.BlogUser;

/**
 * Created by zhangbokang on 2017/6/3.
 */
public interface BlogUserDao {

    /**
     * 校验用户
     *  校验正确就返回填充后的BlogUser
     *  校验失败返回null
     * @param username
     * @param password
     * @return
     */
    BlogUser authBlogUser(String username,String password);
}
