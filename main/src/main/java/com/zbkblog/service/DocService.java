package com.zbkblog.service;

import com.zbkblog.entity.Doc;
import com.zbkblog.utils.Paging;

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
     * 添加一个节点到一个文档
     * @param docId
     * @param classifyNodeId
     * @return
     */
    Doc addClassifyNodesToDoc(Long docId, List<Long> classifyNodeId);

    /**
     * 分页查询所有
     * @return
     */
    Paging<Doc> findAllByPage(Integer pageSize,Integer currentPage);

    /**
     * 跟据关键字查询
     * @param keyword
     * @param pageSize
     * @param currentPage
     * @return
     */
    Paging<Doc> searchDocByKeywork(String keyword,Integer pageSize,Integer currentPage);

    /**
     * 修改
     * @param doc
     * @param upTime 是否更新修改时间
     * @return
     */
    Doc update(Doc doc,Boolean upTime);

    /**
     * 根据ID删除
     * @param doc
     * @return
     */
    Boolean delete(Doc doc);

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
