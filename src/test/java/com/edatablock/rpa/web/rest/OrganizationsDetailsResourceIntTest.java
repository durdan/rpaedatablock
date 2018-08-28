package com.edatablock.rpa.web.rest;

import com.edatablock.rpa.RpaedatablockApp;

import com.edatablock.rpa.domain.OrganizationsDetails;
import com.edatablock.rpa.repository.OrganizationsDetailsRepository;
import com.edatablock.rpa.service.OrganizationsDetailsService;
import com.edatablock.rpa.service.dto.OrganizationsDetailsDTO;
import com.edatablock.rpa.service.mapper.OrganizationsDetailsMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.edatablock.rpa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrganizationsDetailsResource REST controller.
 *
 * @see OrganizationsDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpaedatablockApp.class)
public class OrganizationsDetailsResourceIntTest {

    private static final String DEFAULT_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ORGANISATION_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ORGANISATION_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_ACTIVE = 1;
    private static final Integer UPDATED_IS_ACTIVE = 2;

    private static final String DEFAULT_EMAIL_SERVER_HOST = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_SERVER_HOST = "BBBBBBBBBB";

    private static final Integer DEFAULT_EMAIL_SERVER_PORT = 1;
    private static final Integer UPDATED_EMAIL_SERVER_PORT = 2;

    private static final String DEFAULT_EMAIL_SERVER_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_SERVER_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_SERVER_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_SERVER_PASSWORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_EMAIL_SERVER_ACCESS_ALLOWED = 1;
    private static final Integer UPDATED_IS_EMAIL_SERVER_ACCESS_ALLOWED = 2;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private OrganizationsDetailsRepository organizationsDetailsRepository;


    @Autowired
    private OrganizationsDetailsMapper organizationsDetailsMapper;
    

    @Autowired
    private OrganizationsDetailsService organizationsDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrganizationsDetailsMockMvc;

