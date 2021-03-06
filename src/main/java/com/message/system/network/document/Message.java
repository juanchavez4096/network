package com.message.system.network.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.Date;

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
    private Date sendDate;
}
