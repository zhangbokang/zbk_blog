package com.zbkblog.test;

import com.alibaba.fastjson.JSON;
import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.ClassifyNodeService;
import com.zbkblog.service.DocService;
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

    @Resource
    private ClassifyNodeService classifyNodeService;

    @Resource
    private DocService docService;

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
        List<Long> classifyNodeId = new ArrayList<>();
        classifyNodeId.add(92449177600002L);
        docDao.addClassifyNodesToDoc(docDao.findById(docId),classifyNodeId);
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
        paging = MyBeanUtils.copyPagingOfDocOrClassifyNode(paging);
        System.out.println(JSON.toJSON(paging));
    }

    //TestClassifyNodeService

    @Test
    public void test1() {
        for (int i = 0;i<10;i++) {
            ClassifyNode classifyNode = new ClassifyNode();
            classifyNode.setText("ceshi"+i);
            Long classifyNodeId = classifyNodeService.saveClassifyNode(classifyNode);
            System.out.println(classifyNodeId);
        }
    }

    @Test
    public void test2() {
        ClassifyNode classifyNode = classifyNodeService.findClassifyNodeById(92451909992449L);
        classifyNode.setText("UpdateNode");
        classifyNode.setUpdateTime(System.currentTimeMillis());
        classifyNode = classifyNodeService.updateClassifyNode(classifyNode);
        System.out.println(JSON.toJSON(classifyNode));
    }

    @Test
    public void test3() {
        ClassifyNode classifyNode = classifyNodeService.findClassifyNodeById(92451909992449L);
        Boolean falg = classifyNodeService.deleteClassifyNode(classifyNode);
        System.out.println(falg);
    }

    @Test
    public void test4() {
        ClassifyNode classifyNode = classifyNodeService.addChildrenNode(92452814848002L, 92452814848003L, null);
        System.out.println(JSON.toJSON(classifyNode));
    }

    @Test
    public void test5() {
        List<ClassifyNode> classifyNodeList = classifyNodeService.findClassifyNodeListByParentId(92451909992449L);
        System.out.println(JSON.toJSON(classifyNodeList));
    }

    @Test
    public void test6() {
        List<ClassifyNode> classifyNodeList = classifyNodeService.findClassifyNodeByDocId(92446664622081L);
        System.out.println(JSON.toJSON(classifyNodeList));
    }

    @Test
    public void test7() {
        List<ClassifyNode> classifyNodeList = classifyNodeService.findAllClassifyNode();
        System.out.println(JSON.toJSON(classifyNodeList));
        Paging<ClassifyNode> classifyNodePaging = classifyNodeService.findAllClassifyNodeByPage(10, 1);
        System.out.println(JSON.toJSON(classifyNodePaging));
    }

    //testDocService

    @Test
    public void dtest1() {
        for(int i=0;i<10;i++) {
            Doc doc = new Doc();
            doc.setTitle("abc"+i);
            doc.setDocMd("md"+i);
            docService.save(doc);
            System.out.println(JSON.toJSON(doc));
        }
    }

    @Test
    public void dtest2() {
        List<Long> classifyNodeIdList = new ArrayList<>();
        classifyNodeIdList.add(92452814848001L);
        classifyNodeIdList.add(92452814848002L);
        Doc doc = docService.addClassifyNodesToDoc(92452815699970L, classifyNodeIdList);
        System.out.println(JSON.toJSON(doc));
    }

    @Test
    public void dtest3() {
        Paging<Doc> paging = docService.findAllByPage(10, 1);
        System.out.println(JSON.toJSON(paging));
    }

    @Test
    public void dtest4() {
        Paging<Doc> paging = docService.searchDocByKeywork("md", 10, 1);
        System.out.println(JSON.toJSON(paging));
    }

    @Test
    public void dtest5() {
        Doc doc = docService.findById(92452815765514L);
        doc.setTitle("hahaha");
        doc.setDocMd("#abc");
        doc = docService.update(doc,true);
        System.out.println(JSON.toJSON(doc));
    }

    @Test
    public void dtest6() {
        Doc doc = docService.findById(92452338335745L);
        Boolean falg = docService.delete(doc);
        System.out.println(falg);
    }

    @Test
    public void dtest7() {
        List<Doc> docList = docService.findAll();
        System.out.println(JSON.toJSON(docList));
        Doc doc = docService.findById(92452815765514L);
        System.out.println(JSON.toJSON(doc));
        List<Doc> docList1 = docService.findByUpdateOfTopX(10);
        System.out.println(JSON.toJSON(docList1));
        List<Doc> docList2 = docService.findByOpenNumberOfTopX(10);
        System.out.println(JSON.toJSON(docList2));
        List<Doc> docList3 = docService.findByFavorNumberOfTopX(10);
        System.out.println(JSON.toJSON(docList3));
        List<Doc> docList4 = docService.findByClassifyNodeId(92452814848001L);
        System.out.println(JSON.toJSON(docList4));
        Paging<Doc> paging = docService.findByClassifyNodeIdOfPage(92452814848001L, 15, 1);
        System.out.println(JSON.toJSON(paging));
//        List<Doc> docList5 = docService.findByTagId()
    }
//    List<Doc> findByTagId(Long tagId);
//    Paging<Doc> findByTagIdOfPage(Long tagId,Integer pageSize,Integer currentPage);
}
