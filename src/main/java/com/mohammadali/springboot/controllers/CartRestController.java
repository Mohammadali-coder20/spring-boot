package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import org.MohammadAli.models.CartDTO;
import org.MohammadAli.models.CartItemDTO;
import org.MohammadAli.models.CustomerDTO;
import org.MohammadAli.models.ProductDTO;
import org.MohammadAli.services.CartItemService;
import org.MohammadAli.services.CartService;
import org.MohammadAli.services.CustomerService;
import org.MohammadAli.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/cart")
public class CartRestController {

    private CustomerService customerService;
    private ProductService productService;
    private CartItemService cartItemService;
    private CartService cartService;
    private ModelMapper mapper;

    @RequestMapping(value = "/add/{productID}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@AuthenticationPrincipal User activeUser, @PathVariable("productID") long productID) throws IOException {
        CustomerDTO.RETRIEVE customer = customerService.getCustomerCartAndCustomerIdByUsername(activeUser.getUsername());
        CartDTO.RETRIEVE cart = customer.getCart();
        ProductDTO.RETRIEVE product = productService.findProductByID(productID);
        List<CartItemDTO.RETRIEVE> cartItems = cart.getCartItems();
        boolean isProductExistingInProductItems = false;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getProductID() == productID){
                isProductExistingInProductItems = true;
                CartItemDTO.RETRIEVE cartItemDTO = cartItems.get(i);
                cartItemDTO.setQuantity(cartItemDTO.getQuantity()+1);
                cartItemDTO.setTotalPrice(product.getProductPrice()* cartItemDTO.getQuantity());
                cartItemService.update(cartItemDTO);
            }
        }
        if (!isProductExistingInProductItems){
            CartItemDTO.CREATE cartItem = new CartItemDTO.CREATE();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(1);
            cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
            cartItemService.save(cartItem);
        }
    }

    @RequestMapping(value = "/{cartId}" , method = RequestMethod.GET)
    public CartDTO.RETRIEVE refreshCart(@PathVariable("cartId") Long cartId){
        CartDTO.RETRIEVE cart = cartService.findCartByID(cartId);
//        CartDTO.TEST map = mapper.map(cart, CartDTO.TEST.class);
        return cart;
    }

    @RequestMapping(value = "/remove/{productID}" , method = RequestMethod.DELETE)
    public void removeProductFromCart(@PathVariable("productID") Long productID , @AuthenticationPrincipal User activeUser){
        CustomerDTO.RETRIEVE customer = customerService.getCustomerCartAndCustomerIdByUsername(activeUser.getUsername());
        List<CartItemDTO.RETRIEVE> cartItems = customer.getCart().getCartItems();
//        cartItemService.remove(customer.getCart().getCartId());

                Iterator<CartItemDTO.RETRIEVE> iterator = cartItems.iterator();
        while (iterator.hasNext()){
            CartItemDTO.RETRIEVE nextCartItem = iterator.next();
            if (nextCartItem.getProduct().getProductID() == productID){
                cartItemService.remove(nextCartItem.getCartItemID());
                break;
            }
        }

//        Iterator<CartItemDTO.RETRIEVE> iterator = cartItems.iterator();
//        while (iterator.hasNext()){
//            CartItemDTO.RETRIEVE nextCartItem = iterator.next();
//            if (nextCartItem.getProduct().getProductID() == productID){
//                cartService.removeCartItem(nextCartItem);
//                break;
//            }
//        }



//        for (int i = 0 ; i<cartItems.size() ; i++){
//            if (cartItems.get(i).getProduct().getProductID() == productID){
//                cartItems.remove(i);
//                break;
//            }
//        }
//        CartDTO.RETRIEVE cart = customer.getCart();
//        cart.setCartItems(cartItems);
//        cartService.update(cart);


//        while(iterator.hasNext()){
//            CartItemDTO.RETRIEVE next = iterator.next();
//            if (next.getProduct().getProductID() == productID){
//                cartItemService.remove(productID);
//            }
//        }
    }

    @RequestMapping(value = "/{cartID}", method = RequestMethod.DELETE)
    public void clearCart(@PathVariable("cartID") Long cartID){
        cartService.clearCart(cartID);
    }



}
