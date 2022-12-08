package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.DeviceDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;

import java.util.List;

public interface DeviceService {

    Device insert(DeviceDTO device);

    Device update(DeviceDTO device);

    Device delete(DeviceDTO device);
    List<DeviceDTO> findAll();

    List<DeviceDTO> getDevicesWithoutUser();

    Device deleteAssociatedDevice(DeviceDTO device);
}
