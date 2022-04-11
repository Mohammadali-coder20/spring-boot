package com.mohammadali.springboot.service;


import com.mohammadali.springboot.entity.Cart;
import com.mohammadali.springboot.entity.Customer;
import com.mohammadali.springboot.models.CartDTO;
import com.mohammadali.springboot.models.CustomerDTO;
import com.mohammadali.springboot.repositories.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    ModelMapper mapper;

    @Autowired
    PasswordEncoder encoder;


    @Override
    @Transactional
    public void addCustomer(CustomerDTO.CREATE customerDTO) {
        customerDTO.setPassword(encoder.encode(customerDTO.getPassword()));
        Customer mapCustomer = mapper.map(customerDTO, Customer.class);
        customerDAO.save(mapCustomer);
        customerDTO.setCustomerID(mapCustomer.getCustomerID());
    }


    @Override
    @Transactional
    public List<CustomerDTO.LOGIN> getAllCustomerForLogin() {

        List<Customer> all = customerDAO.findAll();
        List<CustomerDTO.LOGIN> collect = all.stream()
                                             .map(customer -> mapper.map(customer, CustomerDTO.LOGIN.class))
                                             .collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public CustomerDTO.ADDRESS getCustomerByCustomerID(long customerId) {
        Customer one = customerDAO.findById(customerId).orElseGet(() -> new Customer(0L,"none", "none","none","none","none",true,null , null ,null));
        return mapper.map(one,CustomerDTO.ADDRESS.class);
    }

    @Override
    @Transactional
    public CustomerDTO findCustomerByUserNameAndPassWord(String username, String password) {
        return null;
    }

    @Override
    @Transactional
    public void updateCustomer(CustomerDTO.CREATE customerDTO) {
        Customer customer = mapper.map(customerDTO , Customer.class);
        customerDAO.save(customer);
    }

    @Override
    @Transactional
    public List<CustomerDTO.RETRIEVE> findAll() {
        List<Customer> all = customerDAO.findAll();

        List<CustomerDTO.RETRIEVE> customerList = all.stream()
                                                  .map(customer -> mapper.map(customer , CustomerDTO.RETRIEVE.class ))
                                                  .collect(Collectors.toList());
        return customerList;
    }

    @Override
    @Transactional
    public CustomerDTO.INFO getCustomerInfoByUsername(String username) {
        Customer customer = customerDAO.getCustomerByUserName(username);
        return mapper.map(customer , CustomerDTO.INFO.class);
    }

    @Override
    @Transactional
    public CustomerDTO.RETRIEVE getCustomerCartAndCustomerIdByUsername(String username) {
       Customer customer =  customerDAO.getCustomerByUserName(username);
        CustomerDTO.RETRIEVE map = mapper.map(customer, CustomerDTO.RETRIEVE.class);
        for (Cart cart : customer.getCart()) {
            if (!cart.isCheckout()){
                map.setCart(mapper.map(cart , CartDTO.RETRIEVE.class));
                break;
            }
        }
        return map;
    }
}
