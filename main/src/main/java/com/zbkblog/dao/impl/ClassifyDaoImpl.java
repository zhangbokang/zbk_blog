package com.zbkblog.dao.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
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
@Repository("classifyDao")
//@Transactional
public class ClassifyDaoImpl extends MyDaoSupport implements ClassifyDao {
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry){
        super.setSessionFactory(sessionFacotry);
    }

    @Override
    public List<Classify> findAll() {
        String hql = "from Classify";
        return (List)getHibernateTemplate().find(hql);
    }

    @Override
    public Classify findById(Long id) {
        return getHibernateTemplate().load(Classify.class,id);
    }

    @Override
    public void delete(Classify classify) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(classify);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public Long save(Classify classify) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Long classifyId=new Long(session.save(classify).toString());
            transaction.commit();
            return classifyId;
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public void update(Classify classify) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(classify);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }
}
