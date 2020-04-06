package com.message.system.network.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponseDTO {
    private String messageId;
    private String message;
    private String sender;
    private String channelId;
    private Date sendDate;
}
