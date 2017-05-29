package com.zbkblog.dao.impl;

import com.zbkblog.dao.CommentDao;
import com.zbkblog.entity.Comment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("commentDoc")
public class CommentDocImpl implements CommentDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Comment> findAll() {
        String hql = "from Comment";
        return (List)hibernateTemplate.find(hql);
    }

    @Override
    public Comment findById(Long id) {
        return hibernateTemplate.load(Comment.class,id);
    }

    @Override
    public void save(Comment comment) {
        hibernateTemplate.save(comment);
    }

    @Override
    public void update(Comment comment) {
        hibernateTemplate.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        hibernateTemplate.delete(comment);
    }
}
