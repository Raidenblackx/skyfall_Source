package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.JourneyInstructor;
import co.edu.sena.repository.JourneyInstructorRepository;
import co.edu.sena.service.JourneyInstructorService;
import co.edu.sena.service.dto.JourneyInstructorDTO;
import co.edu.sena.service.mapper.JourneyInstructorMapper;
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
 * Integration tests for the {@link JourneyInstructorResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class JourneyInstructorResourceIT {

    private static final String DEFAULT_NAME_DAY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_DAY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_JOURNEY_INSTRUCTOR = State.ACTIVO;
    private static final State UPDATED_STATE_JOURNEY_INSTRUCTOR = State.INACTIVO;

    @Autowired
    private JourneyInstructorRepository journeyInstructorRepository;

    @Autowired
    private JourneyInstructorMapper journeyInstructorMapper;

    @Autowired
    private JourneyInstructorService journeyInstructorService;

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

    private MockMvc restJourneyInstructorMockMvc;

    private JourneyInstructor journeyInstructor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JourneyInstructorResource journeyInstructorResource = new JourneyInstructorResource(journeyInstructorService);
        this.restJourneyInstructorMockMvc = MockMvcBuilders.standaloneSetup(journeyInstructorResource)
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
    public static JourneyInstructor createEntity(EntityManager em) {
        JourneyInstructor journeyInstructor = new JourneyInstructor()
            .nameDay(DEFAULT_NAME_DAY)
            .description(DEFAULT_DESCRIPTION)
            .stateJourneyInstructor(DEFAULT_STATE_JOURNEY_INSTRUCTOR);
        return journeyInstructor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JourneyInstructor createUpdatedEntity(EntityManager em) {
        JourneyInstructor journeyInstructor = new JourneyInstructor()
            .nameDay(UPDATED_NAME_DAY)
            .description(UPDATED_DESCRIPTION)
            .stateJourneyInstructor(UPDATED_STATE_JOURNEY_INSTRUCTOR);
        return journeyInstructor;
    }

    @BeforeEach
    public void initTest() {
        journeyInstructor = createEntity(em);
    }

    @Test
    @Transactional
    public void createJourneyInstructor() throws Exception {
        int databaseSizeBeforeCreate = journeyInstructorRepository.findAll().size();

        // Create the JourneyInstructor
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(journeyInstructor);
        restJourneyInstructorMockMvc.perform(post("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isCreated());

        // Validate the JourneyInstructor in the database
        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeCreate + 1);
        JourneyInstructor testJourneyInstructor = journeyInstructorList.get(journeyInstructorList.size() - 1);
        assertThat(testJourneyInstructor.getNameDay()).isEqualTo(DEFAULT_NAME_DAY);
        assertThat(testJourneyInstructor.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testJourneyInstructor.getStateJourneyInstructor()).isEqualTo(DEFAULT_STATE_JOURNEY_INSTRUCTOR);
    }

    @Test
    @Transactional
    public void createJourneyInstructorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = journeyInstructorRepository.findAll().size();

        // Create the JourneyInstructor with an existing ID
        journeyInstructor.setId(1L);
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(journeyInstructor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJourneyInstructorMockMvc.perform(post("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JourneyInstructor in the database
        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = journeyInstructorRepository.findAll().size();
        // set the field null
        journeyInstructor.setNameDay(null);

        // Create the JourneyInstructor, which fails.
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(journeyInstructor);

        restJourneyInstructorMockMvc.perform(post("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isBadRequest());

        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = journeyInstructorRepository.findAll().size();
        // set the field null
        journeyInstructor.setDescription(null);

        // Create the JourneyInstructor, which fails.
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(journeyInstructor);

        restJourneyInstructorMockMvc.perform(post("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isBadRequest());

        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateJourneyInstructorIsRequired() throws Exception {
        int databaseSizeBeforeTest = journeyInstructorRepository.findAll().size();
        // set the field null
        journeyInstructor.setStateJourneyInstructor(null);

        // Create the JourneyInstructor, which fails.
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(journeyInstructor);

        restJourneyInstructorMockMvc.perform(post("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isBadRequest());

        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJourneyInstructors() throws Exception {
        // Initialize the database
        journeyInstructorRepository.saveAndFlush(journeyInstructor);

        // Get all the journeyInstructorList
        restJourneyInstructorMockMvc.perform(get("/api/journey-instructors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(journeyInstructor.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameDay").value(hasItem(DEFAULT_NAME_DAY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].stateJourneyInstructor").value(hasItem(DEFAULT_STATE_JOURNEY_INSTRUCTOR.toString())));
    }
    
    @Test
    @Transactional
    public void getJourneyInstructor() throws Exception {
        // Initialize the database
        journeyInstructorRepository.saveAndFlush(journeyInstructor);

        // Get the journeyInstructor
        restJourneyInstructorMockMvc.perform(get("/api/journey-instructors/{id}", journeyInstructor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(journeyInstructor.getId().intValue()))
            .andExpect(jsonPath("$.nameDay").value(DEFAULT_NAME_DAY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.stateJourneyInstructor").value(DEFAULT_STATE_JOURNEY_INSTRUCTOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJourneyInstructor() throws Exception {
        // Get the journeyInstructor
        restJourneyInstructorMockMvc.perform(get("/api/journey-instructors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJourneyInstructor() throws Exception {
        // Initialize the database
        journeyInstructorRepository.saveAndFlush(journeyInstructor);

        int databaseSizeBeforeUpdate = journeyInstructorRepository.findAll().size();

        // Update the journeyInstructor
        JourneyInstructor updatedJourneyInstructor = journeyInstructorRepository.findById(journeyInstructor.getId()).get();
        // Disconnect from session so that the updates on updatedJourneyInstructor are not directly saved in db
        em.detach(updatedJourneyInstructor);
        updatedJourneyInstructor
            .nameDay(UPDATED_NAME_DAY)
            .description(UPDATED_DESCRIPTION)
            .stateJourneyInstructor(UPDATED_STATE_JOURNEY_INSTRUCTOR);
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(updatedJourneyInstructor);

        restJourneyInstructorMockMvc.perform(put("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isOk());

        // Validate the JourneyInstructor in the database
        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeUpdate);
        JourneyInstructor testJourneyInstructor = journeyInstructorList.get(journeyInstructorList.size() - 1);
        assertThat(testJourneyInstructor.getNameDay()).isEqualTo(UPDATED_NAME_DAY);
        assertThat(testJourneyInstructor.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testJourneyInstructor.getStateJourneyInstructor()).isEqualTo(UPDATED_STATE_JOURNEY_INSTRUCTOR);
    }

    @Test
    @Transactional
    public void updateNonExistingJourneyInstructor() throws Exception {
        int databaseSizeBeforeUpdate = journeyInstructorRepository.findAll().size();

        // Create the JourneyInstructor
        JourneyInstructorDTO journeyInstructorDTO = journeyInstructorMapper.toDto(journeyInstructor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJourneyInstructorMockMvc.perform(put("/api/journey-instructors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journeyInstructorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JourneyInstructor in the database
        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJourneyInstructor() throws Exception {
        // Initialize the database
        journeyInstructorRepository.saveAndFlush(journeyInstructor);

        int databaseSizeBeforeDelete = journeyInstructorRepository.findAll().size();

        // Delete the journeyInstructor
        restJourneyInstructorMockMvc.perform(delete("/api/journey-instructors/{id}", journeyInstructor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JourneyInstructor> journeyInstructorList = journeyInstructorRepository.findAll();
        assertThat(journeyInstructorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
