package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.controller;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.*;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/login")
    ResponseEntity<ClientDTO> logIn(@Valid @RequestBody AuthDTO authDTO){
        return new ResponseEntity<>(userService.logIn(authDTO), HttpStatus.OK);
    }


    @GetMapping("/users")
    ResponseEntity<List<ClientDTO>> listAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/users/associate/{email}")
    ResponseEntity<Device> associateDevice(@Valid @PathVariable("email") String email, @Valid @RequestBody DeviceDTO device) {
        return new ResponseEntity<>(userService.associateDevice(email,device),HttpStatus.OK);
    }

    @PostMapping("/users/insert")
    ResponseEntity<ClientDTO> insert(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.insert(user),HttpStatus.CREATED);
    }

    @PostMapping("/users/update")
    ResponseEntity<UpdateUserDTO> update(@Valid @RequestBody UpdateUserDTO user) {
        return new ResponseEntity<>(userService.update(user),HttpStatus.OK);
    }

    @PostMapping("/users/change")
    ResponseEntity<ChangePassDTO> changePassword(@Valid @RequestBody ChangePassDTO user) {
        return new ResponseEntity<>(userService.changePassword(user),HttpStatus.OK);
    }

    @PostMapping("/users/delete")
    ResponseEntity<ClientDTO> delete(@Valid @RequestBody ClientDTO user) {
        return new ResponseEntity<>(userService.delete(user),HttpStatus.OK);
    }



    @GetMapping("/users/get/associated/{email}")
    ResponseEntity<List<DeviceDTO>> deleteAssociatedDevices(@Valid @PathVariable("email") String email) {
        return new ResponseEntity<>(userService.getAssociatedDevices(email),HttpStatus.OK);
    }



}
