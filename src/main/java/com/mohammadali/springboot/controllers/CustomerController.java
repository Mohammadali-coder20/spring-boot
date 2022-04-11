package com.mohammadali.springboot.controllers;


import com.mohammadali.springboot.models.CartDTO;
import com.mohammadali.springboot.models.CustomerContactDTO;
import com.mohammadali.springboot.models.CustomerDTO;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private CustomerContactService contactService;

    private CustomerService customerService;

    @Autowired
    private CustomerRegisterController customerRegister;


    @GetMapping("/customer-update")
    public String customerUpdateGet(CustomerDTO.REGISTER dto , Model model){
//        customerRegister.register(dto , true  , model);
        return "register-customer";
    }

    @PostMapping("/customer-update")
    public void customerUpdatePost(){

    }

    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User activeUser){

        CustomerDTO.RETRIEVE customer = customerService.getCustomerCartAndCustomerIdByUsername(activeUser.getUsername());
//        List<CartItemDTO.RETRIEVE> cartItems = customer.getCart().getCartItems();
        CartDTO.RETRIEVE cartDTO = customer.getCart();
        return "redirect:/customer/cart/"+ cartDTO.getCartId();
    }


    @GetMapping("/cart/{cartID}")
    public String getCart(@PathVariable("cartID") Long cartId , Model model){
        model.addAttribute("cartId", cartId );
        return "cart";
    }

    @PostMapping("/contact/new-message")
    public String saveCustomerMessage(@AuthenticationPrincipal User activeUser,
                                      @RequestParam("message") String message,
                                      Model model,
                                      @ModelAttribute("dto") CustomerContactDTO.CREATE dto) throws IOException {
        CustomerDTO.INFO customerInfo =customerService.getCustomerInfoByUsername(activeUser.getUsername());
        dto.setContactInfo(message);
        dto.setCustomer(customerInfo);
        contactService.save(dto);

        model.addAttribute("msg" , "Your Message Has Been Successfully Sent To Online Green Shop");
        return "contact";
    }

}
