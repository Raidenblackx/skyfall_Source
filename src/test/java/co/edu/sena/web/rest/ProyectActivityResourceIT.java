package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.ProyectActivity;
import co.edu.sena.domain.Phase;
import co.edu.sena.repository.ProyectActivityRepository;
import co.edu.sena.service.ProyectActivityService;
import co.edu.sena.service.dto.ProyectActivityDTO;
import co.edu.sena.service.mapper.ProyectActivityMapper;
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
 * Integration tests for the {@link ProyectActivityResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ProyectActivityResourceIT {

    private static final Integer DEFAULT_NUMBER_PROYECT_ACTIVITY = 1;
    private static final Integer UPDATED_NUMBER_PROYECT_ACTIVITY = 2;

    private static final String DEFAULT_DESCRIPTION_ACTIVITY = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_ACTIVITY = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_PROYECT_ACTIVITY = State.ACTIVO;
    private static final State UPDATED_STATE_PROYECT_ACTIVITY = State.INACTIVO;

    @Autowired
    private ProyectActivityRepository proyectActivityRepository;

    @Autowired
    private ProyectActivityMapper proyectActivityMapper;

    @Autowired
    private ProyectActivityService proyectActivityService;

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

    private MockMvc restProyectActivityMockMvc;

    private ProyectActivity proyectActivity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProyectActivityResource proyectActivityResource = new ProyectActivityResource(proyectActivityService);
        this.restProyectActivityMockMvc = MockMvcBuilders.standaloneSetup(proyectActivityResource)
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
    public static ProyectActivity createEntity(EntityManager em) {
        ProyectActivity proyectActivity = new ProyectActivity()
            .numberProyectActivity(DEFAULT_NUMBER_PROYECT_ACTIVITY)
            .descriptionActivity(DEFAULT_DESCRIPTION_ACTIVITY)
            .stateProyectActivity(DEFAULT_STATE_PROYECT_ACTIVITY);
        // Add required entity
        Phase phase;
        if (TestUtil.findAll(em, Phase.class).isEmpty()) {
            phase = PhaseResourceIT.createEntity(em);
            em.persist(phase);
            em.flush();
        } else {
            phase = TestUtil.findAll(em, Phase.class).get(0);
        }
        proyectActivity.setPhase(phase);
        return proyectActivity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProyectActivity createUpdatedEntity(EntityManager em) {
        ProyectActivity proyectActivity = new ProyectActivity()
            .numberProyectActivity(UPDATED_NUMBER_PROYECT_ACTIVITY)
            .descriptionActivity(UPDATED_DESCRIPTION_ACTIVITY)
            .stateProyectActivity(UPDATED_STATE_PROYECT_ACTIVITY);
        // Add required entity
        Phase phase;
        if (TestUtil.findAll(em, Phase.class).isEmpty()) {
            phase = PhaseResourceIT.createUpdatedEntity(em);
            em.persist(phase);
            em.flush();
        } else {
            phase = TestUtil.findAll(em, Phase.class).get(0);
        }
        proyectActivity.setPhase(phase);
        return proyectActivity;
    }

    @BeforeEach
    public void initTest() {
        proyectActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createProyectActivity() throws Exception {
        int databaseSizeBeforeCreate = proyectActivityRepository.findAll().size();

        // Create the ProyectActivity
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(proyectActivity);
        restProyectActivityMockMvc.perform(post("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the ProyectActivity in the database
        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeCreate + 1);
        ProyectActivity testProyectActivity = proyectActivityList.get(proyectActivityList.size() - 1);
        assertThat(testProyectActivity.getNumberProyectActivity()).isEqualTo(DEFAULT_NUMBER_PROYECT_ACTIVITY);
        assertThat(testProyectActivity.getDescriptionActivity()).isEqualTo(DEFAULT_DESCRIPTION_ACTIVITY);
        assertThat(testProyectActivity.getStateProyectActivity()).isEqualTo(DEFAULT_STATE_PROYECT_ACTIVITY);
    }

    @Test
    @Transactional
    public void createProyectActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proyectActivityRepository.findAll().size();

        // Create the ProyectActivity with an existing ID
        proyectActivity.setId(1L);
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(proyectActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProyectActivityMockMvc.perform(post("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProyectActivity in the database
        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumberProyectActivityIsRequired() throws Exception {
        int databaseSizeBeforeTest = proyectActivityRepository.findAll().size();
        // set the field null
        proyectActivity.setNumberProyectActivity(null);

        // Create the ProyectActivity, which fails.
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(proyectActivity);

        restProyectActivityMockMvc.perform(post("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isBadRequest());

        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionActivityIsRequired() throws Exception {
        int databaseSizeBeforeTest = proyectActivityRepository.findAll().size();
        // set the field null
        proyectActivity.setDescriptionActivity(null);

        // Create the ProyectActivity, which fails.
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(proyectActivity);

        restProyectActivityMockMvc.perform(post("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isBadRequest());

        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateProyectActivityIsRequired() throws Exception {
        int databaseSizeBeforeTest = proyectActivityRepository.findAll().size();
        // set the field null
        proyectActivity.setStateProyectActivity(null);

        // Create the ProyectActivity, which fails.
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(proyectActivity);

        restProyectActivityMockMvc.perform(post("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isBadRequest());

        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProyectActivities() throws Exception {
        // Initialize the database
        proyectActivityRepository.saveAndFlush(proyectActivity);

        // Get all the proyectActivityList
        restProyectActivityMockMvc.perform(get("/api/proyect-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proyectActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberProyectActivity").value(hasItem(DEFAULT_NUMBER_PROYECT_ACTIVITY)))
            .andExpect(jsonPath("$.[*].descriptionActivity").value(hasItem(DEFAULT_DESCRIPTION_ACTIVITY)))
            .andExpect(jsonPath("$.[*].stateProyectActivity").value(hasItem(DEFAULT_STATE_PROYECT_ACTIVITY.toString())));
    }
    
    @Test
    @Transactional
    public void getProyectActivity() throws Exception {
        // Initialize the database
        proyectActivityRepository.saveAndFlush(proyectActivity);

        // Get the proyectActivity
        restProyectActivityMockMvc.perform(get("/api/proyect-activities/{id}", proyectActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proyectActivity.getId().intValue()))
            .andExpect(jsonPath("$.numberProyectActivity").value(DEFAULT_NUMBER_PROYECT_ACTIVITY))
            .andExpect(jsonPath("$.descriptionActivity").value(DEFAULT_DESCRIPTION_ACTIVITY))
            .andExpect(jsonPath("$.stateProyectActivity").value(DEFAULT_STATE_PROYECT_ACTIVITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProyectActivity() throws Exception {
        // Get the proyectActivity
        restProyectActivityMockMvc.perform(get("/api/proyect-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProyectActivity() throws Exception {
        // Initialize the database
        proyectActivityRepository.saveAndFlush(proyectActivity);

        int databaseSizeBeforeUpdate = proyectActivityRepository.findAll().size();

        // Update the proyectActivity
        ProyectActivity updatedProyectActivity = proyectActivityRepository.findById(proyectActivity.getId()).get();
        // Disconnect from session so that the updates on updatedProyectActivity are not directly saved in db
        em.detach(updatedProyectActivity);
        updatedProyectActivity
            .numberProyectActivity(UPDATED_NUMBER_PROYECT_ACTIVITY)
            .descriptionActivity(UPDATED_DESCRIPTION_ACTIVITY)
            .stateProyectActivity(UPDATED_STATE_PROYECT_ACTIVITY);
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(updatedProyectActivity);

        restProyectActivityMockMvc.perform(put("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isOk());

        // Validate the ProyectActivity in the database
        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeUpdate);
        ProyectActivity testProyectActivity = proyectActivityList.get(proyectActivityList.size() - 1);
        assertThat(testProyectActivity.getNumberProyectActivity()).isEqualTo(UPDATED_NUMBER_PROYECT_ACTIVITY);
        assertThat(testProyectActivity.getDescriptionActivity()).isEqualTo(UPDATED_DESCRIPTION_ACTIVITY);
        assertThat(testProyectActivity.getStateProyectActivity()).isEqualTo(UPDATED_STATE_PROYECT_ACTIVITY);
    }

    @Test
    @Transactional
    public void updateNonExistingProyectActivity() throws Exception {
        int databaseSizeBeforeUpdate = proyectActivityRepository.findAll().size();

        // Create the ProyectActivity
        ProyectActivityDTO proyectActivityDTO = proyectActivityMapper.toDto(proyectActivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProyectActivityMockMvc.perform(put("/api/proyect-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProyectActivity in the database
        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProyectActivity() throws Exception {
        // Initialize the database
        proyectActivityRepository.saveAndFlush(proyectActivity);

        int databaseSizeBeforeDelete = proyectActivityRepository.findAll().size();

        // Delete the proyectActivity
        restProyectActivityMockMvc.perform(delete("/api/proyect-activities/{id}", proyectActivity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProyectActivity> proyectActivityList = proyectActivityRepository.findAll();
        assertThat(proyectActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
