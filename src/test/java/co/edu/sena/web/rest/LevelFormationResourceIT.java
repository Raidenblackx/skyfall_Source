package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.LevelFormation;
import co.edu.sena.repository.LevelFormationRepository;
import co.edu.sena.service.LevelFormationService;
import co.edu.sena.service.dto.LevelFormationDTO;
import co.edu.sena.service.mapper.LevelFormationMapper;
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
 * Integration tests for the {@link LevelFormationResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class LevelFormationResourceIT {

    private static final String DEFAULT_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_LEVEL_FORMATION = State.ACTIVO;
    private static final State UPDATED_STATE_LEVEL_FORMATION = State.INACTIVO;

    @Autowired
    private LevelFormationRepository levelFormationRepository;

    @Autowired
    private LevelFormationMapper levelFormationMapper;

    @Autowired
    private LevelFormationService levelFormationService;

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

    private MockMvc restLevelFormationMockMvc;

    private LevelFormation levelFormation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LevelFormationResource levelFormationResource = new LevelFormationResource(levelFormationService);
        this.restLevelFormationMockMvc = MockMvcBuilders.standaloneSetup(levelFormationResource)
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
    public static LevelFormation createEntity(EntityManager em) {
        LevelFormation levelFormation = new LevelFormation()
            .level(DEFAULT_LEVEL)
            .stateLevelFormation(DEFAULT_STATE_LEVEL_FORMATION);
        return levelFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LevelFormation createUpdatedEntity(EntityManager em) {
        LevelFormation levelFormation = new LevelFormation()
            .level(UPDATED_LEVEL)
            .stateLevelFormation(UPDATED_STATE_LEVEL_FORMATION);
        return levelFormation;
    }

    @BeforeEach
    public void initTest() {
        levelFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createLevelFormation() throws Exception {
        int databaseSizeBeforeCreate = levelFormationRepository.findAll().size();

        // Create the LevelFormation
        LevelFormationDTO levelFormationDTO = levelFormationMapper.toDto(levelFormation);
        restLevelFormationMockMvc.perform(post("/api/level-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(levelFormationDTO)))
            .andExpect(status().isCreated());

        // Validate the LevelFormation in the database
        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeCreate + 1);
        LevelFormation testLevelFormation = levelFormationList.get(levelFormationList.size() - 1);
        assertThat(testLevelFormation.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testLevelFormation.getStateLevelFormation()).isEqualTo(DEFAULT_STATE_LEVEL_FORMATION);
    }

    @Test
    @Transactional
    public void createLevelFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = levelFormationRepository.findAll().size();

        // Create the LevelFormation with an existing ID
        levelFormation.setId(1L);
        LevelFormationDTO levelFormationDTO = levelFormationMapper.toDto(levelFormation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLevelFormationMockMvc.perform(post("/api/level-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(levelFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LevelFormation in the database
        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = levelFormationRepository.findAll().size();
        // set the field null
        levelFormation.setLevel(null);

        // Create the LevelFormation, which fails.
        LevelFormationDTO levelFormationDTO = levelFormationMapper.toDto(levelFormation);

        restLevelFormationMockMvc.perform(post("/api/level-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(levelFormationDTO)))
            .andExpect(status().isBadRequest());

        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateLevelFormationIsRequired() throws Exception {
        int databaseSizeBeforeTest = levelFormationRepository.findAll().size();
        // set the field null
        levelFormation.setStateLevelFormation(null);

        // Create the LevelFormation, which fails.
        LevelFormationDTO levelFormationDTO = levelFormationMapper.toDto(levelFormation);

        restLevelFormationMockMvc.perform(post("/api/level-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(levelFormationDTO)))
            .andExpect(status().isBadRequest());

        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLevelFormations() throws Exception {
        // Initialize the database
        levelFormationRepository.saveAndFlush(levelFormation);

        // Get all the levelFormationList
        restLevelFormationMockMvc.perform(get("/api/level-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(levelFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].stateLevelFormation").value(hasItem(DEFAULT_STATE_LEVEL_FORMATION.toString())));
    }
    
    @Test
    @Transactional
    public void getLevelFormation() throws Exception {
        // Initialize the database
        levelFormationRepository.saveAndFlush(levelFormation);

        // Get the levelFormation
        restLevelFormationMockMvc.perform(get("/api/level-formations/{id}", levelFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(levelFormation.getId().intValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.stateLevelFormation").value(DEFAULT_STATE_LEVEL_FORMATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLevelFormation() throws Exception {
        // Get the levelFormation
        restLevelFormationMockMvc.perform(get("/api/level-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLevelFormation() throws Exception {
        // Initialize the database
        levelFormationRepository.saveAndFlush(levelFormation);

        int databaseSizeBeforeUpdate = levelFormationRepository.findAll().size();

        // Update the levelFormation
        LevelFormation updatedLevelFormation = levelFormationRepository.findById(levelFormation.getId()).get();
        // Disconnect from session so that the updates on updatedLevelFormation are not directly saved in db
        em.detach(updatedLevelFormation);
        updatedLevelFormation
            .level(UPDATED_LEVEL)
            .stateLevelFormation(UPDATED_STATE_LEVEL_FORMATION);
        LevelFormationDTO levelFormationDTO = levelFormationMapper.toDto(updatedLevelFormation);

        restLevelFormationMockMvc.perform(put("/api/level-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(levelFormationDTO)))
            .andExpect(status().isOk());

        // Validate the LevelFormation in the database
        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeUpdate);
        LevelFormation testLevelFormation = levelFormationList.get(levelFormationList.size() - 1);
        assertThat(testLevelFormation.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testLevelFormation.getStateLevelFormation()).isEqualTo(UPDATED_STATE_LEVEL_FORMATION);
    }

    @Test
    @Transactional
    public void updateNonExistingLevelFormation() throws Exception {
        int databaseSizeBeforeUpdate = levelFormationRepository.findAll().size();

        // Create the LevelFormation
        LevelFormationDTO levelFormationDTO = levelFormationMapper.toDto(levelFormation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLevelFormationMockMvc.perform(put("/api/level-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(levelFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LevelFormation in the database
        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLevelFormation() throws Exception {
        // Initialize the database
        levelFormationRepository.saveAndFlush(levelFormation);

        int databaseSizeBeforeDelete = levelFormationRepository.findAll().size();

        // Delete the levelFormation
        restLevelFormationMockMvc.perform(delete("/api/level-formations/{id}", levelFormation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LevelFormation> levelFormationList = levelFormationRepository.findAll();
        assertThat(levelFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
