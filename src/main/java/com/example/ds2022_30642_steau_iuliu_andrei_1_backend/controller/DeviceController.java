package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.controller;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.DeviceDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DeviceController {
    @Autowired
    private final DeviceService deviceService;

    @GetMapping(value="/devices")
    public ResponseEntity<List<DeviceDTO>> findAll(){
        return new ResponseEntity<>(deviceService.findAll(),HttpStatus.OK);
    }

    @GetMapping(value="/devices/nullUser")
    public ResponseEntity<List<DeviceDTO>> findAllNullUser(){
        return new ResponseEntity<>(deviceService.getDevicesWithoutUser(),HttpStatus.OK);
    }

    @PostMapping(value="/devices/insert")
    public ResponseEntity<Device> insert(@Valid @RequestBody DeviceDTO device){
        return new ResponseEntity<>(deviceService.insert(device),HttpStatus.OK);
    }

    @PostMapping(value="/devices/update")
    public ResponseEntity<Device> update(@Valid @RequestBody DeviceDTO device){
        return new ResponseEntity<>(deviceService.update(device),HttpStatus.OK);
    }

    @PostMapping(value = "/devices/delete")
    public ResponseEntity<Device> delete(@Valid @RequestBody DeviceDTO device){
        return new ResponseEntity<>(deviceService.delete(device),HttpStatus.OK);
    }

    @PostMapping("/devices/delete/associate")
    ResponseEntity<Device> deleteAssociatedDevices(@Valid @RequestBody DeviceDTO device) {
        return new ResponseEntity<>(deviceService.deleteAssociatedDevice(device),HttpStatus.OK);
    }


}
