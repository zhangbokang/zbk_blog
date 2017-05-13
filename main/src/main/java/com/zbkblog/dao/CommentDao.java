package com.zbkblog.dao;

import com.zbkblog.entity.Comment;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface CommentDao {
    /**
     * 查找所有评论
     * @return
     */
    List<Comment> findAll();
    /**
     * 根据id查找评论
     * @param id
     * @return
     */
    Comment findById(Long id);
    /**
     * 根据文章id查询评论
     * @param docId
     * @return
     */
    List<Comment> findByDocId(Long docId);
    /**
     * 根据文章id删除评论
     * @param docId
     */
    void deleteByDocId(Long docId);
    /**
     * 保存评论
     * @param comment
     */
    void save(Comment comment);
    /**
     * 更新评论
     * @param comment
     */
    void update(Comment comment);
    /**
     * 根据id删除评论
     * @param id
     */
    void deleteById(Long id);
}
