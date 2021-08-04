package com.atguigu.curd.dao;

import com.atguigu.curd.bean.Employee;
import com.atguigu.curd.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int insert(Employee record);

    int insertSelective(Employee record);
    //选择查询 具体条件下的 employees （无部门名字）
    List<Employee> selectByExample(EmployeeExample example);
    //查询 具体empID的 employee（无部门名字）
    Employee selectByPrimaryKey(Integer empId);

    //选择查询 具体条件下的 employees （有部门名字）
    List<Employee> selectByExampleWithDept(EmployeeExample example);
    //查询 具体empID的 employee（有部门名字）
    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(@Param("record") Employee record);
}