package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.LearningResult;
import co.edu.sena.domain.Competition;
import co.edu.sena.repository.LearningResultRepository;
import co.edu.sena.service.LearningResultService;
import co.edu.sena.service.dto.LearningResultDTO;
import co.edu.sena.service.mapper.LearningResultMapper;
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
 * Integration tests for the {@link LearningResultResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class LearningResultResourceIT {

    private static final String DEFAULT_CODE_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINATION = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION = "BBBBBBBBBB";

    @Autowired
    private LearningResultRepository learningResultRepository;

    @Autowired
    private LearningResultMapper learningResultMapper;

    @Autowired
    private LearningResultService learningResultService;

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

    private MockMvc restLearningResultMockMvc;

    private LearningResult learningResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LearningResultResource learningResultResource = new LearningResultResource(learningResultService);
        this.restLearningResultMockMvc = MockMvcBuilders.standaloneSetup(learningResultResource)
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
    public static LearningResult createEntity(EntityManager em) {
        LearningResult learningResult = new LearningResult()
            .codeResult(DEFAULT_CODE_RESULT)
            .denomination(DEFAULT_DENOMINATION);
        // Add required entity
        Competition competition;
        if (TestUtil.findAll(em, Competition.class).isEmpty()) {
            competition = CompetitionResourceIT.createEntity(em);
            em.persist(competition);
            em.flush();
        } else {
            competition = TestUtil.findAll(em, Competition.class).get(0);
        }
        learningResult.setCompetition(competition);
        return learningResult;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LearningResult createUpdatedEntity(EntityManager em) {
        LearningResult learningResult = new LearningResult()
            .codeResult(UPDATED_CODE_RESULT)
            .denomination(UPDATED_DENOMINATION);
        // Add required entity
        Competition competition;
        if (TestUtil.findAll(em, Competition.class).isEmpty()) {
            competition = CompetitionResourceIT.createUpdatedEntity(em);
            em.persist(competition);
            em.flush();
        } else {
            competition = TestUtil.findAll(em, Competition.class).get(0);
        }
        learningResult.setCompetition(competition);
        return learningResult;
    }

    @BeforeEach
    public void initTest() {
        learningResult = createEntity(em);
    }

    @Test
    @Transactional
    public void createLearningResult() throws Exception {
        int databaseSizeBeforeCreate = learningResultRepository.findAll().size();

        // Create the LearningResult
        LearningResultDTO learningResultDTO = learningResultMapper.toDto(learningResult);
        restLearningResultMockMvc.perform(post("/api/learning-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(learningResultDTO)))
            .andExpect(status().isCreated());

        // Validate the LearningResult in the database
        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeCreate + 1);
        LearningResult testLearningResult = learningResultList.get(learningResultList.size() - 1);
        assertThat(testLearningResult.getCodeResult()).isEqualTo(DEFAULT_CODE_RESULT);
        assertThat(testLearningResult.getDenomination()).isEqualTo(DEFAULT_DENOMINATION);
    }

    @Test
    @Transactional
    public void createLearningResultWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = learningResultRepository.findAll().size();

        // Create the LearningResult with an existing ID
        learningResult.setId(1L);
        LearningResultDTO learningResultDTO = learningResultMapper.toDto(learningResult);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLearningResultMockMvc.perform(post("/api/learning-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(learningResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LearningResult in the database
        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = learningResultRepository.findAll().size();
        // set the field null
        learningResult.setCodeResult(null);

        // Create the LearningResult, which fails.
        LearningResultDTO learningResultDTO = learningResultMapper.toDto(learningResult);

        restLearningResultMockMvc.perform(post("/api/learning-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(learningResultDTO)))
            .andExpect(status().isBadRequest());

        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDenominationIsRequired() throws Exception {
        int databaseSizeBeforeTest = learningResultRepository.findAll().size();
        // set the field null
        learningResult.setDenomination(null);

        // Create the LearningResult, which fails.
        LearningResultDTO learningResultDTO = learningResultMapper.toDto(learningResult);

        restLearningResultMockMvc.perform(post("/api/learning-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(learningResultDTO)))
            .andExpect(status().isBadRequest());

        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLearningResults() throws Exception {
        // Initialize the database
        learningResultRepository.saveAndFlush(learningResult);

        // Get all the learningResultList
        restLearningResultMockMvc.perform(get("/api/learning-results?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(learningResult.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeResult").value(hasItem(DEFAULT_CODE_RESULT)))
            .andExpect(jsonPath("$.[*].denomination").value(hasItem(DEFAULT_DENOMINATION)));
    }
    
    @Test
    @Transactional
    public void getLearningResult() throws Exception {
        // Initialize the database
        learningResultRepository.saveAndFlush(learningResult);

        // Get the learningResult
        restLearningResultMockMvc.perform(get("/api/learning-results/{id}", learningResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(learningResult.getId().intValue()))
            .andExpect(jsonPath("$.codeResult").value(DEFAULT_CODE_RESULT))
            .andExpect(jsonPath("$.denomination").value(DEFAULT_DENOMINATION));
    }

    @Test
    @Transactional
    public void getNonExistingLearningResult() throws Exception {
        // Get the learningResult
        restLearningResultMockMvc.perform(get("/api/learning-results/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLearningResult() throws Exception {
        // Initialize the database
        learningResultRepository.saveAndFlush(learningResult);

        int databaseSizeBeforeUpdate = learningResultRepository.findAll().size();

        // Update the learningResult
        LearningResult updatedLearningResult = learningResultRepository.findById(learningResult.getId()).get();
        // Disconnect from session so that the updates on updatedLearningResult are not directly saved in db
        em.detach(updatedLearningResult);
        updatedLearningResult
            .codeResult(UPDATED_CODE_RESULT)
            .denomination(UPDATED_DENOMINATION);
        LearningResultDTO learningResultDTO = learningResultMapper.toDto(updatedLearningResult);

        restLearningResultMockMvc.perform(put("/api/learning-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(learningResultDTO)))
            .andExpect(status().isOk());

        // Validate the LearningResult in the database
        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeUpdate);
        LearningResult testLearningResult = learningResultList.get(learningResultList.size() - 1);
        assertThat(testLearningResult.getCodeResult()).isEqualTo(UPDATED_CODE_RESULT);
        assertThat(testLearningResult.getDenomination()).isEqualTo(UPDATED_DENOMINATION);
    }

    @Test
    @Transactional
    public void updateNonExistingLearningResult() throws Exception {
        int databaseSizeBeforeUpdate = learningResultRepository.findAll().size();

        // Create the LearningResult
        LearningResultDTO learningResultDTO = learningResultMapper.toDto(learningResult);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLearningResultMockMvc.perform(put("/api/learning-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(learningResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LearningResult in the database
        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLearningResult() throws Exception {
        // Initialize the database
        learningResultRepository.saveAndFlush(learningResult);

        int databaseSizeBeforeDelete = learningResultRepository.findAll().size();

        // Delete the learningResult
        restLearningResultMockMvc.perform(delete("/api/learning-results/{id}", learningResult.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LearningResult> learningResultList = learningResultRepository.findAll();
        assertThat(learningResultList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
