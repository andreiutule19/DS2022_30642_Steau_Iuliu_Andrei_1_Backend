package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.impl;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder.EnergyBuilder;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.consumer.ReceiverRabbit;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.EnergyDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.EnergyConsumption;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.exception.ResourceNotFoundException;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.EnergyConsumptionRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.EnergyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnergyServiceImpl implements EnergyService {

    @Autowired
    EnergyConsumptionRepository energyRepository;

    private static final Logger logger = LoggerFactory.getLogger(EnergyServiceImpl.class);

    @Override
    public List<EnergyConsumption> deleteEnergy(Timestamp timestamp, Timestamp timestamp2) {
        return energyRepository.deleteEnergyConsumptionByTimestampBetween(timestamp,timestamp2);
    }

    @Override
    public List<EnergyConsumption> getEnergyBetween(Long deviceId,String timestamp,String timestamp2) {

        if(deviceId != 0) {
            try {
                long first = Long.parseLong(timestamp);
                long second = Long.parseLong(timestamp2);
                Timestamp ts1=new Timestamp(new Date(first).getTime());
                Timestamp ts2=new Timestamp(new Date(second).getTime());
                return energyRepository.getEnergyConsumptionByDeviceIdAndAndTimestampBetween(deviceId,ts1,ts2);
            }
            catch (NumberFormatException e){
                throw new ResourceNotFoundException("Nu e ok data");
            }
        }
        throw new ResourceNotFoundException("Ceva clar nu e ok");
    }

    @Override
    public List<EnergyConsumption> getEnergyByDeviceId(Long deviceId) {
        return energyRepository.getEnergyConsumptionByDeviceId(deviceId);
    }

    @Override
    public EnergyConsumption insert(EnergyDTO energyDTO) {
        return energyRepository.save(EnergyBuilder.dtoToEnergy(energyDTO));
    }

    @Override
    public Float getSumEnergyHourly(Long deviceId, Timestamp timestamp) {
        List<EnergyConsumption> energyConsumptions = energyRepository.getEnergyConsumptionByDeviceId(deviceId);
        List<EnergyConsumption> energyConsumptions1 = energyConsumptions.stream().filter(energyConsumption ->
                energyConsumption.getTimestamp().getDay() == timestamp.getDay() &&
                        energyConsumption.getTimestamp().getHours() == timestamp.getHours() &&
                        energyConsumption.getTimestamp().getMonth() == timestamp.getMonth() &&
                        energyConsumption.getTimestamp().getYear() == timestamp.getYear()).collect(Collectors.toList());
        for(EnergyConsumption ec : energyConsumptions1){
            logger.info("HEREEEEEEE"+ec.toString());
        }

        return energyConsumptions.stream().filter(energyConsumption ->
                energyConsumption.getTimestamp().getDay() == timestamp.getDay() &&
                        energyConsumption.getTimestamp().getHours() == timestamp.getHours() &&
                        energyConsumption.getTimestamp().getMonth() == timestamp.getMonth() &&
                        energyConsumption.getTimestamp().getYear() == timestamp.getYear()
                ).map(EnergyConsumption::getEnergyUsed).reduce( 0F,Float::sum);

    }
}
