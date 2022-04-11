package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import org.MohammadAli.services.CustomerContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/customer-message")
@AllArgsConstructor
public class AdminCustomerMessageController {

    private CustomerContactService customerContactService;

    @RequestMapping(value = "/delete-message" , method = RequestMethod.POST)
    public String deleteMessage(@RequestParam("customerMessageId") Long contactID){
        customerContactService.delete(contactID);
        return "redirect:/admin/customer-message/1";
    }


}
