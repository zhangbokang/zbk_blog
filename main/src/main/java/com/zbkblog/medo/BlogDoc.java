package com.zbkblog.medo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangbokang on 2017/5/4.
 */
public class BlogDoc implements Serializable {
    private String blogId;
    private String blogTitle; //博客文章标题
    private String blogClass; //博客文章分类
    private String blogTag; //博客文章标签
    private String blogMd; //博客文章md内容
    private Long updataTime; //最后更新时间
    private String updataTimeStr; //最后更新时间字符串
    private Integer openNumber; //打开浏览的次数
    private Integer supportNumber; //支持数量

    public BlogDoc() {
    }

    public BlogDoc(String blogId, String blogTitle, String blogClass, String blogTag, String blogMd, Long updataTime, String updataTimeStr, Integer openNumber, Integer supportNumber) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogClass = blogClass;
        this.blogTag = blogTag;
        this.blogMd = blogMd;
        this.updataTime = updataTime;
        this.updataTimeStr = updataTimeStr;
        this.openNumber = openNumber;
        this.supportNumber = supportNumber;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
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

    public Long getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Long updataTime) {
        this.updataTime = updataTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYY-MM-dd HH:mm:ss");
        this.setUpdataTimeStr(simpleDateFormat.format(new Date(updataTime)));
    }

    public String getUpdataTimeStr() {
        return updataTimeStr;
    }

    public void setUpdataTimeStr(String updataTimeStr) {
        this.updataTimeStr = updataTimeStr;
    }

    public Integer getOpenNumber() {
        return openNumber;
    }

    public void setOpenNumber(Integer openNumber) {
        this.openNumber = openNumber;
    }

    public Integer getSupportNumber() {
        return supportNumber;
    }

    public void setSupportNumber(Integer supportNumber) {
        this.supportNumber = supportNumber;
    }
}
