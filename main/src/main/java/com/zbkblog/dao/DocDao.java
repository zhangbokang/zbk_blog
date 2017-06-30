package com.zbkblog.dao;


import com.zbkblog.entity.Doc;
import com.zbkblog.utils.Paging;

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
    Paging<Doc> findAllByPage(Integer pageSize,Integer currentPage);

    /**
     * 跟据关键字查询
     * @param keyword
     * @param  pageSize
     * @param currentPage
     * @return
     */
    Paging<Doc> searchDocByKeywork(String keyword,Integer pageSize,Integer currentPage);

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
     * 添加一个节点到一个文档
     * @param docId
     * @param classifyNodeId
     */
    void addClassifyNodeToDoc(Long docId, Long classifyNodeId);

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
     * @param classifyNodeId
     * @return
     */
    List<Doc> findByClassifyNodeId(Long classifyNodeId);

    /**
     * 根据分类ID查询并分页
     * @param classifyNodeId
     * @param pageSize
     * @param currentPage
     * @return
     */
    Paging<Doc> findByClassifyNodeIdOfPage(Long classifyNodeId,Integer pageSize,Integer currentPage);

    /**
     * 根据标签ID查询文章列表
     * @param tagId
     * @return
     */
    List<Doc> findByTagId(Long tagId);

    /**
     * 根据标签ID分页查询
     * @param tagId
     * @param pageSize
     * @param currentPage
     * @return
     */
    Paging<Doc> findByTagIdOfPage(Long tagId,Integer pageSize,Integer currentPage);
}
