package com.zbkblog.dao.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("tagDao")
//@Transactional
public class TagDaoImpl extends HibernateDaoSupport implements TagDao {
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
    public void deleteById(Long id) {
        getHibernateTemplate().delete("id",id);
    }

    @Override
    public void save(Tag tag) {
        getHibernateTemplate().save(tag);
    }

    @Override
    public void update(Tag tag) {
        getHibernateTemplate().update(tag);
    }
}
