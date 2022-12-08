package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.controller;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.SignalDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.EnergyConsumption;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.EnergyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class EnergyController {
    @Autowired
    private final EnergyService energyService;

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/energy/{deviceId}/{timestamp}/{timestamp2}")
    ResponseEntity<List<EnergyConsumption>> getEnergyBetween(@Valid @PathVariable("deviceId") Long deviceId,
                                            @Valid @PathVariable("timestamp") String timestamp,
                                            @Valid @PathVariable("timestamp2") String timestamp2){
        return new ResponseEntity<>(energyService.getEnergyBetween(deviceId,timestamp,timestamp2), HttpStatus.OK);
    }


    @GetMapping("/energy/{deviceId}")
    ResponseEntity<List<EnergyConsumption>> getEnergyByDeviceId(@Valid @PathVariable("deviceId") Long deviceId){
        return new ResponseEntity<>(energyService.getEnergyByDeviceId(deviceId), HttpStatus.OK);
    }

    @EventListener(SignalDTO.class)
    public void handleEvent(SignalDTO event) {
        log.info("Got an event: {}.", event);
        messagingTemplate.convertAndSend("/topic/events", event);
    }




}
