package com.edatablock.rpa.service.mapper;

import com.edatablock.rpa.domain.*;
import com.edatablock.rpa.service.dto.OrganizationsDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrganizationsDetails and its DTO OrganizationsDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganizationsDetailsMapper extends EntityMapper<OrganizationsDetailsDTO, OrganizationsDetails> {



    default OrganizationsDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganizationsDetails organizationsDetails = new OrganizationsDetails();
        organizationsDetails.setId(id);
        return organizationsDetails;
    }
}
