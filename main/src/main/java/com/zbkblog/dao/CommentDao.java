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
     * 删除评论
     * @param comment
     */
    void delete(Comment comment);
}
