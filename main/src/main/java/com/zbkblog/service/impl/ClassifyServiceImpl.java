package com.zbkblog.service.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
import com.zbkblog.service.ClassifyService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Service("classifyService")
@Transactional
public class ClassifyServiceImpl implements ClassifyService {
    @Resource
    private ClassifyDao classifyDao;

    @Override
    @CacheEvict(value = {"classifyCache","docCache"},allEntries = true)
    public Classify save(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis());
        classifyDao.save(classify);
        return classify;
    }

    @Override
    @CacheEvict(value = {"classifyCache","docCache"},allEntries = true)
    public Classify update(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis());
        classifyDao.update(classify);
        return classify;
    }

    @Override
    @CacheEvict(value = {"classifyCache","docCache"},allEntries = true)
    public void delete(Classify classify) {
        classifyDao.delete(classify);
    }

    @Override
    @Cacheable("classifyCache")
    public List<Classify> findAllClassify() {
        return classifyDao.findAll();
    }

    @Override
    @Cacheable("classifyCache")
    public Classify findClassifyById(Long id) {
        return classifyDao.findById(id);
    }
}
