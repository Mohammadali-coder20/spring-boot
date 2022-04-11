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
public class OrderedProduct extends Audit implements Serializable {

    private static final Long serialVersionUID = 112L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column
    private String productName;

    @Column
    private String productBrand;

    @Column
    private String productModel;

    @Column
    private String productPrice;

    @Column
    private Long productDiscount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
 }
