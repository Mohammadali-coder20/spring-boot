package com.mohammadali.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder extends Audit implements Serializable {

    private static final Long serialVersionUID = 107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerOrderID;


    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;


    @OneToOne
    @JoinColumn(name = "shippingAddressId")
    private ShippingAddress shippingAddress;


    @OneToOne
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;



}
