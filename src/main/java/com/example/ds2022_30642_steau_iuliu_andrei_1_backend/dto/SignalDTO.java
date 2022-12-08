package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignalDTO {
    private String fullName;
    private String email;
    private Long deviceId;
    private Float overflow;

}
