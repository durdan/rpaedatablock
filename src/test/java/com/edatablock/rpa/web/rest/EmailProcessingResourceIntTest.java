package com.edatablock.rpa.web.rest;

import com.edatablock.rpa.RpaedatablockApp;

import com.edatablock.rpa.domain.EmailProcessing;
import com.edatablock.rpa.repository.EmailProcessingRepository;
import com.edatablock.rpa.service.EmailProcessingService;
import com.edatablock.rpa.service.dto.EmailProcessingDTO;
import com.edatablock.rpa.service.mapper.EmailProcessingMapper;
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
 * Test class for the EmailProcessingResource REST controller.
 *
 * @see EmailProcessingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpaedatablockApp.class)
public class EmailProcessingResourceIntTest {

    private static final String DEFAULT_MESSAGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVE_FROM = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_FROM = "BBBBBBBBBB";

    private static final Instant DEFAULT_RECEIVED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECEIVED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NUMBER_OF_ATTACHMENTS = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_OF_ATTACHMENTS = "BBBBBBBBBB";

    @Autowired
    private EmailProcessingRepository emailProcessingRepository;


    @Autowired
    private EmailProcessingMapper emailProcessingMapper;
    

    @Autowired
    private EmailProcessingService emailProcessingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmailProcessingMockMvc;

    private EmailProcessing emailProcessing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmailProcessingResource emailProcessingResource = new EmailProcessingResource(emailProcessingService);
        this.restEmailProcessingMockMvc = MockMvcBuilders.standaloneSetup(emailProcessingResource)
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
    public static EmailProcessing createEntity(EntityManager em) {
        EmailProcessing emailProcessing = new EmailProcessing()
            .messageId(DEFAULT_MESSAGE_ID)
            .receiveFrom(DEFAULT_RECEIVE_FROM)
            .receivedTime(DEFAULT_RECEIVED_TIME)
            .numberOfAttachments(DEFAULT_NUMBER_OF_ATTACHMENTS);
        return emailProcessing;
    }

