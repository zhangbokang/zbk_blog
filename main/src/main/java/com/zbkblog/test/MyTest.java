package com.zbkblog.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public class MyTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("test.xml");
        SessionFactory sessionFactory = applicationContext.getBean("sessionFactory",SessionFactory.class);
        System.out.println(sessionFactory);
        Session session = sessionFactory.openSession();
        System.out.println(session);
        DataSource d = applicationContext.getBean("dataSource", DataSource.class);
        System.out.println(d);
    }

}
