package com.zbkblog.dao.impl;

import com.zbkblog.dao.CommentDao;
import com.zbkblog.entity.Comment;
import org.hibernate.SessionFactory;
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
public class CommentDocImpl extends HibernateDaoSupport implements CommentDao {

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
        getHibernateTemplate().save(comment);
    }

    @Override
    public void update(Comment comment) {
        getHibernateTemplate().update(comment);
    }

    @Override
    public void deleteById(Long id) {
        getHibernateTemplate().delete("id",id);
    }
}
