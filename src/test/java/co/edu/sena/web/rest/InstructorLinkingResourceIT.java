package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.InstructorLinking;
import co.edu.sena.domain.Year;
import co.edu.sena.domain.Instructor;
import co.edu.sena.domain.Linkage;
import co.edu.sena.repository.InstructorLinkingRepository;
import co.edu.sena.service.InstructorLinkingService;
import co.edu.sena.service.dto.InstructorLinkingDTO;
import co.edu.sena.service.mapper.InstructorLinkingMapper;
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
 * Integration tests for the {@link InstructorLinkingResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class InstructorLinkingResourceIT {

    private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private InstructorLinkingRepository instructorLinkingRepository;

    @Autowired
    private InstructorLinkingMapper instructorLinkingMapper;

    @Autowired
    private InstructorLinkingService instructorLinkingService;

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

    private MockMvc restInstructorLinkingMockMvc;

    private InstructorLinking instructorLinking;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstructorLinkingResource instructorLinkingResource = new InstructorLinkingResource(instructorLinkingService);
        this.restInstructorLinkingMockMvc = MockMvcBuilders.standaloneSetup(instructorLinkingResource)
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
    public static InstructorLinking createEntity(EntityManager em) {
        InstructorLinking instructorLinking = new InstructorLinking()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        // Add required entity
        Year year;
        if (TestUtil.findAll(em, Year.class).isEmpty()) {
            year = YearResourceIT.createEntity(em);
            em.persist(year);
            em.flush();
        } else {
            year = TestUtil.findAll(em, Year.class).get(0);
        }
        instructorLinking.setYear(year);
        // Add required entity
        Instructor instructor;
        if (TestUtil.findAll(em, Instructor.class).isEmpty()) {
            instructor = InstructorResourceIT.createEntity(em);
            em.persist(instructor);
            em.flush();
        } else {
            instructor = TestUtil.findAll(em, Instructor.class).get(0);
        }
        instructorLinking.setInstructor(instructor);
        // Add required entity
        Linkage linkage;
        if (TestUtil.findAll(em, Linkage.class).isEmpty()) {
            linkage = LinkageResourceIT.createEntity(em);
            em.persist(linkage);
            em.flush();
        } else {
            linkage = TestUtil.findAll(em, Linkage.class).get(0);
        }
        instructorLinking.setLinkage(linkage);
        return instructorLinking;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstructorLinking createUpdatedEntity(EntityManager em) {
        InstructorLinking instructorLinking = new InstructorLinking()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        // Add required entity
        Year year;
        if (TestUtil.findAll(em, Year.class).isEmpty()) {
            year = YearResourceIT.createUpdatedEntity(em);
            em.persist(year);
            em.flush();
        } else {
            year = TestUtil.findAll(em, Year.class).get(0);
        }
        instructorLinking.setYear(year);
        // Add required entity
        Instructor instructor;
        if (TestUtil.findAll(em, Instructor.class).isEmpty()) {
            instructor = InstructorResourceIT.createUpdatedEntity(em);
            em.persist(instructor);
            em.flush();
        } else {
            instructor = TestUtil.findAll(em, Instructor.class).get(0);
        }
        instructorLinking.setInstructor(instructor);
        // Add required entity
        Linkage linkage;
        if (TestUtil.findAll(em, Linkage.class).isEmpty()) {
            linkage = LinkageResourceIT.createUpdatedEntity(em);
            em.persist(linkage);
            em.flush();
        } else {
            linkage = TestUtil.findAll(em, Linkage.class).get(0);
        }
        instructorLinking.setLinkage(linkage);
        return instructorLinking;
    }

    @BeforeEach
    public void initTest() {
        instructorLinking = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstructorLinking() throws Exception {
        int databaseSizeBeforeCreate = instructorLinkingRepository.findAll().size();

        // Create the InstructorLinking
        InstructorLinkingDTO instructorLinkingDTO = instructorLinkingMapper.toDto(instructorLinking);
        restInstructorLinkingMockMvc.perform(post("/api/instructor-linkings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorLinkingDTO)))
            .andExpect(status().isCreated());

        // Validate the InstructorLinking in the database
        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeCreate + 1);
        InstructorLinking testInstructorLinking = instructorLinkingList.get(instructorLinkingList.size() - 1);
        assertThat(testInstructorLinking.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testInstructorLinking.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createInstructorLinkingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instructorLinkingRepository.findAll().size();

        // Create the InstructorLinking with an existing ID
        instructorLinking.setId(1L);
        InstructorLinkingDTO instructorLinkingDTO = instructorLinkingMapper.toDto(instructorLinking);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstructorLinkingMockMvc.perform(post("/api/instructor-linkings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorLinkingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InstructorLinking in the database
        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = instructorLinkingRepository.findAll().size();
        // set the field null
        instructorLinking.setStartDate(null);

        // Create the InstructorLinking, which fails.
        InstructorLinkingDTO instructorLinkingDTO = instructorLinkingMapper.toDto(instructorLinking);

        restInstructorLinkingMockMvc.perform(post("/api/instructor-linkings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorLinkingDTO)))
            .andExpect(status().isBadRequest());

        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = instructorLinkingRepository.findAll().size();
        // set the field null
        instructorLinking.setEndDate(null);

        // Create the InstructorLinking, which fails.
        InstructorLinkingDTO instructorLinkingDTO = instructorLinkingMapper.toDto(instructorLinking);

        restInstructorLinkingMockMvc.perform(post("/api/instructor-linkings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorLinkingDTO)))
            .andExpect(status().isBadRequest());

        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstructorLinkings() throws Exception {
        // Initialize the database
        instructorLinkingRepository.saveAndFlush(instructorLinking);

        // Get all the instructorLinkingList
        restInstructorLinkingMockMvc.perform(get("/api/instructor-linkings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instructorLinking.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(sameInstant(DEFAULT_START_DATE))))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(sameInstant(DEFAULT_END_DATE))));
    }
    
    @Test
    @Transactional
    public void getInstructorLinking() throws Exception {
        // Initialize the database
        instructorLinkingRepository.saveAndFlush(instructorLinking);

        // Get the instructorLinking
        restInstructorLinkingMockMvc.perform(get("/api/instructor-linkings/{id}", instructorLinking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instructorLinking.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(sameInstant(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.endDate").value(sameInstant(DEFAULT_END_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingInstructorLinking() throws Exception {
        // Get the instructorLinking
        restInstructorLinkingMockMvc.perform(get("/api/instructor-linkings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstructorLinking() throws Exception {
        // Initialize the database
        instructorLinkingRepository.saveAndFlush(instructorLinking);

        int databaseSizeBeforeUpdate = instructorLinkingRepository.findAll().size();

        // Update the instructorLinking
        InstructorLinking updatedInstructorLinking = instructorLinkingRepository.findById(instructorLinking.getId()).get();
        // Disconnect from session so that the updates on updatedInstructorLinking are not directly saved in db
        em.detach(updatedInstructorLinking);
        updatedInstructorLinking
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        InstructorLinkingDTO instructorLinkingDTO = instructorLinkingMapper.toDto(updatedInstructorLinking);

        restInstructorLinkingMockMvc.perform(put("/api/instructor-linkings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorLinkingDTO)))
            .andExpect(status().isOk());

        // Validate the InstructorLinking in the database
        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeUpdate);
        InstructorLinking testInstructorLinking = instructorLinkingList.get(instructorLinkingList.size() - 1);
        assertThat(testInstructorLinking.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testInstructorLinking.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingInstructorLinking() throws Exception {
        int databaseSizeBeforeUpdate = instructorLinkingRepository.findAll().size();

        // Create the InstructorLinking
        InstructorLinkingDTO instructorLinkingDTO = instructorLinkingMapper.toDto(instructorLinking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstructorLinkingMockMvc.perform(put("/api/instructor-linkings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructorLinkingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InstructorLinking in the database
        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstructorLinking() throws Exception {
        // Initialize the database
        instructorLinkingRepository.saveAndFlush(instructorLinking);

        int databaseSizeBeforeDelete = instructorLinkingRepository.findAll().size();

        // Delete the instructorLinking
        restInstructorLinkingMockMvc.perform(delete("/api/instructor-linkings/{id}", instructorLinking.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstructorLinking> instructorLinkingList = instructorLinkingRepository.findAll();
        assertThat(instructorLinkingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
