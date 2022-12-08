package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class AuthDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
    //private String token;

}
