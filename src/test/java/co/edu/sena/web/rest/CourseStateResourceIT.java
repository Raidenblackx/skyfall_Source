package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.CourseState;
import co.edu.sena.repository.CourseStateRepository;
import co.edu.sena.service.CourseStateService;
import co.edu.sena.service.dto.CourseStateDTO;
import co.edu.sena.service.mapper.CourseStateMapper;
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
 * Integration tests for the {@link CourseStateResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class CourseStateResourceIT {

    private static final String DEFAULT_NAME_COURSE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_COURSE_STATE = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_COURSE_STATE = State.ACTIVO;
    private static final State UPDATED_STATE_COURSE_STATE = State.INACTIVO;

    @Autowired
    private CourseStateRepository courseStateRepository;

    @Autowired
    private CourseStateMapper courseStateMapper;

    @Autowired
    private CourseStateService courseStateService;

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

    private MockMvc restCourseStateMockMvc;

    private CourseState courseState;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseStateResource courseStateResource = new CourseStateResource(courseStateService);
        this.restCourseStateMockMvc = MockMvcBuilders.standaloneSetup(courseStateResource)
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
    public static CourseState createEntity(EntityManager em) {
        CourseState courseState = new CourseState()
            .nameCourseState(DEFAULT_NAME_COURSE_STATE)
            .stateCourseState(DEFAULT_STATE_COURSE_STATE);
        return courseState;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourseState createUpdatedEntity(EntityManager em) {
        CourseState courseState = new CourseState()
            .nameCourseState(UPDATED_NAME_COURSE_STATE)
            .stateCourseState(UPDATED_STATE_COURSE_STATE);
        return courseState;
    }

    @BeforeEach
    public void initTest() {
        courseState = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseState() throws Exception {
        int databaseSizeBeforeCreate = courseStateRepository.findAll().size();

        // Create the CourseState
        CourseStateDTO courseStateDTO = courseStateMapper.toDto(courseState);
        restCourseStateMockMvc.perform(post("/api/course-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseStateDTO)))
            .andExpect(status().isCreated());

        // Validate the CourseState in the database
        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeCreate + 1);
        CourseState testCourseState = courseStateList.get(courseStateList.size() - 1);
        assertThat(testCourseState.getNameCourseState()).isEqualTo(DEFAULT_NAME_COURSE_STATE);
        assertThat(testCourseState.getStateCourseState()).isEqualTo(DEFAULT_STATE_COURSE_STATE);
    }

    @Test
    @Transactional
    public void createCourseStateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseStateRepository.findAll().size();

        // Create the CourseState with an existing ID
        courseState.setId(1L);
        CourseStateDTO courseStateDTO = courseStateMapper.toDto(courseState);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseStateMockMvc.perform(post("/api/course-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseStateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseState in the database
        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameCourseStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseStateRepository.findAll().size();
        // set the field null
        courseState.setNameCourseState(null);

        // Create the CourseState, which fails.
        CourseStateDTO courseStateDTO = courseStateMapper.toDto(courseState);

        restCourseStateMockMvc.perform(post("/api/course-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseStateDTO)))
            .andExpect(status().isBadRequest());

        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateCourseStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseStateRepository.findAll().size();
        // set the field null
        courseState.setStateCourseState(null);

        // Create the CourseState, which fails.
        CourseStateDTO courseStateDTO = courseStateMapper.toDto(courseState);

        restCourseStateMockMvc.perform(post("/api/course-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseStateDTO)))
            .andExpect(status().isBadRequest());

        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseStates() throws Exception {
        // Initialize the database
        courseStateRepository.saveAndFlush(courseState);

        // Get all the courseStateList
        restCourseStateMockMvc.perform(get("/api/course-states?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseState.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameCourseState").value(hasItem(DEFAULT_NAME_COURSE_STATE)))
            .andExpect(jsonPath("$.[*].stateCourseState").value(hasItem(DEFAULT_STATE_COURSE_STATE.toString())));
    }
    
    @Test
    @Transactional
    public void getCourseState() throws Exception {
        // Initialize the database
        courseStateRepository.saveAndFlush(courseState);

        // Get the courseState
        restCourseStateMockMvc.perform(get("/api/course-states/{id}", courseState.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseState.getId().intValue()))
            .andExpect(jsonPath("$.nameCourseState").value(DEFAULT_NAME_COURSE_STATE))
            .andExpect(jsonPath("$.stateCourseState").value(DEFAULT_STATE_COURSE_STATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseState() throws Exception {
        // Get the courseState
        restCourseStateMockMvc.perform(get("/api/course-states/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseState() throws Exception {
        // Initialize the database
        courseStateRepository.saveAndFlush(courseState);

        int databaseSizeBeforeUpdate = courseStateRepository.findAll().size();

        // Update the courseState
        CourseState updatedCourseState = courseStateRepository.findById(courseState.getId()).get();
        // Disconnect from session so that the updates on updatedCourseState are not directly saved in db
        em.detach(updatedCourseState);
        updatedCourseState
            .nameCourseState(UPDATED_NAME_COURSE_STATE)
            .stateCourseState(UPDATED_STATE_COURSE_STATE);
        CourseStateDTO courseStateDTO = courseStateMapper.toDto(updatedCourseState);

        restCourseStateMockMvc.perform(put("/api/course-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseStateDTO)))
            .andExpect(status().isOk());

        // Validate the CourseState in the database
        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeUpdate);
        CourseState testCourseState = courseStateList.get(courseStateList.size() - 1);
        assertThat(testCourseState.getNameCourseState()).isEqualTo(UPDATED_NAME_COURSE_STATE);
        assertThat(testCourseState.getStateCourseState()).isEqualTo(UPDATED_STATE_COURSE_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseState() throws Exception {
        int databaseSizeBeforeUpdate = courseStateRepository.findAll().size();

        // Create the CourseState
        CourseStateDTO courseStateDTO = courseStateMapper.toDto(courseState);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseStateMockMvc.perform(put("/api/course-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseStateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseState in the database
        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCourseState() throws Exception {
        // Initialize the database
        courseStateRepository.saveAndFlush(courseState);

        int databaseSizeBeforeDelete = courseStateRepository.findAll().size();

        // Delete the courseState
        restCourseStateMockMvc.perform(delete("/api/course-states/{id}", courseState.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CourseState> courseStateList = courseStateRepository.findAll();
        assertThat(courseStateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
