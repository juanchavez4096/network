package com.message.system.network.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
@Getter
@Setter
@NoArgsConstructor
public class Photo {
    @Id
    private String id;
    private String title;
    private Binary image;

    public Photo(String title) {
        this.title = title;
    }
}
