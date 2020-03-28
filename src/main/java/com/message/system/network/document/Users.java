package com.message.system.network.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserSubscription")
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    private String id;

    private String name;
    private String creationDate;
    private String lastSignIn;
}
