package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.TypeEnvironment;
import co.edu.sena.repository.TypeEnvironmentRepository;
import co.edu.sena.service.TypeEnvironmentService;
import co.edu.sena.service.dto.TypeEnvironmentDTO;
import co.edu.sena.service.mapper.TypeEnvironmentMapper;
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
 * Integration tests for the {@link TypeEnvironmentResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class TypeEnvironmentResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_TYPE_ENVIRONMENT = State.ACTIVO;
    private static final State UPDATED_STATE_TYPE_ENVIRONMENT = State.INACTIVO;

    @Autowired
    private TypeEnvironmentRepository typeEnvironmentRepository;

    @Autowired
    private TypeEnvironmentMapper typeEnvironmentMapper;

    @Autowired
    private TypeEnvironmentService typeEnvironmentService;

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

    private MockMvc restTypeEnvironmentMockMvc;

    private TypeEnvironment typeEnvironment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeEnvironmentResource typeEnvironmentResource = new TypeEnvironmentResource(typeEnvironmentService);
        this.restTypeEnvironmentMockMvc = MockMvcBuilders.standaloneSetup(typeEnvironmentResource)
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
    public static TypeEnvironment createEntity(EntityManager em) {
        TypeEnvironment typeEnvironment = new TypeEnvironment()
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .stateTypeEnvironment(DEFAULT_STATE_TYPE_ENVIRONMENT);
        return typeEnvironment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeEnvironment createUpdatedEntity(EntityManager em) {
        TypeEnvironment typeEnvironment = new TypeEnvironment()
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .stateTypeEnvironment(UPDATED_STATE_TYPE_ENVIRONMENT);
        return typeEnvironment;
    }

    @BeforeEach
    public void initTest() {
        typeEnvironment = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeEnvironment() throws Exception {
        int databaseSizeBeforeCreate = typeEnvironmentRepository.findAll().size();

        // Create the TypeEnvironment
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(typeEnvironment);
        restTypeEnvironmentMockMvc.perform(post("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeEnvironment in the database
        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeCreate + 1);
        TypeEnvironment testTypeEnvironment = typeEnvironmentList.get(typeEnvironmentList.size() - 1);
        assertThat(testTypeEnvironment.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTypeEnvironment.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTypeEnvironment.getStateTypeEnvironment()).isEqualTo(DEFAULT_STATE_TYPE_ENVIRONMENT);
    }

    @Test
    @Transactional
    public void createTypeEnvironmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeEnvironmentRepository.findAll().size();

        // Create the TypeEnvironment with an existing ID
        typeEnvironment.setId(1L);
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(typeEnvironment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeEnvironmentMockMvc.perform(post("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEnvironment in the database
        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEnvironmentRepository.findAll().size();
        // set the field null
        typeEnvironment.setType(null);

        // Create the TypeEnvironment, which fails.
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(typeEnvironment);

        restTypeEnvironmentMockMvc.perform(post("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEnvironmentRepository.findAll().size();
        // set the field null
        typeEnvironment.setDescription(null);

        // Create the TypeEnvironment, which fails.
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(typeEnvironment);

        restTypeEnvironmentMockMvc.perform(post("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateTypeEnvironmentIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEnvironmentRepository.findAll().size();
        // set the field null
        typeEnvironment.setStateTypeEnvironment(null);

        // Create the TypeEnvironment, which fails.
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(typeEnvironment);

        restTypeEnvironmentMockMvc.perform(post("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeEnvironments() throws Exception {
        // Initialize the database
        typeEnvironmentRepository.saveAndFlush(typeEnvironment);

        // Get all the typeEnvironmentList
        restTypeEnvironmentMockMvc.perform(get("/api/type-environments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeEnvironment.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].stateTypeEnvironment").value(hasItem(DEFAULT_STATE_TYPE_ENVIRONMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeEnvironment() throws Exception {
        // Initialize the database
        typeEnvironmentRepository.saveAndFlush(typeEnvironment);

        // Get the typeEnvironment
        restTypeEnvironmentMockMvc.perform(get("/api/type-environments/{id}", typeEnvironment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeEnvironment.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.stateTypeEnvironment").value(DEFAULT_STATE_TYPE_ENVIRONMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeEnvironment() throws Exception {
        // Get the typeEnvironment
        restTypeEnvironmentMockMvc.perform(get("/api/type-environments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeEnvironment() throws Exception {
        // Initialize the database
        typeEnvironmentRepository.saveAndFlush(typeEnvironment);

        int databaseSizeBeforeUpdate = typeEnvironmentRepository.findAll().size();

        // Update the typeEnvironment
        TypeEnvironment updatedTypeEnvironment = typeEnvironmentRepository.findById(typeEnvironment.getId()).get();
        // Disconnect from session so that the updates on updatedTypeEnvironment are not directly saved in db
        em.detach(updatedTypeEnvironment);
        updatedTypeEnvironment
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .stateTypeEnvironment(UPDATED_STATE_TYPE_ENVIRONMENT);
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(updatedTypeEnvironment);

        restTypeEnvironmentMockMvc.perform(put("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isOk());

        // Validate the TypeEnvironment in the database
        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeUpdate);
        TypeEnvironment testTypeEnvironment = typeEnvironmentList.get(typeEnvironmentList.size() - 1);
        assertThat(testTypeEnvironment.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTypeEnvironment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTypeEnvironment.getStateTypeEnvironment()).isEqualTo(UPDATED_STATE_TYPE_ENVIRONMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeEnvironment() throws Exception {
        int databaseSizeBeforeUpdate = typeEnvironmentRepository.findAll().size();

        // Create the TypeEnvironment
        TypeEnvironmentDTO typeEnvironmentDTO = typeEnvironmentMapper.toDto(typeEnvironment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeEnvironmentMockMvc.perform(put("/api/type-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEnvironment in the database
        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeEnvironment() throws Exception {
        // Initialize the database
        typeEnvironmentRepository.saveAndFlush(typeEnvironment);

        int databaseSizeBeforeDelete = typeEnvironmentRepository.findAll().size();

        // Delete the typeEnvironment
        restTypeEnvironmentMockMvc.perform(delete("/api/type-environments/{id}", typeEnvironment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeEnvironment> typeEnvironmentList = typeEnvironmentRepository.findAll();
        assertThat(typeEnvironmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
