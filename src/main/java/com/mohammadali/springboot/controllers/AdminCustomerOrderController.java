package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import org.MohammadAli.models.CustomerOrderDTO;
import org.MohammadAli.services.CustomerOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/customer-order")
public class AdminCustomerOrderController {

    private CustomerOrderService orderService;

    @RequestMapping(value = "/show-list/{cartID}" , method = RequestMethod.GET)
    public String showCustomerOrders(@PathVariable("cartID") Long cartID , Model model){
        CustomerOrderDTO.RETRIEVE dto = orderService.findById(cartID);
        model.addAttribute("order", dto);
        return "order-details";
    }

    @RequestMapping(value = "/delete-order/{cartID}" , method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("cartID") Long cartID){
        orderService.deleteByID(cartID);
        return "redirect:/admin/customer-order";
    }

}
