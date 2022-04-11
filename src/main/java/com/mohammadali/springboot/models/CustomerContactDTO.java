package com.mohammadali.springboot.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerContactDTO {


    private String contactInfo;


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CREATE extends CustomerContactDTO{
        private CustomerDTO.INFO customer;
    }

    @Data
    public static class DELETE extends CustomerContactDTO{

        private Long contactID;
    }

    @Setter
    @Getter
    public static class RETRIEVE extends CustomerContactDTO{

        private Long contactID;

        private CustomerDTO.INFO customer;

        private Date updateDate;
    }
}
