package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import org.MohammadAli.models.CustomerDTO;
import org.MohammadAli.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/customer-management")
@AllArgsConstructor
public class AdminCustomerManagementController {

    private CustomerService customerService;

    @RequestMapping(value = "/address/{customerID}" , method = RequestMethod.GET)
    public String showAddressDetail(@PathVariable("customerID") long customerID , Model model){
        //TODO: Create a address interface that with it can receive customer billing address and shipping address with an array 2d
        CustomerDTO.ADDRESS customerByCustomerID = customerService.getCustomerByCustomerID(customerID);
        model.addAttribute("customerAddress", customerByCustomerID);
        return "customer-address-detail";
    }
}