    @Before
    public void initTest() {
        emailProcessing = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmailProcessing() throws Exception {
        int databaseSizeBeforeCreate = emailProcessingRepository.findAll().size();

        // Create the EmailProcessing
        EmailProcessingDTO emailProcessingDTO = emailProcessingMapper.toDto(emailProcessing);
        restEmailProcessingMockMvc.perform(post("/api/email-processings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailProcessingDTO)))
            .andExpect(status().isCreated());

        // Validate the EmailProcessing in the database
        List<EmailProcessing> emailProcessingList = emailProcessingRepository.findAll();
        assertThat(emailProcessingList).hasSize(databaseSizeBeforeCreate + 1);
        EmailProcessing testEmailProcessing = emailProcessingList.get(emailProcessingList.size() - 1);
        assertThat(testEmailProcessing.getMessageId()).isEqualTo(DEFAULT_MESSAGE_ID);
        assertThat(testEmailProcessing.getReceiveFrom()).isEqualTo(DEFAULT_RECEIVE_FROM);
        assertThat(testEmailProcessing.getReceivedTime()).isEqualTo(DEFAULT_RECEIVED_TIME);
        assertThat(testEmailProcessing.getNumberOfAttachments()).isEqualTo(DEFAULT_NUMBER_OF_ATTACHMENTS);
    }

    @Test
    @Transactional
    public void createEmailProcessingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emailProcessingRepository.findAll().size();

        // Create the EmailProcessing with an existing ID
        emailProcessing.setId(1L);
        EmailProcessingDTO emailProcessingDTO = emailProcessingMapper.toDto(emailProcessing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmailProcessingMockMvc.perform(post("/api/email-processings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailProcessingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailProcessing in the database
        List<EmailProcessing> emailProcessingList = emailProcessingRepository.findAll();
        assertThat(emailProcessingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmailProcessings() throws Exception {
        // Initialize the database
        emailProcessingRepository.saveAndFlush(emailProcessing);

        // Get all the emailProcessingList
        restEmailProcessingMockMvc.perform(get("/api/email-processings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emailProcessing.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageId").value(hasItem(DEFAULT_MESSAGE_ID.toString())))
            .andExpect(jsonPath("$.[*].receiveFrom").value(hasItem(DEFAULT_RECEIVE_FROM.toString())))
            .andExpect(jsonPath("$.[*].receivedTime").value(hasItem(DEFAULT_RECEIVED_TIME.toString())))
            .andExpect(jsonPath("$.[*].numberOfAttachments").value(hasItem(DEFAULT_NUMBER_OF_ATTACHMENTS.toString())));
    }
    

    @Test
    @Transactional
    public void getEmailProcessing() throws Exception {
        // Initialize the database
        emailProcessingRepository.saveAndFlush(emailProcessing);

        // Get the emailProcessing
        restEmailProcessingMockMvc.perform(get("/api/email-processings/{id}", emailProcessing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emailProcessing.getId().intValue()))
            .andExpect(jsonPath("$.messageId").value(DEFAULT_MESSAGE_ID.toString()))
            .andExpect(jsonPath("$.receiveFrom").value(DEFAULT_RECEIVE_FROM.toString()))
            .andExpect(jsonPath("$.receivedTime").value(DEFAULT_RECEIVED_TIME.toString()))
            .andExpect(jsonPath("$.numberOfAttachments").value(DEFAULT_NUMBER_OF_ATTACHMENTS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmailProcessing() throws Exception {
        // Get the emailProcessing
        restEmailProcessingMockMvc.perform(get("/api/email-processings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmailProcessing() throws Exception {
        // Initialize the database
        emailProcessingRepository.saveAndFlush(emailProcessing);

        int databaseSizeBeforeUpdate = emailProcessingRepository.findAll().size();

        // Update the emailProcessing
        EmailProcessing updatedEmailProcessing = emailProcessingRepository.findById(emailProcessing.getId()).get();
        // Disconnect from session so that the updates on updatedEmailProcessing are not directly saved in db
        em.detach(updatedEmailProcessing);
        updatedEmailProcessing
            .messageId(UPDATED_MESSAGE_ID)
            .receiveFrom(UPDATED_RECEIVE_FROM)
            .receivedTime(UPDATED_RECEIVED_TIME)
            .numberOfAttachments(UPDATED_NUMBER_OF_ATTACHMENTS);
        EmailProcessingDTO emailProcessingDTO = emailProcessingMapper.toDto(updatedEmailProcessing);

        restEmailProcessingMockMvc.perform(put("/api/email-processings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailProcessingDTO)))
            .andExpect(status().isOk());

        // Validate the EmailProcessing in the database
        List<EmailProcessing> emailProcessingList = emailProcessingRepository.findAll();
        assertThat(emailProcessingList).hasSize(databaseSizeBeforeUpdate);
        EmailProcessing testEmailProcessing = emailProcessingList.get(emailProcessingList.size() - 1);
        assertThat(testEmailProcessing.getMessageId()).isEqualTo(UPDATED_MESSAGE_ID);
        assertThat(testEmailProcessing.getReceiveFrom()).isEqualTo(UPDATED_RECEIVE_FROM);
        assertThat(testEmailProcessing.getReceivedTime()).isEqualTo(UPDATED_RECEIVED_TIME);
        assertThat(testEmailProcessing.getNumberOfAttachments()).isEqualTo(UPDATED_NUMBER_OF_ATTACHMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingEmailProcessing() throws Exception {
        int databaseSizeBeforeUpdate = emailProcessingRepository.findAll().size();

        // Create the EmailProcessing
        EmailProcessingDTO emailProcessingDTO = emailProcessingMapper.toDto(emailProcessing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restEmailProcessingMockMvc.perform(put("/api/email-processings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailProcessingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailProcessing in the database
        List<EmailProcessing> emailProcessingList = emailProcessingRepository.findAll();
        assertThat(emailProcessingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmailProcessing() throws Exception {
        // Initialize the database
        emailProcessingRepository.saveAndFlush(emailProcessing);

        int databaseSizeBeforeDelete = emailProcessingRepository.findAll().size();

        // Get the emailProcessing
        restEmailProcessingMockMvc.perform(delete("/api/email-processings/{id}", emailProcessing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EmailProcessing> emailProcessingList = emailProcessingRepository.findAll();
        assertThat(emailProcessingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailProcessing.class);
        EmailProcessing emailProcessing1 = new EmailProcessing();
        emailProcessing1.setId(1L);
        EmailProcessing emailProcessing2 = new EmailProcessing();
        emailProcessing2.setId(emailProcessing1.getId());
        assertThat(emailProcessing1).isEqualTo(emailProcessing2);
        emailProcessing2.setId(2L);
        assertThat(emailProcessing1).isNotEqualTo(emailProcessing2);
        emailProcessing1.setId(null);
        assertThat(emailProcessing1).isNotEqualTo(emailProcessing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailProcessingDTO.class);
        EmailProcessingDTO emailProcessingDTO1 = new EmailProcessingDTO();
        emailProcessingDTO1.setId(1L);
        EmailProcessingDTO emailProcessingDTO2 = new EmailProcessingDTO();
        assertThat(emailProcessingDTO1).isNotEqualTo(emailProcessingDTO2);
        emailProcessingDTO2.setId(emailProcessingDTO1.getId());
        assertThat(emailProcessingDTO1).isEqualTo(emailProcessingDTO2);
        emailProcessingDTO2.setId(2L);
        assertThat(emailProcessingDTO1).isNotEqualTo(emailProcessingDTO2);
        emailProcessingDTO1.setId(null);
        assertThat(emailProcessingDTO1).isNotEqualTo(emailProcessingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(emailProcessingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(emailProcessingMapper.fromId(null)).isNull();
    }
}
