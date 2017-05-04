package com.zbkblog.controller;

import com.zbkblog.medo.BlogDoc;
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
        String blogMd = request.getParameter("editormd-markdown-doc");
        String blogHtml = request.getParameter("editormd-html-code");
        String  blogTitle = request.getParameter("blogTitle");
        String blogClass = request.getParameter("blogClass");
        String blogTag = request.getParameter("blogTag");

        //封装成对象
        BlogDoc blogDoc = new BlogDoc();
        blogDoc.setBlogTitle(blogTitle);
        blogDoc.setBlogTag(blogTag);
        blogDoc.setBlogMd(blogMd);
        blogDoc.setBlogClass(blogClass);
        blogDoc.setBlogHtml(blogHtml);

        if (blogTitle == null || blogTitle == ""){
            request.setAttribute("blogDoc",blogDoc);
            return "editblog";
        }
        if (blogTag == null || blogTag == ""){
            request.setAttribute("blogDoc",blogDoc);
            return "editblog";
        }
        if (blogClass == null || blogClass == ""){
            request.setAttribute("blogDoc",blogDoc);
            return "editblog";
        }
        if (blogMd == null || blogMd == ""){
            request.setAttribute("blogDoc",blogDoc);
            return "editblog";
        }

        return "blog";
    }
}
