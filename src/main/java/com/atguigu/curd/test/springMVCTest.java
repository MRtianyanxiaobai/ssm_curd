package com.atguigu.curd.test;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class springMVCTest {
    /**
     * 测试 springMVC
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(){
        System.out.println("处理 some.do");
        //调用 service 处理请求，返回结构
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","使用注解的SpringMVC");
        mv.addObject("fun","使用注解的 SpringMVC 的应用");
        mv.setViewName("show");
        return mv;
    }
}
