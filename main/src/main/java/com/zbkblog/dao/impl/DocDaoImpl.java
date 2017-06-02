package com.zbkblog.dao.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.utils.Page;
import com.zbkblog.utils.Paging;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("docDao")
public class DocDaoImpl implements DocDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Doc> findAll() {
        String hql = "from Doc ";
        return (List)hibernateTemplate.find(hql);
    }

    @Override
    public Paging<Doc> findAllByPage(final Paging paging) {
        String hql = "from Doc";
        return (Paging<Doc>) hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                //查询总记录数
                Query queryCount = session.createQuery("select count(*) from Doc");
                Integer totalCounts = ((Number)queryCount.uniqueResult()).intValue();
                paging.setTotalCounts(totalCounts);

                Query query = session.createQuery(hql);
                //设置每页显示多少个，设置多大结果。
                query.setMaxResults(paging.getPageSize());
                //设置起点
                query.setFirstResult(paging.getFirstResult());
                List<Doc> docList =  query.list();
                paging.setPageList(docList);
                return paging;
            }
        });
    }

    @Override
    public Doc findById(Long id) {
        return hibernateTemplate.load(Doc.class,id);
    }

    @Override
    public void delete(Doc doc) {
        hibernateTemplate.delete(doc);
    }

    @Override
    public void save(Doc doc) {
        hibernateTemplate.save(doc);
    }

    @Override
    public void update(Doc doc) {
        hibernateTemplate.update(doc);
    }

    @Override
    public List<Doc> findByUpdateOfTopX(Integer top) {
        String hql = "from Doc order by updateTime desc";
        return (List)hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setFirstResult(0);
                query.setMaxResults(top);
                return query.list();
            }
        });
    }

    @Override
    public List<Doc> findByOpenNumberOfTopX(Integer top) {
        String hql = "from Doc order by openNumber desc";
        return (List)hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setFirstResult(0);
                query.setMaxResults(top);
                return query.list();
            }
        });
    }

    @Override
    public List<Doc> findByFavorNumberOfTopX(Integer top) {
        String hql = "from Doc order by favorNumber desc";
        return (List)hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setFirstResult(0);
                query.setMaxResults(top);
                return query.list();
            }
        });
    }

    @Override
    public List<Doc> findByClassifyId(Long classifyId) {
        String hql = "from Doc where classify.classifyId = ?";
        return (List)hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
//              query.setFirstResult(0);
//              query.setMaxResults(top);
                query.setParameter(0,classifyId);
                return query.list();
            }
        }) ;
    }

    @Override
    public Paging<Doc> findByClassifyIdOfPage(Long classifyId, Paging paging) {
        String hql = "from Doc Where classify.classifyId = ?";
        return (Paging<Doc>)hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                String chql = "select count(*) from Doc where classify.classifyId=:classifyId";
                Query q1 = session.createQuery(chql);
                q1.setParameter("classifyId",classifyId);
                paging.setTotalCounts(((Number)q1.uniqueResult()).intValue());

                Query query = session.createQuery(hql);
                query.setFirstResult(paging.getFirstResult());
                query.setMaxResults(paging.getPageSize());
                query.setParameter(0,classifyId);
                paging.setPageList(query.list());
                return paging;
            }
        });
    }

    @Override
    public List<Doc> findByTagId(Long tagId) {
        String hql = "from Doc where tag.tagId = ?";
        return (List)hibernateTemplate.execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
//              query.setFirstResult(0);
//              query.setMaxResults(top);
                query.setParameter(0,tagId);
                return query.list();
            }
        }) ;
    }
}
