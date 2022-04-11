package com.mohammadali.springboot.service;

import org.MohammadAli.models.CustomerOrderDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerOrderService {

    void save(CustomerOrderDTO.CREATE customerOrderDTO) throws IOException;

    List<CustomerOrderDTO.RETRIEVE> findAll();

    void deleteByID(Long cartID);

    CustomerOrderDTO.RETRIEVE findById(Long cartID);
}

