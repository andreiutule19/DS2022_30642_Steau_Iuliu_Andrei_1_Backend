package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import lombok.Getter;

@Getter
public class ChangePassDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
