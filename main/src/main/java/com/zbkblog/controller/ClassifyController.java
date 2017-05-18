package com.zbkblog.controller;

import com.zbkblog.entity.Classify;
import com.zbkblog.service.ClassifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbokang on 2017/5/15.
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController {
    @Resource
    private ClassifyService classifyService;

    /**
     * 查询所有分类信息
     *  查询失败：{"code":1,"data":List<Classify>}
     *  查询成功：{"code":0,"msg":"查询出现错误"}
     * @param request
     *  无
     * @return
     */
    @RequestMapping("/findAllClassify")
    @ResponseBody
    public Map<String ,Object> findAllClassify(HttpServletRequest request){
        List<Classify> list = classifyService.findAll();
        Map<String ,Object> map = new HashMap<>();
        if (null == list){
            map.put("code",0);
            map.put("msg","查询出现错误");
            return map;
        }
        map.put("code",1);
        map.put("data",list);
        return map;
    }

    /**
     * 添加分类
     *  添加成功：{"code":1,"data":classifyId}
     *  添加失败：{"code":0,"msg":"分类名称不能为空"}
     * @param request
     *  分类名称：classifyName
     * @return
     */
    @RequestMapping("/addClassify")
    @ResponseBody
    public Map<String,Object> addClassify(HttpServletRequest request){
        String classifyName = request.getParameter("classifyName");
        Map<String ,Object> map = new HashMap<>();
        if (null == classifyName){
            map.put("code",0);
            map.put("msg","分类名称不能为空");
            return map;
        }
        Classify classify = new Classify();
        classify.setName(classifyName);
        Long classifyId = classifyService.save(classify);
        map.put("code",1);
        map.put("data",classifyId);
        return map;
    }

    /**
     * 更新分类信息
     *  更新失败：{"code":0,"msg",失败信息提示信息}
     *  更新成功：{"code":1,data",更新后的classify}
     * @param request
     *  分类ID：classifyId
     *  分类名称：classifyName
     * @return
     */
    @RequestMapping("/updateClassify")
    @ResponseBody
    public Map<String,Object> updateClassify(HttpServletRequest request){
        String classifyId = request.getParameter("classifyId");
        String classifyName = request.getParameter("classifyName");
        Map<String ,Object> map = new HashMap<>();
        if (null == classifyName){
            map.put("code",0);
            map.put("msg","分类名称不能为空");
            return map;
        }
        if (null == classifyId || !classifyId.matches("[0-9]{13}")){
            map.put("code",0);
            map.put("msg","分类ID不能为空，且必须是数字");
            return map;
        }
        Classify classify = new Classify();
        classify.setClassifyId(Long.parseLong(classifyId));
        classify.setName(classifyName);
        classifyService.update(classify);
        map.put("code",1);
        map.put("data",classify);
        return map;
    }

    /**
     *删除分类
     *  删除成功：{"code":1,data",要删除的classify}
     *  删除失败：{"code":0,"msg",错误信息}
     * @param request
     *  分类ID：classifyId
     * @return
     */
    @RequestMapping("/deleteClassify")
    @ResponseBody
    public Map<String,Object> deleteClassify(HttpServletRequest request){
        String classifyId = request.getParameter("classifyId");
        Map<String ,Object> map = new HashMap<>();
        if (null == classifyId || !classifyId.matches("[0-9]{13}")){
            map.put("code",0);
            map.put("msg","分类ID不能为空，且必须是数字");
            return map;
        }
        Classify classify = new Classify();
        classify.setClassifyId(Long.parseLong(classifyId));
        classify = classifyService.delete(classify);
        if (null == classify){
            map.put("code",0);
            map.put("msg","删除失败，没有该分类");
            return map;
        }
        map.put("code",1);
        map.put("data",classify);
        return map;
    }
}
