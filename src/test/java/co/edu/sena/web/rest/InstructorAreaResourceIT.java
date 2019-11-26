package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.InstructorArea;
import co.edu.sena.domain.Area;
import co.edu.sena.domain.Instructor;
import co.edu.sena.repository.InstructorAreaRepository;
import co.edu.sena.service.InstructorAreaService;
import co.edu.sena.service.dto.InstructorAreaDTO;
import co.edu.sena.service.mapper.InstructorAreaMapper;
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
 * Integration tests for the {@link InstructorAreaResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class InstructorAreaResourceIT {

    private static final State DEFAULT_STATE_INSTRUCTOR_AREA = State.ACTIVO;
    private static final State UPDATED_STATE_INSTRUCTOR_AREA = State.INACTIVO;

    @Autowired
    private InstructorAreaRepository instructorAreaRepository;

    @Autowired
    private InstructorAreaMapper instructorAreaMapper;

    @Autowired
    private InstructorAreaService instructorAreaService;

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

    private MockMvc restInstructorAreaMockMvc;

    private InstructorArea instructorArea;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstructorAreaResource instructorAreaResource = new InstructorAreaResource(instructorAreaService);
        this.restInstructorAreaMockMvc = MockMvcBuilders.standaloneSetup(instructorAreaResource)
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
    public static InstructorArea createEntity(EntityManager em) {
        InstructorArea instructorArea = new InstructorArea()
            .stateInstructorArea(DEFAULT_STATE_INSTRUCTOR_AREA);
        // Add required entity
        Area area;
        if (TestUtil.findAll(em, Area.class).isEmpty()) {
            area = AreaResourceIT.createEntity(em);
            em.persist(area);
            em.flush();
        } else {
            area = TestUtil.findAll(em, Area.class).get(0);
        }
        instructorArea.setArea(area);
        // Add required entity
        Instructor instructor;
        if (TestUtil.findAll(em, Instructor.class).isEmpty()) {
            instructor = InstructorResourceIT.createEntity(em);
            em.persist(instructor);
            em.flush();
        } else {
            instructor = TestUtil.findAll(em, Instructor.class).get(0);
        }
        instructorArea.setInstructor(instructor);
        return instructorArea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstructorArea createUpdatedEntity(EntityManager em) {
        InstructorArea instructorArea = new InstructorArea()
            .stateInstructorArea(UPDATED_STATE_INSTRUCTOR_AREA);
        // Add required entity
        Area area;
        if (TestUtil.findAll(em, Area.class).isEmpty()) {
            area = AreaResourceIT.createUpdatedEntity(em);
            em.persist(area);
            em.flush();
        } else {
            area = TestUtil.findAll(em, Area.class).get(0);
        }
        instructorArea.setArea(area);
        // Add required entity
        Instructor instructor;
        if (TestUtil.findAll(em, Instructor.class).isEmpty()) {
            instructor = InstructorResourceIT.createUpdatedEntity(em);
            em.persist(instructor);
            em.flush();
        } else {
            instructor = TestUtil.findAll(em, Instructor.class).get(0);
        }
        instructorArea.setInstructor(instructor);
        return instructorArea;
    }

    @BeforeEach
    public void initTest() {
        instructorArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstructorArea() throws Exception {
        int databaseSizeBeforeCreate = instructorAreaRepository.findAll().size();

        // Create the InstructorArea
        InstructorAreaDTO instructorAreaDTO = instructorAreaMapper.toDto(instructorArea);
        restInstructorAreaMockMvc.perform(post("/api/instructor-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the InstructorArea in the database
        List<InstructorArea> instructorAreaList = instructorAreaRepository.findAll();
        assertThat(instructorAreaList).hasSize(databaseSizeBeforeCreate + 1);
        InstructorArea testInstructorArea = instructorAreaList.get(instructorAreaList.size() - 1);
        assertThat(testInstructorArea.getStateInstructorArea()).isEqualTo(DEFAULT_STATE_INSTRUCTOR_AREA);
    }

    @Test
    @Transactional
    public void createInstructorAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instructorAreaRepository.findAll().size();

        // Create the InstructorArea with an existing ID
        instructorArea.setId(1L);
        InstructorAreaDTO instructorAreaDTO = instructorAreaMapper.toDto(instructorArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstructorAreaMockMvc.perform(post("/api/instructor-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InstructorArea in the database
        List<InstructorArea> instructorAreaList = instructorAreaRepository.findAll();
        assertThat(instructorAreaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStateInstructorAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = instructorAreaRepository.findAll().size();
        // set the field null
        instructorArea.setStateInstructorArea(null);

        // Create the InstructorArea, which fails.
        InstructorAreaDTO instructorAreaDTO = instructorAreaMapper.toDto(instructorArea);

        restInstructorAreaMockMvc.perform(post("/api/instructor-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorAreaDTO)))
            .andExpect(status().isBadRequest());

        List<InstructorArea> instructorAreaList = instructorAreaRepository.findAll();
        assertThat(instructorAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstructorAreas() throws Exception {
        // Initialize the database
        instructorAreaRepository.saveAndFlush(instructorArea);

        // Get all the instructorAreaList
        restInstructorAreaMockMvc.perform(get("/api/instructor-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instructorArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].stateInstructorArea").value(hasItem(DEFAULT_STATE_INSTRUCTOR_AREA.toString())));
    }
    
    @Test
    @Transactional
    public void getInstructorArea() throws Exception {
        // Initialize the database
        instructorAreaRepository.saveAndFlush(instructorArea);

        // Get the instructorArea
        restInstructorAreaMockMvc.perform(get("/api/instructor-areas/{id}", instructorArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instructorArea.getId().intValue()))
            .andExpect(jsonPath("$.stateInstructorArea").value(DEFAULT_STATE_INSTRUCTOR_AREA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInstructorArea() throws Exception {
        // Get the instructorArea
        restInstructorAreaMockMvc.perform(get("/api/instructor-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstructorArea() throws Exception {
        // Initialize the database
        instructorAreaRepository.saveAndFlush(instructorArea);

        int databaseSizeBeforeUpdate = instructorAreaRepository.findAll().size();

        // Update the instructorArea
        InstructorArea updatedInstructorArea = instructorAreaRepository.findById(instructorArea.getId()).get();
        // Disconnect from session so that the updates on updatedInstructorArea are not directly saved in db
        em.detach(updatedInstructorArea);
        updatedInstructorArea
            .stateInstructorArea(UPDATED_STATE_INSTRUCTOR_AREA);
        InstructorAreaDTO instructorAreaDTO = instructorAreaMapper.toDto(updatedInstructorArea);

        restInstructorAreaMockMvc.perform(put("/api/instructor-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorAreaDTO)))
            .andExpect(status().isOk());

        // Validate the InstructorArea in the database
        List<InstructorArea> instructorAreaList = instructorAreaRepository.findAll();
        assertThat(instructorAreaList).hasSize(databaseSizeBeforeUpdate);
        InstructorArea testInstructorArea = instructorAreaList.get(instructorAreaList.size() - 1);
        assertThat(testInstructorArea.getStateInstructorArea()).isEqualTo(UPDATED_STATE_INSTRUCTOR_AREA);
    }

    @Test
    @Transactional
    public void updateNonExistingInstructorArea() throws Exception {
        int databaseSizeBeforeUpdate = instructorAreaRepository.findAll().size();

        // Create the InstructorArea
        InstructorAreaDTO instructorAreaDTO = instructorAreaMapper.toDto(instructorArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstructorAreaMockMvc.perform(put("/api/instructor-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InstructorArea in the database
        List<InstructorArea> instructorAreaList = instructorAreaRepository.findAll();
        assertThat(instructorAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstructorArea() throws Exception {
        // Initialize the database
        instructorAreaRepository.saveAndFlush(instructorArea);

        int databaseSizeBeforeDelete = instructorAreaRepository.findAll().size();

        // Delete the instructorArea
        restInstructorAreaMockMvc.perform(delete("/api/instructor-areas/{id}", instructorArea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstructorArea> instructorAreaList = instructorAreaRepository.findAll();
        assertThat(instructorAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
