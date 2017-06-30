package com.zbkblog.utils;

import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.entity.Doc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangbokang on 2017/5/17.
 */
public class MyBeanUtils {
    /**
     * @Description <p>获取到对象中属性为null的属性名  </P>
     * @param source 要拷贝的对象
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * @Description <p> 拷贝非空对象属性值 </P>
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 将Doc中的classifyNodes中的docs置空
     * @param doc
     * @return
     */
    public static Doc copyDoc(Doc doc) {
        Doc tmpDoc = new Doc();
        BeanUtils.copyProperties(doc,tmpDoc,"classifyNodes");
        Set<ClassifyNode> classifyNodes = doc.getClassifyNodes();
        Set<ClassifyNode> tmpClassifyNodes = new HashSet<>();
        for (ClassifyNode classifyNode : classifyNodes) {
            ClassifyNode tmpClassifyNode = new ClassifyNode();
            BeanUtils.copyProperties(classifyNode,tmpClassifyNode,"docs");
            tmpClassifyNodes.add(tmpClassifyNode);
        }
        tmpDoc.setClassifyNodes(tmpClassifyNodes);
        return tmpDoc;
    }

    /**
     * 将classifyNode中的docs中的classifyNodes置空
     * @param classifyNode
     * @return
     */
    public static ClassifyNode copyClassifyNode(ClassifyNode classifyNode) {
        ClassifyNode tmpClassifyNode = new ClassifyNode();
        BeanUtils.copyProperties(classifyNode,tmpClassifyNode,"docs");
        Set<Doc> docs = classifyNode.getDocs();
        Set<Doc> tmpDocs = new HashSet<>();
        for (Doc doc : docs) {
            Doc tmpDoc = new Doc();
            BeanUtils.copyProperties(doc,tmpDoc,"classifyNodes");
            tmpDocs.add(tmpDoc);
        }
        tmpClassifyNode.setDocs(tmpDocs);
        return tmpClassifyNode;
    }

    /**
     * 将doc中的classifyNodes替换为docs为null的classifyNodes，防止无限循环导致堆栈溢出
     * @param docs
     * @return
     */
    public static List<Doc> copyDocList(List<Doc> docs) {
        List<Doc> docList = new ArrayList<>();
        for (Doc doc : docs) {
            Doc tmpDoc = new Doc();
            BeanUtils.copyProperties(doc,tmpDoc,"classifyNodes");
            Set<ClassifyNode> classifyNodes = doc.getClassifyNodes();
            Set<ClassifyNode> tmpClassifyNodes = new HashSet<>();
            for (ClassifyNode classifyNode : classifyNodes) {
                ClassifyNode tmpClassifyNode = new ClassifyNode();
                BeanUtils.copyProperties(classifyNode,tmpClassifyNode,"docs");
                tmpClassifyNodes.add(tmpClassifyNode);
            }
            tmpDoc.setClassifyNodes(tmpClassifyNodes);
            docList.add(tmpDoc);
        }
        return docList;
    }

    /**
     * 将classifyNode中的docs替换为classifyNodes为null的classifyNode，防止无限循环导致堆栈溢出
     * @param classifyNodes
     * @return
     */
    public static List<ClassifyNode> copyClassifyNodeList(List<ClassifyNode> classifyNodes) {
        List<ClassifyNode> classifyNodeList = new ArrayList<>();
        for (ClassifyNode classifyNode : classifyNodes) {
            ClassifyNode tmpClassifyNode = new ClassifyNode();
            BeanUtils.copyProperties(classifyNode,tmpClassifyNode,"docs");
            Set<Doc> docs = classifyNode.getDocs();
            Set<Doc> tmpDocs = new HashSet<>();
            for (Doc doc : docs) {
                Doc tmpDoc = new Doc();
                BeanUtils.copyProperties(doc,tmpDoc,"classifyNodes");
                tmpDocs.add(tmpDoc);
            }
            tmpClassifyNode.setDocs(tmpDocs);
            classifyNodeList.add(tmpClassifyNode);
        }
        return classifyNodeList;
    }

    /**
     * 将paging中的列表中的元素替换，避免无线嵌套
     * @param paging
     * @return
     */
    public static Paging copyPagingOfDocOrClassifyNode(Paging paging) {
        List pageList = paging.getPageList();
        if (null == pageList || pageList.size() == 0) {
            return paging;
        }
        Class clazz = pageList.get(0).getClass();
        if (clazz == Doc.class) {
            System.out.println("doc.class:"+Doc.class.toString());
            paging.setPageList(MyBeanUtils.copyDocList(paging.getPageList()));
            return paging;
        } else if (clazz == ClassifyNode.class) {
            System.out.println("classifyNode.class:"+ClassifyNode.class.toString());
            paging.setPageList(MyBeanUtils.copyClassifyNodeList(paging.getPageList()));
            return paging;
        }
        return paging;
    }
}
