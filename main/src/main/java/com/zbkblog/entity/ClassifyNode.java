package com.zbkblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhangbokang on 2017/6/26.
 */
/*
在使用SpringMVC+Hibernate环境中返回JSON数据有时会出现对象属性不能序列化，
这是因为你需要序列化对象有一个属性是一类类型，
而你使用了Hibernate的延迟加载所以这里是个Hibernate的代理对象。
该代理对象有些属性不能被序列化所以会报错。
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ClassifyNode implements Serializable {
    private long id;
    private String text;
    private Byte childrenByte; //1表示有子节点，0或null表示无子节点
    private Boolean children;
    private Long parentId;
    private Long updateTime;

    //多对多关联映射，这里手动控制
//    private Set<Doc> docs = new HashSet<>();

//    public Set<Doc> getDocs() {
//        return docs;
//    }

//    public void setDocs(Set<Doc> docs) {
//        this.docs = docs;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Byte getChildrenByte() {
        return childrenByte;
    }

    public void setChildrenByte(Byte childrenByte) {
        this.childrenByte = childrenByte;
        if (null != childrenByte && "1".equals(childrenByte.toString())) {
            this.children = true;
        } else {
            this.children = false;
        }
    }

    public Boolean getChildren() {
        return children;
    }

    public void setChildren(Boolean children) {
        this.children = children;
        if (null != children && children) {
            this.childrenByte = Byte.parseByte("1");
        } else {
            this.childrenByte = null;
        }
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassifyNode that = (ClassifyNode) o;

        if (id != that.id) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (childrenByte != null ? !childrenByte.equals(that.childrenByte) : that.childrenByte != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (childrenByte != null ? childrenByte.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
