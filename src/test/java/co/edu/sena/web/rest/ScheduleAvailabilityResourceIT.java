package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.ScheduleAvailability;
import co.edu.sena.domain.InstructorLinking;
import co.edu.sena.domain.JourneyInstructor;
import co.edu.sena.repository.ScheduleAvailabilityRepository;
import co.edu.sena.service.ScheduleAvailabilityService;
import co.edu.sena.service.dto.ScheduleAvailabilityDTO;
import co.edu.sena.service.mapper.ScheduleAvailabilityMapper;
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
 * Integration tests for the {@link ScheduleAvailabilityResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ScheduleAvailabilityResourceIT {

    @Autowired
    private ScheduleAvailabilityRepository scheduleAvailabilityRepository;

    @Autowired
    private ScheduleAvailabilityMapper scheduleAvailabilityMapper;

    @Autowired
    private ScheduleAvailabilityService scheduleAvailabilityService;

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

    private MockMvc restScheduleAvailabilityMockMvc;

    private ScheduleAvailability scheduleAvailability;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ScheduleAvailabilityResource scheduleAvailabilityResource = new ScheduleAvailabilityResource(scheduleAvailabilityService);
        this.restScheduleAvailabilityMockMvc = MockMvcBuilders.standaloneSetup(scheduleAvailabilityResource)
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
    public static ScheduleAvailability createEntity(EntityManager em) {
        ScheduleAvailability scheduleAvailability = new ScheduleAvailability();
        // Add required entity
        InstructorLinking instructorLinking;
        if (TestUtil.findAll(em, InstructorLinking.class).isEmpty()) {
            instructorLinking = InstructorLinkingResourceIT.createEntity(em);
            em.persist(instructorLinking);
            em.flush();
        } else {
            instructorLinking = TestUtil.findAll(em, InstructorLinking.class).get(0);
        }
        scheduleAvailability.setInstructorLinking(instructorLinking);
        // Add required entity
        JourneyInstructor journeyInstructor;
        if (TestUtil.findAll(em, JourneyInstructor.class).isEmpty()) {
            journeyInstructor = JourneyInstructorResourceIT.createEntity(em);
            em.persist(journeyInstructor);
            em.flush();
        } else {
            journeyInstructor = TestUtil.findAll(em, JourneyInstructor.class).get(0);
        }
        scheduleAvailability.setJourneyInstructor(journeyInstructor);
        return scheduleAvailability;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScheduleAvailability createUpdatedEntity(EntityManager em) {
        ScheduleAvailability scheduleAvailability = new ScheduleAvailability();
        // Add required entity
        InstructorLinking instructorLinking;
        if (TestUtil.findAll(em, InstructorLinking.class).isEmpty()) {
            instructorLinking = InstructorLinkingResourceIT.createUpdatedEntity(em);
            em.persist(instructorLinking);
            em.flush();
        } else {
            instructorLinking = TestUtil.findAll(em, InstructorLinking.class).get(0);
        }
        scheduleAvailability.setInstructorLinking(instructorLinking);
        // Add required entity
        JourneyInstructor journeyInstructor;
        if (TestUtil.findAll(em, JourneyInstructor.class).isEmpty()) {
            journeyInstructor = JourneyInstructorResourceIT.createUpdatedEntity(em);
            em.persist(journeyInstructor);
            em.flush();
        } else {
            journeyInstructor = TestUtil.findAll(em, JourneyInstructor.class).get(0);
        }
        scheduleAvailability.setJourneyInstructor(journeyInstructor);
        return scheduleAvailability;
    }

    @BeforeEach
    public void initTest() {
        scheduleAvailability = createEntity(em);
    }

    @Test
    @Transactional
    public void createScheduleAvailability() throws Exception {
        int databaseSizeBeforeCreate = scheduleAvailabilityRepository.findAll().size();

        // Create the ScheduleAvailability
        ScheduleAvailabilityDTO scheduleAvailabilityDTO = scheduleAvailabilityMapper.toDto(scheduleAvailability);
        restScheduleAvailabilityMockMvc.perform(post("/api/schedule-availabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleAvailabilityDTO)))
            .andExpect(status().isCreated());

        // Validate the ScheduleAvailability in the database
        List<ScheduleAvailability> scheduleAvailabilityList = scheduleAvailabilityRepository.findAll();
        assertThat(scheduleAvailabilityList).hasSize(databaseSizeBeforeCreate + 1);
        ScheduleAvailability testScheduleAvailability = scheduleAvailabilityList.get(scheduleAvailabilityList.size() - 1);
    }

    @Test
    @Transactional
    public void createScheduleAvailabilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scheduleAvailabilityRepository.findAll().size();

        // Create the ScheduleAvailability with an existing ID
        scheduleAvailability.setId(1L);
        ScheduleAvailabilityDTO scheduleAvailabilityDTO = scheduleAvailabilityMapper.toDto(scheduleAvailability);

        // An entity with an existing ID cannot be created, so this API call must fail
        restScheduleAvailabilityMockMvc.perform(post("/api/schedule-availabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleAvailabilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleAvailability in the database
        List<ScheduleAvailability> scheduleAvailabilityList = scheduleAvailabilityRepository.findAll();
        assertThat(scheduleAvailabilityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllScheduleAvailabilities() throws Exception {
        // Initialize the database
        scheduleAvailabilityRepository.saveAndFlush(scheduleAvailability);

        // Get all the scheduleAvailabilityList
        restScheduleAvailabilityMockMvc.perform(get("/api/schedule-availabilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheduleAvailability.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getScheduleAvailability() throws Exception {
        // Initialize the database
        scheduleAvailabilityRepository.saveAndFlush(scheduleAvailability);

        // Get the scheduleAvailability
        restScheduleAvailabilityMockMvc.perform(get("/api/schedule-availabilities/{id}", scheduleAvailability.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(scheduleAvailability.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingScheduleAvailability() throws Exception {
        // Get the scheduleAvailability
        restScheduleAvailabilityMockMvc.perform(get("/api/schedule-availabilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScheduleAvailability() throws Exception {
        // Initialize the database
        scheduleAvailabilityRepository.saveAndFlush(scheduleAvailability);

        int databaseSizeBeforeUpdate = scheduleAvailabilityRepository.findAll().size();

        // Update the scheduleAvailability
        ScheduleAvailability updatedScheduleAvailability = scheduleAvailabilityRepository.findById(scheduleAvailability.getId()).get();
        // Disconnect from session so that the updates on updatedScheduleAvailability are not directly saved in db
        em.detach(updatedScheduleAvailability);
        ScheduleAvailabilityDTO scheduleAvailabilityDTO = scheduleAvailabilityMapper.toDto(updatedScheduleAvailability);

        restScheduleAvailabilityMockMvc.perform(put("/api/schedule-availabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleAvailabilityDTO)))
            .andExpect(status().isOk());

        // Validate the ScheduleAvailability in the database
        List<ScheduleAvailability> scheduleAvailabilityList = scheduleAvailabilityRepository.findAll();
        assertThat(scheduleAvailabilityList).hasSize(databaseSizeBeforeUpdate);
        ScheduleAvailability testScheduleAvailability = scheduleAvailabilityList.get(scheduleAvailabilityList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingScheduleAvailability() throws Exception {
        int databaseSizeBeforeUpdate = scheduleAvailabilityRepository.findAll().size();

        // Create the ScheduleAvailability
        ScheduleAvailabilityDTO scheduleAvailabilityDTO = scheduleAvailabilityMapper.toDto(scheduleAvailability);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScheduleAvailabilityMockMvc.perform(put("/api/schedule-availabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleAvailabilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleAvailability in the database
        List<ScheduleAvailability> scheduleAvailabilityList = scheduleAvailabilityRepository.findAll();
        assertThat(scheduleAvailabilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScheduleAvailability() throws Exception {
        // Initialize the database
        scheduleAvailabilityRepository.saveAndFlush(scheduleAvailability);

        int databaseSizeBeforeDelete = scheduleAvailabilityRepository.findAll().size();

        // Delete the scheduleAvailability
        restScheduleAvailabilityMockMvc.perform(delete("/api/schedule-availabilities/{id}", scheduleAvailability.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ScheduleAvailability> scheduleAvailabilityList = scheduleAvailabilityRepository.findAll();
        assertThat(scheduleAvailabilityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
