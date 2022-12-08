package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
public class UpdateUserDTO {
    @NotNull
    private String oldEmail;
    @NotNull
    private String newEmail;
    @NotNull
    private String fullName;
    @NotNull
    private Role role;
}
