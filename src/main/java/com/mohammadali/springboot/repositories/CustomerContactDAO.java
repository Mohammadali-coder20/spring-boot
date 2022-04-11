package com.mohammadali.springboot.repositories;

import com.mohammadali.springboot.entity.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactDAO  extends JpaRepository<CustomerContact, Long> {


}
