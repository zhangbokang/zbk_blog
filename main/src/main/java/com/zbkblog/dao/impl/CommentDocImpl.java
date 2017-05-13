package com.zbkblog.dao.impl;

import com.zbkblog.dao.CommentDao;
import com.zbkblog.entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("commentDoc")
@Transactional
public class CommentDocImpl implements CommentDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Comment> findAll() {
        String hql = "from comment";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public Comment findById(Long id) {
        return sessionFactory.getCurrentSession().load(Comment.class,id);
    }

    @Override
    public List<Comment> findByDocId(Long docId) {
        String hql = "from comment where doc_id=:docId";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter("docId",docId).list();
    }

    @Override
    public void deleteByDocId(Long docId) {
        sessionFactory.getCurrentSession().delete("docId",docId);
    }

    @Override
    public void save(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public void update(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete("id",id);
    }
}
