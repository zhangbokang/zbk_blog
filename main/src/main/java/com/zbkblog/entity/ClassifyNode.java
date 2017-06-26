package com.zbkblog.entity;

import java.io.Serializable;

/**
 * Created by zhangbokang on 2017/6/26.
 */
public class ClassifyNode implements Serializable {
    private long id;
    private String text;
    private Byte childrenByte; //1表示有子节点，0或null表示无子节点
    private Boolean children;
    private Long parentId;
    private Long updateTime;

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
