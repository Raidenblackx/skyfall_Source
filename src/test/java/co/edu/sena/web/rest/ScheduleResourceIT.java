package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Schedule;
import co.edu.sena.domain.CourseHasTrimester;
import co.edu.sena.domain.Ambient;
import co.edu.sena.domain.Instructor;
import co.edu.sena.domain.Day;
import co.edu.sena.domain.ScheduleVersion;
import co.edu.sena.domain.Modality;
import co.edu.sena.repository.ScheduleRepository;
import co.edu.sena.service.ScheduleService;
import co.edu.sena.service.dto.ScheduleDTO;
import co.edu.sena.service.mapper.ScheduleMapper;
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

/**
 * Integration tests for the {@link ScheduleResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ScheduleResourceIT {

    private static final ZonedDateTime DEFAULT_START_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ScheduleService scheduleService;

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

    private MockMvc restScheduleMockMvc;

    private Schedule schedule;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ScheduleResource scheduleResource = new ScheduleResource(scheduleService);
        this.restScheduleMockMvc = MockMvcBuilders.standaloneSetup(scheduleResource)
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
    public static Schedule createEntity(EntityManager em) {
        Schedule schedule = new Schedule()
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME);
        // Add required entity
        CourseHasTrimester courseHasTrimester;
        if (TestUtil.findAll(em, CourseHasTrimester.class).isEmpty()) {
            courseHasTrimester = CourseHasTrimesterResourceIT.createEntity(em);
            em.persist(courseHasTrimester);
            em.flush();
        } else {
            courseHasTrimester = TestUtil.findAll(em, CourseHasTrimester.class).get(0);
        }
        schedule.setCourseHasTrimester(courseHasTrimester);
        // Add required entity
        Ambient ambient;
        if (TestUtil.findAll(em, Ambient.class).isEmpty()) {
            ambient = AmbientResourceIT.createEntity(em);
            em.persist(ambient);
            em.flush();
        } else {
            ambient = TestUtil.findAll(em, Ambient.class).get(0);
        }
        schedule.setAmbient(ambient);
        // Add required entity
        Instructor instructor;
        if (TestUtil.findAll(em, Instructor.class).isEmpty()) {
            instructor = InstructorResourceIT.createEntity(em);
            em.persist(instructor);
            em.flush();
        } else {
            instructor = TestUtil.findAll(em, Instructor.class).get(0);
        }
        schedule.setInstructor(instructor);
        // Add required entity
        Day day;
        if (TestUtil.findAll(em, Day.class).isEmpty()) {
            day = DayResourceIT.createEntity(em);
            em.persist(day);
            em.flush();
        } else {
            day = TestUtil.findAll(em, Day.class).get(0);
        }
        schedule.setDay(day);
        // Add required entity
        ScheduleVersion scheduleVersion;
        if (TestUtil.findAll(em, ScheduleVersion.class).isEmpty()) {
            scheduleVersion = ScheduleVersionResourceIT.createEntity(em);
            em.persist(scheduleVersion);
            em.flush();
        } else {
            scheduleVersion = TestUtil.findAll(em, ScheduleVersion.class).get(0);
        }
        schedule.setScheduleVersion(scheduleVersion);
        // Add required entity
        Modality modality;
        if (TestUtil.findAll(em, Modality.class).isEmpty()) {
            modality = ModalityResourceIT.createEntity(em);
            em.persist(modality);
            em.flush();
        } else {
            modality = TestUtil.findAll(em, Modality.class).get(0);
        }
        schedule.setModality(modality);
        return schedule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Schedule createUpdatedEntity(EntityManager em) {
        Schedule schedule = new Schedule()
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        // Add required entity
        CourseHasTrimester courseHasTrimester;
        if (TestUtil.findAll(em, CourseHasTrimester.class).isEmpty()) {
            courseHasTrimester = CourseHasTrimesterResourceIT.createUpdatedEntity(em);
            em.persist(courseHasTrimester);
            em.flush();
        } else {
            courseHasTrimester = TestUtil.findAll(em, CourseHasTrimester.class).get(0);
        }
        schedule.setCourseHasTrimester(courseHasTrimester);
        // Add required entity
        Ambient ambient;
        if (TestUtil.findAll(em, Ambient.class).isEmpty()) {
            ambient = AmbientResourceIT.createUpdatedEntity(em);
            em.persist(ambient);
            em.flush();
        } else {
            ambient = TestUtil.findAll(em, Ambient.class).get(0);
        }
        schedule.setAmbient(ambient);
        // Add required entity
        Instructor instructor;
        if (TestUtil.findAll(em, Instructor.class).isEmpty()) {
            instructor = InstructorResourceIT.createUpdatedEntity(em);
            em.persist(instructor);
            em.flush();
        } else {
            instructor = TestUtil.findAll(em, Instructor.class).get(0);
        }
        schedule.setInstructor(instructor);
        // Add required entity
        Day day;
        if (TestUtil.findAll(em, Day.class).isEmpty()) {
            day = DayResourceIT.createUpdatedEntity(em);
            em.persist(day);
            em.flush();
        } else {
            day = TestUtil.findAll(em, Day.class).get(0);
        }
        schedule.setDay(day);
        // Add required entity
        ScheduleVersion scheduleVersion;
        if (TestUtil.findAll(em, ScheduleVersion.class).isEmpty()) {
            scheduleVersion = ScheduleVersionResourceIT.createUpdatedEntity(em);
            em.persist(scheduleVersion);
            em.flush();
        } else {
            scheduleVersion = TestUtil.findAll(em, ScheduleVersion.class).get(0);
        }
        schedule.setScheduleVersion(scheduleVersion);
        // Add required entity
        Modality modality;
        if (TestUtil.findAll(em, Modality.class).isEmpty()) {
            modality = ModalityResourceIT.createUpdatedEntity(em);
            em.persist(modality);
            em.flush();
        } else {
            modality = TestUtil.findAll(em, Modality.class).get(0);
        }
        schedule.setModality(modality);
        return schedule;
    }

    @BeforeEach
    public void initTest() {
        schedule = createEntity(em);
    }

    @Test
    @Transactional
    public void createSchedule() throws Exception {
        int databaseSizeBeforeCreate = scheduleRepository.findAll().size();

        // Create the Schedule
        ScheduleDTO scheduleDTO = scheduleMapper.toDto(schedule);
        restScheduleMockMvc.perform(post("/api/schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleDTO)))
            .andExpect(status().isCreated());

        // Validate the Schedule in the database
        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeCreate + 1);
        Schedule testSchedule = scheduleList.get(scheduleList.size() - 1);
        assertThat(testSchedule.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testSchedule.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    }

    @Test
    @Transactional
    public void createScheduleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scheduleRepository.findAll().size();

        // Create the Schedule with an existing ID
        schedule.setId(1L);
        ScheduleDTO scheduleDTO = scheduleMapper.toDto(schedule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restScheduleMockMvc.perform(post("/api/schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Schedule in the database
        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = scheduleRepository.findAll().size();
        // set the field null
        schedule.setStartTime(null);

        // Create the Schedule, which fails.
        ScheduleDTO scheduleDTO = scheduleMapper.toDto(schedule);

        restScheduleMockMvc.perform(post("/api/schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleDTO)))
            .andExpect(status().isBadRequest());

        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = scheduleRepository.findAll().size();
        // set the field null
        schedule.setEndTime(null);

        // Create the Schedule, which fails.
        ScheduleDTO scheduleDTO = scheduleMapper.toDto(schedule);

        restScheduleMockMvc.perform(post("/api/schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleDTO)))
            .andExpect(status().isBadRequest());

        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSchedules() throws Exception {
        // Initialize the database
        scheduleRepository.saveAndFlush(schedule);

        // Get all the scheduleList
        restScheduleMockMvc.perform(get("/api/schedules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(schedule.getId().intValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(sameInstant(DEFAULT_START_TIME))))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(sameInstant(DEFAULT_END_TIME))));
    }
    
    @Test
    @Transactional
    public void getSchedule() throws Exception {
        // Initialize the database
        scheduleRepository.saveAndFlush(schedule);

        // Get the schedule
        restScheduleMockMvc.perform(get("/api/schedules/{id}", schedule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(schedule.getId().intValue()))
            .andExpect(jsonPath("$.startTime").value(sameInstant(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.endTime").value(sameInstant(DEFAULT_END_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSchedule() throws Exception {
        // Get the schedule
        restScheduleMockMvc.perform(get("/api/schedules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSchedule() throws Exception {
        // Initialize the database
        scheduleRepository.saveAndFlush(schedule);

        int databaseSizeBeforeUpdate = scheduleRepository.findAll().size();

        // Update the schedule
        Schedule updatedSchedule = scheduleRepository.findById(schedule.getId()).get();
        // Disconnect from session so that the updates on updatedSchedule are not directly saved in db
        em.detach(updatedSchedule);
        updatedSchedule
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME);
        ScheduleDTO scheduleDTO = scheduleMapper.toDto(updatedSchedule);

        restScheduleMockMvc.perform(put("/api/schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleDTO)))
            .andExpect(status().isOk());

        // Validate the Schedule in the database
        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeUpdate);
        Schedule testSchedule = scheduleList.get(scheduleList.size() - 1);
        assertThat(testSchedule.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testSchedule.getEndTime()).isEqualTo(UPDATED_END_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSchedule() throws Exception {
        int databaseSizeBeforeUpdate = scheduleRepository.findAll().size();

        // Create the Schedule
        ScheduleDTO scheduleDTO = scheduleMapper.toDto(schedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScheduleMockMvc.perform(put("/api/schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Schedule in the database
        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSchedule() throws Exception {
        // Initialize the database
        scheduleRepository.saveAndFlush(schedule);

        int databaseSizeBeforeDelete = scheduleRepository.findAll().size();

        // Delete the schedule
        restScheduleMockMvc.perform(delete("/api/schedules/{id}", schedule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Schedule> scheduleList = scheduleRepository.findAll();
        assertThat(scheduleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
