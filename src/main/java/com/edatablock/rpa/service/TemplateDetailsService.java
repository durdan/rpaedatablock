package com.edatablock.rpa.service;

import com.edatablock.rpa.service.dto.TemplateDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TemplateDetails.
 */
public interface TemplateDetailsService {

    /**
     * Save a templateDetails.
     *
     * @param templateDetailsDTO the entity to save
     * @return the persisted entity
     */
    TemplateDetailsDTO save(TemplateDetailsDTO templateDetailsDTO);

    /**
     * Get all the templateDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TemplateDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" templateDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TemplateDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" templateDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
