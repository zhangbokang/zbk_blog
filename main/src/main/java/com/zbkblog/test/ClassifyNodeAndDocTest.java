package com.zbkblog.test;

import com.alibaba.fastjson.JSON;
import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.entity.Doc;
import com.zbkblog.utils.MyBeanUtils;
import com.zbkblog.utils.Paging;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        doc1.setTitle("doc1");
        doc1.setDocMd("abc1");
        Doc doc2 = new Doc();
        doc2.setTitle("doc2");
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
    public void testFindClassifyNodeByDocId() {
        Long docId = 92450929770497L;
        List<ClassifyNode> classifyNodeList = classifyNodeDao.findClassifyNodeByDocId(docId);
        System.out.println(JSON.toJSON(classifyNodeList));
    }

    @Test
    public void testaddClassifyNodeToDoc() {
        Long docId = 92449177665540L;
        Long classifyNodeId = 92449177600003L;
        docDao.addClassifyNodeToDoc(docId,classifyNodeId);
    }

    @Test
    public void testFindDocByClassifyNodeId() {
        Long classifyNodeId = 92449177600002L;
        List<Doc> docList = docDao.findByClassifyNodeId(classifyNodeId);
        System.out.println(JSON.toJSON(docList));
    }

    @Test
    public void testRemoveDoc() {
        Long docId = 92446664687620L;
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
        Long classifyNodeId = 92446664687619L;
        ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(classifyNodeId);
        classifyNodeDao.deleteClassifyNode(classifyNode);
    }

    //testDocDao

    @Test
    public void testFindAllDoc() {
        List<Doc> docList = docDao.findAll();
        List<Doc> tmpDocList =  MyBeanUtils.copyDocList(docList);
        System.out.println(JSON.toJSON(tmpDocList));
    }

    @Test
    public void testFindAllDocByPage() {
        Paging<Doc> paging = docDao.findAllByPage(10,1);
        List<Doc> docList = paging.getPageList();
        List<Doc> tmpDocList = MyBeanUtils.copyDocList(docList);
        System.out.println(JSON.toJSON(tmpDocList));
    }
}
