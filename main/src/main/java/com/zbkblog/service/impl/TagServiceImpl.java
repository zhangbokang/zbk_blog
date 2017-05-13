package com.zbkblog.service.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;

    @Override
    public void save(Tag tag) {
        tagDao.save(tag);
    }

    @Override
    public void update(Tag tag) {
        tagDao.update(tag);
    }

    @Override
    public void deleteById(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return tagDao.findById(id);
    }
}
