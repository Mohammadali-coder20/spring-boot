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
public class CustomerContact extends Audit implements Serializable {


    private static final Long serialVersionUID = 108L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactID;


    @Lob
    private String contactInfo;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;


}
