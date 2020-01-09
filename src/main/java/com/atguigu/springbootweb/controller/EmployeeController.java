package com.atguigu.springbootweb.controller;

import com.atguigu.springbootweb.dao.DepartmentDao;
import com.atguigu.springbootweb.dao.EmployeeDao;
import com.atguigu.springbootweb.entities.Department;
import com.atguigu.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * @author xp
 * @create 2020-01-09 14:23
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    // 查询所有员工
    @GetMapping("/emps")
    public String getEmps(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    // 进入员工添加界面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 进入员工添加界面之前 查询出所有部门信息
        Collection<Department> daoDepartments = departmentDao.getDepartments();
        model.addAttribute("daoDepartments",daoDepartments);
        return "emp/add";
    }
    // 添加员工
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息 = " + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
