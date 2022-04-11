package com.mohammadali.springboot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductsHistory extends Audit implements Serializable {

    private static final Long serialVersionUID = 110L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyID;

    @OneToMany
    private List<Product> products;

    @Column
    private Double price;




}
