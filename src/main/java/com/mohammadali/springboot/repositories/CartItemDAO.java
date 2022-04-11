package com.mohammadali.springboot.repositories;

import org.MohammadAli.data.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem , Long> {

//    @Modifying
//    void removeByCart_CartId(Long cartID);


    @Modifying
    @Query("delete from CartItem c where c.cartItemID = :cartItemID")
    void removeCartItemByID(@Param("cartItemID") Long cartItemID);
}
