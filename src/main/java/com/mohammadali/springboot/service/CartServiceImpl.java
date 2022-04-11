package com.mohammadali.springboot.service;

import org.MohammadAli.data.CartDAO;
import org.MohammadAli.data.entities.Cart;
import org.MohammadAli.data.entities.CartItem;
import org.MohammadAli.data.entities.Customer;
import org.MohammadAli.models.CartDTO;
import org.MohammadAli.models.CartItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {


    @Autowired
    CartDAO cartDAO;

    @Autowired
    ModelMapper mapper;

//    @Autowired
//    Cart cart;

    @Override
    @Transactional
    public CartDTO.RETRIEVE findCartByID(Long cartID) {
        Cart cart = cartDAO.findById(cartID).get();
        return mapper.map(cart, CartDTO.RETRIEVE.class);
    }

    @Override
    @Transactional
    public void save(CartDTO.CREATE cartDTO) throws IOException {
        Cart cart = mapper.map(cartDTO, Cart.class);
        cartDAO.save(cart);
        cartDTO.setCartId(cart.getCartId());
    }

    @Override
    @Transactional
    public List<CartDTO.RETRIEVE> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void delete(CartDTO.DELETE deleteDTO) {

    }

    @Override
    @Transactional
    public void update(CartDTO.RETRIEVE cart) {
        cartDAO.save(mapper.map(cart, Cart.class));
    }

    @Override
    @Transactional
    public void removeCartItem(CartItemDTO.RETRIEVE nextCartItem) {
        cartDAO.removeCartItem(mapper.map(nextCartItem, CartItem.class).getCartItemID());
    }

    @Override
    @Transactional
    public void clearCart(Long cartID) {
        cartDAO.clearCart(cartID);
    }

    @Override
    @Transactional
    public CartDTO.RETRIEVE findCartByIDAndMakeItReadyToCheckout(long cartID) {
        CartDTO.RETRIEVE cartByID = findCartByID(cartID);
        cartByID.setGrandTotal(calculateCartGrandTotal(cartByID));
        return cartByID;
    }

    @Override
    public void createNewCartForCustomerAndCheckoutCurrentCart(Long customerID , Long cartID) {
        cartDAO.checkoutCart(cartID);
        Cart cart = new Cart();
        cart.setCustomer(new Customer());
        cart.getCustomer().setCustomerID(customerID);
        cart.setGrandTotal(0D);
        cart.setCheckout(false);
        cartDAO.save(cart);
    }

    @Transactional
    public Cart validateCustomerCart(Long cartID) throws IOException {
        Cart cart = cartDAO.findById(cartID).orElseThrow(() -> new IOException("no such cart is existed"));
        if (cart.getCartItems().size() == 0) throw new IOException("cart with'" + cartID + "'id have no items");
        return cart;
    }

    @Transactional
    public double calculateCartGrandTotal(CartDTO.RETRIEVE dto) {
        List<CartItemDTO.RETRIEVE> cartItems = dto.getCartItems();
        double grandTotal = 0D;
        for (CartItemDTO.RETRIEVE cartItem : cartItems) {
            grandTotal += cartItem.getTotalPrice();
        }
        cartDAO.updateCalculatedCartGrandTotal(grandTotal, dto.getCartId());
        return grandTotal;
    }


}
