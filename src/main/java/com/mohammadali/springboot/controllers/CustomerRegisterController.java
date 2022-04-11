package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import org.MohammadAli.data.entities.BillingAddress;
import org.MohammadAli.data.entities.ShippingAddress;
import org.MohammadAli.models.*;
import org.MohammadAli.services.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerRegisterController {


    private CustomerService customerService;
    private UserService userService;
    private CartService cartService;
    private AuthoritiesService authoritiesService;
    private CartItemService cartItemService;



    @GetMapping("/register")
    public String register(@ModelAttribute("customer") CustomerDTO.REGISTER registerDTO){
        registerDTO.setBillingAddress(new BillingAddress());
        registerDTO.setShippingAddress(new ShippingAddress());
        registerDTO.setCustomerName("123321");
        registerDTO.setCustomerEmailAddress("a@example.com");
        registerDTO.setCustomerPhoneNumber("09122323231");
        registerDTO.getBillingAddress().setStreetName("123");
        registerDTO.getBillingAddress().setApartmentNumber("123");
        registerDTO.getBillingAddress().setCity("123");
        registerDTO.getBillingAddress().setState("123");
        registerDTO.getBillingAddress().setCountry("123");
        registerDTO.getBillingAddress().setZipCode("123");
        registerDTO.getShippingAddress().setStreetName("123");
        registerDTO.getShippingAddress().setApartmentNumber("123");
        registerDTO.getShippingAddress().setCity("123");
        registerDTO.getShippingAddress().setState("123");
        registerDTO.getShippingAddress().setCountry("123");
        registerDTO.getShippingAddress().setZipCode("123");
        return "register-customer";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") CustomerDTO.CREATE customerDTO , BindingResult result , Model model , HttpServletRequest request) throws IOException {
        if (result.hasErrors()) {
            return "register-customer";
        }

        List<CustomerDTO.LOGIN> customerDTOList = customerService.getAllCustomerForLogin();

        if (customerDTOList !=null){
            Iterator<CustomerDTO.LOGIN> iterator = customerDTOList.iterator();
            while (iterator.hasNext()){
                CustomerDTO.LOGIN dto = iterator.next();
                if (dto.getCustomerEmailAddress().equalsIgnoreCase(customerDTO.getCustomerEmailAddress())){
                    model.addAttribute("emailMsg","Email is already exist");
                    return "register-customer";
                }

                if (dto.getUserName().equalsIgnoreCase(customerDTO.getUserName())){
                    model.addAttribute("usernameMsg","username is already exist");
                    return "register-customer";
                }
            }
        }
        CartDTO.CREATE cartDTO = new CartDTO.CREATE();
        UserDTO.CREATE userDTO = new UserDTO.CREATE();
        AuthoritiesDTO.CREATE authoritiesDTO = new AuthoritiesDTO.CREATE();
        CartItemDTO.CREATE cartItemDTO = new CartItemDTO.CREATE();

        //persist  customer
        customerDTO.setEnabled(true);
        customerService.addCustomer(customerDTO);

        //persist cart
        cartDTO.setCustomer(customerDTO);
        cartDTO.setGrandTotal(0d);
        cartDTO.setCheckout(false);
        cartService.save(cartDTO);


        //add persisted card to customer
        customerDTO.setCart(cartDTO);
        customerService.updateCustomer(customerDTO);


//        //persist cartItem
//        cartItemDTO.setCart(cartDTO);
//        cartItemService.save(cartItemDTO);

        //persist user
        userDTO.setUsername(customerDTO.getUserName());
        userDTO.setPassword(customerDTO.getPassword());
        userDTO.setEnabled(true);
        userDTO.setCustomer(customerDTO);
        userService.save(userDTO);


        //persist authorities
        authoritiesDTO.setAuthorityType("ROLE_USER");
        authoritiesDTO.setUsername(customerDTO.getUserName());
        authoritiesService.save(authoritiesDTO);


        autoLogin(customerDTO,request);

        return "register-customer-success";
    }


    public void autoLogin(CustomerDTO.CREATE createDTO , HttpServletRequest request){

        String username = createDTO.getUserName();
        String password = createDTO.getPassword();

        request.getSession();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
