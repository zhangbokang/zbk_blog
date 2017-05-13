package com.zbkblog.dao;

import com.zbkblog.entity.Classify;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface ClassifyDao {
    /**
     * 查找所有
     * @return
     */
    List<Classify> findAll();

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Classify findById(Long id);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 保存
     * @param classify
     */
    void save(Classify classify);

    /**
     * 更新
     * @param classify
     */
    void update(Classify classify);
}
