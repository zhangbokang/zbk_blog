package com.zbkblog.entity;

/**
 * Created by zhangbokang on 2017/5/14.
 */
public class Comment {
    private long commentId;
    private String content;
    private Long createTime;
    private Long favorNumber;
    private Long opposeNumber;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
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
