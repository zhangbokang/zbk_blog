package com.zbkblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by zhangbokang on 2017/5/14.
 */
/*
在使用SpringMVC+Hibernate环境中返回JSON数据有时会出现对象属性不能序列化，
这是因为你需要序列化对象有一个属性是一类类型，
而你使用了Hibernate的延迟加载所以这里是个Hibernate的代理对象。
该代理对象有些属性不能被序列化所以会报错。
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {
    private Long commentId;
    private String content;
    private Long createTime;
    private Long favorNumber;
    private Long opposeNumber;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getFavorNumber() {
        return favorNumber;
    }

    public void setFavorNumber(Long favorNumber) {
        this.favorNumber = favorNumber;
    }

    public Long getOpposeNumber() {
        return opposeNumber;
    }

    public void setOpposeNumber(Long opposeNumber) {
        this.opposeNumber = opposeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (createTime != null ? !createTime.equals(comment.createTime) : comment.createTime != null) return false;
        if (favorNumber != null ? !favorNumber.equals(comment.favorNumber) : comment.favorNumber != null) return false;
        if (opposeNumber != null ? !opposeNumber.equals(comment.opposeNumber) : comment.opposeNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (favorNumber != null ? favorNumber.hashCode() : 0);
        result = 31 * result + (opposeNumber != null ? opposeNumber.hashCode() : 0);
        return result;
    }
}
