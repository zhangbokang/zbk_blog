package com.zbkblog.controller;

import com.zbkblog.entity.BlogUser;
import com.zbkblog.entity.Classify;
import com.zbkblog.entity.Doc;
import com.zbkblog.entity.Tag;
import com.zbkblog.service.BlogUserService;
import com.zbkblog.service.ClassifyService;
import com.zbkblog.service.DocService;
import com.zbkblog.service.TagService;
import com.zbkblog.utils.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private BlogUserService blogUserService;

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

    @RequestMapping("login")
    public String login(HttpServletRequest request){
        return "login";
    }

    @RequestMapping("loginAuth")
    @ResponseBody
    public Map<String,Object> loginAuth(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,Object> map = new HashMap<>();
        if (username==null){
            map.put("code",0);
            map.put("msg","用户名不能为空！");
            return map;
        }
        if (password==null){
            map.put("code",0);
            map.put("msg","密码不能为空！");
            return map;
        }
        BlogUser blogUser = new BlogUser();
        blogUser.setUserName(username);
        blogUser.setPassword(password);
        blogUser = blogUserService.authBlogUser(blogUser);
        if (blogUser==null){
            map.put("code",0);
            map.put("msg","验证失败，请确认用户名和密码是否正确！");
            return map;
        }
        map.put("code",1);
//        map.put("data",blogUser);
        request.getSession().setAttribute("userId",blogUser.getUserId());
        return map;
    }

    @RequestMapping("searchDocByKeywork")
    public String searchDocByKeywork(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        if (null == keyword){
            return blogList(request);
        }
        String pageSize = request.getParameter("pageSize");
        String currentPage = request.getParameter("currentPage");
        pageSize = pageSize==null?"10":pageSize;
        currentPage = currentPage==null?"1":currentPage;

        Paging paging = new Paging();
        paging.setPageSize(Integer.parseInt(pageSize));
        paging.setCurrentPage(Integer.parseInt(currentPage));
        paging = docService.searchDocByKeywork(keyword,paging);
        //防止零条记录时分页出错
        if(paging.getTotalCounts()==0){
            request.setAttribute("errorInfo","<b>未查询到记录，请更换关键词后重新查询！</b>");
            return "errorPage";
        }
        request.setAttribute("docPaging",paging);
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("keyword",keyword);

        return "bloglist";
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
        //防止零条记录时分页出错
        if(paging.getTotalCounts()==0){
            request.setAttribute("errorInfo","<b>未查询到记录，暂无文章！</b>");
            return "errorPage";
        }
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
        //防止零条记录时分页出错
        if(paging.getTotalCounts()==0){
            request.setAttribute("errorInfo","<b>未查询到记录，该分类暂无文章！</b>");
            return "errorPage";
        }
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
        //防止零条记录时分页出错
        if(paging.getTotalCounts()==0){
            request.setAttribute("errorInfo","<b>未查询到记录，该标签暂无文章！</b>");
            return "errorPage";
        }
        request.setAttribute("docPaging",paging);
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("tagId",tagId);
        return "bloglist";
    }







    /**
     * 查看具体的文章
     * @param request
     * @return
     */
    @RequestMapping("/docPage")
    public String docPage(HttpServletRequest request){
        //编辑文章的ID
        String docId = request.getParameter("docId");
        if (docId == null || !docId.matches("[0-9]{13}")){
            request.setAttribute("errorInfo","没有该文章。");
            return "errorPage";
        }
        //调用service查询
        Doc doc = docService.findById(Long.parseLong(docId));
        if (null == doc){
            request.setAttribute("errorInfo","未查询到该文章。");
            return "errorPage";
        }
        Long openNumber = doc.getOpenNumber()!=null?doc.getOpenNumber():0L;
        doc.setOpenNumber(++openNumber);
        docService.update(doc);
        request.setAttribute("doc",doc);
        return "docPage";
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
