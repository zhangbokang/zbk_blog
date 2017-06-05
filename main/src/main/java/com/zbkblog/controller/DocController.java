package com.zbkblog.controller;

import com.zbkblog.entity.Classify;
import com.zbkblog.entity.Doc;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.ClassifyService;
import com.zbkblog.service.DocService;
import com.zbkblog.service.TagService;
import com.zbkblog.utils.MyBeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Controller
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;
    @Resource
    private TagService tagService;
    @Resource
    private ClassifyService classifyService;



    /**
     * 保存或更新文档
     * @param request
     * @return
     */
    @RequestMapping("/saveDoc")
    @ResponseBody
    public Map<String,Object> saveDoc(HttpServletRequest request){
        String docId = request.getParameter("docId");
        String title = request.getParameter("title");
        String  docMd = request.getParameter("docMd");
        String classifyId = request.getParameter("classifyId");
        String tagId = request.getParameter("tagId");
        //返回信息的Map
        Map<String,Object> map = new HashMap();
        //验证信息
        if (title == null || title == ""){
            map.put("code",0);
            map.put("msg","保存失败，标题为空。");
            return map;
        }
        if (docMd == null || docMd == ""){
            map.put("code",0);
            map.put("msg","保存失败，文章内容为空。");
            return map;
        }
        if (classifyId == null || classifyId == ""){
            map.put("code",0);
            map.put("msg","保存失败，分类为空");
            return map;
        }
        Classify classify = classifyService.findById(Long.parseLong(classifyId));
        if (null == classify){
            map.put("code",0);
            map.put("msg","保存失败，未查询到该分类");
            return map;
        }
        if (tagId == null || tagId == ""){
            map.put("code",0);
            map.put("msg","保存失败，标签为空");
            return map;
        }
        Tag tag = tagService.findById(Long.parseLong(tagId));
        if (null ==tag){
            map.put("code",0);
            map.put("msg","保存失败，未查询到该标签");
            return map;
        }
        //封装成对象
        Doc doc = new Doc();
        doc.setTitle(title);
        doc.setDocMd(docMd);
        doc.setClassify(classify);
        doc.setTag(tag);

        //保存的逻辑
        if (null != docId && docId.matches("[0-9]{13}")) {
            Doc doc1 = docService.findById(Long.parseLong(docId));
            if (doc1 != null){
                //将doc中的非空属性值复制到doc1
                MyBeanUtils.copyPropertiesIgnoreNull(doc,doc1);
                doc = docService.update(doc1);
                map.put("code",1);
                map.put("data",doc);
                return map;
            }
        }
        doc = docService.save(doc);
        map.put("code",1);
        map.put("data",doc);
        return map;
    }



    /**
     * 编辑或创建文章界面
     * @param request
     * @return
     */
    @RequestMapping("/editDoc")
    public String editDoc(HttpServletRequest request){
        //编辑文章的ID
        String docId = request.getParameter("docId");
        if (null == docId || "" == docId){
            return "editdoc";
        }
        //调用service查询
        Doc doc = docService.findById(Long.parseLong(docId));
        if (null == doc){
            request.setAttribute("errorInfo","未查询到该文章。");
            return "errorPage";
        }
        request.setAttribute("doc",doc);
        return "editdoc";
    }

    /**
     * 上传图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/upImage",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upImage(HttpServletRequest request){//,@RequestParam MultipartFile editormdImageFile){
        Map<String,Object> map = new HashMap<>();
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
