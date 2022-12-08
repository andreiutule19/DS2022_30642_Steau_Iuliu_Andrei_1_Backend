package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.consumer;



import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.EnergyDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.SignalDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.DeviceRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.UserRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.EnergyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ReceiverRabbit implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ReceiverRabbit.class);
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private EnergyService energyService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(String measuresDTO) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Timestamp timestamp = Timestamp.from(Instant.now());
            EnergyDTO energySaved = objectMapper.readValue(measuresDTO, EnergyDTO.class);
            this.energyService.insert(energySaved);
            Float val = energyService.getSumEnergyHourly(energySaved.getDeviceId(),timestamp);
            Float valMaxAccepted = deviceRepository.
                    findDeviceByDeviceId(energySaved.getDeviceId()).get().getMaxHoursSpent();

            if( val > valMaxAccepted){
                User user = userRepository.
                        findUserByUserId(deviceRepository.
                                findDeviceByDeviceId(energySaved.getDeviceId()).get().getUserId()).
                        get();

                eventPublisher.publishEvent(new SignalDTO(user.getFullName(), user.getEmail(),energySaved.getDeviceId(),val-valMaxAccepted));
            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}