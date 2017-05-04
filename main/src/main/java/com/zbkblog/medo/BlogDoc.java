package com.zbkblog.medo;

import java.io.Serializable;

/**
 * Created by zhangbokang on 2017/5/4.
 */
public class BlogDoc implements Serializable {
    private String blogTitle; //博客文章标题
    private String blogClass; //博客文章分类
    private String blogTag; //博客文章标签
    private String blogMd; //博客文章md内容
    private String blogHtml; //博客文章HTML内容
    private Long updataTime; //最后更新时间

    public BlogDoc() {
    }

    public BlogDoc(String blogTitle, String blogClass, String blogTag, String blogMd, String blogHtml, Long updataTime) {
        this.blogTitle = blogTitle;
        this.blogClass = blogClass;
        this.blogTag = blogTag;
        this.blogMd = blogMd;
        this.blogHtml = blogHtml;
        this.updataTime = updataTime;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogClass() {
        return blogClass;
    }

    public void setBlogClass(String blogClass) {
        this.blogClass = blogClass;
    }

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag;
    }

    public String getBlogMd() {
        return blogMd;
    }

    public void setBlogMd(String blogMd) {
        this.blogMd = blogMd;
    }

    public String getBlogHtml() {
        return blogHtml;
    }

    public void setBlogHtml(String blogHtml) {
        this.blogHtml = blogHtml;
    }

    public Long getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Long updataTime) {
        this.updataTime = updataTime;
    }
}
