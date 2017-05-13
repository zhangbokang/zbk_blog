package com.zbkblog.dao.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("tagDao")
@Transactional
public class TagDaoImpl implements TagDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Tag> findAll() {
        String hql = "from tag";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public Tag findById(Long id) {
        return sessionFactory.getCurrentSession().load(Tag.class,id);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete("id",id);
    }

    @Override
    public void save(Tag tag) {
        sessionFactory.getCurrentSession().save(tag);
    }

    @Override
    public void update(Tag tag) {
        sessionFactory.getCurrentSession().update(tag);
    }
}
