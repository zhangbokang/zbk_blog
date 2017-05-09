package com.zbkblog.controller;

import com.zbkblog.medo.BlogDoc;
import com.zbkblog.medo.Page;
import com.zbkblog.service.BlogService;
import com.zbkblog.utils.RequestToBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BlogService blogService;

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        Page page1 = RequestToBean.getBeanOfRequest(request,Page.class);
        //查询逻辑
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

    @RequestMapping("blogpage")
    public String blogpage(HttpServletRequest request){
        //编辑文章的ID
        String blogId = request.getParameter("blogId");
//        if (blogId == null){
//            return "index";
//        }
        //调用service查询
        BlogDoc blogDoc = blogService.findBlogDocByBlogId(Long.parseLong(blogId));
        request.setAttribute("blogDoc",blogDoc);
        return "blogpage";
    }

    @RequestMapping("editblog")
    public String editblog(HttpServletRequest request){
        //编辑文章的ID
        String blogId = request.getParameter("blogId");
        if (blogId == null){
            return "editblog";
        }
        //调用service查询
        BlogDoc blogDoc = blogService.findBlogDocByBlogId(Long.parseLong(blogId));
        request.setAttribute("blogDoc",blogDoc);
        return "editblog";
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest request){
        String blogId = request.getParameter("blogId");
        String blogMd = request.getParameter("blogMd");
        String  blogTitle = request.getParameter("blogTitle");
        String blogClass = request.getParameter("blogClass");
        String blogTag = request.getParameter("blogTag");



        //返回信息Map
        Map<String,Object> map = new HashMap();

        //验证信息
        if (blogTitle == null || blogTitle == ""){
            map.put("success",0);
            map.put("msg","保存失败，标题为空。");
            return map;
        }
        if (blogTag == null || blogTag == ""){
            map.put("success",0);
            map.put("msg","保存失败，标签为空。");
            return map;
        }
        if (blogClass == null || blogClass == ""){
            map.put("success",0);
            map.put("msg","保存失败，分类为空。");
            return map;
        }
        if (blogMd == null || blogMd == ""){
            map.put("success",0);
            map.put("msg","保存失败，文章内容为空。");
            return map;
        }

        //封装成对象
        BlogDoc blogDoc = new BlogDoc();
        if (blogId != null && blogId != ""){blogDoc.setBlogId(Long.parseLong(blogId));}
        blogDoc.setBlogTitle(blogTitle);
        blogDoc.setBlogTag(blogTag);
        blogDoc.setBlogMd(blogMd);
        blogDoc.setBlogClass(blogClass);
        blogDoc.setUpdataTime(new Date().getTime());

        //保存的逻辑
        blogDoc = blogService.save(blogDoc);

        map.put("success",1);
        map.put("msg","保存成功");
        map.put("blogId",blogDoc.getBlogId());
        return map;
    }

    @RequestMapping(value = "/upImage",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upImage(HttpServletRequest request){//,@RequestParam MultipartFile editormdImageFile){
        Map<String,Object> map = new HashMap<String, Object>();
        //上传文件保存的路径WebRoot下的blogUpload
        String savePath = request.getSession().getServletContext().getRealPath("/images");
        DiskFileItemFactory factory = new DiskFileItemFactory();
//        //最大缓存
//        factory.setSizeThreshold(5*1024);
        //设置文件存储目录，并判断目录是否存在，如果不存在或不是目录则创建
        File f = new File(savePath);
        if (!f.exists() || !f.isDirectory()){
            f.mkdirs();
        }
        factory.setRepository(f);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            //获取所有文件列表
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                //上传文件的文件名
                String fileName = item.getName();
                map.put("alt",fileName);
                //使用UUID来作为保存文件的文件名，避免重名
                fileName = UUID.randomUUID().toString();
                //写入文件
                File file = new File(savePath,fileName);
                item.write(file);
                map.put("success",1);
                map.put("message","图片上传成功");
                map.put("url","/images/"+fileName);
            }
        }catch (Exception e){
            map.put("success",0);
            map.put("message","图片上传失败,错误信息："+e.getMessage());
        }

        return map;
    }
}
