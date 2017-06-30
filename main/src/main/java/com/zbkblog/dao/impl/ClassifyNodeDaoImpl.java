package com.zbkblog.dao.impl;

import com.zbkblog.dao.ClassifyNodeDao;
import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.utils.Paging;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbokang on 2017/6/26.
 */
@Repository("classifyNodeDao")
public class ClassifyNodeDaoImpl implements ClassifyNodeDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    /**
     * 根据ID精确查找
     *
     * @param id
     * @return
     */
    @Override
    public ClassifyNode findClassifyNodeById(Long id) {
        return hibernateTemplate.load(ClassifyNode.class,id);
    }

    @Override
    public List<ClassifyNode> findClassifyNodeByDocId(Long docId) {
        String sql = "SELECT * FROM classify_node AS cn JOIN classify_node_doc_map AS cnmap ON cn.id = cnmap.id WHERE cnmap.doc_id = :docId  order by cn.update_time desc";
        return hibernateTemplate.execute(new HibernateCallback<List<ClassifyNode>>() {
            @Override
            public List<ClassifyNode> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql).addEntity(ClassifyNode.class);
                query.setParameter("docId", docId);
                return query.list();
            }
        });
    }

    /**
     * 根据父ID查找列表，id为空则查找root列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<ClassifyNode> findClassifyNodeListByParentId(Long parentId) {
        String sqlWhere = " parent_id is null";
        if (parentId != null && parentId != 0L){
            sqlWhere = " parent_id=" + parentId;
        }

        String sql = "SELECT id ,text,children_byte,parent_id,update_time FROM classify_node WHERE"+sqlWhere;
        return hibernateTemplate.execute(new HibernateCallback<List<ClassifyNode>>() {
            @Override
            public List<ClassifyNode> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql).addEntity(ClassifyNode.class);
                return query.list();
            }
        });
    }

    /**
     * 查找所有分类节点
     *
     * @return
     */
    @Override
    public List<ClassifyNode> findAllClassifyNode() {
        String hql = "from ClassifyNode ";
        return hibernateTemplate.execute(new HibernateCallback<List<ClassifyNode>>() {
            @Override
            public List<ClassifyNode> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                return query.list();
            }
        });
    }

    /**
     * 分页查找所有分类节点
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public Paging<ClassifyNode> findAllClassifyNodeByPage(Integer pageSize, Integer currentPage) {
        String hql = "from ClassifyNode ";
        Paging<ClassifyNode> classifyNodePaging = new Paging<>();
        classifyNodePaging.setPageSize(pageSize);
        classifyNodePaging.setCurrentPage(currentPage);
        List<ClassifyNode> classifyNodeList = hibernateTemplate.execute(new HibernateCallback<List<ClassifyNode>>() {
            @Override
            public List<ClassifyNode> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                Integer t = Integer.parseInt(query.uniqueResult().toString());
                classifyNodePaging.setTotalCounts(t);
                query.setFirstResult(classifyNodePaging.getFirstResult());
                query.setFetchSize(classifyNodePaging.getPageSize());
                return query.list();
            }
        });
        classifyNodePaging.setPageList(classifyNodeList);
        return classifyNodePaging;
    }

    /**
     * 新增节点，成功后返回节点ID
     *
     * @param classifyNode
     * @return
     */
    @Override
    public Long saveClassifyNode(ClassifyNode classifyNode) {
        return (Long) hibernateTemplate.save(classifyNode);
    }

    /**
     * 删除节点
     *   如果有子节点，将子节点设置为root节点（删除父节点），然后删除本节点
     *
     * @param classifyNode
     */
    @Override
    public void deleteClassifyNode(ClassifyNode classifyNode) {
        if (classifyNode.getChildren()){
            hibernateTemplate.execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    String updateParentHql = "update ClassifyNode set parentId=null where parentId=:id";
                    Query query = session.createQuery(updateParentHql);
                    query.setParameter("id", classifyNode.getId());
                    return query.executeUpdate();
                }
            });
        }
        Long parentId = classifyNode.getParentId();
        hibernateTemplate.delete(classifyNode);
        Integer i = this.countChildrenNumber(parentId);
        if (i==1){
            ClassifyNode classifyNode1 =  hibernateTemplate.load(ClassifyNode.class, parentId);
            classifyNode1.setChildrenByte(null);
        }
    }

    /**
     * 更新节点
     *
     * @param classifyNode
     */
    @Override
    public void updateClassifyNode(ClassifyNode classifyNode) {
        hibernateTemplate.update(classifyNode);
    }

    /**
     * 为一个节点添加子节点
     *
     * @param parentId 父节点ID，要添加到根节点传null
     * @param childrenId 子节点ID
     */
    @Override
    public void addChildrenNode(Long parentId, Long childrenId) {
        if (null != parentId) {
            ClassifyNode parentClassifyNode = hibernateTemplate.load(ClassifyNode.class, parentId);
            //设置父节点
            //添加有子节点的标识
            parentClassifyNode.setChildrenByte(Byte.parseByte("1"));
            //保存
            hibernateTemplate.update(parentClassifyNode);
        }

        ClassifyNode childrenClassifyNode = hibernateTemplate.load(ClassifyNode.class,childrenId);
        //如果子节点原来的父节点只有它一个子节点，那么就标记为已没有子节点了
        Integer i = this.countChildrenNumber(childrenClassifyNode.getParentId());
        if (i==1){
            ClassifyNode classifyNode1 =  hibernateTemplate.load(ClassifyNode.class, childrenClassifyNode.getParentId());
            classifyNode1.setChildrenByte(null);
        }

        //设置子节点
        //添加子节点的父节点ID
        childrenClassifyNode.setParentId(parentId);
        //保存
        hibernateTemplate.update(childrenClassifyNode);
    }

    /**
     * 根据父节点ID查询它的子节点数量
     *
     * @param parentId
     * @return
     */
    @Override
    public Integer countChildrenNumber(Long parentId) {
        return hibernateTemplate.execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                String sql = "SELECT COUNT(1) FROM `classify_node` WHERE parent_id=:parentId";
                Query query = session.createSQLQuery(sql);
                query.setParameter("parentId", parentId);
                return ((Number)query.uniqueResult()).intValue();
            }
        });
    }

}
