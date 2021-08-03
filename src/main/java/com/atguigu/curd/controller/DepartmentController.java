package com.atguigu.curd.controller;

import com.atguigu.curd.bean.Department;
import com.atguigu.curd.bean.Msg;
import com.atguigu.curd.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDeptsWithJson(){
        List<Department> lists = departmentService.getDeparts();
        return Msg.sucess().add("depts",lists);
    }

}
