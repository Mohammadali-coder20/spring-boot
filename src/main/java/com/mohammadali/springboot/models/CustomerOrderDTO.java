package com.mohammadali.springboot.models;

import lombok.*;
import org.MohammadAli.data.entities.BillingAddress;
import org.MohammadAli.data.entities.ShippingAddress;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerOrderDTO implements Serializable {

    private ShippingAddress shippingAddress;

    private BillingAddress billingAddress;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class CREATE extends CustomerOrderDTO{

        private CartDTO.RETRIEVE cart;

        private CustomerDTO.RETRIEVE customer;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class DELETE extends CustomerOrderDTO{

        private Long customerOrderID;

        private CartDTO.DELETE cart;

        private CustomerDTO.DELETE customer;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class RETRIEVE extends CustomerOrderDTO{

        private Long customerOrderID;

        private CartDTO.RETRIEVE cart;

        private CustomerDTO.RETRIEVE customer;

    }
}
