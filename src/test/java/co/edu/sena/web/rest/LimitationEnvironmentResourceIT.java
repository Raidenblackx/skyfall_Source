package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.LimitationEnvironment;
import co.edu.sena.domain.LearningResult;
import co.edu.sena.domain.Ambient;
import co.edu.sena.repository.LimitationEnvironmentRepository;
import co.edu.sena.service.LimitationEnvironmentService;
import co.edu.sena.service.dto.LimitationEnvironmentDTO;
import co.edu.sena.service.mapper.LimitationEnvironmentMapper;
import co.edu.sena.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static co.edu.sena.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LimitationEnvironmentResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class LimitationEnvironmentResourceIT {

    @Autowired
    private LimitationEnvironmentRepository limitationEnvironmentRepository;

    @Autowired
    private LimitationEnvironmentMapper limitationEnvironmentMapper;

    @Autowired
    private LimitationEnvironmentService limitationEnvironmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restLimitationEnvironmentMockMvc;

    private LimitationEnvironment limitationEnvironment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LimitationEnvironmentResource limitationEnvironmentResource = new LimitationEnvironmentResource(limitationEnvironmentService);
        this.restLimitationEnvironmentMockMvc = MockMvcBuilders.standaloneSetup(limitationEnvironmentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LimitationEnvironment createEntity(EntityManager em) {
        LimitationEnvironment limitationEnvironment = new LimitationEnvironment();
        // Add required entity
        LearningResult learningResult;
        if (TestUtil.findAll(em, LearningResult.class).isEmpty()) {
            learningResult = LearningResultResourceIT.createEntity(em);
            em.persist(learningResult);
            em.flush();
        } else {
            learningResult = TestUtil.findAll(em, LearningResult.class).get(0);
        }
        limitationEnvironment.setLearningResult(learningResult);
        // Add required entity
        Ambient ambient;
        if (TestUtil.findAll(em, Ambient.class).isEmpty()) {
            ambient = AmbientResourceIT.createEntity(em);
            em.persist(ambient);
            em.flush();
        } else {
            ambient = TestUtil.findAll(em, Ambient.class).get(0);
        }
        limitationEnvironment.setAmbient(ambient);
        return limitationEnvironment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LimitationEnvironment createUpdatedEntity(EntityManager em) {
        LimitationEnvironment limitationEnvironment = new LimitationEnvironment();
        // Add required entity
        LearningResult learningResult;
        if (TestUtil.findAll(em, LearningResult.class).isEmpty()) {
            learningResult = LearningResultResourceIT.createUpdatedEntity(em);
            em.persist(learningResult);
            em.flush();
        } else {
            learningResult = TestUtil.findAll(em, LearningResult.class).get(0);
        }
        limitationEnvironment.setLearningResult(learningResult);
        // Add required entity
        Ambient ambient;
        if (TestUtil.findAll(em, Ambient.class).isEmpty()) {
            ambient = AmbientResourceIT.createUpdatedEntity(em);
            em.persist(ambient);
            em.flush();
        } else {
            ambient = TestUtil.findAll(em, Ambient.class).get(0);
        }
        limitationEnvironment.setAmbient(ambient);
        return limitationEnvironment;
    }

    @BeforeEach
    public void initTest() {
        limitationEnvironment = createEntity(em);
    }

    @Test
    @Transactional
    public void createLimitationEnvironment() throws Exception {
        int databaseSizeBeforeCreate = limitationEnvironmentRepository.findAll().size();

        // Create the LimitationEnvironment
        LimitationEnvironmentDTO limitationEnvironmentDTO = limitationEnvironmentMapper.toDto(limitationEnvironment);
        restLimitationEnvironmentMockMvc.perform(post("/api/limitation-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitationEnvironmentDTO)))
            .andExpect(status().isCreated());

        // Validate the LimitationEnvironment in the database
        List<LimitationEnvironment> limitationEnvironmentList = limitationEnvironmentRepository.findAll();
        assertThat(limitationEnvironmentList).hasSize(databaseSizeBeforeCreate + 1);
        LimitationEnvironment testLimitationEnvironment = limitationEnvironmentList.get(limitationEnvironmentList.size() - 1);
    }

    @Test
    @Transactional
    public void createLimitationEnvironmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = limitationEnvironmentRepository.findAll().size();

        // Create the LimitationEnvironment with an existing ID
        limitationEnvironment.setId(1L);
        LimitationEnvironmentDTO limitationEnvironmentDTO = limitationEnvironmentMapper.toDto(limitationEnvironment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLimitationEnvironmentMockMvc.perform(post("/api/limitation-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitationEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LimitationEnvironment in the database
        List<LimitationEnvironment> limitationEnvironmentList = limitationEnvironmentRepository.findAll();
        assertThat(limitationEnvironmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLimitationEnvironments() throws Exception {
        // Initialize the database
        limitationEnvironmentRepository.saveAndFlush(limitationEnvironment);

        // Get all the limitationEnvironmentList
        restLimitationEnvironmentMockMvc.perform(get("/api/limitation-environments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(limitationEnvironment.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getLimitationEnvironment() throws Exception {
        // Initialize the database
        limitationEnvironmentRepository.saveAndFlush(limitationEnvironment);

        // Get the limitationEnvironment
        restLimitationEnvironmentMockMvc.perform(get("/api/limitation-environments/{id}", limitationEnvironment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(limitationEnvironment.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLimitationEnvironment() throws Exception {
        // Get the limitationEnvironment
        restLimitationEnvironmentMockMvc.perform(get("/api/limitation-environments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLimitationEnvironment() throws Exception {
        // Initialize the database
        limitationEnvironmentRepository.saveAndFlush(limitationEnvironment);

        int databaseSizeBeforeUpdate = limitationEnvironmentRepository.findAll().size();

        // Update the limitationEnvironment
        LimitationEnvironment updatedLimitationEnvironment = limitationEnvironmentRepository.findById(limitationEnvironment.getId()).get();
        // Disconnect from session so that the updates on updatedLimitationEnvironment are not directly saved in db
        em.detach(updatedLimitationEnvironment);
        LimitationEnvironmentDTO limitationEnvironmentDTO = limitationEnvironmentMapper.toDto(updatedLimitationEnvironment);

        restLimitationEnvironmentMockMvc.perform(put("/api/limitation-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitationEnvironmentDTO)))
            .andExpect(status().isOk());

        // Validate the LimitationEnvironment in the database
        List<LimitationEnvironment> limitationEnvironmentList = limitationEnvironmentRepository.findAll();
        assertThat(limitationEnvironmentList).hasSize(databaseSizeBeforeUpdate);
        LimitationEnvironment testLimitationEnvironment = limitationEnvironmentList.get(limitationEnvironmentList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingLimitationEnvironment() throws Exception {
        int databaseSizeBeforeUpdate = limitationEnvironmentRepository.findAll().size();

        // Create the LimitationEnvironment
        LimitationEnvironmentDTO limitationEnvironmentDTO = limitationEnvironmentMapper.toDto(limitationEnvironment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLimitationEnvironmentMockMvc.perform(put("/api/limitation-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitationEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LimitationEnvironment in the database
        List<LimitationEnvironment> limitationEnvironmentList = limitationEnvironmentRepository.findAll();
        assertThat(limitationEnvironmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLimitationEnvironment() throws Exception {
        // Initialize the database
        limitationEnvironmentRepository.saveAndFlush(limitationEnvironment);

        int databaseSizeBeforeDelete = limitationEnvironmentRepository.findAll().size();

        // Delete the limitationEnvironment
        restLimitationEnvironmentMockMvc.perform(delete("/api/limitation-environments/{id}", limitationEnvironment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LimitationEnvironment> limitationEnvironmentList = limitationEnvironmentRepository.findAll();
        assertThat(limitationEnvironmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
