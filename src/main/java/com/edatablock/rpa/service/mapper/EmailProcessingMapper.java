package com.edatablock.rpa.service.mapper;

import com.edatablock.rpa.domain.*;
import com.edatablock.rpa.service.dto.EmailProcessingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmailProcessing and its DTO EmailProcessingDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientEmailListMapper.class})
public interface EmailProcessingMapper extends EntityMapper<EmailProcessingDTO, EmailProcessing> {

    @Mapping(source = "clientEmailList.id", target = "clientEmailListId")
    EmailProcessingDTO toDto(EmailProcessing emailProcessing);

    @Mapping(source = "clientEmailListId", target = "clientEmailList")
    @Mapping(target = "emailProcessingError", ignore = true)
    EmailProcessing toEntity(EmailProcessingDTO emailProcessingDTO);

    default EmailProcessing fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailProcessing emailProcessing = new EmailProcessing();
        emailProcessing.setId(id);
        return emailProcessing;
    }
}
