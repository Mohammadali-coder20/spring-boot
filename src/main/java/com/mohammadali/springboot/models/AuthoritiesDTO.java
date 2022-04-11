package com.mohammadali.springboot.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthoritiesDTO {

    private String username;

    private String authorityType;

    public static class CREATE extends AuthoritiesDTO{

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DELETE extends AuthoritiesDTO{
        private Long authoritiesID;
    }

    public static class RETRIEVE extends AuthoritiesDTO{

    }

}
