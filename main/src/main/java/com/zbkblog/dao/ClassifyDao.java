package com.zbkblog.dao;

import com.zbkblog.entity.Classify;
import com.zbkblog.utils.Paging;

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
     * 分页查询所有
     * @param pageSize
     * @param currentPage
     * @return
     */
    Paging<Classify> findAllByPage(Integer pageSize,Integer currentPage);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Classify findById(Long id);

    /**
     * 删除
     * @param classify
     */
    void delete(Classify classify);

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
