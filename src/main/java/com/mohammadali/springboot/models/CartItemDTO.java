package com.mohammadali.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CartItemDTO {

    private Long cartItemID;


    @Setter
    @Getter
    @NoArgsConstructor
    public static class CREATE extends CartItemDTO{


        private ProductDTO.RETRIEVE product;

        private CartDTO.RETRIEVE cart;

        private int quantity;

        private double totalPrice;
    }

    public static class DELETE extends CartItemDTO{

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class RETRIEVE extends CartItemDTO{

//        @JsonIgnore
        private ProductDTO.RETRIEVE product;

        @JsonIgnore
        private CartDTO.RETRIEVE cart;

        private int quantity;

        private double totalPrice;
    }


    @Setter
    @Getter
    @NoArgsConstructor
    public static class INFO extends CartItemDTO {
        private CartDTO.INFO cart;

        private int quantity;

        private double totalPrice;
    }
}
