package com.zbkblog.test;

import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.entity.Doc;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Created by zhangbokang on 2017/6/29.
 */
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class ClassifyNodeAndDocTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private DocDao docDao;

    @Resource
    private ClassifyNodeDao classifyNodeDao;

    @Test
    public void testDocAddClassifyNode() {
        Doc doc1 = new Doc();
        doc1.setTitle("xxx1");
        doc1.setDocMd("abc1");
        Doc doc2 = new Doc();
        doc2.setTitle("xxx2");
        doc2.setDocMd("abc2");
        ClassifyNode classifyNode1 = new ClassifyNode();
        classifyNode1.setText("xxx1");
        ClassifyNode classifyNode2 = new ClassifyNode();
        classifyNode2.setText("xxx2");

        //doc1中有c1、c2
        doc1.getClassifyNodes().add(classifyNode1);
        doc1.getClassifyNodes().add(classifyNode2);
        //doc2中有c1
        doc2.getClassifyNodes().add(classifyNode1);

        docDao.save(doc1);
        docDao.save(doc2);
    }

    @Test
    public void testRemoveDoc() {
        Long docId = 92444972023809L;
//        Long classifyNodeId = 1498709692439L;
//        System.out.println(classifyNode.getId());
//        System.out.println(classifyNode);
        Doc doc = docDao.findById(docId);
        docDao.delete(doc);
//        ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(classifyNodeId);
//        System.out.println(doc);
//        doc.getClassifyNodes().remove(classifyNode);
//        System.out.println(doc);
//        docDao.save(doc);
//        System.out.println(doc);
    }

    @Test
    public void testRemoveClassifyNode() {
        Long classifyNodeId = 92444972023810L;
        ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(classifyNodeId);
        classifyNodeDao.deleteClassifyNode(classifyNode);
    }
}
