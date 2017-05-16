package com.zbkblog.dao.impl;

import com.zbkblog.dao.CommentDao;
import com.zbkblog.entity.Comment;
import com.zbkblog.utils.MyDaoSupport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("commentDoc")
//@Transactional
public class CommentDocImpl extends MyDaoSupport implements CommentDao {

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry){
        super.setSessionFactory(sessionFacotry);
    }

    @Override
    public List<Comment> findAll() {
        String hql = "from Comment";
        return (List)getHibernateTemplate().find(hql);
    }

    @Override
    public Comment findById(Long id) {
        return getHibernateTemplate().load(Comment.class,id);
    }

    @Override
    public void save(Comment comment) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(comment);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void update(Comment comment) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(comment);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void delete(Comment comment) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(comment);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }
}
