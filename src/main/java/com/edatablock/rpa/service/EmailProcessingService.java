package com.edatablock.rpa.service;

import com.edatablock.rpa.service.dto.EmailProcessingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EmailProcessing.
 */
public interface EmailProcessingService {

    /**
     * Save a emailProcessing.
     *
     * @param emailProcessingDTO the entity to save
     * @return the persisted entity
     */
    EmailProcessingDTO save(EmailProcessingDTO emailProcessingDTO);

    /**
     * Get all the emailProcessings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EmailProcessingDTO> findAll(Pageable pageable);
    /**
     * Get all the EmailProcessingDTO where EmailProcessingError is null.
     *
     * @return the list of entities
     */
    List<EmailProcessingDTO> findAllWhereEmailProcessingErrorIsNull();


    /**
     * Get the "id" emailProcessing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmailProcessingDTO> findOne(Long id);

    /**
     * Delete the "id" emailProcessing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
