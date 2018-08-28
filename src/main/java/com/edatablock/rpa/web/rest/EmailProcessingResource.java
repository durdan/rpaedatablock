package com.edatablock.rpa.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.edatablock.rpa.service.EmailProcessingService;
import com.edatablock.rpa.web.rest.errors.BadRequestAlertException;
import com.edatablock.rpa.web.rest.util.HeaderUtil;
import com.edatablock.rpa.web.rest.util.PaginationUtil;
import com.edatablock.rpa.service.dto.EmailProcessingDTO;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing EmailProcessing.
 */
@RestController
@RequestMapping("/api")
public class EmailProcessingResource {

    private final Logger log = LoggerFactory.getLogger(EmailProcessingResource.class);

    private static final String ENTITY_NAME = "emailProcessing";

    private final EmailProcessingService emailProcessingService;

    public EmailProcessingResource(EmailProcessingService emailProcessingService) {
        this.emailProcessingService = emailProcessingService;
    }

    /**
     * POST  /email-processings : Create a new emailProcessing.
     *
     * @param emailProcessingDTO the emailProcessingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emailProcessingDTO, or with status 400 (Bad Request) if the emailProcessing has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/email-processings")
    @Timed
    public ResponseEntity<EmailProcessingDTO> createEmailProcessing(@RequestBody EmailProcessingDTO emailProcessingDTO) throws URISyntaxException {
        log.debug("REST request to save EmailProcessing : {}", emailProcessingDTO);
        if (emailProcessingDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailProcessing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailProcessingDTO result = emailProcessingService.save(emailProcessingDTO);
        return ResponseEntity.created(new URI("/api/email-processings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /email-processings : Updates an existing emailProcessing.
     *
     * @param emailProcessingDTO the emailProcessingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emailProcessingDTO,
     * or with status 400 (Bad Request) if the emailProcessingDTO is not valid,
     * or with status 500 (Internal Server Error) if the emailProcessingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/email-processings")
    @Timed
    public ResponseEntity<EmailProcessingDTO> updateEmailProcessing(@RequestBody EmailProcessingDTO emailProcessingDTO) throws URISyntaxException {
        log.debug("REST request to update EmailProcessing : {}", emailProcessingDTO);
        if (emailProcessingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailProcessingDTO result = emailProcessingService.save(emailProcessingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emailProcessingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /email-processings : get all the emailProcessings.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of emailProcessings in body
     */
    @GetMapping("/email-processings")
    @Timed
    public ResponseEntity<List<EmailProcessingDTO>> getAllEmailProcessings(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("emailprocessingerror-is-null".equals(filter)) {
            log.debug("REST request to get all EmailProcessings where emailProcessingError is null");
            return new ResponseEntity<>(emailProcessingService.findAllWhereEmailProcessingErrorIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of EmailProcessings");
        Page<EmailProcessingDTO> page = emailProcessingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/email-processings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /email-processings/:id : get the "id" emailProcessing.
     *
     * @param id the id of the emailProcessingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emailProcessingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/email-processings/{id}")
    @Timed
    public ResponseEntity<EmailProcessingDTO> getEmailProcessing(@PathVariable Long id) {
        log.debug("REST request to get EmailProcessing : {}", id);
        Optional<EmailProcessingDTO> emailProcessingDTO = emailProcessingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailProcessingDTO);
    }

    /**
     * DELETE  /email-processings/:id : delete the "id" emailProcessing.
     *
     * @param id the id of the emailProcessingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/email-processings/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmailProcessing(@PathVariable Long id) {
        log.debug("REST request to delete EmailProcessing : {}", id);
        emailProcessingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
