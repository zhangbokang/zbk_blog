package com.zbkblog.entity;

import java.io.Serializable;

/**
 * Created by zhangbokang on 2017/6/3.
 */
public class BlogUser implements Serializable {
    private Long userId;
    private String userName;
    private String password;
    private String status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogUser blogUser = (BlogUser) o;

        if (userId != blogUser.userId) return false;
        if (userName != null ? !userName.equals(blogUser.userName) : blogUser.userName != null) return false;
        if (password != null ? !password.equals(blogUser.password) : blogUser.password != null) return false;
        if (status != null ? !status.equals(blogUser.status) : blogUser.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
