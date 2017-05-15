package com.zbkblog.dao.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
import org.hibernate.SessionFactory;
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
public class ClassifyDaoImpl extends HibernateDaoSupport implements ClassifyDao {
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
    public void deleteById(Long id) {
        getHibernateTemplate().delete("id",id);
    }

    @Override
    public void save(Classify classify) {
        getHibernateTemplate().save(classify);
    }

    @Override
    public void update(Classify classify) {
        getHibernateTemplate().update(classify);
    }
}
