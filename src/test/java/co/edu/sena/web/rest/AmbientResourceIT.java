package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Ambient;
import co.edu.sena.domain.TypeEnvironment;
import co.edu.sena.domain.Sede;
import co.edu.sena.repository.AmbientRepository;
import co.edu.sena.service.AmbientService;
import co.edu.sena.service.dto.AmbientDTO;
import co.edu.sena.service.mapper.AmbientMapper;
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

import co.edu.sena.domain.enumeration.Limitation;
import co.edu.sena.domain.enumeration.State;
/**
 * Integration tests for the {@link AmbientResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class AmbientResourceIT {

    private static final String DEFAULT_NUMBER_ROOM = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_ROOM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Limitation DEFAULT_LIMITATION = Limitation.SIN_LIMITACION;
    private static final Limitation UPDATED_LIMITATION = Limitation.CON_LIMITACION;

    private static final State DEFAULT_STATE_AMBIENT = State.ACTIVO;
    private static final State UPDATED_STATE_AMBIENT = State.INACTIVO;

    @Autowired
    private AmbientRepository ambientRepository;

    @Autowired
    private AmbientMapper ambientMapper;

    @Autowired
    private AmbientService ambientService;

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

    private MockMvc restAmbientMockMvc;

    private Ambient ambient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AmbientResource ambientResource = new AmbientResource(ambientService);
        this.restAmbientMockMvc = MockMvcBuilders.standaloneSetup(ambientResource)
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
    public static Ambient createEntity(EntityManager em) {
        Ambient ambient = new Ambient()
            .numberRoom(DEFAULT_NUMBER_ROOM)
            .description(DEFAULT_DESCRIPTION)
            .limitation(DEFAULT_LIMITATION)
            .stateAmbient(DEFAULT_STATE_AMBIENT);
        // Add required entity
        TypeEnvironment typeEnvironment;
        if (TestUtil.findAll(em, TypeEnvironment.class).isEmpty()) {
            typeEnvironment = TypeEnvironmentResourceIT.createEntity(em);
            em.persist(typeEnvironment);
            em.flush();
        } else {
            typeEnvironment = TestUtil.findAll(em, TypeEnvironment.class).get(0);
        }
        ambient.setTypeEnvironment(typeEnvironment);
        // Add required entity
        Sede sede;
        if (TestUtil.findAll(em, Sede.class).isEmpty()) {
            sede = SedeResourceIT.createEntity(em);
            em.persist(sede);
            em.flush();
        } else {
            sede = TestUtil.findAll(em, Sede.class).get(0);
        }
        ambient.setSede(sede);
        return ambient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ambient createUpdatedEntity(EntityManager em) {
        Ambient ambient = new Ambient()
            .numberRoom(UPDATED_NUMBER_ROOM)
            .description(UPDATED_DESCRIPTION)
            .limitation(UPDATED_LIMITATION)
            .stateAmbient(UPDATED_STATE_AMBIENT);
        // Add required entity
        TypeEnvironment typeEnvironment;
        if (TestUtil.findAll(em, TypeEnvironment.class).isEmpty()) {
            typeEnvironment = TypeEnvironmentResourceIT.createUpdatedEntity(em);
            em.persist(typeEnvironment);
            em.flush();
        } else {
            typeEnvironment = TestUtil.findAll(em, TypeEnvironment.class).get(0);
        }
        ambient.setTypeEnvironment(typeEnvironment);
        // Add required entity
        Sede sede;
        if (TestUtil.findAll(em, Sede.class).isEmpty()) {
            sede = SedeResourceIT.createUpdatedEntity(em);
            em.persist(sede);
            em.flush();
        } else {
            sede = TestUtil.findAll(em, Sede.class).get(0);
        }
        ambient.setSede(sede);
        return ambient;
    }

    @BeforeEach
    public void initTest() {
        ambient = createEntity(em);
    }

    @Test
    @Transactional
    public void createAmbient() throws Exception {
        int databaseSizeBeforeCreate = ambientRepository.findAll().size();

        // Create the Ambient
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);
        restAmbientMockMvc.perform(post("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isCreated());

        // Validate the Ambient in the database
        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeCreate + 1);
        Ambient testAmbient = ambientList.get(ambientList.size() - 1);
        assertThat(testAmbient.getNumberRoom()).isEqualTo(DEFAULT_NUMBER_ROOM);
        assertThat(testAmbient.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAmbient.getLimitation()).isEqualTo(DEFAULT_LIMITATION);
        assertThat(testAmbient.getStateAmbient()).isEqualTo(DEFAULT_STATE_AMBIENT);
    }

    @Test
    @Transactional
    public void createAmbientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ambientRepository.findAll().size();

        // Create the Ambient with an existing ID
        ambient.setId(1L);
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAmbientMockMvc.perform(post("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ambient in the database
        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumberRoomIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambientRepository.findAll().size();
        // set the field null
        ambient.setNumberRoom(null);

        // Create the Ambient, which fails.
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);

        restAmbientMockMvc.perform(post("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isBadRequest());

        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambientRepository.findAll().size();
        // set the field null
        ambient.setDescription(null);

        // Create the Ambient, which fails.
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);

        restAmbientMockMvc.perform(post("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isBadRequest());

        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLimitationIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambientRepository.findAll().size();
        // set the field null
        ambient.setLimitation(null);

        // Create the Ambient, which fails.
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);

        restAmbientMockMvc.perform(post("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isBadRequest());

        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateAmbientIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambientRepository.findAll().size();
        // set the field null
        ambient.setStateAmbient(null);

        // Create the Ambient, which fails.
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);

        restAmbientMockMvc.perform(post("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isBadRequest());

        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAmbients() throws Exception {
        // Initialize the database
        ambientRepository.saveAndFlush(ambient);

        // Get all the ambientList
        restAmbientMockMvc.perform(get("/api/ambients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ambient.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberRoom").value(hasItem(DEFAULT_NUMBER_ROOM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].limitation").value(hasItem(DEFAULT_LIMITATION.toString())))
            .andExpect(jsonPath("$.[*].stateAmbient").value(hasItem(DEFAULT_STATE_AMBIENT.toString())));
    }
    
    @Test
    @Transactional
    public void getAmbient() throws Exception {
        // Initialize the database
        ambientRepository.saveAndFlush(ambient);

        // Get the ambient
        restAmbientMockMvc.perform(get("/api/ambients/{id}", ambient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ambient.getId().intValue()))
            .andExpect(jsonPath("$.numberRoom").value(DEFAULT_NUMBER_ROOM))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.limitation").value(DEFAULT_LIMITATION.toString()))
            .andExpect(jsonPath("$.stateAmbient").value(DEFAULT_STATE_AMBIENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAmbient() throws Exception {
        // Get the ambient
        restAmbientMockMvc.perform(get("/api/ambients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAmbient() throws Exception {
        // Initialize the database
        ambientRepository.saveAndFlush(ambient);

        int databaseSizeBeforeUpdate = ambientRepository.findAll().size();

        // Update the ambient
        Ambient updatedAmbient = ambientRepository.findById(ambient.getId()).get();
        // Disconnect from session so that the updates on updatedAmbient are not directly saved in db
        em.detach(updatedAmbient);
        updatedAmbient
            .numberRoom(UPDATED_NUMBER_ROOM)
            .description(UPDATED_DESCRIPTION)
            .limitation(UPDATED_LIMITATION)
            .stateAmbient(UPDATED_STATE_AMBIENT);
        AmbientDTO ambientDTO = ambientMapper.toDto(updatedAmbient);

        restAmbientMockMvc.perform(put("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isOk());

        // Validate the Ambient in the database
        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeUpdate);
        Ambient testAmbient = ambientList.get(ambientList.size() - 1);
        assertThat(testAmbient.getNumberRoom()).isEqualTo(UPDATED_NUMBER_ROOM);
        assertThat(testAmbient.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAmbient.getLimitation()).isEqualTo(UPDATED_LIMITATION);
        assertThat(testAmbient.getStateAmbient()).isEqualTo(UPDATED_STATE_AMBIENT);
    }

    @Test
    @Transactional
    public void updateNonExistingAmbient() throws Exception {
        int databaseSizeBeforeUpdate = ambientRepository.findAll().size();

        // Create the Ambient
        AmbientDTO ambientDTO = ambientMapper.toDto(ambient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAmbientMockMvc.perform(put("/api/ambients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ambient in the database
        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAmbient() throws Exception {
        // Initialize the database
        ambientRepository.saveAndFlush(ambient);

        int databaseSizeBeforeDelete = ambientRepository.findAll().size();

        // Delete the ambient
        restAmbientMockMvc.perform(delete("/api/ambients/{id}", ambient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ambient> ambientList = ambientRepository.findAll();
        assertThat(ambientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
