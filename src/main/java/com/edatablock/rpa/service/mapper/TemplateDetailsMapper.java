package com.edatablock.rpa.service.mapper;

import com.edatablock.rpa.domain.*;
import com.edatablock.rpa.service.dto.TemplateDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TemplateDetails and its DTO TemplateDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface TemplateDetailsMapper extends EntityMapper<TemplateDetailsDTO, TemplateDetails> {

    @Mapping(source = "client.id", target = "clientId")
    TemplateDetailsDTO toDto(TemplateDetails templateDetails);

    @Mapping(source = "clientId", target = "client")
    TemplateDetails toEntity(TemplateDetailsDTO templateDetailsDTO);

    default TemplateDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        TemplateDetails templateDetails = new TemplateDetails();
        templateDetails.setId(id);
        return templateDetails;
    }
}
