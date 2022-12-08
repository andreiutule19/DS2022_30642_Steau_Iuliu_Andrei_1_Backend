package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.EnergyDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.EnergyConsumption;

public class EnergyBuilder {

    public static EnergyConsumption dtoToEnergy(EnergyDTO energyDTO){
        return new EnergyConsumption(energyDTO.getEnergyUsed(), energyDTO.getDeviceId(),energyDTO.getTimestamp());
    }
}
