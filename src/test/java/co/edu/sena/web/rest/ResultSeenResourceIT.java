package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.ResultSeen;
import co.edu.sena.domain.LearningResult;
import co.edu.sena.domain.CourseHasTrimester;
import co.edu.sena.repository.ResultSeenRepository;
import co.edu.sena.service.ResultSeenService;
import co.edu.sena.service.dto.ResultSeenDTO;
import co.edu.sena.service.mapper.ResultSeenMapper;
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
 * Integration tests for the {@link ResultSeenResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ResultSeenResourceIT {

    @Autowired
    private ResultSeenRepository resultSeenRepository;

    @Autowired
    private ResultSeenMapper resultSeenMapper;

    @Autowired
    private ResultSeenService resultSeenService;

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

    private MockMvc restResultSeenMockMvc;

    private ResultSeen resultSeen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResultSeenResource resultSeenResource = new ResultSeenResource(resultSeenService);
        this.restResultSeenMockMvc = MockMvcBuilders.standaloneSetup(resultSeenResource)
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
    public static ResultSeen createEntity(EntityManager em) {
        ResultSeen resultSeen = new ResultSeen();
        // Add required entity
        LearningResult learningResult;
        if (TestUtil.findAll(em, LearningResult.class).isEmpty()) {
            learningResult = LearningResultResourceIT.createEntity(em);
            em.persist(learningResult);
            em.flush();
        } else {
            learningResult = TestUtil.findAll(em, LearningResult.class).get(0);
        }
        resultSeen.setLearningResult(learningResult);
        // Add required entity
        CourseHasTrimester courseHasTrimester;
        if (TestUtil.findAll(em, CourseHasTrimester.class).isEmpty()) {
            courseHasTrimester = CourseHasTrimesterResourceIT.createEntity(em);
            em.persist(courseHasTrimester);
            em.flush();
        } else {
            courseHasTrimester = TestUtil.findAll(em, CourseHasTrimester.class).get(0);
        }
        resultSeen.setCourseHasTrimester(courseHasTrimester);
        return resultSeen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResultSeen createUpdatedEntity(EntityManager em) {
        ResultSeen resultSeen = new ResultSeen();
        // Add required entity
        LearningResult learningResult;
        if (TestUtil.findAll(em, LearningResult.class).isEmpty()) {
            learningResult = LearningResultResourceIT.createUpdatedEntity(em);
            em.persist(learningResult);
            em.flush();
        } else {
            learningResult = TestUtil.findAll(em, LearningResult.class).get(0);
        }
        resultSeen.setLearningResult(learningResult);
        // Add required entity
        CourseHasTrimester courseHasTrimester;
        if (TestUtil.findAll(em, CourseHasTrimester.class).isEmpty()) {
            courseHasTrimester = CourseHasTrimesterResourceIT.createUpdatedEntity(em);
            em.persist(courseHasTrimester);
            em.flush();
        } else {
            courseHasTrimester = TestUtil.findAll(em, CourseHasTrimester.class).get(0);
        }
        resultSeen.setCourseHasTrimester(courseHasTrimester);
        return resultSeen;
    }

    @BeforeEach
    public void initTest() {
        resultSeen = createEntity(em);
    }

    @Test
    @Transactional
    public void createResultSeen() throws Exception {
        int databaseSizeBeforeCreate = resultSeenRepository.findAll().size();

        // Create the ResultSeen
        ResultSeenDTO resultSeenDTO = resultSeenMapper.toDto(resultSeen);
        restResultSeenMockMvc.perform(post("/api/result-seens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultSeenDTO)))
            .andExpect(status().isCreated());

        // Validate the ResultSeen in the database
        List<ResultSeen> resultSeenList = resultSeenRepository.findAll();
        assertThat(resultSeenList).hasSize(databaseSizeBeforeCreate + 1);
        ResultSeen testResultSeen = resultSeenList.get(resultSeenList.size() - 1);
    }

    @Test
    @Transactional
    public void createResultSeenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultSeenRepository.findAll().size();

        // Create the ResultSeen with an existing ID
        resultSeen.setId(1L);
        ResultSeenDTO resultSeenDTO = resultSeenMapper.toDto(resultSeen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultSeenMockMvc.perform(post("/api/result-seens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultSeenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResultSeen in the database
        List<ResultSeen> resultSeenList = resultSeenRepository.findAll();
        assertThat(resultSeenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllResultSeens() throws Exception {
        // Initialize the database
        resultSeenRepository.saveAndFlush(resultSeen);

        // Get all the resultSeenList
        restResultSeenMockMvc.perform(get("/api/result-seens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resultSeen.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getResultSeen() throws Exception {
        // Initialize the database
        resultSeenRepository.saveAndFlush(resultSeen);

        // Get the resultSeen
        restResultSeenMockMvc.perform(get("/api/result-seens/{id}", resultSeen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resultSeen.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResultSeen() throws Exception {
        // Get the resultSeen
        restResultSeenMockMvc.perform(get("/api/result-seens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultSeen() throws Exception {
        // Initialize the database
        resultSeenRepository.saveAndFlush(resultSeen);

        int databaseSizeBeforeUpdate = resultSeenRepository.findAll().size();

        // Update the resultSeen
        ResultSeen updatedResultSeen = resultSeenRepository.findById(resultSeen.getId()).get();
        // Disconnect from session so that the updates on updatedResultSeen are not directly saved in db
        em.detach(updatedResultSeen);
        ResultSeenDTO resultSeenDTO = resultSeenMapper.toDto(updatedResultSeen);

        restResultSeenMockMvc.perform(put("/api/result-seens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultSeenDTO)))
            .andExpect(status().isOk());

        // Validate the ResultSeen in the database
        List<ResultSeen> resultSeenList = resultSeenRepository.findAll();
        assertThat(resultSeenList).hasSize(databaseSizeBeforeUpdate);
        ResultSeen testResultSeen = resultSeenList.get(resultSeenList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingResultSeen() throws Exception {
        int databaseSizeBeforeUpdate = resultSeenRepository.findAll().size();

        // Create the ResultSeen
        ResultSeenDTO resultSeenDTO = resultSeenMapper.toDto(resultSeen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResultSeenMockMvc.perform(put("/api/result-seens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultSeenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResultSeen in the database
        List<ResultSeen> resultSeenList = resultSeenRepository.findAll();
        assertThat(resultSeenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResultSeen() throws Exception {
        // Initialize the database
        resultSeenRepository.saveAndFlush(resultSeen);

        int databaseSizeBeforeDelete = resultSeenRepository.findAll().size();

        // Delete the resultSeen
        restResultSeenMockMvc.perform(delete("/api/result-seens/{id}", resultSeen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResultSeen> resultSeenList = resultSeenRepository.findAll();
        assertThat(resultSeenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
