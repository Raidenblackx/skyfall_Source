package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.TimeStudy;
import co.edu.sena.domain.JourneyInstructor;
import co.edu.sena.domain.Day;
import co.edu.sena.repository.TimeStudyRepository;
import co.edu.sena.service.TimeStudyService;
import co.edu.sena.service.dto.TimeStudyDTO;
import co.edu.sena.service.mapper.TimeStudyMapper;
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
 * Integration tests for the {@link TimeStudyResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class TimeStudyResourceIT {

    private static final Integer DEFAULT_START_TIME = 1;
    private static final Integer UPDATED_START_TIME = 2;

    private static final Integer DEFAULT_END_TIME = 1;
    private static final Integer UPDATED_END_TIME = 2;

    @Autowired
    private TimeStudyRepository timeStudyRepository;

    @Autowired
    private TimeStudyMapper timeStudyMapper;

    @Autowired
    private TimeStudyService timeStudyService;

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

    private MockMvc restTimeStudyMockMvc;

    private TimeStudy timeStudy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TimeStudyResource timeStudyResource = new TimeStudyResource(timeStudyService);
        this.restTimeStudyMockMvc = MockMvcBuilders.standaloneSetup(timeStudyResource)
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
    public static TimeStudy createEntity(EntityManager em) {
        TimeStudy timeStudy = new TimeStudy()
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME);
        // Add required entity
        JourneyInstructor journeyInstructor;
        if (TestUtil.findAll(em, JourneyInstructor.class).isEmpty()) {
            journeyInstructor = JourneyInstructorResourceIT.createEntity(em);
            em.persist(journeyInstructor);
            em.flush();
        } else {
            journeyInstructor = TestUtil.findAll(em, JourneyInstructor.class).get(0);
        }
        timeStudy.setJourneyInstructor(journeyInstructor);
        // Add required entity
        Day day;
        if (TestUtil.findAll(em, Day.class).isEmpty()) {
            day = DayResourceIT.createEntity(em);
            em.persist(day);
            em.flush();
        } else {
            day = TestUtil.findAll(em, Day.class).get(0);
        }
        timeStudy.setDay(day);
        return timeStudy;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TimeStudy createUpdatedEntity(EntityManager em) {
        TimeStudy timeStudy = new TimeStudy()
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        // Add required entity
        JourneyInstructor journeyInstructor;
        if (TestUtil.findAll(em, JourneyInstructor.class).isEmpty()) {
            journeyInstructor = JourneyInstructorResourceIT.createUpdatedEntity(em);
            em.persist(journeyInstructor);
            em.flush();
        } else {
            journeyInstructor = TestUtil.findAll(em, JourneyInstructor.class).get(0);
        }
        timeStudy.setJourneyInstructor(journeyInstructor);
        // Add required entity
        Day day;
        if (TestUtil.findAll(em, Day.class).isEmpty()) {
            day = DayResourceIT.createUpdatedEntity(em);
            em.persist(day);
            em.flush();
        } else {
            day = TestUtil.findAll(em, Day.class).get(0);
        }
        timeStudy.setDay(day);
        return timeStudy;
    }

    @BeforeEach
    public void initTest() {
        timeStudy = createEntity(em);
    }

    @Test
    @Transactional
    public void createTimeStudy() throws Exception {
        int databaseSizeBeforeCreate = timeStudyRepository.findAll().size();

        // Create the TimeStudy
        TimeStudyDTO timeStudyDTO = timeStudyMapper.toDto(timeStudy);
        restTimeStudyMockMvc.perform(post("/api/time-studies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeStudyDTO)))
            .andExpect(status().isCreated());

        // Validate the TimeStudy in the database
        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeCreate + 1);
        TimeStudy testTimeStudy = timeStudyList.get(timeStudyList.size() - 1);
        assertThat(testTimeStudy.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testTimeStudy.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    @Transactional
    public void createTimeStudyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = timeStudyRepository.findAll().size();

        // Create the TimeStudy with an existing ID
        timeStudy.setId(1L);
        TimeStudyDTO timeStudyDTO = timeStudyMapper.toDto(timeStudy);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTimeStudyMockMvc.perform(post("/api/time-studies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeStudyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TimeStudy in the database
        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = timeStudyRepository.findAll().size();
        // set the field null
        timeStudy.setStartTime(null);

        // Create the TimeStudy, which fails.
        TimeStudyDTO timeStudyDTO = timeStudyMapper.toDto(timeStudy);

        restTimeStudyMockMvc.perform(post("/api/time-studies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeStudyDTO)))
            .andExpect(status().isBadRequest());

        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = timeStudyRepository.findAll().size();
        // set the field null
        timeStudy.setEndTime(null);

        // Create the TimeStudy, which fails.
        TimeStudyDTO timeStudyDTO = timeStudyMapper.toDto(timeStudy);

        restTimeStudyMockMvc.perform(post("/api/time-studies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeStudyDTO)))
            .andExpect(status().isBadRequest());

        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTimeStudies() throws Exception {
        // Initialize the database
        timeStudyRepository.saveAndFlush(timeStudy);

        // Get all the timeStudyList
        restTimeStudyMockMvc.perform(get("/api/time-studies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(timeStudy.getId().intValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME)));
    }
    
    @Test
    @Transactional
    public void getTimeStudy() throws Exception {
        // Initialize the database
        timeStudyRepository.saveAndFlush(timeStudy);

        // Get the timeStudy
        restTimeStudyMockMvc.perform(get("/api/time-studies/{id}", timeStudy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(timeStudy.getId().intValue()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME));
    }

    @Test
    @Transactional
    public void getNonExistingTimeStudy() throws Exception {
        // Get the timeStudy
        restTimeStudyMockMvc.perform(get("/api/time-studies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTimeStudy() throws Exception {
        // Initialize the database
        timeStudyRepository.saveAndFlush(timeStudy);

        int databaseSizeBeforeUpdate = timeStudyRepository.findAll().size();

        // Update the timeStudy
        TimeStudy updatedTimeStudy = timeStudyRepository.findById(timeStudy.getId()).get();
        // Disconnect from session so that the updates on updatedTimeStudy are not directly saved in db
        em.detach(updatedTimeStudy);
        updatedTimeStudy
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        TimeStudyDTO timeStudyDTO = timeStudyMapper.toDto(updatedTimeStudy);

        restTimeStudyMockMvc.perform(put("/api/time-studies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeStudyDTO)))
            .andExpect(status().isOk());

        // Validate the TimeStudy in the database
        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeUpdate);
        TimeStudy testTimeStudy = timeStudyList.get(timeStudyList.size() - 1);
        assertThat(testTimeStudy.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testTimeStudy.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingTimeStudy() throws Exception {
        int databaseSizeBeforeUpdate = timeStudyRepository.findAll().size();

        // Create the TimeStudy
        TimeStudyDTO timeStudyDTO = timeStudyMapper.toDto(timeStudy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimeStudyMockMvc.perform(put("/api/time-studies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeStudyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TimeStudy in the database
        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTimeStudy() throws Exception {
        // Initialize the database
        timeStudyRepository.saveAndFlush(timeStudy);

        int databaseSizeBeforeDelete = timeStudyRepository.findAll().size();

        // Delete the timeStudy
        restTimeStudyMockMvc.perform(delete("/api/time-studies/{id}", timeStudy.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TimeStudy> timeStudyList = timeStudyRepository.findAll();
        assertThat(timeStudyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
