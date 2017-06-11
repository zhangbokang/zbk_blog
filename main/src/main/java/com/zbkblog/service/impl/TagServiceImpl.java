package com.zbkblog.service.impl;

import com.zbkblog.dao.TagDao;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.TagService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = {"tagCache","docCache"},allEntries = true)
    public Tag save(Tag tag) {
        tag.setCreateTime(System.currentTimeMillis());
        tagDao.save(tag);
        return tag;
    }

    @Override
    @CacheEvict(value = {"tagCache","docCache"},allEntries = true)
    public void update(Tag tag) {
        tag.setCreateTime(System.currentTimeMillis());
        tagDao.update(tag);
    }

    @Override
    @CacheEvict(value = {"tagCache","docCache"},allEntries = true)
    public void delete(Tag tag) {
        tagDao.delete(tag);
    }

    @Override
    @Cacheable(value = "tagCache",key = "#root.target.class.toString()+#root.method.name")
    public List<Tag> findAllTag() {
        return tagDao.findAll();
    }

    @Override
    @Cacheable("tagCache")
    public Tag findTagById(Long id) {
        return tagDao.findById(id);
    }
}
