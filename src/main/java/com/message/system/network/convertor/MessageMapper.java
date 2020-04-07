package com.message.system.network.convertor;

import com.message.system.network.document.Message;
import com.message.system.network.model.MessageRequestDTO;
import com.message.system.network.model.MessageResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

	MessageMapper INSTANCE = Mappers.getMapper( MessageMapper.class );

	@Mappings({
			@Mapping(target = "messageId", ignore = true),
			@Mapping(target = "sendDate", ignore = true),
	})
	Message messageRequestDTOToMessage(MessageRequestDTO messageRequestDTO);

	@Mappings({
			@Mapping(target = "sendDate", ignore = true),
	})
	MessageResponseDTO messageToMessageResponseDTO(Message message);


}
