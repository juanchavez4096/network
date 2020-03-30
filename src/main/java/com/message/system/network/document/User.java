package com.message.system.network.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "UserSubscription")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String userId;


    private String name;
    private Date creationDate;
    private Date lastSignIn;
}
