package com.zbkblog.dao.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import com.zbkblog.utils.MyDaoSupport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("tagDao")
//@Transactional
public class TagDaoImpl extends MyDaoSupport implements TagDao {
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry){
        super.setSessionFactory(sessionFacotry);
    }

    @Override
    public List<Tag> findAll() {
        String hql = "from Tag ";
        return (List)getHibernateTemplate().find(hql);
    }

    @Override
    public Tag findById(Long id) {
        return getHibernateTemplate().load(Tag.class,id);
    }

    @Override
    public void delete(Tag tag) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(tag);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void save(Tag tag) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(tag);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void update(Tag tag) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(tag);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }
}
