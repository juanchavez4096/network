package com.message.system.network.controller;

import com.message.system.network.document.Message;
import com.message.system.network.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Message> postChat(@Valid @RequestBody Message chatMessage){
        return messageRepository.save(chatMessage);
    }
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> streamMessages(@RequestParam String channelId){
        return messageRepository.findWithTailableCursorByChannelId(channelId);
    }
}
