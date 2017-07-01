package com.zbkblog.dao;

import com.zbkblog.entity.DocX;

import java.util.List;

/**
 * Created by zhangbokang on 2017/7/2.
 */
public interface DocXDao {
    List<DocX> findAllDocList();
}
