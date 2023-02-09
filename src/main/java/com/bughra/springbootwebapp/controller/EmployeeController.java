package com.bughra.springbootwebapp.controller;

import com.bughra.springbootwebapp.dao.EmployeeDao;
import com.bughra.springbootwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping({"/showEmployees","/","/list"})
    public ModelAndView showEmployees(){
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> list = employeeDao.findAll();
        mav.addObject("employees", list);
        return mav;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        mav.addObject("employee", newEmployee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeDao.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee=employeeDao.findById(employeeId).get();
        mav.addObject("employee", employee);
        return mav;
    }

    @GetMapping ("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId){
        employeeDao.deleteById(employeeId);
//        employeeDao.findById(employeeId);
        return "redirect:/list";
    }
}
