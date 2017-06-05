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
public class Tag implements Serializable {
    private Long tagId;
    private String name;
    private Long createTime;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (tagId != tag.tagId) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        if (createTime != null ? !createTime.equals(tag.createTime) : tag.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
