package com.zbkblog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Entity
public class Doc {
    private long id;
    private String title;
    private String docMd;
    private Long classifyId;
    private Long tagId;
    private Long updateTime;
    private Long favorNumber;
    private Long openNumber;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "doc_md")
    public String getDocMd() {
        return docMd;
    }

    public void setDocMd(String docMd) {
        this.docMd = docMd;
    }

    @Basic
    @Column(name = "classify_id")
    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    @Basic
    @Column(name = "tag_id")
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "update_time")
    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "favor_number")
    public Long getFavorNumber() {
        return favorNumber;
    }

    public void setFavorNumber(Long favorNumber) {
        this.favorNumber = favorNumber;
    }

    @Basic
    @Column(name = "open_number")
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

        if (id != doc.id) return false;
        if (title != null ? !title.equals(doc.title) : doc.title != null) return false;
        if (docMd != null ? !docMd.equals(doc.docMd) : doc.docMd != null) return false;
        if (classifyId != null ? !classifyId.equals(doc.classifyId) : doc.classifyId != null) return false;
        if (tagId != null ? !tagId.equals(doc.tagId) : doc.tagId != null) return false;
        if (updateTime != null ? !updateTime.equals(doc.updateTime) : doc.updateTime != null) return false;
        if (favorNumber != null ? !favorNumber.equals(doc.favorNumber) : doc.favorNumber != null) return false;
        if (openNumber != null ? !openNumber.equals(doc.openNumber) : doc.openNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (docMd != null ? docMd.hashCode() : 0);
        result = 31 * result + (classifyId != null ? classifyId.hashCode() : 0);
        result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (favorNumber != null ? favorNumber.hashCode() : 0);
        result = 31 * result + (openNumber != null ? openNumber.hashCode() : 0);
        return result;
    }
}
