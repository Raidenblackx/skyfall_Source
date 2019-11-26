package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.WorkingDay;
import co.edu.sena.repository.WorkingDayRepository;
import co.edu.sena.service.WorkingDayService;
import co.edu.sena.service.dto.WorkingDayDTO;
import co.edu.sena.service.mapper.WorkingDayMapper;
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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link WorkingDayResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class WorkingDayResourceIT {

    private static final String DEFAULT_INITIAL_WORKING_DAY = "AAAAAAAAAA";
    private static final String UPDATED_INITIAL_WORKING_DAY = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_WORKING_DAY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_WORKING_DAY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN_URL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_URL = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_URL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_URL_CONTENT_TYPE = "image/png";

    private static final State DEFAULT_STATE_WORKING_DAY = State.ACTIVO;
    private static final State UPDATED_STATE_WORKING_DAY = State.INACTIVO;

    @Autowired
    private WorkingDayRepository workingDayRepository;

    @Autowired
    private WorkingDayMapper workingDayMapper;

    @Autowired
    private WorkingDayService workingDayService;

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

    private MockMvc restWorkingDayMockMvc;

    private WorkingDay workingDay;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WorkingDayResource workingDayResource = new WorkingDayResource(workingDayService);
        this.restWorkingDayMockMvc = MockMvcBuilders.standaloneSetup(workingDayResource)
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
    public static WorkingDay createEntity(EntityManager em) {
        WorkingDay workingDay = new WorkingDay()
            .initialWorkingDay(DEFAULT_INITIAL_WORKING_DAY)
            .nameWorkingDay(DEFAULT_NAME_WORKING_DAY)
            .description(DEFAULT_DESCRIPTION)
            .imagenUrl(DEFAULT_IMAGEN_URL)
            .imagenUrlContentType(DEFAULT_IMAGEN_URL_CONTENT_TYPE)
            .stateWorkingDay(DEFAULT_STATE_WORKING_DAY);
        return workingDay;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkingDay createUpdatedEntity(EntityManager em) {
        WorkingDay workingDay = new WorkingDay()
            .initialWorkingDay(UPDATED_INITIAL_WORKING_DAY)
            .nameWorkingDay(UPDATED_NAME_WORKING_DAY)
            .description(UPDATED_DESCRIPTION)
            .imagenUrl(UPDATED_IMAGEN_URL)
            .imagenUrlContentType(UPDATED_IMAGEN_URL_CONTENT_TYPE)
            .stateWorkingDay(UPDATED_STATE_WORKING_DAY);
        return workingDay;
    }

    @BeforeEach
    public void initTest() {
        workingDay = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkingDay() throws Exception {
        int databaseSizeBeforeCreate = workingDayRepository.findAll().size();

        // Create the WorkingDay
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);
        restWorkingDayMockMvc.perform(post("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isCreated());

        // Validate the WorkingDay in the database
        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeCreate + 1);
        WorkingDay testWorkingDay = workingDayList.get(workingDayList.size() - 1);
        assertThat(testWorkingDay.getInitialWorkingDay()).isEqualTo(DEFAULT_INITIAL_WORKING_DAY);
        assertThat(testWorkingDay.getNameWorkingDay()).isEqualTo(DEFAULT_NAME_WORKING_DAY);
        assertThat(testWorkingDay.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testWorkingDay.getImagenUrl()).isEqualTo(DEFAULT_IMAGEN_URL);
        assertThat(testWorkingDay.getImagenUrlContentType()).isEqualTo(DEFAULT_IMAGEN_URL_CONTENT_TYPE);
        assertThat(testWorkingDay.getStateWorkingDay()).isEqualTo(DEFAULT_STATE_WORKING_DAY);
    }

    @Test
    @Transactional
    public void createWorkingDayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workingDayRepository.findAll().size();

        // Create the WorkingDay with an existing ID
        workingDay.setId(1L);
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkingDayMockMvc.perform(post("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WorkingDay in the database
        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkInitialWorkingDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = workingDayRepository.findAll().size();
        // set the field null
        workingDay.setInitialWorkingDay(null);

        // Create the WorkingDay, which fails.
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);

        restWorkingDayMockMvc.perform(post("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isBadRequest());

        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameWorkingDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = workingDayRepository.findAll().size();
        // set the field null
        workingDay.setNameWorkingDay(null);

        // Create the WorkingDay, which fails.
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);

        restWorkingDayMockMvc.perform(post("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isBadRequest());

        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = workingDayRepository.findAll().size();
        // set the field null
        workingDay.setDescription(null);

        // Create the WorkingDay, which fails.
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);

        restWorkingDayMockMvc.perform(post("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isBadRequest());

        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateWorkingDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = workingDayRepository.findAll().size();
        // set the field null
        workingDay.setStateWorkingDay(null);

        // Create the WorkingDay, which fails.
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);

        restWorkingDayMockMvc.perform(post("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isBadRequest());

        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWorkingDays() throws Exception {
        // Initialize the database
        workingDayRepository.saveAndFlush(workingDay);

        // Get all the workingDayList
        restWorkingDayMockMvc.perform(get("/api/working-days?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workingDay.getId().intValue())))
            .andExpect(jsonPath("$.[*].initialWorkingDay").value(hasItem(DEFAULT_INITIAL_WORKING_DAY)))
            .andExpect(jsonPath("$.[*].nameWorkingDay").value(hasItem(DEFAULT_NAME_WORKING_DAY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imagenUrlContentType").value(hasItem(DEFAULT_IMAGEN_URL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagenUrl").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_URL))))
            .andExpect(jsonPath("$.[*].stateWorkingDay").value(hasItem(DEFAULT_STATE_WORKING_DAY.toString())));
    }
    
    @Test
    @Transactional
    public void getWorkingDay() throws Exception {
        // Initialize the database
        workingDayRepository.saveAndFlush(workingDay);

        // Get the workingDay
        restWorkingDayMockMvc.perform(get("/api/working-days/{id}", workingDay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(workingDay.getId().intValue()))
            .andExpect(jsonPath("$.initialWorkingDay").value(DEFAULT_INITIAL_WORKING_DAY))
            .andExpect(jsonPath("$.nameWorkingDay").value(DEFAULT_NAME_WORKING_DAY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imagenUrlContentType").value(DEFAULT_IMAGEN_URL_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagenUrl").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_URL)))
            .andExpect(jsonPath("$.stateWorkingDay").value(DEFAULT_STATE_WORKING_DAY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkingDay() throws Exception {
        // Get the workingDay
        restWorkingDayMockMvc.perform(get("/api/working-days/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkingDay() throws Exception {
        // Initialize the database
        workingDayRepository.saveAndFlush(workingDay);

        int databaseSizeBeforeUpdate = workingDayRepository.findAll().size();

        // Update the workingDay
        WorkingDay updatedWorkingDay = workingDayRepository.findById(workingDay.getId()).get();
        // Disconnect from session so that the updates on updatedWorkingDay are not directly saved in db
        em.detach(updatedWorkingDay);
        updatedWorkingDay
            .initialWorkingDay(UPDATED_INITIAL_WORKING_DAY)
            .nameWorkingDay(UPDATED_NAME_WORKING_DAY)
            .description(UPDATED_DESCRIPTION)
            .imagenUrl(UPDATED_IMAGEN_URL)
            .imagenUrlContentType(UPDATED_IMAGEN_URL_CONTENT_TYPE)
            .stateWorkingDay(UPDATED_STATE_WORKING_DAY);
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(updatedWorkingDay);

        restWorkingDayMockMvc.perform(put("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isOk());

        // Validate the WorkingDay in the database
        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeUpdate);
        WorkingDay testWorkingDay = workingDayList.get(workingDayList.size() - 1);
        assertThat(testWorkingDay.getInitialWorkingDay()).isEqualTo(UPDATED_INITIAL_WORKING_DAY);
        assertThat(testWorkingDay.getNameWorkingDay()).isEqualTo(UPDATED_NAME_WORKING_DAY);
        assertThat(testWorkingDay.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testWorkingDay.getImagenUrl()).isEqualTo(UPDATED_IMAGEN_URL);
        assertThat(testWorkingDay.getImagenUrlContentType()).isEqualTo(UPDATED_IMAGEN_URL_CONTENT_TYPE);
        assertThat(testWorkingDay.getStateWorkingDay()).isEqualTo(UPDATED_STATE_WORKING_DAY);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkingDay() throws Exception {
        int databaseSizeBeforeUpdate = workingDayRepository.findAll().size();

        // Create the WorkingDay
        WorkingDayDTO workingDayDTO = workingDayMapper.toDto(workingDay);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkingDayMockMvc.perform(put("/api/working-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workingDayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WorkingDay in the database
        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWorkingDay() throws Exception {
        // Initialize the database
        workingDayRepository.saveAndFlush(workingDay);

        int databaseSizeBeforeDelete = workingDayRepository.findAll().size();

        // Delete the workingDay
        restWorkingDayMockMvc.perform(delete("/api/working-days/{id}", workingDay.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkingDay> workingDayList = workingDayRepository.findAll();
        assertThat(workingDayList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
