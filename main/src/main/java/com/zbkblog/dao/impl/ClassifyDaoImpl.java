package com.zbkblog.dao.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
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
    public Paging<Classify> findAllByPage(Integer pageSize, Integer currentPage) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setCurrentPage(currentPage);
        String hql = "from Classify order by createTime desc";
        return (Paging<Classify>) hibernateTemplate.execute(new HibernateCallback<Paging>() {
            @Override
            public Paging doInHibernate(Session session) throws HibernateException {
                //查询总记录数
                Query queryCount = session.createQuery("select count(1) from Classify ");
                Integer totalCounts = ((Number)queryCount.uniqueResult()).intValue();
                paging.setTotalCounts(totalCounts);
                Query query = session.createQuery(hql);
                //设置每页显示多少个，设置多大结果。
                query.setMaxResults(pageSize);
                //设置起点
                query.setFirstResult(Paging.firstResultCount(pageSize,currentPage));
                List<Classify> classifyList =  query.list();
                paging.setPageList(classifyList);
                return paging;
            }
        });
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
