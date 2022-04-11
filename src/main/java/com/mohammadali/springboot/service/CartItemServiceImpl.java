package com.mohammadali.springboot.service;

import org.MohammadAli.data.CartItemDAO;
import org.MohammadAli.data.entities.CartItem;
import org.MohammadAli.models.CartItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemDAO cartItemDAO;

    @Autowired
    ModelMapper mapper;

    @Override
    @Transactional
    public void save(CartItemDTO.CREATE cartItemDTO) throws IOException {
        CartItem cartItem = mapper.map(cartItemDTO, CartItem.class);
        cartItemDAO.save(cartItem);
    }

    @Override
    @Transactional
    public List<CartItemDTO.RETRIEVE> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void delete(CartItemDTO.DELETE deleteDTO) {

    }

    @Override
    @Transactional
    public void update(CartItemDTO cartItemDTO) {
        cartItemDAO.save(mapper.map(cartItemDTO , CartItem.class));
    }

    @Override
    @Transactional
    public void remove(Long cartItem) {
        cartItemDAO.removeCartItemByID(cartItem);
    }
}
