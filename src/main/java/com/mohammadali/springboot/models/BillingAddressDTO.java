package com.mohammadali.springboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BillingAddressDTO {

    @NotEmpty
    private String streetName;

    @NotEmpty
    private String apartmentNumber;

    @NotEmpty
    private String city;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String state;

    @NotEmpty
    private String country;

    public static class CREATE extends BillingAddressDTO{

    }

    public static class DELETE extends BillingAddressDTO{

    }

    public static class RETRIEVE extends BillingAddressDTO{

    }
}
