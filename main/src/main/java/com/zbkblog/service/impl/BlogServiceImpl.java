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
        if (blogDoc.getBlogId()!=null){
            blogDoc = blogDao.updata(blogDoc);
        }else {
            blogDoc.setBlogId(100L);
            blogDoc = blogDao.save(blogDoc);
        }
        return blogDoc;
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
