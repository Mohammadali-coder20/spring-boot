package com.mohammadali.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends Audit implements Serializable {


    private static final Long serialVersionUID = 101L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany( mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.EAGER , orphanRemoval = true)
    private List<CartItem> cartItems;

//    @OneToMany( mappedBy = "cart",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER , orphanRemoval = true)
//    private List<CartItem> cartItems;

    private Double grandTotal;

    private boolean isCheckout;

}
