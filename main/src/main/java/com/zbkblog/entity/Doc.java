package com.zbkblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
public class Doc implements Serializable {
    private Long docId;
    private String title;
    private String docMd;
    private Long updateTime;
    private Long favorNumber;
    private Long openNumber;
//    private Classify classify;
    private Tag tag;

    //多对多关系映射，这里手动控制
    private Set<ClassifyNode> classifyNodes = new HashSet<>();

    public Set<ClassifyNode> getClassifyNodes() {
        return classifyNodes;
    }

    public void setClassifyNodes(Set<ClassifyNode> classifyNodes) {
        this.classifyNodes = classifyNodes;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

//    public Classify getClassify() {
//        return classify;
//    }

//    public void setClassify(Classify classify) {
//        this.classify = classify;
//    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocMd() {
        return docMd;
    }

    public void setDocMd(String docMd) {
        this.docMd = docMd;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getFavorNumber() {
        return favorNumber;
    }

    public void setFavorNumber(Long favorNumber) {
        this.favorNumber = favorNumber;
    }

    public Long getOpenNumber() {
        return openNumber;
    }

    public void setOpenNumber(Long openNumber) {
        this.openNumber = openNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doc doc = (Doc) o;

        if (docId != doc.docId) return false;
        if (title != null ? !title.equals(doc.title) : doc.title != null) return false;
        if (docMd != null ? !docMd.equals(doc.docMd) : doc.docMd != null) return false;
        if (updateTime != null ? !updateTime.equals(doc.updateTime) : doc.updateTime != null) return false;
        if (favorNumber != null ? !favorNumber.equals(doc.favorNumber) : doc.favorNumber != null) return false;
        if (openNumber != null ? !openNumber.equals(doc.openNumber) : doc.openNumber != null) return false;
//        if (classify != null ? !classify.equals(doc.classify) : doc.classify != null) return false;
        if (tag != null ? !tag.equals(doc.tag) : doc.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (docId ^ (docId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (docMd != null ? docMd.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (favorNumber != null ? favorNumber.hashCode() : 0);
        result = 31 * result + (openNumber != null ? openNumber.hashCode() : 0);
//        result = 31 * result + (classify != null ? classify.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
