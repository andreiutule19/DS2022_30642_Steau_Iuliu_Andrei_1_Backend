package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.seed;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.EnergyConsumption;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.enums.Role;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.DeviceRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.EnergyConsumptionRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeed implements CommandLineRunner {
    private final UserRepository factory;
    private final EnergyConsumptionRepository factory2;

    private final DeviceRepository factory3;

    @Override
    public void run(String... args) throws Exception {


        if(factory.findAll().isEmpty()){
            User user = new User("andrei_steau@yahoo.com",
                    new BCryptPasswordEncoder().encode("role"),"Andrei",Role.ADMIN);
            factory.save(user);
            User user1 = new User("andrei_steau1@yahoo.com",
                    new BCryptPasswordEncoder().encode("role"),"Andrei",Role.ADMIN);
            factory.save(user1);
            User user2 = new User("andrei_steau2@yahoo.com",
                    new BCryptPasswordEncoder().encode("role"),"Andrei",Role.ADMIN);
            factory.save(user2);
        }
        if(factory3.findAll().isEmpty()){
            Device device = new Device("new Product","Strada necunoscuta",13F);
            factory3.save(device);
        }

    }
}
