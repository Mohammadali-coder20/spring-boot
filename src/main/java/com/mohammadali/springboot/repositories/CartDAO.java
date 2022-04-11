package com.mohammadali.springboot.repositories;

import org.MohammadAli.data.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends JpaRepository<Cart , Long> {

    @Modifying
    @Query("delete from CartItem c where c.cartItemID = :cartItemID")
    void removeCartItem(@Param("cartItemID") Long cartItemID);


    @Modifying
    @Query("delete from CartItem c where c.cart.cartId = :cartID")
    void clearCart(@Param("cartID") Long cartID);

    @Modifying
    @Query(value = "update Cart c set c.grandTotal =:grandTotal where c.cartId =:cartID ")
    void updateCalculatedCartGrandTotal(@Param("grandTotal") Double grandTotal , @Param("cartID") Long CartID);

    @Modifying
    @Query("update Cart c set c.isCheckout = true where c.cartId =:cartID")
    void checkoutCart(@Param("cartID") Long cartID);
}
