package com.edatablock.rpa.service;

import com.edatablock.rpa.service.dto.OrganizationsDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrganizationsDetails.
 */
public interface OrganizationsDetailsService {

    /**
     * Save a organizationsDetails.
     *
     * @param organizationsDetailsDTO the entity to save
     * @return the persisted entity
     */
    OrganizationsDetailsDTO save(OrganizationsDetailsDTO organizationsDetailsDTO);

    /**
     * Get all the organizationsDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrganizationsDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" organizationsDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrganizationsDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" organizationsDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
