package com.zbkblog.utils;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Created by zhangbokang on 2017/5/16.
 */
public class MyDaoSupport extends HibernateDaoSupport {
    protected Session getSession(){
        return getSessionFactory().openSession();
    }
}
