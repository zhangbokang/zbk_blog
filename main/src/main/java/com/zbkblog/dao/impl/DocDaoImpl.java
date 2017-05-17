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
        return (List)getHibernateTemplate().find(hql);
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
}
