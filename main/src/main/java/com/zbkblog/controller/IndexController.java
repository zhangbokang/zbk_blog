package com.zbkblog.controller;

import com.zbkblog.entity.Classify;
import com.zbkblog.entity.Doc;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.ClassifyService;
import com.zbkblog.service.DocService;
import com.zbkblog.service.TagService;
import com.zbkblog.utils.Paging;
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
        //调用填充标签列表
        this.panel(request);
        return "index";
    }

    @RequestMapping("findAllDoc")
    public String blogList(HttpServletRequest request){
        String pageSize = request.getParameter("pageSize");
        String currentPage = request.getParameter("currentPage");
        pageSize = pageSize==null?"10":pageSize;
        currentPage = currentPage==null?"1":currentPage;

        Paging paging = new Paging();
        paging.setPageSize(Integer.parseInt(pageSize));
        paging.setCurrentPage(Integer.parseInt(currentPage));
        paging = docService.findAllByPage(paging);
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("docPaging",paging);
        return "bloglist";
    }

    @RequestMapping("findDocByClassifyId")
    public String findDocByClassifyId(HttpServletRequest request){
        //根据分类ID查询文章
        String classifyId = request.getParameter("classifyId");
        if (classifyId == null){
            request.setAttribute("errorInfo","分类ID不能为空！");
            return "errorPage";
        }

        String pageSize = request.getParameter("pageSize");
        String currentPage = request.getParameter("currentPage");
        pageSize = pageSize==null?"10":pageSize;
        currentPage = currentPage==null?"1":currentPage;
        Paging paging = new Paging();
        paging.setPageSize(Integer.parseInt(pageSize));
        paging.setCurrentPage(Integer.parseInt(currentPage));
        paging = docService.findByClassifyIdOfPage(Long.parseLong(classifyId),paging);
        request.setAttribute("docPaging",paging);
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("classifyId",classifyId);
        return "bloglist";
    }
    @RequestMapping("findDocByTagId")
    public String findDocByTagId(HttpServletRequest request){
        //根据标签ID查询文章
        String tagId = request.getParameter("tagId");
        if (tagId == null){
            request.setAttribute("errorInfo","标签ID不能为空！");
            return "errorPage";
        }
        String pageSize = request.getParameter("pageSize");
        String currentPage = request.getParameter("currentPage");
        pageSize = pageSize==null?"10":pageSize;
        currentPage = currentPage==null?"1":currentPage;
        Paging paging = new Paging();
        paging.setPageSize(Integer.parseInt(pageSize));
        paging.setCurrentPage(Integer.parseInt(currentPage));
        paging = docService.findByTagIdOfPage(Long.parseLong(tagId),paging);
        request.setAttribute("docPaging",paging);
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("tagId",tagId);
        return "bloglist";
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
