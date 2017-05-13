package com.zbkblog.controller;

import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
import com.zbkblog.utils.PageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Controller
//@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;

    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest request){
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String  docMd = request.getParameter("docMd");
        String classifyId = request.getParameter("classifyId");
        String tagId = request.getParameter("tagId");



        //返回信息Map
        Map<String,Object> map = new HashMap();

        //验证信息
        if (title == null || title == ""){
            map.put("success",0);
            map.put("msg","保存失败，标题为空。");
            return map;
        }
//        if (tagId == null || tagId == ""){
//            map.put("success",0);
//            map.put("msg","保存失败，标签为空。");
//            return map;
//        }
//        if (classifyId == null || classifyId == ""){
//            map.put("success",0);
//            map.put("msg","保存失败，分类为空。");
//            return map;
//        }
        if (docMd == null || docMd == ""){
            map.put("success",0);
            map.put("msg","保存失败，文章内容为空。");
            return map;
        }

        //封装成对象
        Doc doc = new Doc();
        if (id != null && id != ""){doc.setId(Long.parseLong(id));}
        doc.setTitle(title);
        doc.setDocMd(docMd);
        //doc.setClassifyId(Long.valueOf(classifyId));
        doc.setUpdateTime(new Date().getTime());

        //保存的逻辑
        doc = docService.save(doc);

        map.put("success",1);
        map.put("msg","保存成功");
        map.put("docId",doc.getId());
        return map;
    }

    @RequestMapping("blogpage")
    public String blogpage(HttpServletRequest request){
        //编辑文章的ID
        String docId = request.getParameter("docId");
//        if (blogId == null){
//            return "index";
//        }
        //调用service查询
        Doc doc = docService.findById(Long.parseLong(docId));
        request.setAttribute("doc",doc);
        return "blogpage";
    }

    @RequestMapping("editblog")
    public String editblog(HttpServletRequest request){
        //编辑文章的ID
        String docId = request.getParameter("docId");
        if (docId == null){
            return "editblog";
        }
        //调用service查询
        Doc doc = docService.findById(Long.parseLong(docId));
        request.setAttribute("doc",doc);
        return "editblog";
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
