package com.message.system.network.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Photo {
    @Id
    private String id;
    private String userId;

    public Photo(String id) {
        this.id = id;
    }
}
