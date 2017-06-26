package com.zbkblog.service.impl;

import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.service.ClassifyNodeService;
import com.zbkblog.utils.Paging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/6/26.
 */
@Service("classifyNodeService")
public class ClassifyNodeServiceImpl implements ClassifyNodeService {
    @Resource
    private ClassifyNodeDao classifyNodeDao;

    /**
     * 根据ID精确查找
     *
     * @param id
     * @return
     */
    @Override
    public ClassifyNode findClassifyNodeById(Long id) {
        return classifyNodeDao.findClassifyNodeById(id);
    }

    /**
     * 根据父ID查找列表，id为空则查找root列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<ClassifyNode> findClassifyNodeListByParentId(Long parentId) {
        return classifyNodeDao.findClassifyNodeListByParentId(parentId);
    }

    /**
     * 查找所有分类节点
     *
     * @return
     */
    @Override
    public List<ClassifyNode> findAllClassify() {
        return classifyNodeDao.findAllClassify();
    }

    /**
     * 分页查找所有分类节点
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public Paging<ClassifyNode> findAllClassifyByPage(Integer pageSize, Integer currentPage) {
        return classifyNodeDao.findAllClassifyByPage(pageSize, currentPage);
    }

    /**
     * 新增节点，成功后返回节点ID
     *
     * @param classifyNode
     * @return
     */
    @Override
    public Long saveClassifyNode(ClassifyNode classifyNode) {
        classifyNode.setUpdateTime(System.currentTimeMillis());
        try {
            return classifyNodeDao.saveClassifyNode(classifyNode);
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 删除节点
     *   如果有子节点，将子节点设置为root节点（删除父节点），然后删除本节点
     *
     * @param classifyNode
     */
    @Override
    public void deleteClassifyNode(ClassifyNode classifyNode) {
        classifyNodeDao.deleteClassifyNode(classifyNode);
    }

    /**
     * 更新节点
     *
     * @param classifyNode
     */
    @Override
    public void updateClassifyNode(ClassifyNode classifyNode) {
        classifyNode.setUpdateTime(System.currentTimeMillis());
        classifyNodeDao.updateClassifyNode(classifyNode);
    }

    /**
     * 为一个节点添加子节点
     *
     * @param parentId
     * @param childrenId
     */
    @Override
    public void addChildrenNode(Long parentId, Long childrenId) {
        classifyNodeDao.addChildrenNode(parentId, childrenId);
    }
}
