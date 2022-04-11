package com.mohammadali.springboot.service;



import com.mohammadali.springboot.models.CustomerDTO;

import java.util.List;


public interface CustomerService {

    void addCustomer(CustomerDTO.CREATE customerDTO);

    List<CustomerDTO.LOGIN> getAllCustomerForLogin();

    CustomerDTO.ADDRESS getCustomerByCustomerID(long customerId);

    CustomerDTO findCustomerByUserNameAndPassWord(String username, String password);

    void updateCustomer(CustomerDTO.CREATE customerDTO);

    List<CustomerDTO.RETRIEVE> findAll();

    CustomerDTO.INFO getCustomerInfoByUsername(String username);

    CustomerDTO.RETRIEVE getCustomerCartAndCustomerIdByUsername(String username);
}
