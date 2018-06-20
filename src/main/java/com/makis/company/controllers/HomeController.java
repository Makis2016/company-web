package com.makis.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  首页页面跳转控制类
 *
 *  @author liujianning
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "forward:/company/home/index";
    }
}
