package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.CourseHasTrimester;
import co.edu.sena.domain.Course;
import co.edu.sena.domain.Trimester;
import co.edu.sena.repository.CourseHasTrimesterRepository;
import co.edu.sena.service.CourseHasTrimesterService;
import co.edu.sena.service.dto.CourseHasTrimesterDTO;
import co.edu.sena.service.mapper.CourseHasTrimesterMapper;
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
 * Integration tests for the {@link CourseHasTrimesterResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class CourseHasTrimesterResourceIT {

    @Autowired
    private CourseHasTrimesterRepository courseHasTrimesterRepository;

    @Autowired
    private CourseHasTrimesterMapper courseHasTrimesterMapper;

    @Autowired
    private CourseHasTrimesterService courseHasTrimesterService;

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

    private MockMvc restCourseHasTrimesterMockMvc;

    private CourseHasTrimester courseHasTrimester;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseHasTrimesterResource courseHasTrimesterResource = new CourseHasTrimesterResource(courseHasTrimesterService);
        this.restCourseHasTrimesterMockMvc = MockMvcBuilders.standaloneSetup(courseHasTrimesterResource)
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
    public static CourseHasTrimester createEntity(EntityManager em) {
        CourseHasTrimester courseHasTrimester = new CourseHasTrimester();
        // Add required entity
        Course course;
        if (TestUtil.findAll(em, Course.class).isEmpty()) {
            course = CourseResourceIT.createEntity(em);
            em.persist(course);
            em.flush();
        } else {
            course = TestUtil.findAll(em, Course.class).get(0);
        }
        courseHasTrimester.setCourse(course);
        // Add required entity
        Trimester trimester;
        if (TestUtil.findAll(em, Trimester.class).isEmpty()) {
            trimester = TrimesterResourceIT.createEntity(em);
            em.persist(trimester);
            em.flush();
        } else {
            trimester = TestUtil.findAll(em, Trimester.class).get(0);
        }
        courseHasTrimester.setTrimester(trimester);
        return courseHasTrimester;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourseHasTrimester createUpdatedEntity(EntityManager em) {
        CourseHasTrimester courseHasTrimester = new CourseHasTrimester();
        // Add required entity
        Course course;
        if (TestUtil.findAll(em, Course.class).isEmpty()) {
            course = CourseResourceIT.createUpdatedEntity(em);
            em.persist(course);
            em.flush();
        } else {
            course = TestUtil.findAll(em, Course.class).get(0);
        }
        courseHasTrimester.setCourse(course);
        // Add required entity
        Trimester trimester;
        if (TestUtil.findAll(em, Trimester.class).isEmpty()) {
            trimester = TrimesterResourceIT.createUpdatedEntity(em);
            em.persist(trimester);
            em.flush();
        } else {
            trimester = TestUtil.findAll(em, Trimester.class).get(0);
        }
        courseHasTrimester.setTrimester(trimester);
        return courseHasTrimester;
    }

    @BeforeEach
    public void initTest() {
        courseHasTrimester = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseHasTrimester() throws Exception {
        int databaseSizeBeforeCreate = courseHasTrimesterRepository.findAll().size();

        // Create the CourseHasTrimester
        CourseHasTrimesterDTO courseHasTrimesterDTO = courseHasTrimesterMapper.toDto(courseHasTrimester);
        restCourseHasTrimesterMockMvc.perform(post("/api/course-has-trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseHasTrimesterDTO)))
            .andExpect(status().isCreated());

        // Validate the CourseHasTrimester in the database
        List<CourseHasTrimester> courseHasTrimesterList = courseHasTrimesterRepository.findAll();
        assertThat(courseHasTrimesterList).hasSize(databaseSizeBeforeCreate + 1);
        CourseHasTrimester testCourseHasTrimester = courseHasTrimesterList.get(courseHasTrimesterList.size() - 1);
    }

    @Test
    @Transactional
    public void createCourseHasTrimesterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseHasTrimesterRepository.findAll().size();

        // Create the CourseHasTrimester with an existing ID
        courseHasTrimester.setId(1L);
        CourseHasTrimesterDTO courseHasTrimesterDTO = courseHasTrimesterMapper.toDto(courseHasTrimester);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseHasTrimesterMockMvc.perform(post("/api/course-has-trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseHasTrimesterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseHasTrimester in the database
        List<CourseHasTrimester> courseHasTrimesterList = courseHasTrimesterRepository.findAll();
        assertThat(courseHasTrimesterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCourseHasTrimesters() throws Exception {
        // Initialize the database
        courseHasTrimesterRepository.saveAndFlush(courseHasTrimester);

        // Get all the courseHasTrimesterList
        restCourseHasTrimesterMockMvc.perform(get("/api/course-has-trimesters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseHasTrimester.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCourseHasTrimester() throws Exception {
        // Initialize the database
        courseHasTrimesterRepository.saveAndFlush(courseHasTrimester);

        // Get the courseHasTrimester
        restCourseHasTrimesterMockMvc.perform(get("/api/course-has-trimesters/{id}", courseHasTrimester.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseHasTrimester.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseHasTrimester() throws Exception {
        // Get the courseHasTrimester
        restCourseHasTrimesterMockMvc.perform(get("/api/course-has-trimesters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseHasTrimester() throws Exception {
        // Initialize the database
        courseHasTrimesterRepository.saveAndFlush(courseHasTrimester);

        int databaseSizeBeforeUpdate = courseHasTrimesterRepository.findAll().size();

        // Update the courseHasTrimester
        CourseHasTrimester updatedCourseHasTrimester = courseHasTrimesterRepository.findById(courseHasTrimester.getId()).get();
        // Disconnect from session so that the updates on updatedCourseHasTrimester are not directly saved in db
        em.detach(updatedCourseHasTrimester);
        CourseHasTrimesterDTO courseHasTrimesterDTO = courseHasTrimesterMapper.toDto(updatedCourseHasTrimester);

        restCourseHasTrimesterMockMvc.perform(put("/api/course-has-trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseHasTrimesterDTO)))
            .andExpect(status().isOk());

        // Validate the CourseHasTrimester in the database
        List<CourseHasTrimester> courseHasTrimesterList = courseHasTrimesterRepository.findAll();
        assertThat(courseHasTrimesterList).hasSize(databaseSizeBeforeUpdate);
        CourseHasTrimester testCourseHasTrimester = courseHasTrimesterList.get(courseHasTrimesterList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseHasTrimester() throws Exception {
        int databaseSizeBeforeUpdate = courseHasTrimesterRepository.findAll().size();

        // Create the CourseHasTrimester
        CourseHasTrimesterDTO courseHasTrimesterDTO = courseHasTrimesterMapper.toDto(courseHasTrimester);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseHasTrimesterMockMvc.perform(put("/api/course-has-trimesters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseHasTrimesterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseHasTrimester in the database
        List<CourseHasTrimester> courseHasTrimesterList = courseHasTrimesterRepository.findAll();
        assertThat(courseHasTrimesterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCourseHasTrimester() throws Exception {
        // Initialize the database
        courseHasTrimesterRepository.saveAndFlush(courseHasTrimester);

        int databaseSizeBeforeDelete = courseHasTrimesterRepository.findAll().size();

        // Delete the courseHasTrimester
        restCourseHasTrimesterMockMvc.perform(delete("/api/course-has-trimesters/{id}", courseHasTrimester.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CourseHasTrimester> courseHasTrimesterList = courseHasTrimesterRepository.findAll();
        assertThat(courseHasTrimesterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
