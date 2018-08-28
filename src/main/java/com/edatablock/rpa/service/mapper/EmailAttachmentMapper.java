package com.edatablock.rpa.service.mapper;

import com.edatablock.rpa.domain.*;
import com.edatablock.rpa.service.dto.EmailAttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmailAttachment and its DTO EmailAttachmentDTO.
 */
@Mapper(componentModel = "spring", uses = {EmailProcessingMapper.class})
public interface EmailAttachmentMapper extends EntityMapper<EmailAttachmentDTO, EmailAttachment> {

    @Mapping(source = "emailProcessing.id", target = "emailProcessingId")
    EmailAttachmentDTO toDto(EmailAttachment emailAttachment);

    @Mapping(source = "emailProcessingId", target = "emailProcessing")
    @Mapping(target = "fileForOCRProcessing", ignore = true)
    EmailAttachment toEntity(EmailAttachmentDTO emailAttachmentDTO);

    default EmailAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setId(id);
        return emailAttachment;
    }
}
