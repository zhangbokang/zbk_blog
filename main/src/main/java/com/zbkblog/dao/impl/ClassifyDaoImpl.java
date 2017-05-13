package com.zbkblog.dao.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("classifyDao")
@Transactional
public class ClassifyDaoImpl implements ClassifyDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Classify> findAll() {
        String hql = "from classify";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public Classify findById(Long id) {
        return sessionFactory.getCurrentSession().load(Classify.class,id);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete("id",id);
    }

    @Override
    public void save(Classify classify) {
        sessionFactory.getCurrentSession().save(classify);
    }

    @Override
    public void update(Classify classify) {
        sessionFactory.getCurrentSession().update(classify);
    }
}
