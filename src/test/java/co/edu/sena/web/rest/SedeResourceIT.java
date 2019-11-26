package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Sede;
import co.edu.sena.repository.SedeRepository;
import co.edu.sena.service.SedeService;
import co.edu.sena.service.dto.SedeDTO;
import co.edu.sena.service.mapper.SedeMapper;
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
 * Integration tests for the {@link SedeResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class SedeResourceIT {

    private static final String DEFAULT_NAME_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_SEDE = State.ACTIVO;
    private static final State UPDATED_STATE_SEDE = State.INACTIVO;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private SedeMapper sedeMapper;

    @Autowired
    private SedeService sedeService;

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

    private MockMvc restSedeMockMvc;

    private Sede sede;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SedeResource sedeResource = new SedeResource(sedeService);
        this.restSedeMockMvc = MockMvcBuilders.standaloneSetup(sedeResource)
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
    public static Sede createEntity(EntityManager em) {
        Sede sede = new Sede()
            .nameSede(DEFAULT_NAME_SEDE)
            .description(DEFAULT_DESCRIPTION)
            .stateSede(DEFAULT_STATE_SEDE);
        return sede;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sede createUpdatedEntity(EntityManager em) {
        Sede sede = new Sede()
            .nameSede(UPDATED_NAME_SEDE)
            .description(UPDATED_DESCRIPTION)
            .stateSede(UPDATED_STATE_SEDE);
        return sede;
    }

    @BeforeEach
    public void initTest() {
        sede = createEntity(em);
    }

    @Test
    @Transactional
    public void createSede() throws Exception {
        int databaseSizeBeforeCreate = sedeRepository.findAll().size();

        // Create the Sede
        SedeDTO sedeDTO = sedeMapper.toDto(sede);
        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isCreated());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeCreate + 1);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getNameSede()).isEqualTo(DEFAULT_NAME_SEDE);
        assertThat(testSede.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSede.getStateSede()).isEqualTo(DEFAULT_STATE_SEDE);
    }

    @Test
    @Transactional
    public void createSedeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sedeRepository.findAll().size();

        // Create the Sede with an existing ID
        sede.setId(1L);
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameSedeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setNameSede(null);

        // Create the Sede, which fails.
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setDescription(null);

        // Create the Sede, which fails.
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateSedeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setStateSede(null);

        // Create the Sede, which fails.
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSedes() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        // Get all the sedeList
        restSedeMockMvc.perform(get("/api/sedes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sede.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameSede").value(hasItem(DEFAULT_NAME_SEDE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].stateSede").value(hasItem(DEFAULT_STATE_SEDE.toString())));
    }
    
    @Test
    @Transactional
    public void getSede() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        // Get the sede
        restSedeMockMvc.perform(get("/api/sedes/{id}", sede.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sede.getId().intValue()))
            .andExpect(jsonPath("$.nameSede").value(DEFAULT_NAME_SEDE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.stateSede").value(DEFAULT_STATE_SEDE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSede() throws Exception {
        // Get the sede
        restSedeMockMvc.perform(get("/api/sedes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSede() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Update the sede
        Sede updatedSede = sedeRepository.findById(sede.getId()).get();
        // Disconnect from session so that the updates on updatedSede are not directly saved in db
        em.detach(updatedSede);
        updatedSede
            .nameSede(UPDATED_NAME_SEDE)
            .description(UPDATED_DESCRIPTION)
            .stateSede(UPDATED_STATE_SEDE);
        SedeDTO sedeDTO = sedeMapper.toDto(updatedSede);

        restSedeMockMvc.perform(put("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isOk());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getNameSede()).isEqualTo(UPDATED_NAME_SEDE);
        assertThat(testSede.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSede.getStateSede()).isEqualTo(UPDATED_STATE_SEDE);
    }

    @Test
    @Transactional
    public void updateNonExistingSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Create the Sede
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSedeMockMvc.perform(put("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSede() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        int databaseSizeBeforeDelete = sedeRepository.findAll().size();

        // Delete the sede
        restSedeMockMvc.perform(delete("/api/sedes/{id}", sede.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
