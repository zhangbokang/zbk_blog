package com.zbkblog.controller;

import com.zbkblog.entity.ClassifyNode;
import com.zbkblog.service.ClassifyNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbokang on 2017/6/26.
 */
@Controller
@RequestMapping("/classifyNode")
public class ClassifyNodeController {
    @Resource
    private ClassifyNodeService classifyNodeService;

    /**
     * 新增分类节点
     *
     * @param request
     * @return 失败 {code:0,msg:失败消息}
     * 成功{code:1,data:保存成功后的classifyNode对象}
     */
    @RequestMapping("/addClassifyNode")
    @ResponseBody
    public Map<String, Object> addClassifyNode(HttpServletRequest request) {
        String text = request.getParameter("classifyNodeText");
        Map<String, Object> map = new HashMap<>();
        if (null == text || "".equals(text)) {
            map.put("code", 0);
            map.put("msg", "分类节点名称不能为空！");
            return map;
        }
        ClassifyNode classifyNode = new ClassifyNode();
        classifyNode.setText(text);
        Long id = classifyNodeService.saveClassifyNode(classifyNode);
        if (0L == id) {
            map.put("code", 0);
            map.put("msg", "保存失败！");
            return map;
        }
        map.put("code", 1);
        map.put("data", classifyNode);
        return map;
    }

    /**
     * 更新分类节点信息
     * 只更新text属性
     *
     * @param request
     * @return 失败 {code:0,msg:失败消息}
     * 成功{code:1,data:保存成功后的classifyNode对象}
     */
    @RequestMapping("/updateClassifyNode")
    @ResponseBody
    public Map<String, Object> updateClassifyNode(HttpServletRequest request) {
        String id = request.getParameter("classifyNodeId");
        String text = request.getParameter("classifyNodeText");
        Map<String, Object> map = new HashMap<>();
        if (null == id || "".equals(id) || !id.matches("[0-9]+")) {
            map.put("code", 0);
            map.put("msg", "分类ID不能为空且必须是数字！");
            return map;
        }
        if (null == text || "".equals(text)) {
            map.put("code", 0);
            map.put("msg", "分类名称不能为空！");
            return map;
        }
        ClassifyNode classifyNode = classifyNodeService.findClassifyNodeById(Long.parseLong(id));
        if (null == classifyNode) {
            map.put("code", 0);
            map.put("map", "未查询到该分类节点！");
            return map;
        }
        classifyNode.setId(Long.parseLong(id));
        classifyNode.setText(text);
        classifyNodeService.updateClassifyNode(classifyNode);
        map.put("code", 1);
        map.put("data", classifyNode);
        return map;
    }

    /**
     * 删除分类节点
     *
     * @param request
     *  deleteOk用来指定强制删除（对于有子节点的）
     * @return
     * 失败 {code:0,msg:失败消息}
     * 成功{code:1,data:保存成功后的classifyNode对象}
     */
    @RequestMapping("/deleteClassifyNode")
    @ResponseBody
    public Map<String, Object> deleteClassifyNode(HttpServletRequest request) {
        String id = request.getParameter("classifyNodeId");
        //是否强制删除（删除后如果有子节点，所有子节点成为root节点）
        String deleteOk = request.getParameter("deleteOk");
        Map<String, Object> map = new HashMap<>();
        if (null == id || !id.matches("[0-9]+")) {
            map.put("code", 0);
            map.put("msg", "分类ID不能为空且必须是数字！");
            return map;
        }
        ClassifyNode classifyNode = classifyNodeService.findClassifyNodeById(Long.parseLong(id));
        if (null == classifyNode) {
            map.put("code", 0);
            map.put("msg", "未查询到该分类！");
            return map;
        }
        //如果没有子节点就直接删除
        if (null == classifyNode.getChildren() || !classifyNode.getChildren()) {
            classifyNodeService.deleteClassifyNode(classifyNode);
            map.put("code", 1);
            map.put("data", classifyNode);
            return map;
        }
        //如果有子节点，但没有确认强制删除，则不执行删除
        if (null == deleteOk || "".equals(deleteOk) || "false".equals(deleteOk)) {
            map.put("code", 1);
            map.put("data", "no_deleteOk");
            return map;
        }
        if (Boolean.parseBoolean(deleteOk)) {
            classifyNodeService.deleteClassifyNode(classifyNode);
            map.put("code", 1);
            map.put("data", classifyNode);
            return map;
        }
        map.put("code", 0);
        map.put("msg", "未执行删除！");
        return map;
    }

    /**
     * 添加节点到另一个节点
     *
     * @param request
     * @return
     * 失败 {code:0,msg:失败消息}
     * 成功{code:1,data:保存成功后的classifyNode对象}
     */
    @RequestMapping("/appendToParentClassifyNode")
    @ResponseBody
    public Map<String, Object> appendToParentClassifyNode(HttpServletRequest request) {
        String id = request.getParameter("classifyNodeId");
        String text = request.getParameter("classifyNodeText");
        String parentId = request.getParameter("parentClassifyNodeId");
        Map<String, Object> map = new HashMap<>();
        //验证传参
        if (null == parentId || "".equals(parentId) || (!parentId.matches("[0-9]+") && !"#".equals(parentId))) {
            map.put("code", 0);
            map.put("msg", "父分类ID不能为空且必须是数字或“#”！");
            return map;
        }
        if ((null == id || !id.matches("[0-9]+")) && (null == text || "".equals(text))) {
            map.put("code", 0);
            map.put("msg", "无子分类节点！");
            return map;
        }
        //处理父分类Id
        Long parentIdLong = null;
        if (!"#".equals(parentId)) {
            parentIdLong = Long.parseLong(parentId);
        }
        //处理子分类ID
        Long idLong = null;
        if (id.matches("[0-9]+")) {
            idLong = Long.parseLong(id);
        }
        //保存关系
        ClassifyNode childrenClassifyNode = classifyNodeService.addChildrenNode(parentIdLong, idLong, text);
        if (null == childrenClassifyNode) {
            map.put("code", 0);
            map.put("msg", "添加子节点失败！");
            return map;
        }
        map.put("code", 1);
        map.put("data", "{'parentId':" + parentId + ",'childrenId':" + childrenClassifyNode.getId() + "}");
        return map;
    }

    /**
     * 查询所有分类节点
     * @param request
     * @return
     */
    @RequestMapping("/findAllClassifyNode")
    @ResponseBody
    public Map<String,Object> findAllClassifyNode(HttpServletRequest request) {
        List<ClassifyNode> classifyNodeList = classifyNodeService.findAllClassifyNode();
        Map<String, Object> map = new HashMap<>();
        if (null == classifyNodeList) {
            map.put("code", 0);
            map.put("msg", "查询失败");
            return map;
        }
        map.put("code", 1);
        map.put("data", classifyNodeList);
        return map;
    }

}
