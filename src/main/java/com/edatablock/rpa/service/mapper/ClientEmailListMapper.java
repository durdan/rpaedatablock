package com.edatablock.rpa.service.mapper;

import com.edatablock.rpa.domain.*;
import com.edatablock.rpa.service.dto.ClientEmailListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ClientEmailList and its DTO ClientEmailListDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface ClientEmailListMapper extends EntityMapper<ClientEmailListDTO, ClientEmailList> {

    @Mapping(source = "client.id", target = "clientId")
    ClientEmailListDTO toDto(ClientEmailList clientEmailList);

    @Mapping(source = "clientId", target = "client")
    ClientEmailList toEntity(ClientEmailListDTO clientEmailListDTO);

    default ClientEmailList fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClientEmailList clientEmailList = new ClientEmailList();
        clientEmailList.setId(id);
        return clientEmailList;
    }
}
