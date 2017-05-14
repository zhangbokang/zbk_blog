package com.zbkblog.test;

import com.zbkblog.entity.Doc;
import com.zbkblog.utils.HibernateSessionUtil;
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

    @Test
    public void testGetSession(){
        //System.out.println(System.currentTimeMillis()/1000);
        //System.out.println(HibernateSessionUtil.getSession());
        Session session = HibernateSessionUtil.getSession();
        Doc doc = session.get(Doc.class,Long.valueOf(1494777977));
        System.out.println("Title:"+doc.getTitle());
        System.out.println("DocMd:"+doc.getDocMd());
        System.out.println("Classify:"+doc.getClassify().getName());
        System.out.println("Tag:"+doc.getTag().getName());
        session.close();
    }

}
