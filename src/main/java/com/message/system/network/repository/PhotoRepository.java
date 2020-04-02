package com.message.system.network.repository;

import com.message.system.network.document.Photo;
import com.message.system.network.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PhotoRepository extends ReactiveMongoRepository<Photo, String> {
}
