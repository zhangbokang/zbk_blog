package com.zbkblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangbokang on 2017/5/3.
 */
@Controller
public class BlogController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
