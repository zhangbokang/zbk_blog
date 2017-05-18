package com.zbkblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangbokang on 2017/5/18.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("")
    public String admin(HttpServletRequest request){
        return "admin/admin";
    }

    @RequestMapping("/classifyManage")
    public String classifyManage(HttpServletRequest request){
        return "admin/classifyManage";
    }

    @RequestMapping("/commentManage")
    public String commentManage(HttpServletRequest request){
        return "admin/commentManage";
    }

    @RequestMapping("/docManage")
    public String docManage(HttpServletRequest request){
        return "admin/docManage";
    }

    @RequestMapping("/tagManage")
    public String tagManage(HttpServletRequest request){
        return "admin/tagManage";
    }
}
