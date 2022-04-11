package com.mohammadali.springboot.repositories;

import org.MohammadAli.data.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDAO extends JpaRepository<CustomerOrder , Long> {



}
