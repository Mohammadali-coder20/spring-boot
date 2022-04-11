package com.mohammadali.springboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String username;

    private String password;

    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
}
