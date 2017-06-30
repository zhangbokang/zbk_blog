package com.zbkblog.service;

import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.utils.Paging;

import java.util.List;

/**
 * Created by zhangbokang on 2017/6/26.
 */
public interface ClassifyNodeService {
    /**
     * 根据ID精确查找
     * @param id
     * @return
     */
    ClassifyNode findClassifyNodeById(Long id);

    /**
     * 根据docId查询分类节点列表
     * @param docId
     * @return
     */
    List<ClassifyNode> findClassifyNodeByDocId(Long docId);

    /**
     * 根据父ID查找列表，id为空则查找root列表
     * @param parentId
     * @return
     */
    List<ClassifyNode> findClassifyNodeListByParentId(Long parentId);

    /**
     * 查找所有分类节点
     * @return
     */
    List<ClassifyNode> findAllClassifyNode();

    /**
     * 分页查找所有分类节点
     * @param pageSize
     * @param currentPage
     * @return
     */
    Paging<ClassifyNode> findAllClassifyNodeByPage(Integer pageSize, Integer currentPage);

    /**
     * 新增节点，成功后返回节点ID
     * @param classifyNode
     * @return
     */
    Long saveClassifyNode(ClassifyNode classifyNode);

    /**
     * 删除节点
     *   如果有子节点，将子节点设置为root节点（删除父节点），然后删除本节点
     *
     * @param classifyNodeId
     * @return
     *  成功返回true，失败返回false
     */
    Boolean deleteClassifyNode(Long classifyNodeId);

    /**
     * 更新节点
     * @param classifyNode
     * @return
     *  成功就返回更新后的对象，失败返回null
     */
    ClassifyNode updateClassifyNode(ClassifyNode classifyNode);

    /**
     * 为一个节点添加子节点
     * @param parentId
     * @param childrenId
     */
    ClassifyNode addChildrenNode(Long parentId, Long childrenId, String childrenText);
}
