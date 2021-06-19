package com.zhao.mvcdemo.controller;

import com.zhao.mvcdemo.service.DemoService;
import com.zhao.mvcframework.annotation.Autowired;
import com.zhao.mvcframework.annotation.Controller;
import com.zhao.mvcframework.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;
    @RequestMapping("/query")
    public String query(HttpServletRequest req, HttpServletResponse resp,String name){
        System.out.println("参数为"+name);
        return demoService.getName(name);
    }
}
