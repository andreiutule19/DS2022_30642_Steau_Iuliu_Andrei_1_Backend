package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.DeviceDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DeviceBuilder {
    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(device.getDeviceId(), device.getDescription(),
                device.getAddress(), device.getMaxHoursSpent(),device.getUserId());
    }

    public static Device dtoForInsert(DeviceDTO device) {
        return new Device(device.getDescription(), device.getAddress(), device.getMaxHoursSpent());
    }

    public static List<DeviceDTO> toDeviceDTOList(List<Device> devices) {
        return devices.stream().map(DeviceBuilder::toDeviceDTO).collect(Collectors.toList());
    }





}
