package com.atguigu.curd.service;

import com.atguigu.curd.bean.Employee;
import com.atguigu.curd.bean.EmployeeExample;
import com.atguigu.curd.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    /**
     * 查询所有员工
     */
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }
    /**
     * 验证当前用户名是否可用
     */
    public boolean checkUser(String empName){
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andStuNameEqualTo(empName);
        long count = employeeMapper.countByExample(employeeExample);
        return count==0;
    }
    /**
     * 添加员工
     */
    public void saveEmployee(Employee employee){
        employeeMapper.insertSelective(employee);
    }
}
