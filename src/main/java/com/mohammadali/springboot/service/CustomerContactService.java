package com.mohammadali.springboot.service;

import org.MohammadAli.models.CustomerContactDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerContactService {

    void save(CustomerContactDTO.CREATE customerContactDTO) throws IOException;

    List<CustomerContactDTO.RETRIEVE> findAll();

    void delete(Long contactID);

}
