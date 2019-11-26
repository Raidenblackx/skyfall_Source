package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.TrimesterPlanning;
import co.edu.sena.domain.LearningResult;
import co.edu.sena.domain.Trimester;
import co.edu.sena.domain.Planning;
import co.edu.sena.repository.TrimesterPlanningRepository;
import co.edu.sena.service.TrimesterPlanningService;
import co.edu.sena.service.dto.TrimesterPlanningDTO;
import co.edu.sena.service.mapper.TrimesterPlanningMapper;
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
 * Integration tests for the {@link TrimesterPlanningResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class TrimesterPlanningResourceIT {

    @Autowired
    private TrimesterPlanningRepository trimesterPlanningRepository;

    @Autowired
    private TrimesterPlanningMapper trimesterPlanningMapper;

    @Autowired
    private TrimesterPlanningService trimesterPlanningService;

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

    private MockMvc restTrimesterPlanningMockMvc;

    private TrimesterPlanning trimesterPlanning;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrimesterPlanningResource trimesterPlanningResource = new TrimesterPlanningResource(trimesterPlanningService);
        this.restTrimesterPlanningMockMvc = MockMvcBuilders.standaloneSetup(trimesterPlanningResource)
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
    public static TrimesterPlanning createEntity(EntityManager em) {
        TrimesterPlanning trimesterPlanning = new TrimesterPlanning();
        // Add required entity
        LearningResult learningResult;
        if (TestUtil.findAll(em, LearningResult.class).isEmpty()) {
            learningResult = LearningResultResourceIT.createEntity(em);
            em.persist(learningResult);
            em.flush();
        } else {
            learningResult = TestUtil.findAll(em, LearningResult.class).get(0);
        }
        trimesterPlanning.setLearningResult(learningResult);
        // Add required entity
        Trimester trimester;
        if (TestUtil.findAll(em, Trimester.class).isEmpty()) {
            trimester = TrimesterResourceIT.createEntity(em);
            em.persist(trimester);
            em.flush();
        } else {
            trimester = TestUtil.findAll(em, Trimester.class).get(0);
        }
        trimesterPlanning.setTrimester(trimester);
        // Add required entity
        Planning planning;
        if (TestUtil.findAll(em, Planning.class).isEmpty()) {
            planning = PlanningResourceIT.createEntity(em);
            em.persist(planning);
            em.flush();
        } else {
            planning = TestUtil.findAll(em, Planning.class).get(0);
        }
        trimesterPlanning.setPlanning(planning);
        return trimesterPlanning;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrimesterPlanning createUpdatedEntity(EntityManager em) {
        TrimesterPlanning trimesterPlanning = new TrimesterPlanning();
        // Add required entity
        LearningResult learningResult;
        if (TestUtil.findAll(em, LearningResult.class).isEmpty()) {
            learningResult = LearningResultResourceIT.createUpdatedEntity(em);
            em.persist(learningResult);
            em.flush();
        } else {
            learningResult = TestUtil.findAll(em, LearningResult.class).get(0);
        }
        trimesterPlanning.setLearningResult(learningResult);
        // Add required entity
        Trimester trimester;
        if (TestUtil.findAll(em, Trimester.class).isEmpty()) {
            trimester = TrimesterResourceIT.createUpdatedEntity(em);
            em.persist(trimester);
            em.flush();
        } else {
            trimester = TestUtil.findAll(em, Trimester.class).get(0);
        }
        trimesterPlanning.setTrimester(trimester);
        // Add required entity
        Planning planning;
        if (TestUtil.findAll(em, Planning.class).isEmpty()) {
            planning = PlanningResourceIT.createUpdatedEntity(em);
            em.persist(planning);
            em.flush();
        } else {
            planning = TestUtil.findAll(em, Planning.class).get(0);
        }
        trimesterPlanning.setPlanning(planning);
        return trimesterPlanning;
    }

    @BeforeEach
    public void initTest() {
        trimesterPlanning = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrimesterPlanning() throws Exception {
        int databaseSizeBeforeCreate = trimesterPlanningRepository.findAll().size();

        // Create the TrimesterPlanning
        TrimesterPlanningDTO trimesterPlanningDTO = trimesterPlanningMapper.toDto(trimesterPlanning);
        restTrimesterPlanningMockMvc.perform(post("/api/trimester-plannings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterPlanningDTO)))
            .andExpect(status().isCreated());

        // Validate the TrimesterPlanning in the database
        List<TrimesterPlanning> trimesterPlanningList = trimesterPlanningRepository.findAll();
        assertThat(trimesterPlanningList).hasSize(databaseSizeBeforeCreate + 1);
        TrimesterPlanning testTrimesterPlanning = trimesterPlanningList.get(trimesterPlanningList.size() - 1);
    }

    @Test
    @Transactional
    public void createTrimesterPlanningWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trimesterPlanningRepository.findAll().size();

        // Create the TrimesterPlanning with an existing ID
        trimesterPlanning.setId(1L);
        TrimesterPlanningDTO trimesterPlanningDTO = trimesterPlanningMapper.toDto(trimesterPlanning);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrimesterPlanningMockMvc.perform(post("/api/trimester-plannings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterPlanningDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrimesterPlanning in the database
        List<TrimesterPlanning> trimesterPlanningList = trimesterPlanningRepository.findAll();
        assertThat(trimesterPlanningList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTrimesterPlannings() throws Exception {
        // Initialize the database
        trimesterPlanningRepository.saveAndFlush(trimesterPlanning);

        // Get all the trimesterPlanningList
        restTrimesterPlanningMockMvc.perform(get("/api/trimester-plannings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trimesterPlanning.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTrimesterPlanning() throws Exception {
        // Initialize the database
        trimesterPlanningRepository.saveAndFlush(trimesterPlanning);

        // Get the trimesterPlanning
        restTrimesterPlanningMockMvc.perform(get("/api/trimester-plannings/{id}", trimesterPlanning.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trimesterPlanning.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTrimesterPlanning() throws Exception {
        // Get the trimesterPlanning
        restTrimesterPlanningMockMvc.perform(get("/api/trimester-plannings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrimesterPlanning() throws Exception {
        // Initialize the database
        trimesterPlanningRepository.saveAndFlush(trimesterPlanning);

        int databaseSizeBeforeUpdate = trimesterPlanningRepository.findAll().size();

        // Update the trimesterPlanning
        TrimesterPlanning updatedTrimesterPlanning = trimesterPlanningRepository.findById(trimesterPlanning.getId()).get();
        // Disconnect from session so that the updates on updatedTrimesterPlanning are not directly saved in db
        em.detach(updatedTrimesterPlanning);
        TrimesterPlanningDTO trimesterPlanningDTO = trimesterPlanningMapper.toDto(updatedTrimesterPlanning);

        restTrimesterPlanningMockMvc.perform(put("/api/trimester-plannings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterPlanningDTO)))
            .andExpect(status().isOk());

        // Validate the TrimesterPlanning in the database
        List<TrimesterPlanning> trimesterPlanningList = trimesterPlanningRepository.findAll();
        assertThat(trimesterPlanningList).hasSize(databaseSizeBeforeUpdate);
        TrimesterPlanning testTrimesterPlanning = trimesterPlanningList.get(trimesterPlanningList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTrimesterPlanning() throws Exception {
        int databaseSizeBeforeUpdate = trimesterPlanningRepository.findAll().size();

        // Create the TrimesterPlanning
        TrimesterPlanningDTO trimesterPlanningDTO = trimesterPlanningMapper.toDto(trimesterPlanning);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrimesterPlanningMockMvc.perform(put("/api/trimester-plannings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterPlanningDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrimesterPlanning in the database
        List<TrimesterPlanning> trimesterPlanningList = trimesterPlanningRepository.findAll();
        assertThat(trimesterPlanningList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrimesterPlanning() throws Exception {
        // Initialize the database
        trimesterPlanningRepository.saveAndFlush(trimesterPlanning);

        int databaseSizeBeforeDelete = trimesterPlanningRepository.findAll().size();

        // Delete the trimesterPlanning
        restTrimesterPlanningMockMvc.perform(delete("/api/trimester-plannings/{id}", trimesterPlanning.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TrimesterPlanning> trimesterPlanningList = trimesterPlanningRepository.findAll();
        assertThat(trimesterPlanningList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
