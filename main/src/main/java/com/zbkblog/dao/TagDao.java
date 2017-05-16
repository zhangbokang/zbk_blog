package com.zbkblog.dao;

import com.zbkblog.entity.Tag;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface TagDao {
    /**
     * 查找所有
     * @return
     */
    List<Tag> findAll();

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Tag findById(Long id);

    /**
     * 根据ID删除
     * @param tag
     */
    void delete(Tag tag);

    /**
     * 保存
     * @param tag
     */
    Long save(Tag tag);

    /**
     * 更新
     * @param tag
     */
    void update(Tag tag);
}
