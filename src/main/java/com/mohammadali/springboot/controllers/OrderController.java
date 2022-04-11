package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.MohammadAli.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
@Log4j
public class OrderController  {

    private CartService cartService;

    @GetMapping("/{cartID}")
    public String createOrder(@PathVariable("cartID") long cartID){

        cartService.findCartByIDAndMakeItReadyToCheckout(cartID);

        return "redirect:/checkout?cartId=" + cartID;
    }



}
