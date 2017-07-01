package com.zbkblog.dao.impl;

import com.zbkblog.dao.DocXDao;
import com.zbkblog.entity.DocX;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/7/2.
 */
@Repository("docXDao")
public class DocXDaoImpl implements DocXDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    @Override
    public List<DocX> findAllDocList() {
        return hibernateTemplate.execute(new HibernateCallback<List<DocX>>() {
            @Override
            public List<DocX> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from DocX");
                return query.list();
            }
        });
    }
}
