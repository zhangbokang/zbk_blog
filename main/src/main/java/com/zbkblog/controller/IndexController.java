package com.zbkblog.controller;

import com.zbkblog.entity.Classify;
import com.zbkblog.entity.Doc;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.ClassifyService;
import com.zbkblog.service.DocService;
import com.zbkblog.service.TagService;
import com.zbkblog.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/13.
 */
@Controller
public class IndexController {
    @Resource
    private DocService docService;
    @Resource
    private ClassifyService classifyService;
    @Resource
    private TagService tagService;

    @RequestMapping("")
    public String all(HttpServletRequest request){
        return this.index(request);
    }
    @RequestMapping("index")
    public String index(HttpServletRequest request){
        //查询文章列表
        //Page page1 = RequestToBean.getBeanOfRequest(request,Page.class);
        //查询逻辑
        //PageInfo<BlogDoc> pageInfo =blogService.findAllBlogDocPaging(null,1,10);
//        String everyPage = request.getParameter("everyPage");
//        String totalCount = request.getParameter("totalCount");
//        String currentPage = request.getParameter("currentPage");
//        List<Doc> docList = docService.findAllByPage(PageUtil.createPage(Integer.parseInt(everyPage),Integer.parseInt(totalCount,0),Integer.parseInt(currentPage,0)));
        List<Doc> docList = docService.findAll();
        request.setAttribute("docList",docList);

        //调用填充标签列表
        this.panel(request);

        return "index";
    }

    @RequestMapping("findDocByClassifyId")
    public String findDocByClassifyId(HttpServletRequest request){
        //根据分类ID查询文章
        String classifyId = request.getParameter("classifyId");
        if (classifyId == null){
            return index(request);
        }
        List<Doc> docList = docService.findByClassifyId(Long.parseLong(classifyId));
        request.setAttribute("docList",docList);
        //调用填充标签列表
        this.panel(request);
        return "index";
    }
    @RequestMapping("findDocByTagId")
    public String findDocByTagId(HttpServletRequest request){
        //根据标签ID查询文章
        String tagId = request.getParameter("tagId");
        if (tagId == null){
            return index(request);
        }
        List<Doc> docList = docService.findByTagId(Long.parseLong(tagId));
        request.setAttribute("docList",docList);
        //调用填充标签列表
        this.panel(request);
        return "index";
    }
    /**
     * 填充标签列表
     * @param request
     */
    public void panel(HttpServletRequest request){
        //标签列表
        List<Tag> tagList = tagService.findAll();
        request.setAttribute("tagList",tagList);
        //分类列表
        List<Classify> classifyList = classifyService.findAll();
        request.setAttribute("classifyList",classifyList);

        //点赞排行列表查询
        List<Doc> dzph = docService.findByFavorNumberOfTopX(10);
        request.setAttribute("dzph",dzph);
        //阅读排行列表查询
        List<Doc> ydph = docService.findByOpenNumberOfTopX(10);
        request.setAttribute("ydph",ydph);

        //最新文章列表查询
        List<Doc> zxwz = docService.findByUpdateOfTopX(10);
        request.setAttribute("zxwz",zxwz);
    }
}
