package com.zbkblog.service;

import com.zbkblog.medo.BlogDoc;
import com.zbkblog.medo.Page;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/7.
 */
public interface BlogService {
    /**
     * 保存和更新博客文章
     * @return
     */
    BlogDoc save(BlogDoc blogDoc);

    /**
     * 查询文章，传入一个BlogDoc对象，根据非空属性匹配
     * 如果传入Page不为空则分页查询，否则查询全部
     * @param blogDoc
     * @param page
     * @return
     */
    List<BlogDoc> findAllBlogDoc(BlogDoc blogDoc, Page page);

    /**
     * 根据关键字link标题和文章
     * 如果Page不为空则分页查询，否则返回所有
     * @param keyword
     * @return
     */
    List<BlogDoc> findBlogDocByKeyword(String keyword,Page page);
}
