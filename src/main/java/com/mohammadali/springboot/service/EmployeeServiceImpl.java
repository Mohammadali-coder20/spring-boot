package com.mohammadali.springboot.service;

import com.mohammadali.springboot.controllers.repositories.EmployeeRepository;
import com.mohammadali.springboot.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findALl(Pageable pageable) {
        Page<Employee> all = employeeRepository.findAll(pageable);
        return all.stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void sava(Employee employee) {
        employeeRepository.save(employee);
    }
}
