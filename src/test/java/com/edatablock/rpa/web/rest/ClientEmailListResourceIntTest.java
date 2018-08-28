package com.edatablock.rpa.web.rest;

import com.edatablock.rpa.RpaedatablockApp;

import com.edatablock.rpa.domain.ClientEmailList;
import com.edatablock.rpa.repository.ClientEmailListRepository;
import com.edatablock.rpa.service.ClientEmailListService;
import com.edatablock.rpa.service.dto.ClientEmailListDTO;
import com.edatablock.rpa.service.mapper.ClientEmailListMapper;
import com.edatablock.rpa.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.edatablock.rpa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ClientEmailListResource REST controller.
 *
 * @see ClientEmailListResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpaedatablockApp.class)
public class ClientEmailListResourceIntTest {

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_ACTIVE = 1;
    private static final Integer UPDATED_IS_ACTIVE = 2;

    @Autowired
    private ClientEmailListRepository clientEmailListRepository;


    @Autowired
    private ClientEmailListMapper clientEmailListMapper;
    

    @Autowired
    private ClientEmailListService clientEmailListService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClientEmailListMockMvc;

    private ClientEmailList clientEmailList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClientEmailListResource clientEmailListResource = new ClientEmailListResource(clientEmailListService);
        this.restClientEmailListMockMvc = MockMvcBuilders.standaloneSetup(clientEmailListResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientEmailList createEntity(EntityManager em) {
        ClientEmailList clientEmailList = new ClientEmailList()
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .description(DEFAULT_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE);
        return clientEmailList;
    }

    @Before
    public void initTest() {
        clientEmailList = createEntity(em);
    }

    @Test
    @Transactional
    public void createClientEmailList() throws Exception {
        int databaseSizeBeforeCreate = clientEmailListRepository.findAll().size();

        // Create the ClientEmailList
        ClientEmailListDTO clientEmailListDTO = clientEmailListMapper.toDto(clientEmailList);
        restClientEmailListMockMvc.perform(post("/api/client-email-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientEmailListDTO)))
            .andExpect(status().isCreated());

        // Validate the ClientEmailList in the database
        List<ClientEmailList> clientEmailListList = clientEmailListRepository.findAll();
        assertThat(clientEmailListList).hasSize(databaseSizeBeforeCreate + 1);
        ClientEmailList testClientEmailList = clientEmailListList.get(clientEmailListList.size() - 1);
        assertThat(testClientEmailList.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testClientEmailList.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testClientEmailList.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createClientEmailListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientEmailListRepository.findAll().size();

        // Create the ClientEmailList with an existing ID
        clientEmailList.setId(1L);
        ClientEmailListDTO clientEmailListDTO = clientEmailListMapper.toDto(clientEmailList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientEmailListMockMvc.perform(post("/api/client-email-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientEmailListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientEmailList in the database
        List<ClientEmailList> clientEmailListList = clientEmailListRepository.findAll();
        assertThat(clientEmailListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClientEmailLists() throws Exception {
        // Initialize the database
        clientEmailListRepository.saveAndFlush(clientEmailList);

        // Get all the clientEmailListList
        restClientEmailListMockMvc.perform(get("/api/client-email-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientEmailList.getId().intValue())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)));
    }
    

    @Test
    @Transactional
    public void getClientEmailList() throws Exception {
        // Initialize the database
        clientEmailListRepository.saveAndFlush(clientEmailList);

        // Get the clientEmailList
        restClientEmailListMockMvc.perform(get("/api/client-email-lists/{id}", clientEmailList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clientEmailList.getId().intValue()))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE));
    }
    @Test
    @Transactional
    public void getNonExistingClientEmailList() throws Exception {
        // Get the clientEmailList
        restClientEmailListMockMvc.perform(get("/api/client-email-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientEmailList() throws Exception {
        // Initialize the database
        clientEmailListRepository.saveAndFlush(clientEmailList);

        int databaseSizeBeforeUpdate = clientEmailListRepository.findAll().size();

        // Update the clientEmailList
        ClientEmailList updatedClientEmailList = clientEmailListRepository.findById(clientEmailList.getId()).get();
        // Disconnect from session so that the updates on updatedClientEmailList are not directly saved in db
        em.detach(updatedClientEmailList);
        updatedClientEmailList
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE);
        ClientEmailListDTO clientEmailListDTO = clientEmailListMapper.toDto(updatedClientEmailList);

        restClientEmailListMockMvc.perform(put("/api/client-email-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientEmailListDTO)))
            .andExpect(status().isOk());

        // Validate the ClientEmailList in the database
        List<ClientEmailList> clientEmailListList = clientEmailListRepository.findAll();
        assertThat(clientEmailListList).hasSize(databaseSizeBeforeUpdate);
        ClientEmailList testClientEmailList = clientEmailListList.get(clientEmailListList.size() - 1);
        assertThat(testClientEmailList.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testClientEmailList.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testClientEmailList.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingClientEmailList() throws Exception {
        int databaseSizeBeforeUpdate = clientEmailListRepository.findAll().size();

        // Create the ClientEmailList
        ClientEmailListDTO clientEmailListDTO = clientEmailListMapper.toDto(clientEmailList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restClientEmailListMockMvc.perform(put("/api/client-email-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientEmailListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientEmailList in the database
        List<ClientEmailList> clientEmailListList = clientEmailListRepository.findAll();
        assertThat(clientEmailListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClientEmailList() throws Exception {
        // Initialize the database
        clientEmailListRepository.saveAndFlush(clientEmailList);

        int databaseSizeBeforeDelete = clientEmailListRepository.findAll().size();

        // Get the clientEmailList
        restClientEmailListMockMvc.perform(delete("/api/client-email-lists/{id}", clientEmailList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClientEmailList> clientEmailListList = clientEmailListRepository.findAll();
        assertThat(clientEmailListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientEmailList.class);
        ClientEmailList clientEmailList1 = new ClientEmailList();
        clientEmailList1.setId(1L);
        ClientEmailList clientEmailList2 = new ClientEmailList();
        clientEmailList2.setId(clientEmailList1.getId());
        assertThat(clientEmailList1).isEqualTo(clientEmailList2);
        clientEmailList2.setId(2L);
        assertThat(clientEmailList1).isNotEqualTo(clientEmailList2);
        clientEmailList1.setId(null);
        assertThat(clientEmailList1).isNotEqualTo(clientEmailList2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientEmailListDTO.class);
        ClientEmailListDTO clientEmailListDTO1 = new ClientEmailListDTO();
        clientEmailListDTO1.setId(1L);
        ClientEmailListDTO clientEmailListDTO2 = new ClientEmailListDTO();
        assertThat(clientEmailListDTO1).isNotEqualTo(clientEmailListDTO2);
        clientEmailListDTO2.setId(clientEmailListDTO1.getId());
        assertThat(clientEmailListDTO1).isEqualTo(clientEmailListDTO2);
        clientEmailListDTO2.setId(2L);
        assertThat(clientEmailListDTO1).isNotEqualTo(clientEmailListDTO2);
        clientEmailListDTO1.setId(null);
        assertThat(clientEmailListDTO1).isNotEqualTo(clientEmailListDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(clientEmailListMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(clientEmailListMapper.fromId(null)).isNull();
    }
}
