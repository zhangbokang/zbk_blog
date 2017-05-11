package com.zbkblog.service;

import com.github.pagehelper.PageInfo;
import com.zbkblog.medo.BlogDoc;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangbokang on 2017/5/7.
 */
public interface BlogService {

    /**
     * 查询所有类
     * @return
     */
    List<String> findClass();

    /**
     * 查询所有标签
     * @return
     */
    List<String> findTag();

    /**
     * 保存和更新博客文章
     * @return
     */
    BlogDoc save(BlogDoc blogDoc);

    /**
     * 分页查询文章，传入一个BlogDoc对象，根据非空属性匹配
     * 传入一个页码和页大小实现分页
     * @param blogDoc
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<BlogDoc> findAllBlogDocPaging(BlogDoc blogDoc, Integer pageNo, Integer pageSize);

    /**
     * 根据ID查询文章
     * @param blogId
     * @return
     */
    BlogDoc findBlogDocByBlogId(Long blogId);
    /**
     * 根据关键字link标题和文章
     * 如果Page不为空则分页查询，否则返回所有
     * @param keyword
     * @return
     */
    List<BlogDoc> findBlogDocByKeyword(String keyword,Integer pageNo, Integer pageSize);
}
