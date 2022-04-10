package com.mohammadali.springboot.controllers.repositories;

import com.mohammadali.springboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
