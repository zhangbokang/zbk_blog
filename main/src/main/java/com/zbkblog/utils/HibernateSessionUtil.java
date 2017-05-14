package com.zbkblog.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangbokang on 2017/5/14.
 */
public class HibernateSessionUtil {
    private static SessionFactory sessionFactory;

    static {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("test.xml");
        sessionFactory = applicationContext.getBean("sessionFactory",SessionFactory.class);
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
