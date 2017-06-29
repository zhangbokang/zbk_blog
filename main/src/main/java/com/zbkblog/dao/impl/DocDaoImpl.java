package com.zbkblog.dao.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.utils.MyBeanUtils;
import com.zbkblog.utils.Paging;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
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
    public Paging<Doc> findAllByPage(Integer pageSize,Integer currentPage) {
        Paging paging1 = new Paging();
        paging1.setPageSize(pageSize);
        paging1.setCurrentPage(currentPage);
        String hql = "from Doc order by updateTime desc";
        return (Paging<Doc>) hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                //查询总记录数
                Query queryCount = session.createQuery("select count(1) from Doc");
                Integer totalCounts = ((Number)queryCount.uniqueResult()).intValue();
                paging1.setTotalCounts(totalCounts);
                Query query = session.createQuery(hql);
                //设置每页显示多少个，设置多大结果。
                query.setMaxResults(pageSize);
                //设置起点
                query.setFirstResult(Paging.firstResultCount(pageSize,currentPage));
                List<Doc> docList =  query.list();
                paging1.setPageList(docList);
                return paging1;
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
    public List<Doc> findByClassifyNodeId(Long classifyNodeId) {
        String sql = "select * from doc JOIN classify_node_doc_map ON classify_node_doc_map.id=:classifyNodeId  order by doc.update_time desc";
        return (List<Doc>)hibernateTemplate.execute(new HibernateCallback<List<Doc>>() {
            @Override
            public List<Doc> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql).addEntity(Doc.class);
//              query.setFirstResult(0);
//              query.setMaxResults(top);
                query.setParameter("classifyNodeId",classifyNodeId);
                return query.list();
            }
        }) ;
    }

    @Override
    public Paging<Doc> findByClassifyNodeIdOfPage(Long classifyNodeId, Integer pageSize,Integer currentPage) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setCurrentPage(currentPage);
        String sql = "select * from doc JOIN classify_node_doc_map ON classify_node_doc_map.id=:classifyNodeId  order by doc.update_time desc";
        return (Paging<Doc>)hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql).addEntity(Doc.class);
                query.setParameter("classifyNodeId",classifyNodeId);
                paging.setTotalCounts(((Number)query.uniqueResult()).intValue());
                query.setFirstResult(Paging.firstResultCount(pageSize,currentPage));
                query.setMaxResults(pageSize);
                paging.setPageList(query.list());
                return paging;
            }
        });
    }

    @Override
    public List<Doc> findByTagId(Long tagId) {
        String hql = "from Doc where tag.tagId = ? order by updateTime desc";
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

    @Override
    public Paging<Doc> findByTagIdOfPage(Long tagId, Integer pageSize,Integer currentPage) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setCurrentPage(currentPage);
        String hql = "from Doc where tag.tagId = ? order by updateTime desc";
        return (Paging<Doc>)hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                Query q1 = session.createQuery("select count (1) from Doc where tag.tagId=:tagId");
                q1.setParameter("tagId",tagId);
                paging.setTotalCounts(((Number)q1.uniqueResult()).intValue());
                Query query = session.createQuery(hql);
                query.setFirstResult(Paging.firstResultCount(pageSize,currentPage));
                query.setMaxResults(pageSize);
                query.setParameter(0,tagId);
                paging.setPageList(query.list());
                return paging;
            }
        });
    }

    @Override
    public Paging<Doc> searchDocByKeywork(String keyword, Integer pageSize,Integer currentPage) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setCurrentPage(currentPage);
        String hql = "from Doc where UPPER(title) like UPPER(:keyword) or docMd like :keyword order by updateTime desc";
        return (Paging<Doc>)hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                Query q1 = session.createQuery("select count(1) "+hql);
                q1.setParameter("keyword","%"+keyword+"%");
                paging.setTotalCounts(((Number)q1.uniqueResult()).intValue());

                Query query = session.createQuery(hql);
                query.setFirstResult(Paging.firstResultCount(pageSize,currentPage));
                query.setMaxResults(pageSize);
                query.setParameter("keyword","%"+keyword+"%");
                paging.setPageList(query.list());
                return paging;
            }
        });
    }
}
