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

    /**
     * 查询topX的文章
     * @param top
     * @return
     */
    List<Doc> findByUpdateOfTopX(Integer top);

    /**
     * 查询打开条数topX的文章
     * @param top
     * @return
     */
    List<Doc> findByOpenNumberOfTopX(Integer top);

    /**
     * 查询点赞条数topX的文章
     * @param top
     * @return
     */
    List<Doc> findByFavorNumberOfTopX(Integer top);

    /**
     * 根据分类ID查询文章列表
     * @param classifyId
     * @return
     */
    List<Doc> findByClassifyId(Long classifyId);
}
