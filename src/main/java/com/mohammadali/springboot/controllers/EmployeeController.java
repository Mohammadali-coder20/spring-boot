package com.mohammadali.springboot.controllers;

import com.mohammadali.springboot.entity.Employee;
import com.mohammadali.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
//@AllArgsConstructor
public class EmployeeController {

//    @Value("${user.name.customer}")
//    private String name;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll(Pageable pageable){
        List<Employee> employees = employeeService.findALl(pageable);
        HttpHeaders header = new HttpHeaders();
        header.add("key","value");
        header.add(HttpHeaders.CONTENT_LANGUAGE , "EN");
        ResponseEntity<List<Employee>> response = new ResponseEntity<>(employees , header , HttpStatus.OK);
        return response;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return  employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        try {
            Employee employee = employeeService.findByID(id);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "not found employee", e);
        }
        employeeService.deleteById(id);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON.getType());
        return new ResponseEntity<String>("deleted",header,HttpStatus.OK);
    }

}
