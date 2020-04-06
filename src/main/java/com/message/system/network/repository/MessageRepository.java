package com.message.system.network.repository;

import com.message.system.network.document.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    @Tailable
    Flux<Message> findWithTailableCursorByChannelId(String channelId);

    @Tailable
    Flux<Message> findWithTailableCursorByChannelIdAndSendDateGreaterThanEqual(String channelId, Date sendDate);
}
