package com.edatablock.rpa.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.edatablock.rpa.service.TemplateDetailsService;
import com.edatablock.rpa.web.rest.errors.BadRequestAlertException;
import com.edatablock.rpa.web.rest.util.HeaderUtil;
import com.edatablock.rpa.web.rest.util.PaginationUtil;
import com.edatablock.rpa.service.dto.TemplateDetailsDTO;
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
 * REST controller for managing TemplateDetails.
 */
@RestController
@RequestMapping("/api")
public class TemplateDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TemplateDetailsResource.class);

    private static final String ENTITY_NAME = "templateDetails";

    private final TemplateDetailsService templateDetailsService;

    public TemplateDetailsResource(TemplateDetailsService templateDetailsService) {
        this.templateDetailsService = templateDetailsService;
    }

    /**
     * POST  /template-details : Create a new templateDetails.
     *
     * @param templateDetailsDTO the templateDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new templateDetailsDTO, or with status 400 (Bad Request) if the templateDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/template-details")
    @Timed
    public ResponseEntity<TemplateDetailsDTO> createTemplateDetails(@RequestBody TemplateDetailsDTO templateDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save TemplateDetails : {}", templateDetailsDTO);
        if (templateDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new templateDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplateDetailsDTO result = templateDetailsService.save(templateDetailsDTO);
        return ResponseEntity.created(new URI("/api/template-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /template-details : Updates an existing templateDetails.
     *
     * @param templateDetailsDTO the templateDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated templateDetailsDTO,
     * or with status 400 (Bad Request) if the templateDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the templateDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/template-details")
    @Timed
    public ResponseEntity<TemplateDetailsDTO> updateTemplateDetails(@RequestBody TemplateDetailsDTO templateDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update TemplateDetails : {}", templateDetailsDTO);
        if (templateDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplateDetailsDTO result = templateDetailsService.save(templateDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, templateDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /template-details : get all the templateDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of templateDetails in body
     */
    @GetMapping("/template-details")
    @Timed
    public ResponseEntity<List<TemplateDetailsDTO>> getAllTemplateDetails(Pageable pageable) {
        log.debug("REST request to get a page of TemplateDetails");
        Page<TemplateDetailsDTO> page = templateDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/template-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /template-details/:id : get the "id" templateDetails.
     *
     * @param id the id of the templateDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the templateDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/template-details/{id}")
    @Timed
    public ResponseEntity<TemplateDetailsDTO> getTemplateDetails(@PathVariable Long id) {
        log.debug("REST request to get TemplateDetails : {}", id);
        Optional<TemplateDetailsDTO> templateDetailsDTO = templateDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templateDetailsDTO);
    }

    /**
     * DELETE  /template-details/:id : delete the "id" templateDetails.
     *
     * @param id the id of the templateDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/template-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteTemplateDetails(@PathVariable Long id) {
        log.debug("REST request to delete TemplateDetails : {}", id);
        templateDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
