package com.zbkblog.service;

import com.zbkblog.entity.Comment;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface CommentService {
    /**
     * 添加一个
     * @param comment
     */
    void save(Comment comment);

    /**
     * 修改
     * @param comment
     */
    void update(Comment comment);

    /**
     * 根据ID删除
     * @param comment
     */
    void delete(Comment comment);

    /**
     * 查找所有
     * @return
     */
    List<Comment> findAll();

    /**
     * 根据id查找
     * @return
     */
    Comment findById(Long id);
}
