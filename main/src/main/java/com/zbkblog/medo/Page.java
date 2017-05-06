package com.zbkblog.medo;

/**
 * Created by zhangbokang on 2017/5/7.
 */
public class Page {
    //order=asc&offset=0&limit=10
    private String order;
    private String offset;
    private String limit;

    public Page() {
    }

    public Page(String order, String offset, String limit) {
        this.order = order;
        this.offset = offset;
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
