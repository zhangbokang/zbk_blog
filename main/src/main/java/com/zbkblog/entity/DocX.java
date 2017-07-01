package com.zbkblog.entity;

/**
 * Created by zhangbokang on 2017/7/2.
 */
public class DocX {
    private long docId;
    private String title;
    private String docMd;
    private Long classifyId;
    private Long tagId;
    private Long updateTime;
    private Long favorNumber;
    private Long openNumber;

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
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

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
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

        DocX docX = (DocX) o;

        if (docId != docX.docId) return false;
        if (title != null ? !title.equals(docX.title) : docX.title != null) return false;
        if (docMd != null ? !docMd.equals(docX.docMd) : docX.docMd != null) return false;
        if (classifyId != null ? !classifyId.equals(docX.classifyId) : docX.classifyId != null) return false;
        if (tagId != null ? !tagId.equals(docX.tagId) : docX.tagId != null) return false;
        if (updateTime != null ? !updateTime.equals(docX.updateTime) : docX.updateTime != null) return false;
        if (favorNumber != null ? !favorNumber.equals(docX.favorNumber) : docX.favorNumber != null) return false;
        if (openNumber != null ? !openNumber.equals(docX.openNumber) : docX.openNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (docId ^ (docId >>> 32));
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
