package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.ScheduleVersion;
import co.edu.sena.domain.CurrentQuarter;
import co.edu.sena.repository.ScheduleVersionRepository;
import co.edu.sena.service.ScheduleVersionService;
import co.edu.sena.service.dto.ScheduleVersionDTO;
import co.edu.sena.service.mapper.ScheduleVersionMapper;
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
 * Integration tests for the {@link ScheduleVersionResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ScheduleVersionResourceIT {

    private static final String DEFAULT_NUMBER_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_VERSION = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_SCHEDULE_VERSION = State.ACTIVO;
    private static final State UPDATED_STATE_SCHEDULE_VERSION = State.INACTIVO;

    @Autowired
    private ScheduleVersionRepository scheduleVersionRepository;

    @Autowired
    private ScheduleVersionMapper scheduleVersionMapper;

    @Autowired
    private ScheduleVersionService scheduleVersionService;

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

    private MockMvc restScheduleVersionMockMvc;

    private ScheduleVersion scheduleVersion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ScheduleVersionResource scheduleVersionResource = new ScheduleVersionResource(scheduleVersionService);
        this.restScheduleVersionMockMvc = MockMvcBuilders.standaloneSetup(scheduleVersionResource)
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
    public static ScheduleVersion createEntity(EntityManager em) {
        ScheduleVersion scheduleVersion = new ScheduleVersion()
            .numberVersion(DEFAULT_NUMBER_VERSION)
            .stateScheduleVersion(DEFAULT_STATE_SCHEDULE_VERSION);
        // Add required entity
        CurrentQuarter currentQuarter;
        if (TestUtil.findAll(em, CurrentQuarter.class).isEmpty()) {
            currentQuarter = CurrentQuarterResourceIT.createEntity(em);
            em.persist(currentQuarter);
            em.flush();
        } else {
            currentQuarter = TestUtil.findAll(em, CurrentQuarter.class).get(0);
        }
        scheduleVersion.setCurrentQuarter(currentQuarter);
        return scheduleVersion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScheduleVersion createUpdatedEntity(EntityManager em) {
        ScheduleVersion scheduleVersion = new ScheduleVersion()
            .numberVersion(UPDATED_NUMBER_VERSION)
            .stateScheduleVersion(UPDATED_STATE_SCHEDULE_VERSION);
        // Add required entity
        CurrentQuarter currentQuarter;
        if (TestUtil.findAll(em, CurrentQuarter.class).isEmpty()) {
            currentQuarter = CurrentQuarterResourceIT.createUpdatedEntity(em);
            em.persist(currentQuarter);
            em.flush();
        } else {
            currentQuarter = TestUtil.findAll(em, CurrentQuarter.class).get(0);
        }
        scheduleVersion.setCurrentQuarter(currentQuarter);
        return scheduleVersion;
    }

    @BeforeEach
    public void initTest() {
        scheduleVersion = createEntity(em);
    }

    @Test
    @Transactional
    public void createScheduleVersion() throws Exception {
        int databaseSizeBeforeCreate = scheduleVersionRepository.findAll().size();

        // Create the ScheduleVersion
        ScheduleVersionDTO scheduleVersionDTO = scheduleVersionMapper.toDto(scheduleVersion);
        restScheduleVersionMockMvc.perform(post("/api/schedule-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleVersionDTO)))
            .andExpect(status().isCreated());

        // Validate the ScheduleVersion in the database
        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeCreate + 1);
        ScheduleVersion testScheduleVersion = scheduleVersionList.get(scheduleVersionList.size() - 1);
        assertThat(testScheduleVersion.getNumberVersion()).isEqualTo(DEFAULT_NUMBER_VERSION);
        assertThat(testScheduleVersion.getStateScheduleVersion()).isEqualTo(DEFAULT_STATE_SCHEDULE_VERSION);
    }

    @Test
    @Transactional
    public void createScheduleVersionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scheduleVersionRepository.findAll().size();

        // Create the ScheduleVersion with an existing ID
        scheduleVersion.setId(1L);
        ScheduleVersionDTO scheduleVersionDTO = scheduleVersionMapper.toDto(scheduleVersion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restScheduleVersionMockMvc.perform(post("/api/schedule-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleVersionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleVersion in the database
        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumberVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = scheduleVersionRepository.findAll().size();
        // set the field null
        scheduleVersion.setNumberVersion(null);

        // Create the ScheduleVersion, which fails.
        ScheduleVersionDTO scheduleVersionDTO = scheduleVersionMapper.toDto(scheduleVersion);

        restScheduleVersionMockMvc.perform(post("/api/schedule-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleVersionDTO)))
            .andExpect(status().isBadRequest());

        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateScheduleVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = scheduleVersionRepository.findAll().size();
        // set the field null
        scheduleVersion.setStateScheduleVersion(null);

        // Create the ScheduleVersion, which fails.
        ScheduleVersionDTO scheduleVersionDTO = scheduleVersionMapper.toDto(scheduleVersion);

        restScheduleVersionMockMvc.perform(post("/api/schedule-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleVersionDTO)))
            .andExpect(status().isBadRequest());

        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllScheduleVersions() throws Exception {
        // Initialize the database
        scheduleVersionRepository.saveAndFlush(scheduleVersion);

        // Get all the scheduleVersionList
        restScheduleVersionMockMvc.perform(get("/api/schedule-versions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheduleVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberVersion").value(hasItem(DEFAULT_NUMBER_VERSION)))
            .andExpect(jsonPath("$.[*].stateScheduleVersion").value(hasItem(DEFAULT_STATE_SCHEDULE_VERSION.toString())));
    }
    
    @Test
    @Transactional
    public void getScheduleVersion() throws Exception {
        // Initialize the database
        scheduleVersionRepository.saveAndFlush(scheduleVersion);

        // Get the scheduleVersion
        restScheduleVersionMockMvc.perform(get("/api/schedule-versions/{id}", scheduleVersion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(scheduleVersion.getId().intValue()))
            .andExpect(jsonPath("$.numberVersion").value(DEFAULT_NUMBER_VERSION))
            .andExpect(jsonPath("$.stateScheduleVersion").value(DEFAULT_STATE_SCHEDULE_VERSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingScheduleVersion() throws Exception {
        // Get the scheduleVersion
        restScheduleVersionMockMvc.perform(get("/api/schedule-versions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScheduleVersion() throws Exception {
        // Initialize the database
        scheduleVersionRepository.saveAndFlush(scheduleVersion);

        int databaseSizeBeforeUpdate = scheduleVersionRepository.findAll().size();

        // Update the scheduleVersion
        ScheduleVersion updatedScheduleVersion = scheduleVersionRepository.findById(scheduleVersion.getId()).get();
        // Disconnect from session so that the updates on updatedScheduleVersion are not directly saved in db
        em.detach(updatedScheduleVersion);
        updatedScheduleVersion
            .numberVersion(UPDATED_NUMBER_VERSION)
            .stateScheduleVersion(UPDATED_STATE_SCHEDULE_VERSION);
        ScheduleVersionDTO scheduleVersionDTO = scheduleVersionMapper.toDto(updatedScheduleVersion);

        restScheduleVersionMockMvc.perform(put("/api/schedule-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleVersionDTO)))
            .andExpect(status().isOk());

        // Validate the ScheduleVersion in the database
        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeUpdate);
        ScheduleVersion testScheduleVersion = scheduleVersionList.get(scheduleVersionList.size() - 1);
        assertThat(testScheduleVersion.getNumberVersion()).isEqualTo(UPDATED_NUMBER_VERSION);
        assertThat(testScheduleVersion.getStateScheduleVersion()).isEqualTo(UPDATED_STATE_SCHEDULE_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingScheduleVersion() throws Exception {
        int databaseSizeBeforeUpdate = scheduleVersionRepository.findAll().size();

        // Create the ScheduleVersion
        ScheduleVersionDTO scheduleVersionDTO = scheduleVersionMapper.toDto(scheduleVersion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScheduleVersionMockMvc.perform(put("/api/schedule-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleVersionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleVersion in the database
        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScheduleVersion() throws Exception {
        // Initialize the database
        scheduleVersionRepository.saveAndFlush(scheduleVersion);

        int databaseSizeBeforeDelete = scheduleVersionRepository.findAll().size();

        // Delete the scheduleVersion
        restScheduleVersionMockMvc.perform(delete("/api/schedule-versions/{id}", scheduleVersion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ScheduleVersion> scheduleVersionList = scheduleVersionRepository.findAll();
        assertThat(scheduleVersionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
