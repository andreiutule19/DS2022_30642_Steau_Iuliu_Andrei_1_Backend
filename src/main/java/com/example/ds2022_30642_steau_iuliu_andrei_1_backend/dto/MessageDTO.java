package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MessageDTO {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
    private Boolean seen;
}
