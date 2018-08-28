package com.edatablock.rpa.service.impl;

import com.edatablock.rpa.service.EmailProcessingService;
import com.edatablock.rpa.domain.EmailProcessing;
import com.edatablock.rpa.repository.EmailProcessingRepository;
import com.edatablock.rpa.service.dto.EmailProcessingDTO;
import com.edatablock.rpa.service.mapper.EmailProcessingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
/**
 * Service Implementation for managing EmailProcessing.
 */
@Service
@Transactional
public class EmailProcessingServiceImpl implements EmailProcessingService {

    private final Logger log = LoggerFactory.getLogger(EmailProcessingServiceImpl.class);

    private final EmailProcessingRepository emailProcessingRepository;

    private final EmailProcessingMapper emailProcessingMapper;

    public EmailProcessingServiceImpl(EmailProcessingRepository emailProcessingRepository, EmailProcessingMapper emailProcessingMapper) {
        this.emailProcessingRepository = emailProcessingRepository;
        this.emailProcessingMapper = emailProcessingMapper;
    }

    /**
     * Save a emailProcessing.
     *
     * @param emailProcessingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmailProcessingDTO save(EmailProcessingDTO emailProcessingDTO) {
        log.debug("Request to save EmailProcessing : {}", emailProcessingDTO);
        EmailProcessing emailProcessing = emailProcessingMapper.toEntity(emailProcessingDTO);
        emailProcessing = emailProcessingRepository.save(emailProcessing);
        return emailProcessingMapper.toDto(emailProcessing);
    }

    /**
     * Get all the emailProcessings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmailProcessingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmailProcessings");
        return emailProcessingRepository.findAll(pageable)
            .map(emailProcessingMapper::toDto);
    }



    /**
     *  get all the emailProcessings where EmailProcessingError is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EmailProcessingDTO> findAllWhereEmailProcessingErrorIsNull() {
        log.debug("Request to get all emailProcessings where EmailProcessingError is null");
        return StreamSupport
            .stream(emailProcessingRepository.findAll().spliterator(), false)
            .filter(emailProcessing -> emailProcessing.getEmailProcessingError() == null)
            .map(emailProcessingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one emailProcessing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmailProcessingDTO> findOne(Long id) {
        log.debug("Request to get EmailProcessing : {}", id);
        return emailProcessingRepository.findById(id)
            .map(emailProcessingMapper::toDto);
    }

    /**
     * Delete the emailProcessing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmailProcessing : {}", id);
        emailProcessingRepository.deleteById(id);
    }
}
