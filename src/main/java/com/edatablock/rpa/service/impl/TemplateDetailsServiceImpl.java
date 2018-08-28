package com.edatablock.rpa.service.impl;

import com.edatablock.rpa.service.TemplateDetailsService;
import com.edatablock.rpa.domain.TemplateDetails;
import com.edatablock.rpa.repository.TemplateDetailsRepository;
import com.edatablock.rpa.service.dto.TemplateDetailsDTO;
import com.edatablock.rpa.service.mapper.TemplateDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing TemplateDetails.
 */
@Service
@Transactional
public class TemplateDetailsServiceImpl implements TemplateDetailsService {

    private final Logger log = LoggerFactory.getLogger(TemplateDetailsServiceImpl.class);

    private final TemplateDetailsRepository templateDetailsRepository;

    private final TemplateDetailsMapper templateDetailsMapper;

    public TemplateDetailsServiceImpl(TemplateDetailsRepository templateDetailsRepository, TemplateDetailsMapper templateDetailsMapper) {
        this.templateDetailsRepository = templateDetailsRepository;
        this.templateDetailsMapper = templateDetailsMapper;
    }

    /**
     * Save a templateDetails.
     *
     * @param templateDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TemplateDetailsDTO save(TemplateDetailsDTO templateDetailsDTO) {
        log.debug("Request to save TemplateDetails : {}", templateDetailsDTO);
        TemplateDetails templateDetails = templateDetailsMapper.toEntity(templateDetailsDTO);
        templateDetails = templateDetailsRepository.save(templateDetails);
        return templateDetailsMapper.toDto(templateDetails);
    }

    /**
     * Get all the templateDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TemplateDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TemplateDetails");
        return templateDetailsRepository.findAll(pageable)
            .map(templateDetailsMapper::toDto);
    }


    /**
     * Get one templateDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TemplateDetailsDTO> findOne(Long id) {
        log.debug("Request to get TemplateDetails : {}", id);
        return templateDetailsRepository.findById(id)
            .map(templateDetailsMapper::toDto);
    }

    /**
     * Delete the templateDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TemplateDetails : {}", id);
        templateDetailsRepository.deleteById(id);
    }
}
