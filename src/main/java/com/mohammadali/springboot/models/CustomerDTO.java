package com.mohammadali.springboot.models;

import lombok.*;
import org.MohammadAli.data.entities.BillingAddress;
import org.MohammadAli.data.entities.ShippingAddress;
import org.MohammadAli.validators.Phone;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerDTO {

    @NotEmpty
    @Size(min = 4 , max = 10 )
    private String customerName;

    @NotEmpty
    @Email()
    private String customerEmailAddress;

    @NotEmpty
    @Phone
    private String customerPhoneNumber;

    @NotEmpty
    @Size(min = 3 , max = 10)
    private String userName;

    @NotEmpty
    private String password;

    private boolean enabled;

    @Valid
    private ShippingAddress shippingAddress;

    @Valid
    private BillingAddress billingAddress;


    @Setter
    @Getter
    public static class CREATE extends CustomerDTO{
        private CartDTO.CREATE cart;
        private Long customerID;
    }


    public static class REGISTER extends CustomerDTO{

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RETRIEVE extends CustomerDTO{

        private Long customerID;
        private CartDTO.RETRIEVE cart;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DELETE extends CustomerDTO{

        private Long customerID;
        private CartDTO.DELETE cart;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LOGIN {

        @NotEmpty
        @Size(min = 3 , max = 10)
        private String userName;

        @NotEmpty
        @Email()
        private String customerEmailAddress;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ADDRESS{
        @Valid
        private ShippingAddress shippingAddress;

        @Valid
        private BillingAddress billingAddress;

        @NotEmpty
        @Size(min = 3 , max = 10)
        private String userName;


        private Long customerID;
    }

    @Data
    public static class INFO{

        private Long customerID;


        @NotEmpty
        @Size(min = 4 , max = 10 )
        private String customerName;

    }

}
