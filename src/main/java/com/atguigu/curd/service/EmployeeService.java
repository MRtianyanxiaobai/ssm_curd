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
    /**
     * 查询员工
     */
    public Employee getEmp(Integer id) {
        return  employeeMapper.selectByPrimaryKey(id);

    }
    /**
     * 更新员工
     */
    public  int updateEmployee(Employee employee){
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }
    /**
     * 根据 id 㤇 删除员工
     */
    public  int deletEmployee(Integer id){
       EmployeeExample example = new EmployeeExample();
       example.createCriteria().andStuIdEqualTo(id);
        return employeeMapper.deleteByExample(example);
    }
    /**
     * 根据 传过来的 id 数组，删除多个员工
     */
    public  int deleBatchEmployee(List<Integer> ids){
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andStuIdIn(ids);
        return  employeeMapper.deleteByExample(example);
    }


}
