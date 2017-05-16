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
    public Long save(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis()/1000);
        return classifyDao.save(classify);
    }

    @Override
    public Classify update(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis()/1000);
        classifyDao.update(classify);
        return classify;
    }

    @Override
    public void delete(Classify classify) {
        classifyDao.delete(classify);
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
