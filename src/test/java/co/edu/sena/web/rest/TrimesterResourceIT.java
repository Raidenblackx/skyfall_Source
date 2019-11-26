package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Trimester;
import co.edu.sena.domain.WorkingDay;
import co.edu.sena.domain.LevelFormation;
import co.edu.sena.repository.TrimesterRepository;
import co.edu.sena.service.TrimesterService;
import co.edu.sena.service.dto.TrimesterDTO;
import co.edu.sena.service.mapper.TrimesterMapper;
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

import co.edu.sena.domain.enumeration.State;
/**
 * Integration tests for the {@link TrimesterResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class TrimesterResourceIT {

    private static final Integer DEFAULT_NAME_TRIMESTER = 1;
    private static final Integer UPDATED_NAME_TRIMESTER = 2;

    private static final State DEFAULT_STATE_TRIMESTER = State.ACTIVO;
    private static final State UPDATED_STATE_TRIMESTER = State.INACTIVO;

    @Autowired
    private TrimesterRepository trimesterRepository;

    @Autowired
    private TrimesterMapper trimesterMapper;

    @Autowired
    private TrimesterService trimesterService;

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

    private MockMvc restTrimesterMockMvc;

    private Trimester trimester;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrimesterResource trimesterResource = new TrimesterResource(trimesterService);
        this.restTrimesterMockMvc = MockMvcBuilders.standaloneSetup(trimesterResource)
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
    public static Trimester createEntity(EntityManager em) {
        Trimester trimester = new Trimester()
            .nameTrimester(DEFAULT_NAME_TRIMESTER)
            .stateTrimester(DEFAULT_STATE_TRIMESTER);
        // Add required entity
        WorkingDay workingDay;
        if (TestUtil.findAll(em, WorkingDay.class).isEmpty()) {
            workingDay = WorkingDayResourceIT.createEntity(em);
            em.persist(workingDay);
            em.flush();
        } else {
            workingDay = TestUtil.findAll(em, WorkingDay.class).get(0);
        }
        trimester.setWorkingDay(workingDay);
        // Add required entity
        LevelFormation levelFormation;
        if (TestUtil.findAll(em, LevelFormation.class).isEmpty()) {
            levelFormation = LevelFormationResourceIT.createEntity(em);
            em.persist(levelFormation);
            em.flush();
        } else {
            levelFormation = TestUtil.findAll(em, LevelFormation.class).get(0);
        }
        trimester.setLevelFormation(levelFormation);
        return trimester;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trimester createUpdatedEntity(EntityManager em) {
        Trimester trimester = new Trimester()
            .nameTrimester(UPDATED_NAME_TRIMESTER)
            .stateTrimester(UPDATED_STATE_TRIMESTER);
        // Add required entity
        WorkingDay workingDay;
        if (TestUtil.findAll(em, WorkingDay.class).isEmpty()) {
            workingDay = WorkingDayResourceIT.createUpdatedEntity(em);
            em.persist(workingDay);
            em.flush();
        } else {
            workingDay = TestUtil.findAll(em, WorkingDay.class).get(0);
        }
        trimester.setWorkingDay(workingDay);
        // Add required entity
        LevelFormation levelFormation;
        if (TestUtil.findAll(em, LevelFormation.class).isEmpty()) {
            levelFormation = LevelFormationResourceIT.createUpdatedEntity(em);
            em.persist(levelFormation);
            em.flush();
        } else {
            levelFormation = TestUtil.findAll(em, LevelFormation.class).get(0);
        }
        trimester.setLevelFormation(levelFormation);
        return trimester;
    }

    @BeforeEach
    public void initTest() {
        trimester = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrimester() throws Exception {
        int databaseSizeBeforeCreate = trimesterRepository.findAll().size();

        // Create the Trimester
        TrimesterDTO trimesterDTO = trimesterMapper.toDto(trimester);
        restTrimesterMockMvc.perform(post("/api/trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterDTO)))
            .andExpect(status().isCreated());

        // Validate the Trimester in the database
        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeCreate + 1);
        Trimester testTrimester = trimesterList.get(trimesterList.size() - 1);
        assertThat(testTrimester.getNameTrimester()).isEqualTo(DEFAULT_NAME_TRIMESTER);
        assertThat(testTrimester.getStateTrimester()).isEqualTo(DEFAULT_STATE_TRIMESTER);
    }

    @Test
    @Transactional
    public void createTrimesterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trimesterRepository.findAll().size();

        // Create the Trimester with an existing ID
        trimester.setId(1L);
        TrimesterDTO trimesterDTO = trimesterMapper.toDto(trimester);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrimesterMockMvc.perform(post("/api/trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trimester in the database
        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameTrimesterIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimesterRepository.findAll().size();
        // set the field null
        trimester.setNameTrimester(null);

        // Create the Trimester, which fails.
        TrimesterDTO trimesterDTO = trimesterMapper.toDto(trimester);

        restTrimesterMockMvc.perform(post("/api/trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterDTO)))
            .andExpect(status().isBadRequest());

        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateTrimesterIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimesterRepository.findAll().size();
        // set the field null
        trimester.setStateTrimester(null);

        // Create the Trimester, which fails.
        TrimesterDTO trimesterDTO = trimesterMapper.toDto(trimester);

        restTrimesterMockMvc.perform(post("/api/trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterDTO)))
            .andExpect(status().isBadRequest());

        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTrimesters() throws Exception {
        // Initialize the database
        trimesterRepository.saveAndFlush(trimester);

        // Get all the trimesterList
        restTrimesterMockMvc.perform(get("/api/trimesters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trimester.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameTrimester").value(hasItem(DEFAULT_NAME_TRIMESTER)))
            .andExpect(jsonPath("$.[*].stateTrimester").value(hasItem(DEFAULT_STATE_TRIMESTER.toString())));
    }
    
    @Test
    @Transactional
    public void getTrimester() throws Exception {
        // Initialize the database
        trimesterRepository.saveAndFlush(trimester);

        // Get the trimester
        restTrimesterMockMvc.perform(get("/api/trimesters/{id}", trimester.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trimester.getId().intValue()))
            .andExpect(jsonPath("$.nameTrimester").value(DEFAULT_NAME_TRIMESTER))
            .andExpect(jsonPath("$.stateTrimester").value(DEFAULT_STATE_TRIMESTER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrimester() throws Exception {
        // Get the trimester
        restTrimesterMockMvc.perform(get("/api/trimesters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrimester() throws Exception {
        // Initialize the database
        trimesterRepository.saveAndFlush(trimester);

        int databaseSizeBeforeUpdate = trimesterRepository.findAll().size();

        // Update the trimester
        Trimester updatedTrimester = trimesterRepository.findById(trimester.getId()).get();
        // Disconnect from session so that the updates on updatedTrimester are not directly saved in db
        em.detach(updatedTrimester);
        updatedTrimester
            .nameTrimester(UPDATED_NAME_TRIMESTER)
            .stateTrimester(UPDATED_STATE_TRIMESTER);
        TrimesterDTO trimesterDTO = trimesterMapper.toDto(updatedTrimester);

        restTrimesterMockMvc.perform(put("/api/trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterDTO)))
            .andExpect(status().isOk());

        // Validate the Trimester in the database
        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeUpdate);
        Trimester testTrimester = trimesterList.get(trimesterList.size() - 1);
        assertThat(testTrimester.getNameTrimester()).isEqualTo(UPDATED_NAME_TRIMESTER);
        assertThat(testTrimester.getStateTrimester()).isEqualTo(UPDATED_STATE_TRIMESTER);
    }

    @Test
    @Transactional
    public void updateNonExistingTrimester() throws Exception {
        int databaseSizeBeforeUpdate = trimesterRepository.findAll().size();

        // Create the Trimester
        TrimesterDTO trimesterDTO = trimesterMapper.toDto(trimester);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrimesterMockMvc.perform(put("/api/trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimesterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trimester in the database
        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrimester() throws Exception {
        // Initialize the database
        trimesterRepository.saveAndFlush(trimester);

        int databaseSizeBeforeDelete = trimesterRepository.findAll().size();

        // Delete the trimester
        restTrimesterMockMvc.perform(delete("/api/trimesters/{id}", trimester.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Trimester> trimesterList = trimesterRepository.findAll();
        assertThat(trimesterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
