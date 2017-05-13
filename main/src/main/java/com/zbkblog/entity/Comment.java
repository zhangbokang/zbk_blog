package com.zbkblog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Entity
public class Comment implements Serializable {
    private long id;
    private Long docId;
    private String content;
    private Long createTime;
    private Long favorNumber;
    private Long opposeNumber;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doc_id")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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
    @Column(name = "oppose_number")
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

        if (id != comment.id) return false;
        if (docId != null ? !docId.equals(comment.docId) : comment.docId != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (createTime != null ? !createTime.equals(comment.createTime) : comment.createTime != null) return false;
        if (favorNumber != null ? !favorNumber.equals(comment.favorNumber) : comment.favorNumber != null) return false;
        if (opposeNumber != null ? !opposeNumber.equals(comment.opposeNumber) : comment.opposeNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (favorNumber != null ? favorNumber.hashCode() : 0);
        result = 31 * result + (opposeNumber != null ? opposeNumber.hashCode() : 0);
        return result;
    }
}
