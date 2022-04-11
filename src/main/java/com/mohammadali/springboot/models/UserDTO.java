package com.mohammadali.springboot.models;


import lombok.*;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {

    private String username;

    private String password;

    private boolean enabled;



    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE extends UserDTO{
        private CustomerDTO.CREATE customer;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DELETE extends UserDTO{
        private Long userID;
    }

    public static class RETRIEVE extends UserDTO{

    }


}

