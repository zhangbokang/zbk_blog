package com.zbkblog.dao.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("classifyDao")
public class ClassifyDaoImpl implements ClassifyDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Classify> findAll() {
        String hql = "from Classify";
        return (List)hibernateTemplate.find(hql);
    }

    @Override
    public Classify findById(Long id) {
        return hibernateTemplate.load(Classify.class,id);
    }

    @Override
    public void delete(Classify classify) {
        hibernateTemplate.delete(hibernateTemplate.load(Classify.class,classify.getClassifyId()));
    }

    @Override
    public void save(Classify classify) {
        hibernateTemplate.save(classify);
    }

    @Override
    public void update(Classify classify) {
        hibernateTemplate.update(classify);
    }
}
