package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.controller;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.MessageDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.SeenDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.exception.ResourceNotFoundException;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageDTO receivePublicMessage(@Payload MessageDTO messageDTO){
        return messageDTO;
    }

    @MessageMapping("/private-message")
    public MessageDTO receivePrivateMessage(@Payload MessageDTO messageDTO){
        simpMessagingTemplate.convertAndSendToUser(messageDTO.getReceiverName(),"/private",messageDTO);
        return messageDTO;
    }


    @MessageMapping("/private-seen")
    public SeenDTO seen(@Payload SeenDTO messageDTO){
        simpMessagingTemplate.convertAndSendToUser(messageDTO.getReceiverName(),"/seen",messageDTO);
        return  messageDTO;
    }
}
