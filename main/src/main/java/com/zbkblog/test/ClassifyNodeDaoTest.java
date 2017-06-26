package com.zbkblog.test;


import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.entity.ClassifyNode;
import org.junit.Test;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/6/26.
 */
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class ClassifyNodeDaoTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private ClassifyNodeDao classifyNodeDao;

    @Test
    public void testSave() {
        for (int i=0;i<20;i++){
            ClassifyNode classifyNode = new ClassifyNode();
//        classifyNode.setId(100L);
            classifyNode.setText("node"+i);
            classifyNode.setUpdateTime(System.currentTimeMillis());
            Long classifyNodeId = classifyNodeDao.saveClassifyNode(classifyNode);
            System.out.println(classifyNodeId);
        }
    }

    @Test
    public void testFindById() {
        ClassifyNode classifyNode =  classifyNodeDao.findClassifyNodeById(1498464244149L);
        System.out.println(classifyNode);
    }

    @Test
    public void testUpdate() {
        ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(1498464243854L);
        classifyNode.setText("cNode1");
        classifyNode.setChildren(Byte.parseByte("1"));
        classifyNode.setUpdateTime(System.currentTimeMillis());
        classifyNodeDao.updateClassifyNode(classifyNode);
    }

    @Test
    public void testAddChilren() {
        classifyNodeDao.addChildrenNode(1498464244149L,1498464243994L);
        classifyNodeDao.addChildrenNode(1498464244149L,1498464244103L);
    }

    @Test
    public void teseDelete() {
//        classifyNodeDao.deleteClassifyNode(1498464243854L);
//        classifyNodeDao.deleteClassifyNode(1498464243854L);
        ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(1498464243854L);
        classifyNodeDao.deleteClassifyNode(classifyNode);
    }

    @Test
    public void testParentId() {
//        List<ClassifyNode> classifyNodeList = classifyNodeDao.findClassifyNodeListByParentId(1498464244149L);
        List<ClassifyNode> classifyNodeList = classifyNodeDao.findClassifyNodeListByParentId(null);
        for (ClassifyNode c:classifyNodeList) {
            System.out.println(c);
        }
    }
}
