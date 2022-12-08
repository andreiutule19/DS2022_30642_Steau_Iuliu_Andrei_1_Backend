package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class EnergyDTO {
    private Long energyId;
    private Timestamp timestamp;
    private Float energyUsed;
    private Long deviceId;

}