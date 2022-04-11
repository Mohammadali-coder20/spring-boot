package com.mohammadali.springboot.repositories;

import org.MohammadAli.data.entities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesDAO extends JpaRepository<Authorities , Long> {



}
