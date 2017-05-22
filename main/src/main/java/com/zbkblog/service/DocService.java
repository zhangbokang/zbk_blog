package com.zbkblog.service;

import com.zbkblog.entity.Doc;
import com.zbkblog.utils.Page;

import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
public interface DocService {
    /**
     * 添加一个
     * @param doc
     */
    Doc save(Doc doc);

    /**
     * 分页查询所有
     * @return
     */
    List<Doc> findAllByPage(Page page);

    /**
     * 修改
     * @param doc
     */
    Doc update(Doc doc);

    /**
     * 根据ID删除
     * @param doc
     */
    void delete(Doc doc);

    /**
     * 查找所有
     * @return
     */
    List<Doc> findAll();

    /**
     * 根据id查找
     * @return
     */
    Doc findById(Long id);

    /**
     * 查询更新时间topX的文章
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
