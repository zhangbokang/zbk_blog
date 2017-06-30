package com.zbkblog.service.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.Paging;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = {"docCache"},allEntries = true)
    public Doc save(Doc doc) {
        doc.setUpdateTime(System.currentTimeMillis());
        try {
            docDao.save(doc);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加一个节点到一个文档
     *
     * @param docId
     * @param classifyNodeId
     */
    @Override
    @CacheEvict(value = {"docCache"},allEntries = true)
    public void addClassifyNodeToDoc(Long docId, Long classifyNodeId) {
        docDao.addClassifyNodeToDoc(docId, classifyNodeId);
    }

    @Override
    @CacheEvict(value = {"docCache"},allEntries = true)
    public Doc update(Doc doc,Boolean upTime) {
        if (upTime){
            doc.setUpdateTime(System.currentTimeMillis());
        }
        docDao.update(doc);
        return doc;
    }

    @Override
    @CacheEvict(value = {"docCache"},allEntries = true)
    public void delete(Doc doc) {
        docDao.delete(doc);
    }

    @Override
    @Cacheable("docCache")
    public Paging<Doc> findAllByPage(Integer pageSize,Integer currentPage) {
        return docDao.findAllByPage(pageSize,currentPage);
    }

    @Override
    public Paging<Doc> searchDocByKeywork(String keyword,Integer pageSize,Integer currentPage) {
        return docDao.searchDocByKeywork(keyword, pageSize,currentPage);
    }

    @Override
    public List<Doc> findAll() {
        return docDao.findAll();
    }

    @Override
    @Cacheable("docCache")
    public Doc findById(Long id) {
        return docDao.findById(id);
    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByUpdateOfTopX(Integer top) {
        return docDao.findByUpdateOfTopX(top);
    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByOpenNumberOfTopX(Integer top) {
        return docDao.findByOpenNumberOfTopX(top);
    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByFavorNumberOfTopX(Integer top) {
        return docDao.findByFavorNumberOfTopX(top);
    }

    @Override
    public List<Doc> findByClassifyNodeId(Long classifyNodeId) {
        return docDao.findByClassifyNodeId(classifyNodeId);
    }

    @Override
    @Cacheable("docCache")
    public Paging<Doc> findByClassifyNodeIdOfPage(Long classifyNodeId, Integer pageSize,Integer currentPage) {
        return docDao.findByClassifyNodeIdOfPage(classifyNodeId,pageSize,currentPage);
    }

    @Override
    public List<Doc> findByTagId(Long tagId) {
        return docDao.findByTagId(tagId);
    }

    @Override
    @Cacheable("docCache")
    public Paging<Doc> findByTagIdOfPage(Long tagId, Integer pageSize,Integer currentPage) {
        return docDao.findByTagIdOfPage(tagId,pageSize,currentPage);
    }
}
