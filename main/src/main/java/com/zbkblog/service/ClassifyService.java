package com.zbkblog.service;

import com.zbkblog.entity.Classify;
import com.zbkblog.utils.Paging;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface ClassifyService {
    /**
     * 添加一个分类
     * @param classify
     */
    Classify save(Classify classify);

    /**
     * 修改分类
     * @param classify
     */
    Classify update(Classify classify);

    /**
     * 根据ID删除分类
     * @param classify
     */
    void delete(Classify classify);

    /**
     * 查找所有分类
     * @return
     */
    List<Classify> findAllClassify();

    /**
     * 分页查找所有分类
     * @param pageSize
     * @param currentPage
     * @return
     */
    Paging<Classify> findAllClassifyByPage(Integer pageSize, Integer currentPage);

    /**
     * 根据id查找分类
     * @return
     */
    Classify findClassifyById(Long id);
}
