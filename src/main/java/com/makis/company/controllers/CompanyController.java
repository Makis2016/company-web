package com.makis.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页页面控制器
 *
 * @author liujianning
 */
@Controller
@RequestMapping("/company/home")
public class CompanyController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){

        return "resources/company/index.html";
    }
}