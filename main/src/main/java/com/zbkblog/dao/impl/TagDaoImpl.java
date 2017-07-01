package com.zbkblog.dao.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
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
@Repository("tagDao")
public class TagDaoImpl implements TagDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Tag> findAll() {
        String hql = "from Tag ";
        return (List<Tag>)hibernateTemplate.find(hql);
    }

    @Override
    public Paging<Tag> findAllByPage(Integer pageSize, Integer currentPage) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setCurrentPage(currentPage);
        String hql = "from Tag order by createTime desc";
        return (Paging<Tag>) hibernateTemplate.execute(new HibernateCallback<Paging<Tag>>() {
            @Override
            public Paging<Tag> doInHibernate(Session session) throws HibernateException {
                //查询总记录数
                Query queryCount = session.createQuery("select count(1) " + hql);
                Integer totalCounts = ((Number)queryCount.uniqueResult()).intValue();
                paging.setTotalCounts(totalCounts);
                Query query = session.createQuery(hql);
                //设置每页显示多少个，设置多大结果。
                query.setMaxResults(pageSize);
                //设置起点
                query.setFirstResult(Paging.firstResultCount(pageSize,currentPage));
                List<Tag> tagList =  query.list();
                paging.setPageList(tagList);
                return paging;
            }
        });
    }

    @Override
    public Tag findById(Long id) {
        return hibernateTemplate.load(Tag.class,id);
    }

    @Override
    public void delete(Tag tag) {
        hibernateTemplate.delete(hibernateTemplate.load(Tag.class,tag.getTagId()));
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
