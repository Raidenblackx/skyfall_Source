package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Phase;
import co.edu.sena.domain.Proyect;
import co.edu.sena.repository.PhaseRepository;
import co.edu.sena.service.PhaseService;
import co.edu.sena.service.dto.PhaseDTO;
import co.edu.sena.service.mapper.PhaseMapper;
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
 * Integration tests for the {@link PhaseResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class PhaseResourceIT {

    private static final String DEFAULT_NAME_PHASE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_PHASE = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_PHASE = State.ACTIVO;
    private static final State UPDATED_STATE_PHASE = State.INACTIVO;

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private PhaseMapper phaseMapper;

    @Autowired
    private PhaseService phaseService;

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

    private MockMvc restPhaseMockMvc;

    private Phase phase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhaseResource phaseResource = new PhaseResource(phaseService);
        this.restPhaseMockMvc = MockMvcBuilders.standaloneSetup(phaseResource)
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
    public static Phase createEntity(EntityManager em) {
        Phase phase = new Phase()
            .namePhase(DEFAULT_NAME_PHASE)
            .statePhase(DEFAULT_STATE_PHASE);
        // Add required entity
        Proyect proyect;
        if (TestUtil.findAll(em, Proyect.class).isEmpty()) {
            proyect = ProyectResourceIT.createEntity(em);
            em.persist(proyect);
            em.flush();
        } else {
            proyect = TestUtil.findAll(em, Proyect.class).get(0);
        }
        phase.setProyect(proyect);
        return phase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Phase createUpdatedEntity(EntityManager em) {
        Phase phase = new Phase()
            .namePhase(UPDATED_NAME_PHASE)
            .statePhase(UPDATED_STATE_PHASE);
        // Add required entity
        Proyect proyect;
        if (TestUtil.findAll(em, Proyect.class).isEmpty()) {
            proyect = ProyectResourceIT.createUpdatedEntity(em);
            em.persist(proyect);
            em.flush();
        } else {
            proyect = TestUtil.findAll(em, Proyect.class).get(0);
        }
        phase.setProyect(proyect);
        return phase;
    }

    @BeforeEach
    public void initTest() {
        phase = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhase() throws Exception {
        int databaseSizeBeforeCreate = phaseRepository.findAll().size();

        // Create the Phase
        PhaseDTO phaseDTO = phaseMapper.toDto(phase);
        restPhaseMockMvc.perform(post("/api/phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Phase in the database
        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeCreate + 1);
        Phase testPhase = phaseList.get(phaseList.size() - 1);
        assertThat(testPhase.getNamePhase()).isEqualTo(DEFAULT_NAME_PHASE);
        assertThat(testPhase.getStatePhase()).isEqualTo(DEFAULT_STATE_PHASE);
    }

    @Test
    @Transactional
    public void createPhaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phaseRepository.findAll().size();

        // Create the Phase with an existing ID
        phase.setId(1L);
        PhaseDTO phaseDTO = phaseMapper.toDto(phase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhaseMockMvc.perform(post("/api/phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Phase in the database
        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNamePhaseIsRequired() throws Exception {
        int databaseSizeBeforeTest = phaseRepository.findAll().size();
        // set the field null
        phase.setNamePhase(null);

        // Create the Phase, which fails.
        PhaseDTO phaseDTO = phaseMapper.toDto(phase);

        restPhaseMockMvc.perform(post("/api/phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phaseDTO)))
            .andExpect(status().isBadRequest());

        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatePhaseIsRequired() throws Exception {
        int databaseSizeBeforeTest = phaseRepository.findAll().size();
        // set the field null
        phase.setStatePhase(null);

        // Create the Phase, which fails.
        PhaseDTO phaseDTO = phaseMapper.toDto(phase);

        restPhaseMockMvc.perform(post("/api/phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phaseDTO)))
            .andExpect(status().isBadRequest());

        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPhases() throws Exception {
        // Initialize the database
        phaseRepository.saveAndFlush(phase);

        // Get all the phaseList
        restPhaseMockMvc.perform(get("/api/phases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phase.getId().intValue())))
            .andExpect(jsonPath("$.[*].namePhase").value(hasItem(DEFAULT_NAME_PHASE)))
            .andExpect(jsonPath("$.[*].statePhase").value(hasItem(DEFAULT_STATE_PHASE.toString())));
    }
    
    @Test
    @Transactional
    public void getPhase() throws Exception {
        // Initialize the database
        phaseRepository.saveAndFlush(phase);

        // Get the phase
        restPhaseMockMvc.perform(get("/api/phases/{id}", phase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(phase.getId().intValue()))
            .andExpect(jsonPath("$.namePhase").value(DEFAULT_NAME_PHASE))
            .andExpect(jsonPath("$.statePhase").value(DEFAULT_STATE_PHASE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPhase() throws Exception {
        // Get the phase
        restPhaseMockMvc.perform(get("/api/phases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhase() throws Exception {
        // Initialize the database
        phaseRepository.saveAndFlush(phase);

        int databaseSizeBeforeUpdate = phaseRepository.findAll().size();

        // Update the phase
        Phase updatedPhase = phaseRepository.findById(phase.getId()).get();
        // Disconnect from session so that the updates on updatedPhase are not directly saved in db
        em.detach(updatedPhase);
        updatedPhase
            .namePhase(UPDATED_NAME_PHASE)
            .statePhase(UPDATED_STATE_PHASE);
        PhaseDTO phaseDTO = phaseMapper.toDto(updatedPhase);

        restPhaseMockMvc.perform(put("/api/phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phaseDTO)))
            .andExpect(status().isOk());

        // Validate the Phase in the database
        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeUpdate);
        Phase testPhase = phaseList.get(phaseList.size() - 1);
        assertThat(testPhase.getNamePhase()).isEqualTo(UPDATED_NAME_PHASE);
        assertThat(testPhase.getStatePhase()).isEqualTo(UPDATED_STATE_PHASE);
    }

    @Test
    @Transactional
    public void updateNonExistingPhase() throws Exception {
        int databaseSizeBeforeUpdate = phaseRepository.findAll().size();

        // Create the Phase
        PhaseDTO phaseDTO = phaseMapper.toDto(phase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhaseMockMvc.perform(put("/api/phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Phase in the database
        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePhase() throws Exception {
        // Initialize the database
        phaseRepository.saveAndFlush(phase);

        int databaseSizeBeforeDelete = phaseRepository.findAll().size();

        // Delete the phase
        restPhaseMockMvc.perform(delete("/api/phases/{id}", phase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Phase> phaseList = phaseRepository.findAll();
        assertThat(phaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
