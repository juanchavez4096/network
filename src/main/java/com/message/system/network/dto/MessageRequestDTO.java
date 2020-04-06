package com.message.system.network.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequestDTO {
    private String message;
    private String sender;
    private String channelId;
}
