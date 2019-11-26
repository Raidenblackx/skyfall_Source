package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Modality;
import co.edu.sena.repository.ModalityRepository;
import co.edu.sena.service.ModalityService;
import co.edu.sena.service.dto.ModalityDTO;
import co.edu.sena.service.mapper.ModalityMapper;
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
 * Integration tests for the {@link ModalityResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ModalityResourceIT {

    private static final String DEFAULT_NAME_MODALITY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_MODALITY = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_MODALITY = State.ACTIVO;
    private static final State UPDATED_STATE_MODALITY = State.INACTIVO;

    @Autowired
    private ModalityRepository modalityRepository;

    @Autowired
    private ModalityMapper modalityMapper;

    @Autowired
    private ModalityService modalityService;

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

    private MockMvc restModalityMockMvc;

    private Modality modality;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModalityResource modalityResource = new ModalityResource(modalityService);
        this.restModalityMockMvc = MockMvcBuilders.standaloneSetup(modalityResource)
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
    public static Modality createEntity(EntityManager em) {
        Modality modality = new Modality()
            .nameModality(DEFAULT_NAME_MODALITY)
            .color(DEFAULT_COLOR)
            .stateModality(DEFAULT_STATE_MODALITY);
        return modality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modality createUpdatedEntity(EntityManager em) {
        Modality modality = new Modality()
            .nameModality(UPDATED_NAME_MODALITY)
            .color(UPDATED_COLOR)
            .stateModality(UPDATED_STATE_MODALITY);
        return modality;
    }

    @BeforeEach
    public void initTest() {
        modality = createEntity(em);
    }

    @Test
    @Transactional
    public void createModality() throws Exception {
        int databaseSizeBeforeCreate = modalityRepository.findAll().size();

        // Create the Modality
        ModalityDTO modalityDTO = modalityMapper.toDto(modality);
        restModalityMockMvc.perform(post("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isCreated());

        // Validate the Modality in the database
        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeCreate + 1);
        Modality testModality = modalityList.get(modalityList.size() - 1);
        assertThat(testModality.getNameModality()).isEqualTo(DEFAULT_NAME_MODALITY);
        assertThat(testModality.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testModality.getStateModality()).isEqualTo(DEFAULT_STATE_MODALITY);
    }

    @Test
    @Transactional
    public void createModalityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modalityRepository.findAll().size();

        // Create the Modality with an existing ID
        modality.setId(1L);
        ModalityDTO modalityDTO = modalityMapper.toDto(modality);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModalityMockMvc.perform(post("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Modality in the database
        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameModalityIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalityRepository.findAll().size();
        // set the field null
        modality.setNameModality(null);

        // Create the Modality, which fails.
        ModalityDTO modalityDTO = modalityMapper.toDto(modality);

        restModalityMockMvc.perform(post("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isBadRequest());

        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkColorIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalityRepository.findAll().size();
        // set the field null
        modality.setColor(null);

        // Create the Modality, which fails.
        ModalityDTO modalityDTO = modalityMapper.toDto(modality);

        restModalityMockMvc.perform(post("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isBadRequest());

        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateModalityIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalityRepository.findAll().size();
        // set the field null
        modality.setStateModality(null);

        // Create the Modality, which fails.
        ModalityDTO modalityDTO = modalityMapper.toDto(modality);

        restModalityMockMvc.perform(post("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isBadRequest());

        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModalities() throws Exception {
        // Initialize the database
        modalityRepository.saveAndFlush(modality);

        // Get all the modalityList
        restModalityMockMvc.perform(get("/api/modalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modality.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameModality").value(hasItem(DEFAULT_NAME_MODALITY)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].stateModality").value(hasItem(DEFAULT_STATE_MODALITY.toString())));
    }
    
    @Test
    @Transactional
    public void getModality() throws Exception {
        // Initialize the database
        modalityRepository.saveAndFlush(modality);

        // Get the modality
        restModalityMockMvc.perform(get("/api/modalities/{id}", modality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(modality.getId().intValue()))
            .andExpect(jsonPath("$.nameModality").value(DEFAULT_NAME_MODALITY))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.stateModality").value(DEFAULT_STATE_MODALITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingModality() throws Exception {
        // Get the modality
        restModalityMockMvc.perform(get("/api/modalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModality() throws Exception {
        // Initialize the database
        modalityRepository.saveAndFlush(modality);

        int databaseSizeBeforeUpdate = modalityRepository.findAll().size();

        // Update the modality
        Modality updatedModality = modalityRepository.findById(modality.getId()).get();
        // Disconnect from session so that the updates on updatedModality are not directly saved in db
        em.detach(updatedModality);
        updatedModality
            .nameModality(UPDATED_NAME_MODALITY)
            .color(UPDATED_COLOR)
            .stateModality(UPDATED_STATE_MODALITY);
        ModalityDTO modalityDTO = modalityMapper.toDto(updatedModality);

        restModalityMockMvc.perform(put("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isOk());

        // Validate the Modality in the database
        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeUpdate);
        Modality testModality = modalityList.get(modalityList.size() - 1);
        assertThat(testModality.getNameModality()).isEqualTo(UPDATED_NAME_MODALITY);
        assertThat(testModality.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testModality.getStateModality()).isEqualTo(UPDATED_STATE_MODALITY);
    }

    @Test
    @Transactional
    public void updateNonExistingModality() throws Exception {
        int databaseSizeBeforeUpdate = modalityRepository.findAll().size();

        // Create the Modality
        ModalityDTO modalityDTO = modalityMapper.toDto(modality);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModalityMockMvc.perform(put("/api/modalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Modality in the database
        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModality() throws Exception {
        // Initialize the database
        modalityRepository.saveAndFlush(modality);

        int databaseSizeBeforeDelete = modalityRepository.findAll().size();

        // Delete the modality
        restModalityMockMvc.perform(delete("/api/modalities/{id}", modality.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Modality> modalityList = modalityRepository.findAll();
        assertThat(modalityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
