package com.zbkblog.controller;

import com.zbkblog.entity.Tag;
import com.zbkblog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbokang on 2017/5/16.
 */
@Controller
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;

    /**
     * 查询所有标签信息
     *  查询失败：{"code":1,"data":List<Tag>}
     *  查询成功：{"code":0,"msg":"查询出现错误"}
     * @param request
     *  无
     * @return
     */
    @RequestMapping("/findAllTag")
    @ResponseBody
    public Map<String,Object> findAllTag(HttpServletRequest request){
        List<Tag> list = tagService.findAll();
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
     * 添加标签
     *  添加成功：{"code":1,"data":tagId}
     *  添加失败：{"code":0,"msg":"标签名称不能为空"}
     * @param request
     *  分类名称：tagName
     * @return
     */
    @RequestMapping("/addTag")
    @ResponseBody
    public Map<String,Object> addTag(HttpServletRequest request){
        String tagName = request.getParameter("tagName");
        Map<String ,Object> map = new HashMap<>();
        if (null == tagName){
            map.put("code",0);
            map.put("msg","标签名称不能为空");
            return map;
        }
        Tag tag = new Tag();
        tag.setName(tagName);
        Long tagId = tagService.save(tag);
        map.put("code",1);
        map.put("data",tagId);
        return map;
    }

    /**
     * 更新标签信息
     *  更新失败：{"code":0,"msg",失败信息提示信息}
     *  更新成功：{"code":1,data",更新后的tag}
     * @param request
     *  标签ID：tagId
     *  标签名称：tagName
     * @return
     */
    @RequestMapping("/updateTag")
    @ResponseBody
    public Map<String,Object> updateTag(HttpServletRequest request){
        String tagId = request.getParameter("tagId");
        String tagName = request.getParameter("tagName");
        Map<String ,Object> map = new HashMap<>();
        if (null == tagName){
            map.put("code",0);
            map.put("msg","标签名称不能为空");
            return map;
        }
        if (null == tagId || !tagId.matches("[0-9]{13}")){
            map.put("code",0);
            map.put("msg","标签ID不能为空，且必须是数字");
            return map;
        }
        Tag tag = new Tag();
        tag.setTagId(Long.parseLong(tagId));
        tag.setName(tagName);
        tagService.update(tag);
        map.put("code",1);
        map.put("data",tag);
        return map;
    }

    /**
     *删除标签
     *  删除成功：{"code":1,data",要删除的tag}
     *  删除失败：{"code":0,"msg",错误信息}
     * @param request
     *  标签ID：tagId
     * @return
     */
    @RequestMapping("/deleteTag")
    @ResponseBody
    public Map<String,Object> deleteTag(HttpServletRequest request){
        String tagId = request.getParameter("tagId");
        Map<String ,Object> map = new HashMap<>();
        if (null == tagId || !tagId.matches("[0-9]{13}")){
            map.put("code",0);
            map.put("msg","标签ID不能为空，且必须是数字");
            return map;
        }
        Tag tag = new Tag();
        tag.setTagId(Long.parseLong(tagId));
        tag = tagService.delete(tag);
        if (null == tag){
            map.put("code",0);
            map.put("msg","删除失败，没有该标签");
            return map;
        }
        map.put("code",1);
        map.put("data",tag);
        return map;
    }
}
