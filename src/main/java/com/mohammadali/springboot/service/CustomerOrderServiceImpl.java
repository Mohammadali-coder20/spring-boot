package com.mohammadali.springboot.service;


import org.MohammadAli.data.CustomerOrderDAO;
import org.MohammadAli.data.entities.Cart;
import org.MohammadAli.data.entities.Customer;
import org.MohammadAli.data.entities.CustomerOrder;
import org.MohammadAli.models.CustomerOrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    CustomerOrderDAO customerOrderDAO;

    @Autowired
    CartService cartService;

    @Autowired
    ModelMapper mapper;

    @Override
    @Transactional
    public void save(CustomerOrderDTO.CREATE customerOrderDTO) throws IOException {

    }

    @Override
    @Transactional
    public List<CustomerOrderDTO.RETRIEVE> findAll() {
        List<CustomerOrder> orders = customerOrderDAO.findAll();
        return orders.stream()
                .map(customerOrder -> mapper.map(customerOrder, CustomerOrderDTO.RETRIEVE.class))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deleteByID(Long cartID) {
        customerOrderDAO.deleteById(cartID);
    }

    @Override
    public CustomerOrderDTO.RETRIEVE findById(Long cartID) {
        CustomerOrder order = customerOrderDAO.findById(cartID).orElseThrow(() -> new RuntimeException("Receipt not found"));
        return mapper.map(order , CustomerOrderDTO.RETRIEVE.class);
    }

    @Transactional
    public void addCustomerOrder(CustomerOrder order) throws IOException {

        Cart cart = order.getCart();

        Customer customer = cart.getCustomer();
        order.setCustomer(customer);
        order.setBillingAddress(customer.getBillingAddress());
        order.setShippingAddress(customer.getShippingAddress());
        customerOrderDAO.save(order);
//        cartService.clearCart(cart.getCartId());
        cartService.createNewCartForCustomerAndCheckoutCurrentCart(customer.getCustomerID() , cart.getCartId());
//        CartDTO.CREATE newCustomerCart = new CartDTO.CREATE();
//        newCustomerCart.setCustomer(new CustomerDTO.CREATE());
//        newCustomerCart.getCustomer().setCustomerID(customer.getCustomerID());
//        cartService.save(newCustomerCart);
    }
}
