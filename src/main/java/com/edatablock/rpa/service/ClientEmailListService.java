package com.edatablock.rpa.service;

import com.edatablock.rpa.service.dto.ClientEmailListDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ClientEmailList.
 */
public interface ClientEmailListService {

    /**
     * Save a clientEmailList.
     *
     * @param clientEmailListDTO the entity to save
     * @return the persisted entity
     */
    ClientEmailListDTO save(ClientEmailListDTO clientEmailListDTO);

    /**
     * Get all the clientEmailLists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ClientEmailListDTO> findAll(Pageable pageable);


    /**
     * Get the "id" clientEmailList.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ClientEmailListDTO> findOne(Long id);

    /**
     * Delete the "id" clientEmailList.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
