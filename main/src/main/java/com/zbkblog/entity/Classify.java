package com.zbkblog.entity;

/**
 * Created by zhangbokang on 2017/5/14.
 */
public class Classify {
    private long classifyId;
    private String name;
    private Long createTime;

    public long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(long classifyId) {
        this.classifyId = classifyId;
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

        Classify classify = (Classify) o;

        if (classifyId != classify.classifyId) return false;
        if (name != null ? !name.equals(classify.name) : classify.name != null) return false;
        if (createTime != null ? !createTime.equals(classify.createTime) : classify.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (classifyId ^ (classifyId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
