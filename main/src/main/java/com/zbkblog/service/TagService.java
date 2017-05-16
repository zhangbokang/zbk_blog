package com.zbkblog.service;

import com.zbkblog.entity.Tag;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface TagService {
    /**
     * 添加一个标签
     * @param tag
     */
    void save(Tag tag);

    /**
     * 修改标签
     * @param tag
     */
    void update(Tag tag);

    /**
     * 根据ID删除标签
     * @param tag
     */
    void delete(Tag tag);

    /**
     * 查找所有标签
     * @return
     */
    List<Tag> findAll();

    /**
     * 根据id查找标签
     * @return
     */
    Tag findById(Long id);
}
