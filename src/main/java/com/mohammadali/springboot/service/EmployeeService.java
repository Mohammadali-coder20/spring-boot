package com.mohammadali.springboot.service;

import com.mohammadali.springboot.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> findALl(Pageable pageable);

    void save(Employee employee);

    void deleteById(Long id);

    Employee findByID(Long id);
}
