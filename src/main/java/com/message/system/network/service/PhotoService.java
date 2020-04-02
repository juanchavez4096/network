package com.message.system.network.service;

import com.message.system.network.document.Photo;
import com.message.system.network.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    /*public Mono<String> addPhoto(Flux<FilePart> filePartFlux) throws IOException {

        return filePartFlux.flatMap(filePart ->
                filePart.content().map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];

                    return bytes;
                })
                        .map(this::processAndGetLinesAsList)
                        .flatMapIterable(Function.identity());

        Photo photo = new Photo(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return photoRepository.insert(photo).map(Photo::getId);
    }*/

    public Mono<Photo> getPhoto(String id) {
        return photoRepository.findById(id);
    }


}
