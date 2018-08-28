package com.edatablock.rpa.service.impl;

import com.edatablock.rpa.service.OrganizationsDetailsService;
import com.edatablock.rpa.domain.OrganizationsDetails;
import com.edatablock.rpa.repository.OrganizationsDetailsRepository;
import com.edatablock.rpa.service.dto.OrganizationsDetailsDTO;
import com.edatablock.rpa.service.mapper.OrganizationsDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing OrganizationsDetails.
 */
@Service
@Transactional
public class OrganizationsDetailsServiceImpl implements OrganizationsDetailsService {

    private final Logger log = LoggerFactory.getLogger(OrganizationsDetailsServiceImpl.class);

    private final OrganizationsDetailsRepository organizationsDetailsRepository;

    private final OrganizationsDetailsMapper organizationsDetailsMapper;

    public OrganizationsDetailsServiceImpl(OrganizationsDetailsRepository organizationsDetailsRepository, OrganizationsDetailsMapper organizationsDetailsMapper) {
        this.organizationsDetailsRepository = organizationsDetailsRepository;
        this.organizationsDetailsMapper = organizationsDetailsMapper;
    }

    /**
     * Save a organizationsDetails.
     *
     * @param organizationsDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrganizationsDetailsDTO save(OrganizationsDetailsDTO organizationsDetailsDTO) {
        log.debug("Request to save OrganizationsDetails : {}", organizationsDetailsDTO);
        OrganizationsDetails organizationsDetails = organizationsDetailsMapper.toEntity(organizationsDetailsDTO);
        organizationsDetails = organizationsDetailsRepository.save(organizationsDetails);
        return organizationsDetailsMapper.toDto(organizationsDetails);
    }

    /**
     * Get all the organizationsDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrganizationsDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrganizationsDetails");
        return organizationsDetailsRepository.findAll(pageable)
            .map(organizationsDetailsMapper::toDto);
    }


    /**
     * Get one organizationsDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationsDetailsDTO> findOne(Long id) {
        log.debug("Request to get OrganizationsDetails : {}", id);
        return organizationsDetailsRepository.findById(id)
            .map(organizationsDetailsMapper::toDto);
    }

    /**
     * Delete the organizationsDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrganizationsDetails : {}", id);
        organizationsDetailsRepository.deleteById(id);
    }
}
