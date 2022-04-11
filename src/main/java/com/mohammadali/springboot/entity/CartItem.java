package com.mohammadali.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends Audit implements Serializable {


    private static final Long serialVersionUID = 102L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemID;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;


//    @ManyToOne(fetch = FetchType.EAGER)
//    private Product product;
//
//    @Column (name = "productId", insertable = false, updatable = false)
//    private Long productId;

    private int quantity;

    private double totalPrice;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(cartItemID, cartItem.cartItemID);
    }

}
