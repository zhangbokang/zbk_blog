package com.zbkblog.service.impl;

import com.zbkblog.dao.BlogDao;
import com.zbkblog.medo.BlogDoc;
import com.zbkblog.medo.Page;
import com.zbkblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/7.
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public BlogDoc save(BlogDoc blogDoc) {
        Integer integer=null;
        if (blogDoc.getBlogId()!=null){
            integer = blogDao.updata(blogDoc);
        }else {
            blogDoc.setBlogId(100L);
            integer = blogDao.save(blogDoc);
        }
        if (integer==1){
            return blogDoc;
        }
        return null;
    }

    @Override
    public List<BlogDoc> findAllBlogDoc(BlogDoc blogDoc, Page page) {
        return null;
    }

    @Override
    public List<BlogDoc> findBlogDocByKeyword(String keyword, Page page) {
        return null;
    }
}
