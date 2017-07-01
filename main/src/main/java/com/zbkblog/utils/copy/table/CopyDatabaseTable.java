package com.zbkblog.utils.copy.table;

import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.dao.DocDao;
import com.zbkblog.dao.DocXDao;
import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.entity.Doc;
import com.zbkblog.entity.Tag;
import com.zbkblog.entity.DocX;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/7/1.
 */
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class CopyDatabaseTable extends AbstractJUnit4SpringContextTests {
    @Resource
    private DocXDao docXDao;

    @Resource
    private DocDao docDao;
    @Resource
    private TagDao tagDao;
    @Resource
    private ClassifyNodeDao classifyNodeDao;

    @Test
    public void test1() {
        List<DocX> docXList = docXDao.findAllDocList();
        for (DocX docX : docXList) {
            Doc doc = new Doc();
//            private Long docId;
//            private String title;
//            private String docMd;
//            private Long updateTime;
//            private Long favorNumber;
//            private Long openNumber;
//    private Classify classify;
//            private Tag tag;
            doc.setDocId(docX.getDocId());
            doc.setTitle(docX.getTitle());
            doc.setDocMd(docX.getDocMd());
            doc.setUpdateTime(docX.getUpdateTime());
            doc.setFavorNumber(docX.getFavorNumber());
            doc.setOpenNumber(docX.getOpenNumber());
            try {
                Tag tag = tagDao.findById(docX.getTagId());
                doc.setTag(tag);
                ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(docX.getClassifyId());
                doc.getClassifyNodes().add(classifyNode);
            } catch (Exception e) {
            }
            docDao.save(doc);
        }
    }

}
