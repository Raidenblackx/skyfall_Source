package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Year;
import co.edu.sena.repository.YearRepository;
import co.edu.sena.service.YearService;
import co.edu.sena.service.dto.YearDTO;
import co.edu.sena.service.mapper.YearMapper;
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
 * Integration tests for the {@link YearResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class YearResourceIT {

    private static final Integer DEFAULT_NUMBER_YEAR = 1;
    private static final Integer UPDATED_NUMBER_YEAR = 2;

    private static final State DEFAULT_STATE_YEAR = State.ACTIVO;
    private static final State UPDATED_STATE_YEAR = State.INACTIVO;

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private YearMapper yearMapper;

    @Autowired
    private YearService yearService;

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

    private MockMvc restYearMockMvc;

    private Year year;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final YearResource yearResource = new YearResource(yearService);
        this.restYearMockMvc = MockMvcBuilders.standaloneSetup(yearResource)
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
    public static Year createEntity(EntityManager em) {
        Year year = new Year()
            .numberYear(DEFAULT_NUMBER_YEAR)
            .stateYear(DEFAULT_STATE_YEAR);
        return year;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Year createUpdatedEntity(EntityManager em) {
        Year year = new Year()
            .numberYear(UPDATED_NUMBER_YEAR)
            .stateYear(UPDATED_STATE_YEAR);
        return year;
    }

    @BeforeEach
    public void initTest() {
        year = createEntity(em);
    }

    @Test
    @Transactional
    public void createYear() throws Exception {
        int databaseSizeBeforeCreate = yearRepository.findAll().size();

        // Create the Year
        YearDTO yearDTO = yearMapper.toDto(year);
        restYearMockMvc.perform(post("/api/years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yearDTO)))
            .andExpect(status().isCreated());

        // Validate the Year in the database
        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeCreate + 1);
        Year testYear = yearList.get(yearList.size() - 1);
        assertThat(testYear.getNumberYear()).isEqualTo(DEFAULT_NUMBER_YEAR);
        assertThat(testYear.getStateYear()).isEqualTo(DEFAULT_STATE_YEAR);
    }

    @Test
    @Transactional
    public void createYearWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = yearRepository.findAll().size();

        // Create the Year with an existing ID
        year.setId(1L);
        YearDTO yearDTO = yearMapper.toDto(year);

        // An entity with an existing ID cannot be created, so this API call must fail
        restYearMockMvc.perform(post("/api/years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yearDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Year in the database
        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumberYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = yearRepository.findAll().size();
        // set the field null
        year.setNumberYear(null);

        // Create the Year, which fails.
        YearDTO yearDTO = yearMapper.toDto(year);

        restYearMockMvc.perform(post("/api/years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yearDTO)))
            .andExpect(status().isBadRequest());

        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = yearRepository.findAll().size();
        // set the field null
        year.setStateYear(null);

        // Create the Year, which fails.
        YearDTO yearDTO = yearMapper.toDto(year);

        restYearMockMvc.perform(post("/api/years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yearDTO)))
            .andExpect(status().isBadRequest());

        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllYears() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);

        // Get all the yearList
        restYearMockMvc.perform(get("/api/years?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(year.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberYear").value(hasItem(DEFAULT_NUMBER_YEAR)))
            .andExpect(jsonPath("$.[*].stateYear").value(hasItem(DEFAULT_STATE_YEAR.toString())));
    }
    
    @Test
    @Transactional
    public void getYear() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);

        // Get the year
        restYearMockMvc.perform(get("/api/years/{id}", year.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(year.getId().intValue()))
            .andExpect(jsonPath("$.numberYear").value(DEFAULT_NUMBER_YEAR))
            .andExpect(jsonPath("$.stateYear").value(DEFAULT_STATE_YEAR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingYear() throws Exception {
        // Get the year
        restYearMockMvc.perform(get("/api/years/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateYear() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);

        int databaseSizeBeforeUpdate = yearRepository.findAll().size();

        // Update the year
        Year updatedYear = yearRepository.findById(year.getId()).get();
        // Disconnect from session so that the updates on updatedYear are not directly saved in db
        em.detach(updatedYear);
        updatedYear
            .numberYear(UPDATED_NUMBER_YEAR)
            .stateYear(UPDATED_STATE_YEAR);
        YearDTO yearDTO = yearMapper.toDto(updatedYear);

        restYearMockMvc.perform(put("/api/years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yearDTO)))
            .andExpect(status().isOk());

        // Validate the Year in the database
        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeUpdate);
        Year testYear = yearList.get(yearList.size() - 1);
        assertThat(testYear.getNumberYear()).isEqualTo(UPDATED_NUMBER_YEAR);
        assertThat(testYear.getStateYear()).isEqualTo(UPDATED_STATE_YEAR);
    }

    @Test
    @Transactional
    public void updateNonExistingYear() throws Exception {
        int databaseSizeBeforeUpdate = yearRepository.findAll().size();

        // Create the Year
        YearDTO yearDTO = yearMapper.toDto(year);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restYearMockMvc.perform(put("/api/years")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(yearDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Year in the database
        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteYear() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);

        int databaseSizeBeforeDelete = yearRepository.findAll().size();

        // Delete the year
        restYearMockMvc.perform(delete("/api/years/{id}", year.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Year> yearList = yearRepository.findAll();
        assertThat(yearList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
