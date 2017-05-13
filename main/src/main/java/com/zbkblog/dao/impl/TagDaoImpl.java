package com.zbkblog.dao.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("tagDao")
public class TagDaoImpl implements TagDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Tag> findAll() {
        String hql = "from tag";
        return sessionFactory.openSession().createQuery(hql).list();
    }

    @Override
    public Tag findById(Long id) {
        return sessionFactory.openSession().load(Tag.class,id);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.openSession().delete("id",id);
    }

    @Override
    public void save(Tag tag) {
        sessionFactory.openSession().save(tag);
    }

    @Override
    public void update(Tag tag) {
        sessionFactory.openSession().update(tag);
    }
}
