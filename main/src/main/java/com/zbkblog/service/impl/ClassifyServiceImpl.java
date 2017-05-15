package com.zbkblog.service.impl;

import com.zbkblog.dao.ClassifyDao;
import com.zbkblog.entity.Classify;
import com.zbkblog.service.ClassifyService;
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
    public void save(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis()/1000);
        classifyDao.save(classify);
    }

    @Override
    public void update(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis()/1000);
        classifyDao.update(classify);
    }

    @Override
    public void deleteById(Long id) {
        classifyDao.deleteById(id);
    }

    @Override
    public List<Classify> findAll() {
        return classifyDao.findAll();
    }

    @Override
    public Classify findById(Long id) {
        return classifyDao.findById(id);
    }
}
