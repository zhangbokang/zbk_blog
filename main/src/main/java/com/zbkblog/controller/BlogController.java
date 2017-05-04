package com.zbkblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangbokang on 2017/5/3.
 */
@Controller
public class BlogController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("test.md")
    @ResponseBody
    public Object test(){
//        return "#这是个文件";
        StringBuilder sb = new StringBuilder();
        sb.append("#hahaha哈哈哈哈");
        return sb;
    }

    @RequestMapping("editblog")
    public String editblog(){
        return "editblog";
    }

    @RequestMapping("save")
    public String save(HttpServletRequest request){
        String mddoc = request.getParameter("editormd-markdown-doc");
        String htmldoc = request.getParameter("editormd-html-code");

        request.setAttribute("htmldoc",htmldoc);
        System.out.println(mddoc);
        return "editblog";
    }
}
