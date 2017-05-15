package com.zbkblog.service.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.Page;
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
        doc.setUpdateTime(System.currentTimeMillis()/1000);
        docDao.save(doc);
        return doc;
    }

    @Override
    public List<Doc> findAllByPage(Page page) {
        return docDao.findAllByPage(page);
    }

    @Override
    public Doc update(Doc doc) {
        doc.setUpdateTime(System.currentTimeMillis()/1000);
        docDao.update(doc);
        return doc;
    }

    @Override
    public void deleteById(Long id) {
        docDao.deleteById(id);
    }

    @Override
    public List<Doc> findAll() {
        return docDao.findAll();
    }

    @Override
    public Doc findById(Long id) {
        return docDao.findById(id);
    }
}
