package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Proyect;
import co.edu.sena.domain.Program;
import co.edu.sena.repository.ProyectRepository;
import co.edu.sena.service.ProyectService;
import co.edu.sena.service.dto.ProyectDTO;
import co.edu.sena.service.mapper.ProyectMapper;
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
 * Integration tests for the {@link ProyectResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class ProyectResourceIT {

    private static final String DEFAULT_CODE_PROYECT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PROYECT = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_PROYECT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_PROYECT = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_PROYECT = State.ACTIVO;
    private static final State UPDATED_STATE_PROYECT = State.INACTIVO;

    @Autowired
    private ProyectRepository proyectRepository;

    @Autowired
    private ProyectMapper proyectMapper;

    @Autowired
    private ProyectService proyectService;

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

    private MockMvc restProyectMockMvc;

    private Proyect proyect;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProyectResource proyectResource = new ProyectResource(proyectService);
        this.restProyectMockMvc = MockMvcBuilders.standaloneSetup(proyectResource)
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
    public static Proyect createEntity(EntityManager em) {
        Proyect proyect = new Proyect()
            .codeProyect(DEFAULT_CODE_PROYECT)
            .nameProyect(DEFAULT_NAME_PROYECT)
            .stateProyect(DEFAULT_STATE_PROYECT);
        // Add required entity
        Program program;
        if (TestUtil.findAll(em, Program.class).isEmpty()) {
            program = ProgramResourceIT.createEntity(em);
            em.persist(program);
            em.flush();
        } else {
            program = TestUtil.findAll(em, Program.class).get(0);
        }
        proyect.setProgram(program);
        return proyect;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proyect createUpdatedEntity(EntityManager em) {
        Proyect proyect = new Proyect()
            .codeProyect(UPDATED_CODE_PROYECT)
            .nameProyect(UPDATED_NAME_PROYECT)
            .stateProyect(UPDATED_STATE_PROYECT);
        // Add required entity
        Program program;
        if (TestUtil.findAll(em, Program.class).isEmpty()) {
            program = ProgramResourceIT.createUpdatedEntity(em);
            em.persist(program);
            em.flush();
        } else {
            program = TestUtil.findAll(em, Program.class).get(0);
        }
        proyect.setProgram(program);
        return proyect;
    }

    @BeforeEach
    public void initTest() {
        proyect = createEntity(em);
    }

    @Test
    @Transactional
    public void createProyect() throws Exception {
        int databaseSizeBeforeCreate = proyectRepository.findAll().size();

        // Create the Proyect
        ProyectDTO proyectDTO = proyectMapper.toDto(proyect);
        restProyectMockMvc.perform(post("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isCreated());

        // Validate the Proyect in the database
        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeCreate + 1);
        Proyect testProyect = proyectList.get(proyectList.size() - 1);
        assertThat(testProyect.getCodeProyect()).isEqualTo(DEFAULT_CODE_PROYECT);
        assertThat(testProyect.getNameProyect()).isEqualTo(DEFAULT_NAME_PROYECT);
        assertThat(testProyect.getStateProyect()).isEqualTo(DEFAULT_STATE_PROYECT);
    }

    @Test
    @Transactional
    public void createProyectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proyectRepository.findAll().size();

        // Create the Proyect with an existing ID
        proyect.setId(1L);
        ProyectDTO proyectDTO = proyectMapper.toDto(proyect);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProyectMockMvc.perform(post("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proyect in the database
        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeProyectIsRequired() throws Exception {
        int databaseSizeBeforeTest = proyectRepository.findAll().size();
        // set the field null
        proyect.setCodeProyect(null);

        // Create the Proyect, which fails.
        ProyectDTO proyectDTO = proyectMapper.toDto(proyect);

        restProyectMockMvc.perform(post("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isBadRequest());

        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameProyectIsRequired() throws Exception {
        int databaseSizeBeforeTest = proyectRepository.findAll().size();
        // set the field null
        proyect.setNameProyect(null);

        // Create the Proyect, which fails.
        ProyectDTO proyectDTO = proyectMapper.toDto(proyect);

        restProyectMockMvc.perform(post("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isBadRequest());

        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateProyectIsRequired() throws Exception {
        int databaseSizeBeforeTest = proyectRepository.findAll().size();
        // set the field null
        proyect.setStateProyect(null);

        // Create the Proyect, which fails.
        ProyectDTO proyectDTO = proyectMapper.toDto(proyect);

        restProyectMockMvc.perform(post("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isBadRequest());

        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProyects() throws Exception {
        // Initialize the database
        proyectRepository.saveAndFlush(proyect);

        // Get all the proyectList
        restProyectMockMvc.perform(get("/api/proyects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proyect.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeProyect").value(hasItem(DEFAULT_CODE_PROYECT)))
            .andExpect(jsonPath("$.[*].nameProyect").value(hasItem(DEFAULT_NAME_PROYECT)))
            .andExpect(jsonPath("$.[*].stateProyect").value(hasItem(DEFAULT_STATE_PROYECT.toString())));
    }
    
    @Test
    @Transactional
    public void getProyect() throws Exception {
        // Initialize the database
        proyectRepository.saveAndFlush(proyect);

        // Get the proyect
        restProyectMockMvc.perform(get("/api/proyects/{id}", proyect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proyect.getId().intValue()))
            .andExpect(jsonPath("$.codeProyect").value(DEFAULT_CODE_PROYECT))
            .andExpect(jsonPath("$.nameProyect").value(DEFAULT_NAME_PROYECT))
            .andExpect(jsonPath("$.stateProyect").value(DEFAULT_STATE_PROYECT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProyect() throws Exception {
        // Get the proyect
        restProyectMockMvc.perform(get("/api/proyects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProyect() throws Exception {
        // Initialize the database
        proyectRepository.saveAndFlush(proyect);

        int databaseSizeBeforeUpdate = proyectRepository.findAll().size();

        // Update the proyect
        Proyect updatedProyect = proyectRepository.findById(proyect.getId()).get();
        // Disconnect from session so that the updates on updatedProyect are not directly saved in db
        em.detach(updatedProyect);
        updatedProyect
            .codeProyect(UPDATED_CODE_PROYECT)
            .nameProyect(UPDATED_NAME_PROYECT)
            .stateProyect(UPDATED_STATE_PROYECT);
        ProyectDTO proyectDTO = proyectMapper.toDto(updatedProyect);

        restProyectMockMvc.perform(put("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isOk());

        // Validate the Proyect in the database
        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeUpdate);
        Proyect testProyect = proyectList.get(proyectList.size() - 1);
        assertThat(testProyect.getCodeProyect()).isEqualTo(UPDATED_CODE_PROYECT);
        assertThat(testProyect.getNameProyect()).isEqualTo(UPDATED_NAME_PROYECT);
        assertThat(testProyect.getStateProyect()).isEqualTo(UPDATED_STATE_PROYECT);
    }

    @Test
    @Transactional
    public void updateNonExistingProyect() throws Exception {
        int databaseSizeBeforeUpdate = proyectRepository.findAll().size();

        // Create the Proyect
        ProyectDTO proyectDTO = proyectMapper.toDto(proyect);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProyectMockMvc.perform(put("/api/proyects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proyect in the database
        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProyect() throws Exception {
        // Initialize the database
        proyectRepository.saveAndFlush(proyect);

        int databaseSizeBeforeDelete = proyectRepository.findAll().size();

        // Delete the proyect
        restProyectMockMvc.perform(delete("/api/proyects/{id}", proyect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proyect> proyectList = proyectRepository.findAll();
        assertThat(proyectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
