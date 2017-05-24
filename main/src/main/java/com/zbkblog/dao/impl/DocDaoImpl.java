package com.zbkblog.dao.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.utils.MyDaoSupport;
import com.zbkblog.utils.Page;
import org.hibernate.*;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Repository("docDao")
//@Transactional
public class DocDaoImpl extends MyDaoSupport implements DocDao {
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry){
        super.setSessionFactory(sessionFacotry);
    }

    @Override
    public List<Doc> findAll() {
        String hql = "from Doc ";
        try {
            return (List)getHibernateTemplate().find(hql);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Doc> findAllByPage(final Page page) {
        String hql = "from Doc";
        Query query = getSessionFactory().openSession().createQuery(hql);
        //设置每页显示多少个，设置多大结果。
        query.setMaxResults(page.getEveryPage());
        //设置起点
        query.setFirstResult(page.getBeginIndex());
        return query.list();
    }

    @Override
    public Doc findById(Long id) {
//        return getHibernateTemplate().execute(new HibernateCallback<Doc>() {
//            @Override
//            public Doc doInHibernate(Session session) throws HibernateException {
//                return (Doc)session.load(Doc.class,id);
//            }
//        });
        return getHibernateTemplate().get(Doc.class,id);
    }

    @Override
    public void delete(Doc doc) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(doc);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void save(Doc doc) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(doc);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void update(Doc doc) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(doc);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public List<Doc> findByUpdateOfTopX(Integer top) {
        Session session = getSession();
        String hql = "from Doc order by updateTime desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(top);
        try {
            List<Doc> list = query.list();
            return list;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Doc> findByOpenNumberOfTopX(Integer top) {
        Session session = getSession();
        String hql = "from Doc order by openNumber desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(top);
        try {
            List<Doc> list = query.list();
            return list;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Doc> findByFavorNumberOfTopX(Integer top) {
        Session session = getSession();
        String hql = "from Doc order by favorNumber desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(top);
        try {
            List<Doc> list = query.list();
            return list;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Doc> findByClassifyId(Long classifyId) {
        Session session = getSession();
        String hql = "from Doc where classify.classifyId = ?";
        Query query = session.createQuery(hql);
//        query.setFirstResult(0);
//        query.setMaxResults(top);
        query.setParameter(0,classifyId);
        try {
            List<Doc> list = query.list();
            return list;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Doc> findByTagId(Long tagId) {
        Session session = getSession();
        String hql = "from Doc where tag.tagId = ?";
        Query query = session.createQuery(hql);
//        query.setFirstResult(0);
//        query.setMaxResults(top);
        query.setParameter(0,tagId);
        try {
            List<Doc> list = query.list();
            return list;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }
}
