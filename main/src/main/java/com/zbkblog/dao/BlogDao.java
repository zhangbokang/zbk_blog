package com.zbkblog.dao;

import com.zbkblog.medo.BlogDoc;
import com.zbkblog.medo.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/7.
 */
@Repository
public interface BlogDao {

    /**
     * 根据传入的对象的非空属性查询，如果Page非空，则分页，否则查询全部
     * @param blogDoc
     * @param page
     * @return
     */
    List<BlogDoc> findBlogDoc(BlogDoc blogDoc,Page page);

    /**
     * 保存博客
     * @param blogDoc
     * @return
     */
    BlogDoc save(BlogDoc blogDoc);

    /**
     * 更新博客
     * @param blogDoc
     * @return
     */
    BlogDoc updata(BlogDoc blogDoc);

    /**
     * 根据关键字link标题和文章
     * 如果Page不为空则分页查询，否则返回所有
     * @param keyword
     * @return
     */
    List<BlogDoc> findBlogDocByKeyword(String keyword,Page page);

    /**
     * 查找所有文章
     * @return
     */
    //List<BlogDoc> findAll();

    /**
     * 分页查询所有
     * @param page
     * @return
     */
    //List<BlogDoc> findAllPaging(Page page);

    /**
     * 根据文章ID查询单个文章
     * @param blogId
     * @return
     */
    //BlogDoc findBlogById(String blogId);

    /**
     * 根据文章标题查询link的匹配文章列表
     * @param blogTitle
     * @return
     */
    //List<BlogDoc> findBlogByTitle(String blogTitle);

    /**
     * 根据文章分类查询同一分类文章列表
     * @param blogClass
     * @return
     */
    //List<BlogDoc> findBlogByClass(String blogClass);

    /**
     * 根据文章分类分页查询同一分类文章列表
     * @param map
     * @return
     */
    //Map<String,Object> findBlogByClassPaging(Map<String,Object> map);

    /**
     * 根据文章标签查询同一标签文章列表
     * @param blogTag
     * @return
     */
    //List<BlogDoc> findBlogByTag(String blogTag);

    /**
     * 根据文章标签分页查询同一标签文章
     * @param map
     * @return
     */
    //Map<String,Object> findBlogByTagPaging(Map<String,Object> map);

    /**
     * 根据关键字查询标题和文章中有匹配项的文章列表
     * @param keyword
     * @return
     */
    //List<BlogDoc> linkKeyword(String keyword);

    /**
     * 根据关键字查询标题和文章中有匹配项的文章列表
     * @param keyword
     * @return
     */
    //Map<String,Object> linkKeywordPaging(String keyword);
}
