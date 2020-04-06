package com.message.system.network.controller;

import com.message.system.network.convertor.MessageMapper;
import com.message.system.network.document.Message;
import com.message.system.network.dto.MessageRequestDTO;
import com.message.system.network.dto.MessageResponseDTO;
import com.message.system.network.repository.MessageRepository;
import com.message.system.network.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("api/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Message> save(@Valid @RequestBody MessageRequestDTO messageRequestDTO) {
        Message message = MessageMapper.INSTANCE.messageRequestDTOToMessage(messageRequestDTO);
        message.setSendDate(new Date());
        return messageRepository.save(message);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageResponseDTO> streamMessages(@RequestParam String channelId, @RequestParam(required = false) String messageId, @RequestParam(required = false) String timezone){

        if (messageId != null && !messageId.isEmpty()){
            return messageRepository.findById(messageId)
                    .flatMapMany(message -> messageRepository.findWithTailableCursorByChannelIdAndSendDateGreaterThanEqual(channelId, message.getSendDate())
                            .map(m -> {
                                try {
                                    return mapResponseDto(timezone, m);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }))
                    .switchIfEmpty(getMessagesWithOutMessageId(channelId, timezone));
        }else{
            return getMessagesWithOutMessageId(channelId, timezone);
        }

    }

    private MessageResponseDTO mapResponseDto(String timezone, Message message) throws ParseException {
        if (timezone != null && !timezone.isEmpty()){
            Date sendDate = message.getSendDate() != null ? DateUtils.dateFromUtc(message.getSendDate(), timezone) : null;
            MessageResponseDTO messageResponseDTO = MessageMapper.INSTANCE.messageToMessageResponseDTO(message);
            messageResponseDTO.setSendDate(sendDate);
            return messageResponseDTO;
        }else{
            Date sendDate = message.getSendDate();
            MessageResponseDTO messageResponseDTO = MessageMapper.INSTANCE.messageToMessageResponseDTO(message);
            messageResponseDTO.setSendDate(sendDate);
            return messageResponseDTO;
        }
    }

    private Flux<MessageResponseDTO> getMessagesWithOutMessageId(String channelId, String timezone) {
        return messageRepository.findWithTailableCursorByChannelId(channelId)
                .map(m -> {
                    try {
                        return mapResponseDto(timezone, m);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }
}
