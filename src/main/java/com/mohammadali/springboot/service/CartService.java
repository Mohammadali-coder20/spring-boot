package com.mohammadali.springboot.service;

import org.MohammadAli.models.CartDTO;
import org.MohammadAli.models.CartItemDTO;

import java.io.IOException;
import java.util.List;

public interface CartService {

    CartDTO.RETRIEVE findCartByID(Long cartID);

    void save(CartDTO.CREATE cartDTO) throws IOException;

    List<CartDTO.RETRIEVE> findAll();

    void delete(CartDTO.DELETE deleteDTO);

    void update(CartDTO.RETRIEVE cart);

    void removeCartItem(CartItemDTO.RETRIEVE nextCartItem);

    void clearCart(Long cartID);

    CartDTO.RETRIEVE findCartByIDAndMakeItReadyToCheckout(long cartID);

    void createNewCartForCustomerAndCheckoutCurrentCart(Long customerID , Long cartID);
}
