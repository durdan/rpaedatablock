package com.edatablock.rpa.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.edatablock.rpa.service.OrganizationsDetailsService;
import com.edatablock.rpa.web.rest.errors.BadRequestAlertException;
import com.edatablock.rpa.web.rest.util.HeaderUtil;
import com.edatablock.rpa.web.rest.util.PaginationUtil;
import com.edatablock.rpa.service.dto.OrganizationsDetailsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrganizationsDetails.
 */
@RestController
@RequestMapping("/api")
public class OrganizationsDetailsResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationsDetailsResource.class);

    private static final String ENTITY_NAME = "organizationsDetails";

    private final OrganizationsDetailsService organizationsDetailsService;

    public OrganizationsDetailsResource(OrganizationsDetailsService organizationsDetailsService) {
        this.organizationsDetailsService = organizationsDetailsService;
    }

    /**
     * POST  /organizations-details : Create a new organizationsDetails.
     *
     * @param organizationsDetailsDTO the organizationsDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new organizationsDetailsDTO, or with status 400 (Bad Request) if the organizationsDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/organizations-details")
    @Timed
    public ResponseEntity<OrganizationsDetailsDTO> createOrganizationsDetails(@RequestBody OrganizationsDetailsDTO organizationsDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save OrganizationsDetails : {}", organizationsDetailsDTO);
        if (organizationsDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new organizationsDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganizationsDetailsDTO result = organizationsDetailsService.save(organizationsDetailsDTO);
        return ResponseEntity.created(new URI("/api/organizations-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organizations-details : Updates an existing organizationsDetails.
     *
     * @param organizationsDetailsDTO the organizationsDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated organizationsDetailsDTO,
     * or with status 400 (Bad Request) if the organizationsDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the organizationsDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/organizations-details")
    @Timed
    public ResponseEntity<OrganizationsDetailsDTO> updateOrganizationsDetails(@RequestBody OrganizationsDetailsDTO organizationsDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update OrganizationsDetails : {}", organizationsDetailsDTO);
        if (organizationsDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganizationsDetailsDTO result = organizationsDetailsService.save(organizationsDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, organizationsDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organizations-details : get all the organizationsDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of organizationsDetails in body
     */
    @GetMapping("/organizations-details")
    @Timed
    public ResponseEntity<List<OrganizationsDetailsDTO>> getAllOrganizationsDetails(Pageable pageable) {
        log.debug("REST request to get a page of OrganizationsDetails");
        Page<OrganizationsDetailsDTO> page = organizationsDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/organizations-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /organizations-details/:id : get the "id" organizationsDetails.
     *
     * @param id the id of the organizationsDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the organizationsDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/organizations-details/{id}")
    @Timed
    public ResponseEntity<OrganizationsDetailsDTO> getOrganizationsDetails(@PathVariable Long id) {
        log.debug("REST request to get OrganizationsDetails : {}", id);
        Optional<OrganizationsDetailsDTO> organizationsDetailsDTO = organizationsDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizationsDetailsDTO);
    }

    /**
     * DELETE  /organizations-details/:id : delete the "id" organizationsDetails.
     *
     * @param id the id of the organizationsDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/organizations-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganizationsDetails(@PathVariable Long id) {
        log.debug("REST request to delete OrganizationsDetails : {}", id);
        organizationsDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
