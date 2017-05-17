package com.zbkblog.controller;

import com.zbkblog.entity.Doc;
import com.zbkblog.service.DocService;
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

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        //Page page1 = RequestToBean.getBeanOfRequest(request,Page.class);
        //查询逻辑
        //PageInfo<BlogDoc> pageInfo =blogService.findAllBlogDocPaging(null,1,10);
        String everyPage = request.getParameter("everyPage");
        String totalCount = request.getParameter("totalCount");
        String currentPage = request.getParameter("currentPage");
//        List<Doc> docList = docService.findAllByPage(PageUtil.createPage(Integer.parseInt(everyPage),Integer.parseInt(totalCount,0),Integer.parseInt(currentPage,0)));
        List<Doc> docList = docService.findAll();
        request.setAttribute("docList",docList);
        return "index";
    }
}
