package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Device")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Device {
    @Id
    @Column(name ="device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    private String description;
    private String address;
    private Float maxHoursSpent;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name="device_id", referencedColumnName = "device_id")
    private List<EnergyConsumption> energyConsumptionList;

    @Column(name="user_id")
    private Long userId;

    public Device(String description,String address,Float maxHoursSpent){
        this.energyConsumptionList = new LinkedList<>();
        this.description = description;
        this.address = address;
        this.maxHoursSpent = maxHoursSpent;
    }


}
