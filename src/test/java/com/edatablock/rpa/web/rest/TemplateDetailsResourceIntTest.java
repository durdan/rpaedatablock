package com.edatablock.rpa.web.rest;

import com.edatablock.rpa.RpaedatablockApp;

import com.edatablock.rpa.domain.TemplateDetails;
import com.edatablock.rpa.repository.TemplateDetailsRepository;
import com.edatablock.rpa.service.TemplateDetailsService;
import com.edatablock.rpa.service.dto.TemplateDetailsDTO;
import com.edatablock.rpa.service.mapper.TemplateDetailsMapper;
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
 * Test class for the TemplateDetailsResource REST controller.
 *
 * @see TemplateDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpaedatablockApp.class)
public class TemplateDetailsResourceIntTest {

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_ACTIVE = 1;
    private static final Integer UPDATED_IS_ACTIVE = 2;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private TemplateDetailsRepository templateDetailsRepository;


    @Autowired
    private TemplateDetailsMapper templateDetailsMapper;
    

    @Autowired
    private TemplateDetailsService templateDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTemplateDetailsMockMvc;

    private TemplateDetails templateDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TemplateDetailsResource templateDetailsResource = new TemplateDetailsResource(templateDetailsService);
        this.restTemplateDetailsMockMvc = MockMvcBuilders.standaloneSetup(templateDetailsResource)
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
    public static TemplateDetails createEntity(EntityManager em) {
        TemplateDetails templateDetails = new TemplateDetails()
            .templateName(DEFAULT_TEMPLATE_NAME)
            .templateDescription(DEFAULT_TEMPLATE_DESCRIPTION)
            .templateType(DEFAULT_TEMPLATE_TYPE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createDate(DEFAULT_CREATE_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updatedBy(DEFAULT_UPDATED_BY);
        return templateDetails;
    }

    @Before
    public void initTest() {
        templateDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createTemplateDetails() throws Exception {
        int databaseSizeBeforeCreate = templateDetailsRepository.findAll().size();

        // Create the TemplateDetails
        TemplateDetailsDTO templateDetailsDTO = templateDetailsMapper.toDto(templateDetails);
        restTemplateDetailsMockMvc.perform(post("/api/template-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the TemplateDetails in the database
        List<TemplateDetails> templateDetailsList = templateDetailsRepository.findAll();
        assertThat(templateDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        TemplateDetails testTemplateDetails = templateDetailsList.get(templateDetailsList.size() - 1);
        assertThat(testTemplateDetails.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testTemplateDetails.getTemplateDescription()).isEqualTo(DEFAULT_TEMPLATE_DESCRIPTION);
        assertThat(testTemplateDetails.getTemplateType()).isEqualTo(DEFAULT_TEMPLATE_TYPE);
        assertThat(testTemplateDetails.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testTemplateDetails.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTemplateDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTemplateDetails.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testTemplateDetails.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createTemplateDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = templateDetailsRepository.findAll().size();

        // Create the TemplateDetails with an existing ID
        templateDetails.setId(1L);
        TemplateDetailsDTO templateDetailsDTO = templateDetailsMapper.toDto(templateDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemplateDetailsMockMvc.perform(post("/api/template-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TemplateDetails in the database
        List<TemplateDetails> templateDetailsList = templateDetailsRepository.findAll();
        assertThat(templateDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTemplateDetails() throws Exception {
        // Initialize the database
        templateDetailsRepository.saveAndFlush(templateDetails);

        // Get all the templateDetailsList
        restTemplateDetailsMockMvc.perform(get("/api/template-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templateDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].templateDescription").value(hasItem(DEFAULT_TEMPLATE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].templateType").value(hasItem(DEFAULT_TEMPLATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }
    

    @Test
    @Transactional
    public void getTemplateDetails() throws Exception {
        // Initialize the database
        templateDetailsRepository.saveAndFlush(templateDetails);

        // Get the templateDetails
        restTemplateDetailsMockMvc.perform(get("/api/template-details/{id}", templateDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(templateDetails.getId().intValue()))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME.toString()))
            .andExpect(jsonPath("$.templateDescription").value(DEFAULT_TEMPLATE_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.templateType").value(DEFAULT_TEMPLATE_TYPE.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTemplateDetails() throws Exception {
        // Get the templateDetails
        restTemplateDetailsMockMvc.perform(get("/api/template-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemplateDetails() throws Exception {
        // Initialize the database
        templateDetailsRepository.saveAndFlush(templateDetails);

        int databaseSizeBeforeUpdate = templateDetailsRepository.findAll().size();

        // Update the templateDetails
        TemplateDetails updatedTemplateDetails = templateDetailsRepository.findById(templateDetails.getId()).get();
        // Disconnect from session so that the updates on updatedTemplateDetails are not directly saved in db
        em.detach(updatedTemplateDetails);
        updatedTemplateDetails
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateDescription(UPDATED_TEMPLATE_DESCRIPTION)
            .templateType(UPDATED_TEMPLATE_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .updateDate(UPDATED_UPDATE_DATE)
            .updatedBy(UPDATED_UPDATED_BY);
        TemplateDetailsDTO templateDetailsDTO = templateDetailsMapper.toDto(updatedTemplateDetails);

        restTemplateDetailsMockMvc.perform(put("/api/template-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the TemplateDetails in the database
        List<TemplateDetails> templateDetailsList = templateDetailsRepository.findAll();
        assertThat(templateDetailsList).hasSize(databaseSizeBeforeUpdate);
        TemplateDetails testTemplateDetails = templateDetailsList.get(templateDetailsList.size() - 1);
        assertThat(testTemplateDetails.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testTemplateDetails.getTemplateDescription()).isEqualTo(UPDATED_TEMPLATE_DESCRIPTION);
        assertThat(testTemplateDetails.getTemplateType()).isEqualTo(UPDATED_TEMPLATE_TYPE);
        assertThat(testTemplateDetails.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testTemplateDetails.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTemplateDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTemplateDetails.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testTemplateDetails.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingTemplateDetails() throws Exception {
        int databaseSizeBeforeUpdate = templateDetailsRepository.findAll().size();

        // Create the TemplateDetails
        TemplateDetailsDTO templateDetailsDTO = templateDetailsMapper.toDto(templateDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restTemplateDetailsMockMvc.perform(put("/api/template-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TemplateDetails in the database
        List<TemplateDetails> templateDetailsList = templateDetailsRepository.findAll();
        assertThat(templateDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTemplateDetails() throws Exception {
        // Initialize the database
        templateDetailsRepository.saveAndFlush(templateDetails);

        int databaseSizeBeforeDelete = templateDetailsRepository.findAll().size();

        // Get the templateDetails
        restTemplateDetailsMockMvc.perform(delete("/api/template-details/{id}", templateDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TemplateDetails> templateDetailsList = templateDetailsRepository.findAll();
        assertThat(templateDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplateDetails.class);
        TemplateDetails templateDetails1 = new TemplateDetails();
        templateDetails1.setId(1L);
        TemplateDetails templateDetails2 = new TemplateDetails();
        templateDetails2.setId(templateDetails1.getId());
        assertThat(templateDetails1).isEqualTo(templateDetails2);
        templateDetails2.setId(2L);
        assertThat(templateDetails1).isNotEqualTo(templateDetails2);
        templateDetails1.setId(null);
        assertThat(templateDetails1).isNotEqualTo(templateDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplateDetailsDTO.class);
        TemplateDetailsDTO templateDetailsDTO1 = new TemplateDetailsDTO();
        templateDetailsDTO1.setId(1L);
        TemplateDetailsDTO templateDetailsDTO2 = new TemplateDetailsDTO();
        assertThat(templateDetailsDTO1).isNotEqualTo(templateDetailsDTO2);
        templateDetailsDTO2.setId(templateDetailsDTO1.getId());
        assertThat(templateDetailsDTO1).isEqualTo(templateDetailsDTO2);
        templateDetailsDTO2.setId(2L);
        assertThat(templateDetailsDTO1).isNotEqualTo(templateDetailsDTO2);
        templateDetailsDTO1.setId(null);
        assertThat(templateDetailsDTO1).isNotEqualTo(templateDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(templateDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(templateDetailsMapper.fromId(null)).isNull();
    }
}
