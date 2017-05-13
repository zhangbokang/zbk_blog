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
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据文档ID删除
     * @param docId
     */
    void deleteByDocId(Long docId);

    /**
     * 查找所有
     * @return
     */
    List<Comment> findAll();

    /**
     * 根据文档id查找
     * @param docId
     * @return
     */
    List<Comment> findByDocId(Long docId);

    /**
     * 根据id查找
     * @return
     */
    Comment findById(Long id);
}
