package com.zbkblog.dao.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("tagDao")
public class TagDaoImpl implements TagDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Tag> findAll() {
        String hql = "from Tag ";
        return (List)hibernateTemplate.find(hql);
    }

    @Override
    public Tag findById(Long id) {
        return hibernateTemplate.load(Tag.class,id);
    }

    @Override
    public void delete(Tag tag) {
        hibernateTemplate.delete(tag);
    }

    @Override
    public void save(Tag tag) {
        hibernateTemplate.save(tag);
    }

    @Override
    public void update(Tag tag) {
        hibernateTemplate.update(tag);
    }
}
