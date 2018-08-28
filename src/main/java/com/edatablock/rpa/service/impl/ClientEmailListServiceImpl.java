package com.edatablock.rpa.service.impl;

import com.edatablock.rpa.service.ClientEmailListService;
import com.edatablock.rpa.domain.ClientEmailList;
import com.edatablock.rpa.repository.ClientEmailListRepository;
import com.edatablock.rpa.service.dto.ClientEmailListDTO;
import com.edatablock.rpa.service.mapper.ClientEmailListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ClientEmailList.
 */
@Service
@Transactional
public class ClientEmailListServiceImpl implements ClientEmailListService {

    private final Logger log = LoggerFactory.getLogger(ClientEmailListServiceImpl.class);

    private final ClientEmailListRepository clientEmailListRepository;

    private final ClientEmailListMapper clientEmailListMapper;

    public ClientEmailListServiceImpl(ClientEmailListRepository clientEmailListRepository, ClientEmailListMapper clientEmailListMapper) {
        this.clientEmailListRepository = clientEmailListRepository;
        this.clientEmailListMapper = clientEmailListMapper;
    }

    /**
     * Save a clientEmailList.
     *
     * @param clientEmailListDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ClientEmailListDTO save(ClientEmailListDTO clientEmailListDTO) {
        log.debug("Request to save ClientEmailList : {}", clientEmailListDTO);
        ClientEmailList clientEmailList = clientEmailListMapper.toEntity(clientEmailListDTO);
        clientEmailList = clientEmailListRepository.save(clientEmailList);
        return clientEmailListMapper.toDto(clientEmailList);
    }

    /**
     * Get all the clientEmailLists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClientEmailListDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClientEmailLists");
        return clientEmailListRepository.findAll(pageable)
            .map(clientEmailListMapper::toDto);
    }


    /**
     * Get one clientEmailList by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClientEmailListDTO> findOne(Long id) {
        log.debug("Request to get ClientEmailList : {}", id);
        return clientEmailListRepository.findById(id)
            .map(clientEmailListMapper::toDto);
    }

    /**
     * Delete the clientEmailList by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientEmailList : {}", id);
        clientEmailListRepository.deleteById(id);
    }
}
