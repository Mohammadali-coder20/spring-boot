package com.mohammadali.springboot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee_TLB")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @JsonProperty("fName")
    private String firstName;

    private String lastName;

    private String email;

}
