package com.zbkblog.dao;


import com.zbkblog.entity.Doc;
import com.zbkblog.utils.Page;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface DocDao {
    /**
     * 查找所有
     * @return
     */
    List<Doc> findAll();

    /**
     * 分页查询
     * @return
     */
    List<Doc> findAllByPage(final Page page);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Doc findById(Long id);

    /**
     * 删除
     * @param doc
     */
    void delete(Doc doc);

    /**
     * 保存
     * @param doc
     */
    void save(Doc doc);

    /**
     * 更新
     * @param doc
     */
    void update(Doc doc);
}
