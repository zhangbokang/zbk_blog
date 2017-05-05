package com.zbkblog.controller;

import com.zbkblog.medo.BlogDoc;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

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
        request.setAttribute("blogHtml",blogHtml);

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

        request.setAttribute("blogDoc",blogDoc);
        return "blogpage";
    }

    @RequestMapping(value = "/upImage",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upImage(HttpServletRequest request){//,@RequestParam MultipartFile editormdImageFile){
//        Map<String,Object> map = new HashMap<String, Object>();
//        if (editormdImageFile == null){
//            map.put("success","0");
//            map.put("message","图片不能为空");
//            return map;
//        }
//        //获取上传文件的文件名
//        String fileName = editormdImageFile.getOriginalFilename();
//        //在文件名前面添加时间戳避免文件名重复
//        fileName = new Date().getTime() + fileName;
//        //上传文件保存的路径WebRoot下的blogUpload
//        String savePath = request.getServletContext().getRealPath("/blogUpload");
//
//        //要保存的文件
//        File file = new File(savePath,fileName);
//
//        //写入文件
//        try{
//            editormdImageFile.transferTo(file);
//            map.put("success","1");
//            map.put("message","图片上传成功");
//            map.put("url","/blogUpload/"+fileName);
//        }catch (Exception e){
//            map.put("success","0");
//            map.put("message","图片上传失败,错误信息："+e.getMessage());
//        }


        Map<String,Object> map = new HashMap<String, Object>();
        //上传文件保存的路径WebRoot下的blogUpload
        String savePath = request.getSession().getServletContext().getRealPath("/images");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //最大缓存
        factory.setSizeThreshold(5*1024);
        //设置临时文件目录
//        File f = new File(savePath);
//        f.mkdir();
        factory.setRepository(new File(savePath));
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            //获取所有文件列表
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                //上传文件的文件名
                String fileName = item.getName();
                map.put("alt",fileName);
                //在文件名前面添加时间戳避免文件名重复
                fileName = UUID.randomUUID().toString();
                //写入文件
                File file = new File(savePath,fileName);
                item.write(file);
                map.put("success",1);

                map.put("message","图片上传成功");
                map.put("url","http://localhost:8089/images/"+fileName);
            }
        }catch (Exception e){
            map.put("success",0);
            map.put("message","图片上传失败,错误信息："+e.getMessage());
        }

        return map;
    }
}
