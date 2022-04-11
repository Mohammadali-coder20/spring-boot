package com.mohammadali.springboot.repositories;


import org.MohammadAli.data.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<Users , Long> {




}
