package com.message.system.network.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequestDTO {
    private String message;
    private String sender;
    private String channelId;
}
