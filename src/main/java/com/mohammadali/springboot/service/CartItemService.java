package com.mohammadali.springboot.service;

import org.MohammadAli.models.CartItemDTO;

import java.io.IOException;
import java.util.List;

public interface CartItemService {

    void save(CartItemDTO.CREATE cartItemDTO) throws IOException;

    List<CartItemDTO.RETRIEVE> findAll();

    void delete(CartItemDTO.DELETE deleteDTO);

    void update(CartItemDTO cartItemDTO);

    void remove(Long cartItemID);
}
