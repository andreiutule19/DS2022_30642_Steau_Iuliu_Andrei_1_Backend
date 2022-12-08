package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "EnergyConsumption")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EnergyConsumption {
    @Id
    @Column(name ="energy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long energyId;
    private Timestamp timestamp;
    private Float energyUsed;
    @Column(name ="device_id")
    private Long deviceId;



    public EnergyConsumption(Float energyUsed,Long deviceId,Timestamp timestamp){
        this.energyUsed= energyUsed;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
    }


}
