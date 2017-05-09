package com.zbkblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbkblog.dao.BlogDao;
import com.zbkblog.medo.BlogDoc;
import com.zbkblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            blogDoc.setBlogId(new Date().getTime());
            integer = blogDao.save(blogDoc);
        }
        if (integer==1){
            return blogDoc;
        }
        return null;
    }

    @Override
    public BlogDoc findBlogDocByBlogId(Long blogId) {
        return blogDao.findBlogDocByBlogId(blogId);
    }

    @Override
    public List<BlogDoc> findBlogDocByKeyword(String keyword, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<BlogDoc> findAllBlogDocPaging(BlogDoc blogDoc, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        //应该也能用
        //PageHelper.offsetPage(pageNo,pageSize);//未测试
        PageHelper.startPage(pageNo, pageSize);
        List<BlogDoc> list = blogDao.findAllBlogDocPaging(blogDoc);//.selectUserByUserName(userName);
        //用PageInfo对结果进行包装
        PageInfo<BlogDoc> page = new PageInfo(list);
        //测试PageInfo全部属性
        System.out.println(page.getPageNum());
        System.out.println(page.getPageSize());
        System.out.println(page.getStartRow());
        System.out.println(page.getEndRow());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getFirstPage());
        System.out.println(page.getLastPage());
        System.out.println(page.isHasPreviousPage());
        System.out.println(page.isHasNextPage());
        return page;
    }
}
