package com.zbkblog.test;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.BlogUser;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.BlogUserService;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.HibernateSessionUtil;
import com.zbkblog.utils.MyBeanUtils;
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
    public void testBlogUserAuth(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        BlogUserService blogUserService = applicationContext.getBean("blogUserService",BlogUserService.class);
        BlogUser blogUser = blogUserService.authBlogUser("abc","xxxx");

        System.out.println(blogUser==null);

        System.out.println(blogUser);


    }

    @Test
    public void testPaging(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
//        DocDao docDao = applicationContext.getBean(DocDao.class,"docDao");
//        docDao.findAllByPage()
        //统计行数
        SessionFactory sessionFactory = applicationContext.getBean(SessionFactory.class,"sessionFactory");
        Session session = sessionFactory.openSession();
        String hql = "select count(1) from Doc where classify.classifyId=:classifyId";
        Query query = session.createQuery(hql);
        query.setParameter("classifyId",1495603834642L);
        Long l = (Long)query.uniqueResult();
        System.out.println(l);
        session.close();


    }

    @Test
    public void testGetSession(){
        //System.out.println(System.currentTimeMillis());
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
//            System.out.println("ClassifyId:"+doc.getClassify().getClassifyId());
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
    public void testDocService(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        DocService docService = context.getBean("docService", DocService.class);
        //更新
//        Doc doc = new Doc();
//        doc.setDocId(new Long(1495030981));
//        doc.setTitle("a01-jfkdalfjdsf");
//        //doc.setDocMd("#这里dafd是dmd");
//        Classify classify = new Classify();
//        classify.setClassifyId(new Long(1494777612));
//        doc.setClassify(classify);
//        docService.update(doc);
        //通过ID查找
        Doc doc = docService.findById(new Long(1495033961));
    }

    @Test
    public void testMyBeanUtils(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
//        MyBeanUtils myBeanUtils = context.getBean("myBeanUtils",MyBeanUtils.class);
        Doc src = new Doc();
        src.setTitle("abc");
        Doc dis = new Doc();
        dis.setTitle("xxx");
        dis.setDocMd("ahfdsf");
//        myBeanUtils
        MyBeanUtils.copyPropertiesIgnoreNull(src,dis);
    }

    @Test
    public void testTopX(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        DocDao docDao = context.getBean("docDao", DocDao.class);
        List<Doc> list = docDao.findByUpdateOfTopX(10);
        for (Doc doc:list) {
            System.out.println(doc.getTitle() + ":" + doc.getUpdateTime());
        }
    }

}
