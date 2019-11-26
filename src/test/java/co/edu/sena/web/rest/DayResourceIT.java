package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Day;
import co.edu.sena.repository.DayRepository;
import co.edu.sena.service.DayService;
import co.edu.sena.service.dto.DayDTO;
import co.edu.sena.service.mapper.DayMapper;
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
 * Integration tests for the {@link DayResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class DayResourceIT {

    private static final String DEFAULT_NAME_DAY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_DAY = "BBBBBBBBBB";

    private static final State DEFAULT_STATE_DAY = State.ACTIVO;
    private static final State UPDATED_STATE_DAY = State.INACTIVO;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private DayMapper dayMapper;

    @Autowired
    private DayService dayService;

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

    private MockMvc restDayMockMvc;

    private Day day;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DayResource dayResource = new DayResource(dayService);
        this.restDayMockMvc = MockMvcBuilders.standaloneSetup(dayResource)
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
    public static Day createEntity(EntityManager em) {
        Day day = new Day()
            .nameDay(DEFAULT_NAME_DAY)
            .stateDay(DEFAULT_STATE_DAY);
        return day;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Day createUpdatedEntity(EntityManager em) {
        Day day = new Day()
            .nameDay(UPDATED_NAME_DAY)
            .stateDay(UPDATED_STATE_DAY);
        return day;
    }

    @BeforeEach
    public void initTest() {
        day = createEntity(em);
    }

    @Test
    @Transactional
    public void createDay() throws Exception {
        int databaseSizeBeforeCreate = dayRepository.findAll().size();

        // Create the Day
        DayDTO dayDTO = dayMapper.toDto(day);
        restDayMockMvc.perform(post("/api/days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dayDTO)))
            .andExpect(status().isCreated());

        // Validate the Day in the database
        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeCreate + 1);
        Day testDay = dayList.get(dayList.size() - 1);
        assertThat(testDay.getNameDay()).isEqualTo(DEFAULT_NAME_DAY);
        assertThat(testDay.getStateDay()).isEqualTo(DEFAULT_STATE_DAY);
    }

    @Test
    @Transactional
    public void createDayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dayRepository.findAll().size();

        // Create the Day with an existing ID
        day.setId(1L);
        DayDTO dayDTO = dayMapper.toDto(day);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDayMockMvc.perform(post("/api/days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Day in the database
        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = dayRepository.findAll().size();
        // set the field null
        day.setNameDay(null);

        // Create the Day, which fails.
        DayDTO dayDTO = dayMapper.toDto(day);

        restDayMockMvc.perform(post("/api/days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dayDTO)))
            .andExpect(status().isBadRequest());

        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = dayRepository.findAll().size();
        // set the field null
        day.setStateDay(null);

        // Create the Day, which fails.
        DayDTO dayDTO = dayMapper.toDto(day);

        restDayMockMvc.perform(post("/api/days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dayDTO)))
            .andExpect(status().isBadRequest());

        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDays() throws Exception {
        // Initialize the database
        dayRepository.saveAndFlush(day);

        // Get all the dayList
        restDayMockMvc.perform(get("/api/days?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(day.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameDay").value(hasItem(DEFAULT_NAME_DAY)))
            .andExpect(jsonPath("$.[*].stateDay").value(hasItem(DEFAULT_STATE_DAY.toString())));
    }
    
    @Test
    @Transactional
    public void getDay() throws Exception {
        // Initialize the database
        dayRepository.saveAndFlush(day);

        // Get the day
        restDayMockMvc.perform(get("/api/days/{id}", day.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(day.getId().intValue()))
            .andExpect(jsonPath("$.nameDay").value(DEFAULT_NAME_DAY))
            .andExpect(jsonPath("$.stateDay").value(DEFAULT_STATE_DAY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDay() throws Exception {
        // Get the day
        restDayMockMvc.perform(get("/api/days/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDay() throws Exception {
        // Initialize the database
        dayRepository.saveAndFlush(day);

        int databaseSizeBeforeUpdate = dayRepository.findAll().size();

        // Update the day
        Day updatedDay = dayRepository.findById(day.getId()).get();
        // Disconnect from session so that the updates on updatedDay are not directly saved in db
        em.detach(updatedDay);
        updatedDay
            .nameDay(UPDATED_NAME_DAY)
            .stateDay(UPDATED_STATE_DAY);
        DayDTO dayDTO = dayMapper.toDto(updatedDay);

        restDayMockMvc.perform(put("/api/days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dayDTO)))
            .andExpect(status().isOk());

        // Validate the Day in the database
        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeUpdate);
        Day testDay = dayList.get(dayList.size() - 1);
        assertThat(testDay.getNameDay()).isEqualTo(UPDATED_NAME_DAY);
        assertThat(testDay.getStateDay()).isEqualTo(UPDATED_STATE_DAY);
    }

    @Test
    @Transactional
    public void updateNonExistingDay() throws Exception {
        int databaseSizeBeforeUpdate = dayRepository.findAll().size();

        // Create the Day
        DayDTO dayDTO = dayMapper.toDto(day);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDayMockMvc.perform(put("/api/days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Day in the database
        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDay() throws Exception {
        // Initialize the database
        dayRepository.saveAndFlush(day);

        int databaseSizeBeforeDelete = dayRepository.findAll().size();

        // Delete the day
        restDayMockMvc.perform(delete("/api/days/{id}", day.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Day> dayList = dayRepository.findAll();
        assertThat(dayList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
