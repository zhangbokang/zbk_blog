package com.zbkblog.medo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangbokang on 2017/5/4.
 */
public class BlogDoc implements Serializable {
    private Long blogId;
    private String blogTitle; //博客文章标题
    private Integer blogClassId; //博客文章分类id
    private String blogClassName; //博客文章分类名称
    private Integer blogTagId; //博客文章标签id
    private String blogTagName; //博客文章标签名称
    private String blogMd; //博客文章md内容
    private Long updataTime; //最后更新时间
    private String updataTimeStr; //最后更新时间字符串
    private Integer openNumber; //打开浏览的次数
    private Integer supportNumber; //支持数量

    public BlogDoc() {
    }

    public BlogDoc(Long blogId, String blogTitle, Integer blogClassId, String blogClassName, Integer blogTagId, String blogTagName, String blogMd, Long updataTime, String updataTimeStr, Integer openNumber, Integer supportNumber) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogClassId = blogClassId;
        this.blogClassName = blogClassName;
        this.blogTagId = blogTagId;
        this.blogTagName = blogTagName;
        this.blogMd = blogMd;
        this.updataTime = updataTime;
        this.updataTimeStr = updataTimeStr;
        this.openNumber = openNumber;
        this.supportNumber = supportNumber;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Integer getBlogClassId() {
        return blogClassId;
    }

    public void setBlogClassId(Integer blogClassId) {
        this.blogClassId = blogClassId;
    }

    public String getBlogClassName() {
        return blogClassName;
    }

    public void setBlogClassName(String blogClassName) {
        this.blogClassName = blogClassName;
    }

    public Integer getBlogTagId() {
        return blogTagId;
    }

    public void setBlogTagId(Integer blogTagId) {
        this.blogTagId = blogTagId;
    }

    public String getBlogTagName() {
        return blogTagName;
    }

    public void setBlogTagName(String blogTagName) {
        this.blogTagName = blogTagName;
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
