package com.zbkblog.service.impl;

import com.zbkblog.dao.DocDao;
import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.MyBeanUtils;
import com.zbkblog.utils.Paging;
import org.springframework.beans.BeanUtils;
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
        try {
            docDao.save(doc);
            return MyBeanUtils.copyDoc(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加一个节点到一个文档
     *
     * @param docId
     * @param classifyNodeIdList
     */
    @Override
    @CacheEvict(value = {"docCache"},allEntries = true)
    public Doc addClassifyNodesToDoc(Long docId, List<Long> classifyNodeIdList) {
        try {
            Doc doc = docDao.findById(docId);
            docDao.addClassifyNodesToDoc(doc, classifyNodeIdList);
            return MyBeanUtils.copyDoc(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @CacheEvict(value = {"docCache"},allEntries = true)
    public Doc update(Doc doc,Boolean upTime) {
        try {
            if (upTime) {
                doc.setUpdateTime(System.currentTimeMillis());
            }
            Doc tmpDoc = docDao.findById(doc.getDocId());
            BeanUtils.copyProperties(doc,tmpDoc,"classifyNodes");
            docDao.update(tmpDoc);
            return MyBeanUtils.copyDoc(tmpDoc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @CacheEvict(value = {"docCache"},allEntries = true)
    public Boolean delete(Doc doc) {
        try {
            docDao.delete(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Cacheable("docCache")
    public Paging<Doc> findAllByPage(Integer pageSize,Integer currentPage) {
        try {
            return MyBeanUtils.copyPagingOfDocOrClassifyNode(docDao.findAllByPage(pageSize, currentPage));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Paging<Doc> searchDocByKeywork(String keyword,Integer pageSize,Integer currentPage) {
        try {
            return MyBeanUtils.copyPagingOfDocOrClassifyNode(docDao.searchDocByKeywork(keyword, pageSize, currentPage));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findAll() {
        try {
            return MyBeanUtils.copyDocList(docDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Cacheable("docCache")
    public Doc findById(Long id) {
        try {
            return MyBeanUtils.copyDoc(docDao.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByUpdateOfTopX(Integer top) {
        try {
            return MyBeanUtils.copyDocList(docDao.findByUpdateOfTopX(top));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByOpenNumberOfTopX(Integer top) {
        try {
            return MyBeanUtils.copyDocList(docDao.findByOpenNumberOfTopX(top));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByFavorNumberOfTopX(Integer top) {
        try {
            return MyBeanUtils.copyDocList(docDao.findByFavorNumberOfTopX(top));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByClassifyNodeId(Long classifyNodeId) {
        try {
            return MyBeanUtils.copyDocList(docDao.findByClassifyNodeId(classifyNodeId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable("docCache")
    public Paging<Doc> findByClassifyNodeIdOfPage(Long classifyNodeId, Integer pageSize,Integer currentPage) {
        try {
            return MyBeanUtils.copyPagingOfDocOrClassifyNode(docDao.findByClassifyNodeIdOfPage(classifyNodeId,pageSize,currentPage));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable("docCache")
    public List<Doc> findByTagId(Long tagId) {
        try {
            return MyBeanUtils.copyDocList(docDao.findByTagId(tagId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable("docCache")
    public Paging<Doc> findByTagIdOfPage(Long tagId, Integer pageSize,Integer currentPage) {
        return MyBeanUtils.copyPagingOfDocOrClassifyNode(docDao.findByTagIdOfPage(tagId,pageSize,currentPage));
    }
}
