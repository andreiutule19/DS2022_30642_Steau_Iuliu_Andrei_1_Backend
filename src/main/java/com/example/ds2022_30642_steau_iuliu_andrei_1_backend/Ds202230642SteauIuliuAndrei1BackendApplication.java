package com.example.ds2022_30642_steau_iuliu_andrei_1_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Ds202230642SteauIuliuAndrei1BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ds202230642SteauIuliuAndrei1BackendApplication.class, args);
    }

}
