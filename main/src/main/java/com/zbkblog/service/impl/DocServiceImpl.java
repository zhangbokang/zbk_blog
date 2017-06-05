package com.zbkblog.service.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.Page;
import com.zbkblog.utils.Paging;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public Paging<Doc> findAllByPage(Paging paging) {
        return docDao.findAllByPage(paging);
    }

    @Override
    public Paging<Doc> searchDocByKeywork(String keyword,Paging paging) {
        return docDao.searchDocByKeywork(keyword, paging);
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
    public Paging<Doc> findByClassifyIdOfPage(Long classifyId, Paging paging) {
        return docDao.findByClassifyIdOfPage(classifyId,paging);
    }

    @Override
    public List<Doc> findByTagId(Long tagId) {
        return docDao.findByTagId(tagId);
    }

    @Override
    public Paging<Doc> findByTagIdOfPage(Long tagId, Paging paging) {
        return docDao.findByTagIdOfPage(tagId,paging);
    }
}
