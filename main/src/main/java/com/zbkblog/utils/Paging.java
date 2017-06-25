package com.zbkblog.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangbokang on 2017/6/1.
 */
public class Paging<T> implements Serializable {
    //总记录数
    private Integer totalCounts;
    //页面条目数
    private Integer pageSize;
    //当前页
    private Integer currentPage;
    //当前页条目集合
    private List<T> pageList;
    //记录开始数
    private Integer firstResult;
    //排序方式
    private String orderBy;

    public Paging() {
    }

    public Paging(Integer totalCounts, Integer pageSize, Integer currentPage, List<T> pageList, Integer firstResult, String orderBy) {
        this.totalCounts = totalCounts;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pageList = pageList;
        this.firstResult = firstResult;
        this.orderBy = orderBy;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        //计算条目开始数
        if (firstResult==null && currentPage!=null){
            this.firstResult = (currentPage-1)*pageSize;
        }
    }

    public static Integer firstResultCount(Integer pageSize,Integer currentPage){
        if (pageSize ==null || currentPage==null){
            return 0;
        }
        if (pageSize<1){
            pageSize = 1;
        }
        if (currentPage<1){
            currentPage = 1;
        }
        return (currentPage-1)*pageSize;
    }

    public static Integer currentPageCount(Integer pageSize,Integer firstResult){
        if (pageSize ==null || firstResult==null){
            return 1;
        }
        if (pageSize<1){
            pageSize = 1;
        }
        if (firstResult<0){
            firstResult = 0;
        }
        return firstResult/pageSize+1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        //计算条目开始数
        if (firstResult==null && pageSize!=null){
            this.firstResult = (currentPage-1)*pageSize;
        }
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
