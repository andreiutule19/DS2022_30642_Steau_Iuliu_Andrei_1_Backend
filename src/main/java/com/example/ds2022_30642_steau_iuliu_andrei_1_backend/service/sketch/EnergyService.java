package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.EnergyDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.EnergyConsumption;

import java.sql.Timestamp;
import java.util.List;

public interface EnergyService {
    List<EnergyConsumption> deleteEnergy(Timestamp timestamp, Timestamp timestamp2);

    List<EnergyConsumption> getEnergyBetween(Long deviceId,String timestamp, String timestamp2);

    List<EnergyConsumption> getEnergyByDeviceId(Long deviceId);

    EnergyConsumption insert(EnergyDTO energyDTO);

    Float getSumEnergyHourly(Long deviceId,Timestamp timestamp);

}
