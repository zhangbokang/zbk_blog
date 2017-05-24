package com.zbkblog.service.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Service("docService")
@Transactional
public class DocServiceImpl implements DocService {
    @Resource
    private DocDao docDao;

    @Override
    public Doc save(Doc doc) {
        doc.setUpdateTime(System.currentTimeMillis());
        docDao.save(doc);
        return doc;
    }

    @Override
    public List<Doc> findAllByPage(Page page) {
        return docDao.findAllByPage(page);
    }

    @Override
    public Doc update(Doc doc) {
        doc.setUpdateTime(System.currentTimeMillis());
        docDao.update(doc);
        return doc;
    }

    @Override
    public void delete(Doc doc) {
        docDao.delete(doc);
    }

    @Override
    public List<Doc> findAll() {
        return docDao.findAll();
    }

    @Override
    public Doc findById(Long id) {
        return docDao.findById(id);
    }

    @Override
    public List<Doc> findByUpdateOfTopX(Integer top) {
        return docDao.findByUpdateOfTopX(top);
    }

    @Override
    public List<Doc> findByOpenNumberOfTopX(Integer top) {
        return docDao.findByOpenNumberOfTopX(top);
    }

    @Override
    public List<Doc> findByFavorNumberOfTopX(Integer top) {
        return docDao.findByFavorNumberOfTopX(top);
    }

    @Override
    public List<Doc> findByClassifyId(Long classifyId) {
        return docDao.findByClassifyId(classifyId);
    }

    @Override
    public List<Doc> findByTagId(Long tagId) {
        return docDao.findByTagId(tagId);
    }
}
