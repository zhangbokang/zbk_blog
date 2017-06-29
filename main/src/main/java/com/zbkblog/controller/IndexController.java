package com.zbkblog.controller;

import com.zbkblog.entity.*;
import com.zbkblog.service.*;
import com.zbkblog.utils.Paging;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private TagService tagService;
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private ClassifyNodeService classifyNodeService;

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
        blogUser = blogUserService.authBlogUser(username,password);
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

        Paging paging = docService.searchDocByKeywork(keyword,Integer.parseInt(pageSize),Integer.parseInt(currentPage));
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
        pageSize = pageSize==null?"15":pageSize;
        currentPage = currentPage==null?"1":currentPage;

        Paging paging = docService.findAllByPage(Integer.parseInt(pageSize),Integer.parseInt(currentPage));
        //防止零条记录时分页出错
        if(paging.getTotalCounts()==0){
            request.setAttribute("errorInfo","<b>未查询到记录，暂无文章！</b>");
            return "errorPage";
        }
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("docPaging",paging);
        return "bloglist";
    }

    @RequestMapping("findDocByclassifyNodeId")
    public String findDocByclassifyNodeId(HttpServletRequest request){
        //根据分类ID查询文章
        String classifyNodeId = request.getParameter("classifyNodeId");
        if (classifyNodeId == null){
            request.setAttribute("errorInfo","分类ID不能为空！");
            return "errorPage";
        }

        String pageSize = request.getParameter("pageSize");
        String currentPage = request.getParameter("currentPage");
        pageSize = pageSize==null?"10":pageSize;
        currentPage = currentPage==null?"1":currentPage;
        Paging paging = docService.findByClassifyNodeIdOfPage(Long.parseLong(classifyNodeId),Integer.parseInt(pageSize),Integer.parseInt(currentPage));
        //防止零条记录时分页出错
        if(paging.getTotalCounts()==0){
            request.setAttribute("errorInfo","<b>未查询到记录，该分类暂无文章！</b>");
            return "errorPage";
        }
        request.setAttribute("docPaging",paging);
        request.setAttribute("accessType",request.getParameter("accessType"));
        request.setAttribute("classifyNodeId",classifyNodeId);
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
        Paging paging = docService.findByTagIdOfPage(Long.parseLong(tagId),Integer.parseInt(pageSize),Integer.parseInt(currentPage));
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
        if (docId == null || !docId.matches("[0-9]+")){
            request.setAttribute("errorInfo","没有该文章。");
            return "errorPage";
        }
        //调用service查询
        Doc doc = docService.findById(Long.parseLong(docId));
        if (null == doc){
            request.setAttribute("errorInfo","未查询到该文章。");
            return "errorPage";
        }
        Object openNumberStr = request.getServletContext().getAttribute(docId);
        Long openNumber = 0L;
        if (openNumberStr == null){
            if (doc.getOpenNumber()!=null) {
                openNumber = doc.getOpenNumber();
            }
        }else {
            openNumber = Long.parseLong(openNumberStr.toString());
        }
        openNumber++;
        request.getServletContext().setAttribute(docId,openNumber);
        //设置更新文章的条件，点击x次才更新，避免频繁刷新缓存
        if (openNumber%10==0){
            doc.setOpenNumber(++openNumber);
            docService.update(doc,false);
        }
        request.setAttribute("doc",doc);
        return "docPage";
    }

    /**
     * 根据父ID查找分类列表
     * @param request
     * @return
     */
    /*@RequestMapping("/findClassifyByParentId")
    @ResponseBody
    public List<TreeNode> findClassifyByParentId(HttpServletRequest request){
        String parentId = request.getParameter("id");
        //分类列表
        List<Classify> classifyList = classifyService.findAllClassify();
//        Map<String, Object> map = new HashedMap();

        List<TreeNode> treeNodeList = new ArrayList<>();
//        Long tId = 100L;
        for (Classify classify: classifyList) {
            TreeNode t = new TreeNode();
            t.setId(classify.getClassifyId());
            t.setText(classify.getName());
//            List<TreeNode> tL = new ArrayList<>();
//            TreeNode t1 = new TreeNode();
//            t1.setId(tId);
//            t1.setText("模拟子目录"+tId);
//            tId++;
//            tL.add(t1);
//            t.setChildren(tL);
            t.setChildren(true);
            treeNodeList.add(t);
        }
//        treeNodeList.get(0).setChildren(treeNodeList);
        return treeNodeList;
    }*/

    /**
     * 根据父ID查找节点列表
     * @param request
     * @return
     * 失败 {code:0,msg:失败消息}
     * 成功{code:1,data:节点列表}
     */
    @RequestMapping("/findClassifyNodeByParentId")
    @ResponseBody
    public List<ClassifyNode> findClassifyNodeByParentId(HttpServletRequest request) {
        String parentIdStr = request.getParameter("id");
        Long parentId = null;
        if ((null != parentIdStr || !"#".equals(parentIdStr)) && parentIdStr.matches("[0-9]+")) {
            parentId = Long.parseLong(parentIdStr);
        }
        return classifyNodeService.findClassifyNodeListByParentId(parentId);
    }

    /**
     * 填充标签列表
     * @param request
     */
    public void panel(HttpServletRequest request){
        //标签列表
        List<Tag> tagList = tagService.findAllTag();
        request.setAttribute("tagList",tagList);
        //分类列表
//        List<Classify> classifyList = classifyService.findAllClassify();
//        request.setAttribute("classifyList",classifyList);

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
