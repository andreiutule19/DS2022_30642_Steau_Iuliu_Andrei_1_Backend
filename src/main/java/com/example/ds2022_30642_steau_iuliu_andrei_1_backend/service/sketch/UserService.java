package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.*;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;

import java.util.List;

public interface UserService {

    ClientDTO insert(User user);

    ClientDTO delete(ClientDTO clientDTO);

    UpdateUserDTO update(UpdateUserDTO user);

    ChangePassDTO changePassword(ChangePassDTO user);
    List<ClientDTO> findAll();

    ClientDTO logIn(AuthDTO authDTO);

    Device associateDevice(String email, DeviceDTO device);

    List<DeviceDTO> getAssociatedDevices(String email);

}
