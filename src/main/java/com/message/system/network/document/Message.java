package com.message.system.network.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    private String messageId;
    private String message;
    private String sender;
    private String channelId;
}
