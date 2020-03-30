package com.message.system.network.repository;

import com.message.system.network.document.Message;
import com.message.system.network.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    @Tailable
    public Flux<Message> findWithTailableCursorByChannelId(String channelId);
}
