package com.atguigu.curd.test;

import com.atguigu.curd.bean.Department;
import com.atguigu.curd.bean.DepartmentExample;
import com.atguigu.curd.bean.Employee;
import com.atguigu.curd.bean.EmployeeExample;
import com.atguigu.curd.dao.DepartmentMapper;
import com.atguigu.curd.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MybatisTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    public  void TestSpring(){
        System.out.println(departmentMapper);
    }
    //测试 mybatis自动生成的dao文件
    @Test
    public void TestsSelectByExample(){
        DepartmentExample departmentExample = new DepartmentExample();
        List<Department> departments = departmentMapper.selectByExample(null);
        for (Department dep:departments) {
            System.out.println("========"+dep.getDeptId()+":"+dep.getDeptName());

        }

    }

    //测试级联查询
    @Test
    public void TestsSelectDept(){
        EmployeeExample employeeExample = new EmployeeExample();
        List<Employee> employees = employeeMapper.selectByExampleWithDept(employeeExample);
        for (Employee emp:employees) {
            System.out.println("========"+emp);

        }

    }
    //测试插入
    @Test
    public  void TestInsert(){

        System.out.println( employeeMapper.insertSelective(new Employee(null,"yq","M","yq@134.com",3)));
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andStuNameEqualTo("yq");
        System.out.println(employeeMapper.selectByExampleWithDept(employeeExample));
    }
//    //测试批量输入
    @Autowired
    SqlSession sqlSession;
    @Test
    public void  TestSessionInset(){
        //利用 sql session 代理 mapper 类
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<20;i++){
            String name = "yq_"+i;
            Employee employee = new Employee(null,name,"M",name+"@123.com",3);
            mapper.insertSelective(employee);
        }
    }
    //模糊查询
    @Test
    public  void  TestSelectYq(){
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andStuNameLike("%yq%");
        List<Employee> employees = employeeMapper.selectByExampleWithDept(employeeExample);
        for (Employee emp:employees) {
            System.out.println("========"+emp);

        }
    }

}
