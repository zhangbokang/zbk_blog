package com.zbkblog.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public class MyTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        SessionFactory sessionFactory = (SessionFactory)applicationContext.getBean("sessionFactory");
        System.out.println(sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        System.out.println(session);
    }

}
