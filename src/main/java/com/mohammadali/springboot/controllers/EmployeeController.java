package com.mohammadali.springboot.controllers;

import com.mohammadali.springboot.entity.Employee;
import com.mohammadali.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAll(Pageable pageable){
        return employeeService.findALl(pageable);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employeeService.sava(employee);
        return  employee;
    }
}
