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
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 检验用用户名是否可用
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public  Msg checkUser(@RequestParam("stuName") String stuName){
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        //先检测用户名是否合法
        if(!stuName.matches(regx)){
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        //检查用户名是否重复
        boolean b = employeeService.checkUser(stuName);
        if (b){
            return Msg.sucess();
        }else{
            return  Msg.fail().add("va_msg","用户名不可用");
        }
    }

    /**
     * 员工保存
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(Employee employee){
        System.out.println(employee);
        employeeService.saveEmployee(employee);
        return  Msg.sucess();
    }
}
