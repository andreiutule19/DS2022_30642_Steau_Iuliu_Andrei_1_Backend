package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption,Long> {

    @Override
    EnergyConsumption save(EnergyConsumption energyConsumption);

    @Override
    List<EnergyConsumption> findAll();

    List<EnergyConsumption> deleteEnergyConsumptionByTimestampBetween(Timestamp timestamp, Timestamp timestamp2);

    List<EnergyConsumption> getEnergyConsumptionByDeviceIdAndAndTimestampBetween(Long deviceId,Timestamp timestamp, Timestamp timestamp2);

    List<EnergyConsumption> getEnergyConsumptionByDeviceId(Long deviceId);

}
