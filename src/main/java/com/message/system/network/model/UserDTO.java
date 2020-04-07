package com.message.system.network.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;

    private String name;
    private String creationDate;
    private String lastSignIn;
}
