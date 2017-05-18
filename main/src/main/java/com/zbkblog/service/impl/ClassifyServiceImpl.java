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
        classify.setCreateTime(System.currentTimeMillis());
        return classifyDao.save(classify);
    }

    @Override
    public Classify update(Classify classify) {
        classify.setCreateTime(System.currentTimeMillis());
        classifyDao.update(classify);
        return classify;
    }

    @Override
    public Classify delete(Classify classify) {
        classify = findById(classify.getClassifyId());
        if (null == classify){
            return null;
        }
        classifyDao.delete(classify);
        return classify;
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
