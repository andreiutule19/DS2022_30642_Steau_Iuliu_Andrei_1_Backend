package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Setter
@Getter
public class DeviceDTO {
    @NotNull
    private Long deviceId;
    @NotNull
    private String description;
    @NotNull
    private String address;
    @NotNull
    private Float maxHoursSpent;
    @NotNull
    private Long userId;


}
