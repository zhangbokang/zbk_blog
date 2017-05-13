package com.zbkblog.service;

import com.zbkblog.entity.Classify;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface ClassifyService {
    /**
     * 添加一个标签
     * @param classify
     */
    void save(Classify classify);

    /**
     * 修改标签
     * @param classify
     */
    void update(Classify classify);

    /**
     * 根据ID删除标签
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查找所有标签
     * @return
     */
    List<Classify> findAll();

    /**
     * 根据id查找标签
     * @return
     */
    Classify findById(Long id);
}
