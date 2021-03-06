package com.zbkblog.service.impl;

import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.service.ClassifyNodeService;
import com.zbkblog.utils.MyBeanUtils;
import com.zbkblog.utils.Paging;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("classifyNodeCache")
    public ClassifyNode findClassifyNodeById(Long id) {
        try {
            return MyBeanUtils.copyClassifyNode(classifyNodeDao.findClassifyNodeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据docId查询分类节点列表
     *
     * @param docId
     * @return
     */
    @Override
    @Cacheable("classifyNodeCache")
    public List<ClassifyNode> findClassifyNodeByDocId(Long docId) {
        try {
            return MyBeanUtils.copyClassifyNodeList(classifyNodeDao.findClassifyNodeByDocId(docId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据父ID查找列表，id为空则查找root列表
     *
     * @param parentId
     * @return
     */
    @Override
    @Cacheable("classifyNodeCache")
    public List<ClassifyNode> findClassifyNodeListByParentId(Long parentId) {
        try {
            List<ClassifyNode> classifyNodeList = MyBeanUtils.copyClassifyNodeList(classifyNodeDao.findClassifyNodeListByParentId(parentId));
            for (ClassifyNode classifyNode : classifyNodeList) {
                classifyNode.setDocs(null);
            }
            return classifyNodeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查找所有分类节点
     *
     * @return
     */
    @Override
    @Cacheable("classifyNodeCache")
    public List<ClassifyNode> findAllClassifyNode() {
        try {
            return MyBeanUtils.copyClassifyNodeList(classifyNodeDao.findAllClassifyNode());
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
    @Cacheable("classifyNodeCache")
    public Paging<ClassifyNode> findAllClassifyNodeByPage(Integer pageSize, Integer currentPage) {
        try {
            return MyBeanUtils.copyPagingOfDocOrClassifyNode(classifyNodeDao.findAllClassifyNodeByPage(pageSize, currentPage));
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
    @CacheEvict(value = {"classifyNodeCache","docCache"},allEntries = true)
    public Long saveClassifyNode(ClassifyNode classifyNode) {
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
     * @param classifyNodeId
     * @return
     */
    @Override
    @CacheEvict(value = {"classifyNodeCache","docCache"},allEntries = true)
    public ClassifyNode deleteClassifyNode(Long classifyNodeId) {
        try{
            ClassifyNode classifyNode = classifyNodeDao.findClassifyNodeById(classifyNodeId);
            classifyNodeDao.deleteClassifyNode(classifyNode);
            return classifyNode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
    @CacheEvict(value = {"classifyNodeCache","docCache"},allEntries = true)
    public ClassifyNode updateClassifyNode(ClassifyNode classifyNode) {
        classifyNode.setUpdateTime(System.currentTimeMillis());
        try {
            ClassifyNode tmpClassifyNode = classifyNodeDao.findClassifyNodeById(classifyNode.getId());
            BeanUtils.copyProperties(classifyNode,tmpClassifyNode,"docs");
            classifyNodeDao.updateClassifyNode(tmpClassifyNode);
            return MyBeanUtils.copyClassifyNode(tmpClassifyNode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    @CacheEvict(value = {"classifyNodeCache","docCache"},allEntries = true)
    public ClassifyNode addChildrenNode(Long parentId, Long childrenId, String childrenText) {
        try {
            ClassifyNode parentClassifyNode = null;
            if (null != parentId && parentId != 0L) {
                parentClassifyNode = classifyNodeDao.findClassifyNodeById(parentId);
            }
            if (null != childrenId && childrenId != 0L) {
                ClassifyNode childrenClassifyNode = classifyNodeDao.findClassifyNodeById(childrenId);
                classifyNodeDao.addChildrenNode(parentClassifyNode, childrenClassifyNode);
                return MyBeanUtils.copyClassifyNode(childrenClassifyNode);
            }
            if (null == childrenText || "".equals(childrenText)){
                return null;
            }
            ClassifyNode classifyNode = new ClassifyNode();
            classifyNode.setText(childrenText);
            //先保存子节点并获取节点ID
            Long classifyNodeId = classifyNodeDao.saveClassifyNode(classifyNode);
            if (null == classifyNodeId || classifyNodeId==0L){
                return null;
            }
            classifyNodeDao.addChildrenNode(parentClassifyNode, classifyNode);
            return MyBeanUtils.copyClassifyNode(classifyNode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
