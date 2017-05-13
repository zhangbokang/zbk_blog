package com.zbkblog.dao.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.utils.Page;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("docDao")
@Transactional
public class DocDaoImpl implements DocDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public List<Doc> findAll() {
        String hql = "from doc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Doc> findAllByPage(final Page page) {
        Query query = sessionFactory.getCurrentSession().createQuery("from doc");
        //设置参数
        //query.setParameter(0, username);
        //设置每页显示多少个，设置多大结果。
        query.setMaxResults(page.getEveryPage());
        //设置起点
        query.setFirstResult(page.getBeginIndex());
        return query.list();
    }

    @Override
    public Doc findById(Long id) {
        return sessionFactory.getCurrentSession().load(Doc.class,id);
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession().delete("id",id);
    }

    @Override
    public void save(Doc doc) {
        sessionFactory.getCurrentSession().save(doc);
    }

    @Override
    public void update(Doc doc) {
        sessionFactory.getCurrentSession().update(doc);
    }
}
