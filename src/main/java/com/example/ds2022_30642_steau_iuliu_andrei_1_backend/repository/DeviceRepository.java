package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    @Override
    Device save(Device device);

    @Override
    List<Device> findAll();

    Optional<Device> findDeviceByDeviceId(Long deviceId);




}
