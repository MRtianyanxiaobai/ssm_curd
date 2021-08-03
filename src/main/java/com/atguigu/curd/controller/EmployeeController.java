package com.atguigu.curd.controller;

import com.atguigu.curd.bean.Employee;
import com.atguigu.curd.bean.Msg;
import com.atguigu.curd.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    /**
     * 查询员工数据，分页查询(已弃用，为了跨平台性，使用json返回)
     */
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue ="1") Integer pn, Model model){
        //引入 pageHelper分页插件
        //第一个参数为当前页码，第二个参数页的大小
        PageHelper.startPage(pn,5);
        List<Employee> employees = employeeService.getAll();
        //将查询结果封装到 pageInfo 中
        PageInfo page = new PageInfo(employees,5);
        model.addAttribute("pageInfo",page);
        return  "list";
    }
    /*
    * 需要导入json包，responsebody才能正常工作
    * */
    @RequestMapping("/emps")
    @ResponseBody//返回json对象
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue ="1") Integer pn){
        //引入 pageHelper分页插件
        //第一个参数为当前页码，第二个参数页的大小
        PageHelper.startPage(pn,5);
        List<Employee> employees = employeeService.getAll();
        //将查询结果封装到 pageInfo 中
        PageInfo page = new PageInfo(employees,5);
        return Msg.sucess().add("pageInfo",page);
    }
}
