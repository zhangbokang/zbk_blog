package com.zbkblog.entity;

import java.util.List;

/**
 * Created by zhangbokang on 2017/6/25.
 */
public class TreeNode {
    private Long id;
    private String text;
    private Object children;
    private Long parentId;

    public TreeNode() {
    }

    public TreeNode(Long id, String text, Object children, Long parentId) {
        this.id = id;
        this.text = text;
        this.children = children;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
