package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class ClientDTO {
    @NotNull
    private String email;
    @NotNull
    private String fullName;
    @NotNull
    private Role role;

    List<Device> devices;
}
