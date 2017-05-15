package com.zbkblog.controller;

import com.zbkblog.entity.Classify;
import com.zbkblog.service.ClassifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangbokang on 2017/5/15.
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController {
    @Resource
    private ClassifyService classifyService;

    @RequestMapping("/findAllClassify")
    @ResponseBody
    public List<Classify> findAllClassify(HttpServletRequest request){
        return classifyService.findAll();
    }
}
