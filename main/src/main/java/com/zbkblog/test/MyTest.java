package com.zbkblog.test;

import com.zbkblog.entity.Classify;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.ClassifyService;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.HibernateSessionUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.LongType;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.List;

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
        //Doc doc1 = session.load(Doc.class,Long.valueOf(1494777977));
        String hql = "from Doc where docId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,Long.valueOf(1494777977), LongType.INSTANCE);
        List<Doc> list = query.list();
        for (Doc doc:list) {
            System.out.println("Title:"+doc.getTitle());
            System.out.println("DocMd:"+doc.getDocMd());
            System.out.println("ClassifyId:"+doc.getClassify().getClassifyId());
            System.out.println("TagId:"+doc.getTag().getTagId());
        }

        session.close();
    }

    @Test
    public void testInster(){
        Session session = HibernateSessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Doc doc = new Doc();
        doc.setTitle("jfkdalfjdsf");
        doc.setDocMd("#这里是md");
        try {
            session.save(doc);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        session.close();

    }

    @Test
    public void testclassifyService(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        ClassifyService classifyService = context.getBean("classifyService", ClassifyService.class);
        Classify classify = new Classify();
        classify.setName("测试标题");
        //classifyService.save(classify);
        List<Classify> list = classifyService.findAll();
        for (Classify c:list
             ) {
            System.out.println("className:"+c.getName());
        }

    }

    @Test
    public void testDocService(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        DocService docService = context.getBean("docService", DocService.class);
        Doc doc = new Doc();
        doc.setTitle("a01-jfkdalfjdsf");
        doc.setDocMd("#这里dafd是dmd");
        docService.save(doc);
    }

}
