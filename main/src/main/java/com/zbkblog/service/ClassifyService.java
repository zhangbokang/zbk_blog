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
    Classify save(Classify classify);

    /**
     * 修改标签
     * @param classify
     */
    Classify update(Classify classify);

    /**
     * 根据ID删除标签
     * @param classify
     */
    void delete(Classify classify);

    /**
     * 查找所有标签
     * @return
     */
    List<Classify> findAllClassify();

    /**
     * 根据id查找标签
     * @return
     */
    Classify findClassifyById(Long id);
}
