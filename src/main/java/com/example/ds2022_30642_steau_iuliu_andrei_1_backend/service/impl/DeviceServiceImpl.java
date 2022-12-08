package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.impl;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder.DeviceBuilder;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.DeviceDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.exception.ResourceNotFoundException;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.DeviceRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device insert(DeviceDTO device) {
        return deviceRepository.save(DeviceBuilder.dtoForInsert(device));
    }

    @Override
    public Device update(DeviceDTO device) {
        if(device.getDeviceId() == 0) {
           throw new ResourceNotFoundException(String.valueOf(device.getDeviceId()));
        }
        Optional<Device> newDevice = deviceRepository.findDeviceByDeviceId(device.getDeviceId());
        if(!newDevice.isPresent()){
            throw new ResourceNotFoundException(String.valueOf(device.getDeviceId()));
        }
        newDevice.get().setDescription(device.getDescription());
        newDevice.get().setAddress(device.getAddress());
        newDevice.get().setMaxHoursSpent(device.getMaxHoursSpent());
        return deviceRepository.save(newDevice.get());
    }

    @Override
    public Device delete(DeviceDTO device) {
        Optional<Device>  deletedDevice = deviceRepository.findDeviceByDeviceId(device.getDeviceId());
        if(!deletedDevice.isPresent()){
            throw new ResourceNotFoundException("Invalid device ID");
        }
        deviceRepository.delete(deletedDevice.get());
        return deletedDevice.get();
    }

    @Override
    public List<DeviceDTO> findAll() {
        return DeviceBuilder.toDeviceDTOList(deviceRepository.findAll());
    }


    @Override
    public List<DeviceDTO> getDevicesWithoutUser() {
        return DeviceBuilder.toDeviceDTOList(deviceRepository.findAll().stream().filter(device -> device.getUserId() == null).collect(Collectors.toList()));
    }

    @Override
    public Device deleteAssociatedDevice(DeviceDTO device) {
        Optional<Device> newDevice = deviceRepository.findDeviceByDeviceId(device.getDeviceId());
        if(!newDevice.isPresent()){
            throw new ResourceNotFoundException("Invalid device ID");
        }
        newDevice.get().setUserId(null);
        return deviceRepository.save(newDevice.get());
    }


}
