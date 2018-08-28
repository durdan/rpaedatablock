package com.edatablock.rpa.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.edatablock.rpa.service.ClientEmailListService;
import com.edatablock.rpa.web.rest.errors.BadRequestAlertException;
import com.edatablock.rpa.web.rest.util.HeaderUtil;
import com.edatablock.rpa.web.rest.util.PaginationUtil;
import com.edatablock.rpa.service.dto.ClientEmailListDTO;
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
 * REST controller for managing ClientEmailList.
 */
@RestController
@RequestMapping("/api")
public class ClientEmailListResource {

    private final Logger log = LoggerFactory.getLogger(ClientEmailListResource.class);

    private static final String ENTITY_NAME = "clientEmailList";

    private final ClientEmailListService clientEmailListService;

    public ClientEmailListResource(ClientEmailListService clientEmailListService) {
        this.clientEmailListService = clientEmailListService;
    }

    /**
     * POST  /client-email-lists : Create a new clientEmailList.
     *
     * @param clientEmailListDTO the clientEmailListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientEmailListDTO, or with status 400 (Bad Request) if the clientEmailList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/client-email-lists")
    @Timed
    public ResponseEntity<ClientEmailListDTO> createClientEmailList(@RequestBody ClientEmailListDTO clientEmailListDTO) throws URISyntaxException {
        log.debug("REST request to save ClientEmailList : {}", clientEmailListDTO);
        if (clientEmailListDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientEmailList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientEmailListDTO result = clientEmailListService.save(clientEmailListDTO);
        return ResponseEntity.created(new URI("/api/client-email-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /client-email-lists : Updates an existing clientEmailList.
     *
     * @param clientEmailListDTO the clientEmailListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientEmailListDTO,
     * or with status 400 (Bad Request) if the clientEmailListDTO is not valid,
     * or with status 500 (Internal Server Error) if the clientEmailListDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/client-email-lists")
    @Timed
    public ResponseEntity<ClientEmailListDTO> updateClientEmailList(@RequestBody ClientEmailListDTO clientEmailListDTO) throws URISyntaxException {
        log.debug("REST request to update ClientEmailList : {}", clientEmailListDTO);
        if (clientEmailListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClientEmailListDTO result = clientEmailListService.save(clientEmailListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clientEmailListDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /client-email-lists : get all the clientEmailLists.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of clientEmailLists in body
     */
    @GetMapping("/client-email-lists")
    @Timed
    public ResponseEntity<List<ClientEmailListDTO>> getAllClientEmailLists(Pageable pageable) {
        log.debug("REST request to get a page of ClientEmailLists");
        Page<ClientEmailListDTO> page = clientEmailListService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/client-email-lists");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /client-email-lists/:id : get the "id" clientEmailList.
     *
     * @param id the id of the clientEmailListDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clientEmailListDTO, or with status 404 (Not Found)
     */
    @GetMapping("/client-email-lists/{id}")
    @Timed
    public ResponseEntity<ClientEmailListDTO> getClientEmailList(@PathVariable Long id) {
        log.debug("REST request to get ClientEmailList : {}", id);
        Optional<ClientEmailListDTO> clientEmailListDTO = clientEmailListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientEmailListDTO);
    }

    /**
     * DELETE  /client-email-lists/:id : delete the "id" clientEmailList.
     *
     * @param id the id of the clientEmailListDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/client-email-lists/{id}")
    @Timed
    public ResponseEntity<Void> deleteClientEmailList(@PathVariable Long id) {
        log.debug("REST request to delete ClientEmailList : {}", id);
        clientEmailListService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
