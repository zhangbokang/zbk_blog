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
        try {
            return classifyNodeDao.findAllClassify();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            return classifyNodeDao.findAllClassifyByPage(pageSize, currentPage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新增节点，成功后返回节点ID
     *
     * @param classifyNode
     * @return
     *  成功则返回节点ID，失败返回0
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
    public Boolean deleteClassifyNode(ClassifyNode classifyNode) {
        try{
            classifyNodeDao.deleteClassifyNode(classifyNode);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新节点
     *
     * @param classifyNode
     * @return
     *  成功返回true,失败返回false
     */
    @Override
    public Boolean updateClassifyNode(ClassifyNode classifyNode) {
        classifyNode.setUpdateTime(System.currentTimeMillis());
        try {
            classifyNodeDao.updateClassifyNode(classifyNode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 为一个节点添加子节点
     *
     * @param parentId
     * @param childrenId
     * @return
     *  成功返回true,失败返回false
     */
    @Override
    public Boolean addChildrenNode(Long parentId, Long childrenId) {
        try {
            classifyNodeDao.addChildrenNode(parentId, childrenId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
