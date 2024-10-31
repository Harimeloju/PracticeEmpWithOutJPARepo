package com.springboot.cruddemo.rest;


import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee the=employeeService.findById(employeeId);
        if(the==null){
            throw new RuntimeException("Employee id not found-"+ employeeId);

        }
        return the;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee db=employeeService.save(theEmployee);
        return db;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee the=employeeService.save(theEmployee);
        return the;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee the=employeeService.findById(employeeId);
        if(the==null){
            throw new RuntimeException("Employee id not found -"+employeeId);

        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id -"+ employeeId;
    }
}
