package com.zbkblog.service.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Service("tagService")
@Transactional
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;

    @Override
    public Long save(Tag tag) {
        tag.setCreateTime(System.currentTimeMillis());
        return tagDao.save(tag);
    }

    @Override
    public void update(Tag tag) {
        tag.setCreateTime(System.currentTimeMillis());
        tagDao.update(tag);
    }

    @Override
    public Tag delete(Tag tag) {
        tag = findById(tag.getTagId());
        if (null == tag){
            return null;
        }
        tagDao.delete(tag);
        return tag;
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