    private OrganizationsDetails organizationsDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrganizationsDetailsResource organizationsDetailsResource = new OrganizationsDetailsResource(organizationsDetailsService);
        this.restOrganizationsDetailsMockMvc = MockMvcBuilders.standaloneSetup(organizationsDetailsResource)
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
    public static OrganizationsDetails createEntity(EntityManager em) {
        OrganizationsDetails organizationsDetails = new OrganizationsDetails()
            .organizationName(DEFAULT_ORGANIZATION_NAME)
            .description(DEFAULT_DESCRIPTION)
            .organisationAddress(DEFAULT_ORGANISATION_ADDRESS)
            .organisationEmail(DEFAULT_ORGANISATION_EMAIL)
            .isActive(DEFAULT_IS_ACTIVE)
            .emailServerHost(DEFAULT_EMAIL_SERVER_HOST)
            .emailServerPort(DEFAULT_EMAIL_SERVER_PORT)
            .emailServerUserId(DEFAULT_EMAIL_SERVER_USER_ID)
            .emailServerPassword(DEFAULT_EMAIL_SERVER_PASSWORD)
            .isEmailServerAccessAllowed(DEFAULT_IS_EMAIL_SERVER_ACCESS_ALLOWED)
            .createDate(DEFAULT_CREATE_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updatedBy(DEFAULT_UPDATED_BY);
        return organizationsDetails;
    }

    @Before
    public void initTest() {
        organizationsDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganizationsDetails() throws Exception {
        int databaseSizeBeforeCreate = organizationsDetailsRepository.findAll().size();

        // Create the OrganizationsDetails
        OrganizationsDetailsDTO organizationsDetailsDTO = organizationsDetailsMapper.toDto(organizationsDetails);
        restOrganizationsDetailsMockMvc.perform(post("/api/organizations-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizationsDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the OrganizationsDetails in the database
        List<OrganizationsDetails> organizationsDetailsList = organizationsDetailsRepository.findAll();
        assertThat(organizationsDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        OrganizationsDetails testOrganizationsDetails = organizationsDetailsList.get(organizationsDetailsList.size() - 1);
        assertThat(testOrganizationsDetails.getOrganizationName()).isEqualTo(DEFAULT_ORGANIZATION_NAME);
        assertThat(testOrganizationsDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrganizationsDetails.getOrganisationAddress()).isEqualTo(DEFAULT_ORGANISATION_ADDRESS);
        assertThat(testOrganizationsDetails.getOrganisationEmail()).isEqualTo(DEFAULT_ORGANISATION_EMAIL);
        assertThat(testOrganizationsDetails.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrganizationsDetails.getEmailServerHost()).isEqualTo(DEFAULT_EMAIL_SERVER_HOST);
        assertThat(testOrganizationsDetails.getEmailServerPort()).isEqualTo(DEFAULT_EMAIL_SERVER_PORT);
        assertThat(testOrganizationsDetails.getEmailServerUserId()).isEqualTo(DEFAULT_EMAIL_SERVER_USER_ID);
        assertThat(testOrganizationsDetails.getEmailServerPassword()).isEqualTo(DEFAULT_EMAIL_SERVER_PASSWORD);
        assertThat(testOrganizationsDetails.getIsEmailServerAccessAllowed()).isEqualTo(DEFAULT_IS_EMAIL_SERVER_ACCESS_ALLOWED);
        assertThat(testOrganizationsDetails.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testOrganizationsDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrganizationsDetails.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testOrganizationsDetails.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createOrganizationsDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizationsDetailsRepository.findAll().size();

        // Create the OrganizationsDetails with an existing ID
        organizationsDetails.setId(1L);
        OrganizationsDetailsDTO organizationsDetailsDTO = organizationsDetailsMapper.toDto(organizationsDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizationsDetailsMockMvc.perform(post("/api/organizations-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizationsDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrganizationsDetails in the database
        List<OrganizationsDetails> organizationsDetailsList = organizationsDetailsRepository.findAll();
        assertThat(organizationsDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrganizationsDetails() throws Exception {
        // Initialize the database
        organizationsDetailsRepository.saveAndFlush(organizationsDetails);

        // Get all the organizationsDetailsList
        restOrganizationsDetailsMockMvc.perform(get("/api/organizations-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizationsDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].organizationName").value(hasItem(DEFAULT_ORGANIZATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].organisationAddress").value(hasItem(DEFAULT_ORGANISATION_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].organisationEmail").value(hasItem(DEFAULT_ORGANISATION_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].emailServerHost").value(hasItem(DEFAULT_EMAIL_SERVER_HOST.toString())))
            .andExpect(jsonPath("$.[*].emailServerPort").value(hasItem(DEFAULT_EMAIL_SERVER_PORT)))
            .andExpect(jsonPath("$.[*].emailServerUserId").value(hasItem(DEFAULT_EMAIL_SERVER_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].emailServerPassword").value(hasItem(DEFAULT_EMAIL_SERVER_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].isEmailServerAccessAllowed").value(hasItem(DEFAULT_IS_EMAIL_SERVER_ACCESS_ALLOWED)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }
    

    @Test
    @Transactional
    public void getOrganizationsDetails() throws Exception {
        // Initialize the database
        organizationsDetailsRepository.saveAndFlush(organizationsDetails);

        // Get the organizationsDetails
        restOrganizationsDetailsMockMvc.perform(get("/api/organizations-details/{id}", organizationsDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(organizationsDetails.getId().intValue()))
            .andExpect(jsonPath("$.organizationName").value(DEFAULT_ORGANIZATION_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.organisationAddress").value(DEFAULT_ORGANISATION_ADDRESS.toString()))
            .andExpect(jsonPath("$.organisationEmail").value(DEFAULT_ORGANISATION_EMAIL.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.emailServerHost").value(DEFAULT_EMAIL_SERVER_HOST.toString()))
            .andExpect(jsonPath("$.emailServerPort").value(DEFAULT_EMAIL_SERVER_PORT))
            .andExpect(jsonPath("$.emailServerUserId").value(DEFAULT_EMAIL_SERVER_USER_ID.toString()))
            .andExpect(jsonPath("$.emailServerPassword").value(DEFAULT_EMAIL_SERVER_PASSWORD.toString()))
            .andExpect(jsonPath("$.isEmailServerAccessAllowed").value(DEFAULT_IS_EMAIL_SERVER_ACCESS_ALLOWED))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOrganizationsDetails() throws Exception {
        // Get the organizationsDetails
        restOrganizationsDetailsMockMvc.perform(get("/api/organizations-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganizationsDetails() throws Exception {
        // Initialize the database
        organizationsDetailsRepository.saveAndFlush(organizationsDetails);

        int databaseSizeBeforeUpdate = organizationsDetailsRepository.findAll().size();

        // Update the organizationsDetails
        OrganizationsDetails updatedOrganizationsDetails = organizationsDetailsRepository.findById(organizationsDetails.getId()).get();
        // Disconnect from session so that the updates on updatedOrganizationsDetails are not directly saved in db
        em.detach(updatedOrganizationsDetails);
        updatedOrganizationsDetails
            .organizationName(UPDATED_ORGANIZATION_NAME)
            .description(UPDATED_DESCRIPTION)
            .organisationAddress(UPDATED_ORGANISATION_ADDRESS)
            .organisationEmail(UPDATED_ORGANISATION_EMAIL)
            .isActive(UPDATED_IS_ACTIVE)
            .emailServerHost(UPDATED_EMAIL_SERVER_HOST)
            .emailServerPort(UPDATED_EMAIL_SERVER_PORT)
            .emailServerUserId(UPDATED_EMAIL_SERVER_USER_ID)
            .emailServerPassword(UPDATED_EMAIL_SERVER_PASSWORD)
            .isEmailServerAccessAllowed(UPDATED_IS_EMAIL_SERVER_ACCESS_ALLOWED)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .updateDate(UPDATED_UPDATE_DATE)
            .updatedBy(UPDATED_UPDATED_BY);
        OrganizationsDetailsDTO organizationsDetailsDTO = organizationsDetailsMapper.toDto(updatedOrganizationsDetails);

        restOrganizationsDetailsMockMvc.perform(put("/api/organizations-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizationsDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the OrganizationsDetails in the database
        List<OrganizationsDetails> organizationsDetailsList = organizationsDetailsRepository.findAll();
        assertThat(organizationsDetailsList).hasSize(databaseSizeBeforeUpdate);
        OrganizationsDetails testOrganizationsDetails = organizationsDetailsList.get(organizationsDetailsList.size() - 1);
        assertThat(testOrganizationsDetails.getOrganizationName()).isEqualTo(UPDATED_ORGANIZATION_NAME);
        assertThat(testOrganizationsDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrganizationsDetails.getOrganisationAddress()).isEqualTo(UPDATED_ORGANISATION_ADDRESS);
        assertThat(testOrganizationsDetails.getOrganisationEmail()).isEqualTo(UPDATED_ORGANISATION_EMAIL);
        assertThat(testOrganizationsDetails.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrganizationsDetails.getEmailServerHost()).isEqualTo(UPDATED_EMAIL_SERVER_HOST);
        assertThat(testOrganizationsDetails.getEmailServerPort()).isEqualTo(UPDATED_EMAIL_SERVER_PORT);
        assertThat(testOrganizationsDetails.getEmailServerUserId()).isEqualTo(UPDATED_EMAIL_SERVER_USER_ID);
        assertThat(testOrganizationsDetails.getEmailServerPassword()).isEqualTo(UPDATED_EMAIL_SERVER_PASSWORD);
        assertThat(testOrganizationsDetails.getIsEmailServerAccessAllowed()).isEqualTo(UPDATED_IS_EMAIL_SERVER_ACCESS_ALLOWED);
        assertThat(testOrganizationsDetails.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testOrganizationsDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrganizationsDetails.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testOrganizationsDetails.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganizationsDetails() throws Exception {
        int databaseSizeBeforeUpdate = organizationsDetailsRepository.findAll().size();

        // Create the OrganizationsDetails
        OrganizationsDetailsDTO organizationsDetailsDTO = organizationsDetailsMapper.toDto(organizationsDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restOrganizationsDetailsMockMvc.perform(put("/api/organizations-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizationsDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrganizationsDetails in the database
        List<OrganizationsDetails> organizationsDetailsList = organizationsDetailsRepository.findAll();
        assertThat(organizationsDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganizationsDetails() throws Exception {
        // Initialize the database
        organizationsDetailsRepository.saveAndFlush(organizationsDetails);

        int databaseSizeBeforeDelete = organizationsDetailsRepository.findAll().size();

        // Get the organizationsDetails
        restOrganizationsDetailsMockMvc.perform(delete("/api/organizations-details/{id}", organizationsDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrganizationsDetails> organizationsDetailsList = organizationsDetailsRepository.findAll();
        assertThat(organizationsDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganizationsDetails.class);
        OrganizationsDetails organizationsDetails1 = new OrganizationsDetails();
        organizationsDetails1.setId(1L);
        OrganizationsDetails organizationsDetails2 = new OrganizationsDetails();
        organizationsDetails2.setId(organizationsDetails1.getId());
        assertThat(organizationsDetails1).isEqualTo(organizationsDetails2);
        organizationsDetails2.setId(2L);
        assertThat(organizationsDetails1).isNotEqualTo(organizationsDetails2);
        organizationsDetails1.setId(null);
        assertThat(organizationsDetails1).isNotEqualTo(organizationsDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganizationsDetailsDTO.class);
        OrganizationsDetailsDTO organizationsDetailsDTO1 = new OrganizationsDetailsDTO();
        organizationsDetailsDTO1.setId(1L);
        OrganizationsDetailsDTO organizationsDetailsDTO2 = new OrganizationsDetailsDTO();
        assertThat(organizationsDetailsDTO1).isNotEqualTo(organizationsDetailsDTO2);
        organizationsDetailsDTO2.setId(organizationsDetailsDTO1.getId());
        assertThat(organizationsDetailsDTO1).isEqualTo(organizationsDetailsDTO2);
        organizationsDetailsDTO2.setId(2L);
        assertThat(organizationsDetailsDTO1).isNotEqualTo(organizationsDetailsDTO2);
        organizationsDetailsDTO1.setId(null);
        assertThat(organizationsDetailsDTO1).isNotEqualTo(organizationsDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(organizationsDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(organizationsDetailsMapper.fromId(null)).isNull();
    }
}
