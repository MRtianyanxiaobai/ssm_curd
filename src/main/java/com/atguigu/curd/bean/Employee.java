package com.atguigu.curd.bean;

public class Employee {
    private Integer stuId;

    private String stuName;

    private String gender;

    private String email;

    private Integer dId;

    public   Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dId=" + dId +
                ", department=" + department +
                '}';
    }
    public Employee() {
    }

    public Employee(Integer stuId, String stuName, String gender, String email, Integer dId) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

    public Department getDepartment() {
        return department;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}