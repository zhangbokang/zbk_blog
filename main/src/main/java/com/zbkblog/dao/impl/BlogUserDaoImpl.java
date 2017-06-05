package com.zbkblog.dao.impl;

import com.zbkblog.dao.BlogUserDao;
import com.zbkblog.entity.BlogUser;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zhangbokang on 2017/6/3.
 */
@Repository("blogUserDao")
public class BlogUserDaoImpl implements BlogUserDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public BlogUser authBlogUser(BlogUser blogUser) {
        return hibernateTemplate.execute(new HibernateCallback<BlogUser>() {
            @Override
            public BlogUser doInHibernate(Session session) throws HibernateException {
                String hql = "from BlogUser where userName=:userName and password=:password";
                Query query = session.createQuery(hql);
                query.setParameter("userName",blogUser.getUserName());
                query.setParameter("password",blogUser.getPassword());
                return (BlogUser)query.uniqueResult();
            }
        });
    }
}
