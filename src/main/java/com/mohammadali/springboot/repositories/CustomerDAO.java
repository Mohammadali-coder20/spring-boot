package com.mohammadali.springboot.repositories;

import org.MohammadAli.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer , Long> {

    Customer getCustomerByUserName(String userName);

}
