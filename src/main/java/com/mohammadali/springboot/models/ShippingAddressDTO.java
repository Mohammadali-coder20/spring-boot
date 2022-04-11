package com.mohammadali.springboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ShippingAddressDTO {
    @NotEmpty
    private String streetName;

    @NotEmpty
    private String apartmentNumber;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String country;

    @NotEmpty
    private String zipCode;

    public static class CREATE extends ShippingAddressDTO{

    };

    public static class DELETE extends ShippingAddressDTO{

    };

    public static class RETRIEVE extends ShippingAddressDTO{

    };

}
