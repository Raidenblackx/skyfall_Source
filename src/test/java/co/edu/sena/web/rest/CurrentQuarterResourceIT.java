package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.CurrentQuarter;
import co.edu.sena.domain.Year;
import co.edu.sena.repository.CurrentQuarterRepository;
import co.edu.sena.service.CurrentQuarterService;
import co.edu.sena.service.dto.CurrentQuarterDTO;
import co.edu.sena.service.mapper.CurrentQuarterMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static co.edu.sena.web.rest.TestUtil.sameInstant;
import static co.edu.sena.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.domain.enumeration.State;
/**
 * Integration tests for the {@link CurrentQuarterResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class CurrentQuarterResourceIT {

    private static final Integer DEFAULT_SCHEDULED_QUARTER = 1;
    private static final Integer UPDATED_SCHEDULED_QUARTER = 2;

    private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final State DEFAULT_STATE_CURRENT_QUARTER = State.ACTIVO;
    private static final State UPDATED_STATE_CURRENT_QUARTER = State.INACTIVO;

    @Autowired
    private CurrentQuarterRepository currentQuarterRepository;

    @Autowired
    private CurrentQuarterMapper currentQuarterMapper;

    @Autowired
    private CurrentQuarterService currentQuarterService;

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

    private MockMvc restCurrentQuarterMockMvc;

    private CurrentQuarter currentQuarter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CurrentQuarterResource currentQuarterResource = new CurrentQuarterResource(currentQuarterService);
        this.restCurrentQuarterMockMvc = MockMvcBuilders.standaloneSetup(currentQuarterResource)
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
    public static CurrentQuarter createEntity(EntityManager em) {
        CurrentQuarter currentQuarter = new CurrentQuarter()
            .scheduledQuarter(DEFAULT_SCHEDULED_QUARTER)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .stateCurrentQuarter(DEFAULT_STATE_CURRENT_QUARTER);
        // Add required entity
        Year year;
        if (TestUtil.findAll(em, Year.class).isEmpty()) {
            year = YearResourceIT.createEntity(em);
            em.persist(year);
            em.flush();
        } else {
            year = TestUtil.findAll(em, Year.class).get(0);
        }
        currentQuarter.setYear(year);
        return currentQuarter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrentQuarter createUpdatedEntity(EntityManager em) {
        CurrentQuarter currentQuarter = new CurrentQuarter()
            .scheduledQuarter(UPDATED_SCHEDULED_QUARTER)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .stateCurrentQuarter(UPDATED_STATE_CURRENT_QUARTER);
        // Add required entity
        Year year;
        if (TestUtil.findAll(em, Year.class).isEmpty()) {
            year = YearResourceIT.createUpdatedEntity(em);
            em.persist(year);
            em.flush();
        } else {
            year = TestUtil.findAll(em, Year.class).get(0);
        }
        currentQuarter.setYear(year);
        return currentQuarter;
    }

    @BeforeEach
    public void initTest() {
        currentQuarter = createEntity(em);
    }

    @Test
    @Transactional
    public void createCurrentQuarter() throws Exception {
        int databaseSizeBeforeCreate = currentQuarterRepository.findAll().size();

        // Create the CurrentQuarter
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);
        restCurrentQuarterMockMvc.perform(post("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isCreated());

        // Validate the CurrentQuarter in the database
        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeCreate + 1);
        CurrentQuarter testCurrentQuarter = currentQuarterList.get(currentQuarterList.size() - 1);
        assertThat(testCurrentQuarter.getScheduledQuarter()).isEqualTo(DEFAULT_SCHEDULED_QUARTER);
        assertThat(testCurrentQuarter.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testCurrentQuarter.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCurrentQuarter.getStateCurrentQuarter()).isEqualTo(DEFAULT_STATE_CURRENT_QUARTER);
    }

    @Test
    @Transactional
    public void createCurrentQuarterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = currentQuarterRepository.findAll().size();

        // Create the CurrentQuarter with an existing ID
        currentQuarter.setId(1L);
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurrentQuarterMockMvc.perform(post("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CurrentQuarter in the database
        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkScheduledQuarterIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentQuarterRepository.findAll().size();
        // set the field null
        currentQuarter.setScheduledQuarter(null);

        // Create the CurrentQuarter, which fails.
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);

        restCurrentQuarterMockMvc.perform(post("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isBadRequest());

        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentQuarterRepository.findAll().size();
        // set the field null
        currentQuarter.setStartDate(null);

        // Create the CurrentQuarter, which fails.
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);

        restCurrentQuarterMockMvc.perform(post("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isBadRequest());

        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentQuarterRepository.findAll().size();
        // set the field null
        currentQuarter.setEndDate(null);

        // Create the CurrentQuarter, which fails.
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);

        restCurrentQuarterMockMvc.perform(post("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isBadRequest());

        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateCurrentQuarterIsRequired() throws Exception {
        int databaseSizeBeforeTest = currentQuarterRepository.findAll().size();
        // set the field null
        currentQuarter.setStateCurrentQuarter(null);

        // Create the CurrentQuarter, which fails.
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);

        restCurrentQuarterMockMvc.perform(post("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isBadRequest());

        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCurrentQuarters() throws Exception {
        // Initialize the database
        currentQuarterRepository.saveAndFlush(currentQuarter);

        // Get all the currentQuarterList
        restCurrentQuarterMockMvc.perform(get("/api/current-quarters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(currentQuarter.getId().intValue())))
            .andExpect(jsonPath("$.[*].scheduledQuarter").value(hasItem(DEFAULT_SCHEDULED_QUARTER)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(sameInstant(DEFAULT_START_DATE))))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(sameInstant(DEFAULT_END_DATE))))
            .andExpect(jsonPath("$.[*].stateCurrentQuarter").value(hasItem(DEFAULT_STATE_CURRENT_QUARTER.toString())));
    }
    
    @Test
    @Transactional
    public void getCurrentQuarter() throws Exception {
        // Initialize the database
        currentQuarterRepository.saveAndFlush(currentQuarter);

        // Get the currentQuarter
        restCurrentQuarterMockMvc.perform(get("/api/current-quarters/{id}", currentQuarter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(currentQuarter.getId().intValue()))
            .andExpect(jsonPath("$.scheduledQuarter").value(DEFAULT_SCHEDULED_QUARTER))
            .andExpect(jsonPath("$.startDate").value(sameInstant(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.endDate").value(sameInstant(DEFAULT_END_DATE)))
            .andExpect(jsonPath("$.stateCurrentQuarter").value(DEFAULT_STATE_CURRENT_QUARTER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCurrentQuarter() throws Exception {
        // Get the currentQuarter
        restCurrentQuarterMockMvc.perform(get("/api/current-quarters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurrentQuarter() throws Exception {
        // Initialize the database
        currentQuarterRepository.saveAndFlush(currentQuarter);

        int databaseSizeBeforeUpdate = currentQuarterRepository.findAll().size();

        // Update the currentQuarter
        CurrentQuarter updatedCurrentQuarter = currentQuarterRepository.findById(currentQuarter.getId()).get();
        // Disconnect from session so that the updates on updatedCurrentQuarter are not directly saved in db
        em.detach(updatedCurrentQuarter);
        updatedCurrentQuarter
            .scheduledQuarter(UPDATED_SCHEDULED_QUARTER)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .stateCurrentQuarter(UPDATED_STATE_CURRENT_QUARTER);
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(updatedCurrentQuarter);

        restCurrentQuarterMockMvc.perform(put("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isOk());

        // Validate the CurrentQuarter in the database
        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeUpdate);
        CurrentQuarter testCurrentQuarter = currentQuarterList.get(currentQuarterList.size() - 1);
        assertThat(testCurrentQuarter.getScheduledQuarter()).isEqualTo(UPDATED_SCHEDULED_QUARTER);
        assertThat(testCurrentQuarter.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testCurrentQuarter.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCurrentQuarter.getStateCurrentQuarter()).isEqualTo(UPDATED_STATE_CURRENT_QUARTER);
    }

    @Test
    @Transactional
    public void updateNonExistingCurrentQuarter() throws Exception {
        int databaseSizeBeforeUpdate = currentQuarterRepository.findAll().size();

        // Create the CurrentQuarter
        CurrentQuarterDTO currentQuarterDTO = currentQuarterMapper.toDto(currentQuarter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrentQuarterMockMvc.perform(put("/api/current-quarters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(currentQuarterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CurrentQuarter in the database
        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCurrentQuarter() throws Exception {
        // Initialize the database
        currentQuarterRepository.saveAndFlush(currentQuarter);

        int databaseSizeBeforeDelete = currentQuarterRepository.findAll().size();

        // Delete the currentQuarter
        restCurrentQuarterMockMvc.perform(delete("/api/current-quarters/{id}", currentQuarter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CurrentQuarter> currentQuarterList = currentQuarterRepository.findAll();
        assertThat(currentQuarterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
