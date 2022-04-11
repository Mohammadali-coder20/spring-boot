package com.mohammadali.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
//@Accessors(chain = true)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDTO {


    private Double grandTotal;

    private boolean isCheckout;

    @Setter
    @Getter
    public static class CREATE extends CartDTO{
        private CustomerDTO.CREATE customer;
        private Long cartId;
        private List<CartItemDTO.CREATE> cartItems;

    }

    public static class DELETE extends CartDTO{

    }

    @Setter
    @Getter
    @NoArgsConstructor
//    @JsonIgnore(ign)
    public static class RETRIEVE extends CartDTO{
        private Long cartId;
//        @JsonIgnore
        private List<CartItemDTO.RETRIEVE> cartItems;
        @JsonIgnore
        private CustomerDTO.RETRIEVE customer;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class INFO extends CartDTO{
        private Long cartId;
        private List<CartItemDTO.RETRIEVE> cartItems;

    }

}
