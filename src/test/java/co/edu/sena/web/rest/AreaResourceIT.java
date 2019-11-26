package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Area;
import co.edu.sena.repository.AreaRepository;
import co.edu.sena.service.AreaService;
import co.edu.sena.service.dto.AreaDTO;
import co.edu.sena.service.mapper.AreaMapper;
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
 * Integration tests for the {@link AreaResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class AreaResourceIT {

    private static final String DEFAULT_NAME_AREA = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AREA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_URL_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_URL_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_URL_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_URL_LOGO_CONTENT_TYPE = "image/png";

    private static final State DEFAULT_STATE_AREA = State.ACTIVO;
    private static final State UPDATED_STATE_AREA = State.INACTIVO;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private AreaService areaService;

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

    private MockMvc restAreaMockMvc;

    private Area area;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AreaResource areaResource = new AreaResource(areaService);
        this.restAreaMockMvc = MockMvcBuilders.standaloneSetup(areaResource)
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
    public static Area createEntity(EntityManager em) {
        Area area = new Area()
            .nameArea(DEFAULT_NAME_AREA)
            .urlLogo(DEFAULT_URL_LOGO)
            .urlLogoContentType(DEFAULT_URL_LOGO_CONTENT_TYPE)
            .stateArea(DEFAULT_STATE_AREA);
        return area;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Area createUpdatedEntity(EntityManager em) {
        Area area = new Area()
            .nameArea(UPDATED_NAME_AREA)
            .urlLogo(UPDATED_URL_LOGO)
            .urlLogoContentType(UPDATED_URL_LOGO_CONTENT_TYPE)
            .stateArea(UPDATED_STATE_AREA);
        return area;
    }

    @BeforeEach
    public void initTest() {
        area = createEntity(em);
    }

    @Test
    @Transactional
    public void createArea() throws Exception {
        int databaseSizeBeforeCreate = areaRepository.findAll().size();

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);
        restAreaMockMvc.perform(post("/api/areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isCreated());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeCreate + 1);
        Area testArea = areaList.get(areaList.size() - 1);
        assertThat(testArea.getNameArea()).isEqualTo(DEFAULT_NAME_AREA);
        assertThat(testArea.getUrlLogo()).isEqualTo(DEFAULT_URL_LOGO);
        assertThat(testArea.getUrlLogoContentType()).isEqualTo(DEFAULT_URL_LOGO_CONTENT_TYPE);
        assertThat(testArea.getStateArea()).isEqualTo(DEFAULT_STATE_AREA);
    }

    @Test
    @Transactional
    public void createAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = areaRepository.findAll().size();

        // Create the Area with an existing ID
        area.setId(1L);
        AreaDTO areaDTO = areaMapper.toDto(area);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAreaMockMvc.perform(post("/api/areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = areaRepository.findAll().size();
        // set the field null
        area.setNameArea(null);

        // Create the Area, which fails.
        AreaDTO areaDTO = areaMapper.toDto(area);

        restAreaMockMvc.perform(post("/api/areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isBadRequest());

        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = areaRepository.findAll().size();
        // set the field null
        area.setStateArea(null);

        // Create the Area, which fails.
        AreaDTO areaDTO = areaMapper.toDto(area);

        restAreaMockMvc.perform(post("/api/areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isBadRequest());

        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAreas() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        // Get all the areaList
        restAreaMockMvc.perform(get("/api/areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(area.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameArea").value(hasItem(DEFAULT_NAME_AREA)))
            .andExpect(jsonPath("$.[*].urlLogoContentType").value(hasItem(DEFAULT_URL_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].urlLogo").value(hasItem(Base64Utils.encodeToString(DEFAULT_URL_LOGO))))
            .andExpect(jsonPath("$.[*].stateArea").value(hasItem(DEFAULT_STATE_AREA.toString())));
    }
    
    @Test
    @Transactional
    public void getArea() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        // Get the area
        restAreaMockMvc.perform(get("/api/areas/{id}", area.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(area.getId().intValue()))
            .andExpect(jsonPath("$.nameArea").value(DEFAULT_NAME_AREA))
            .andExpect(jsonPath("$.urlLogoContentType").value(DEFAULT_URL_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.urlLogo").value(Base64Utils.encodeToString(DEFAULT_URL_LOGO)))
            .andExpect(jsonPath("$.stateArea").value(DEFAULT_STATE_AREA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArea() throws Exception {
        // Get the area
        restAreaMockMvc.perform(get("/api/areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArea() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        int databaseSizeBeforeUpdate = areaRepository.findAll().size();

        // Update the area
        Area updatedArea = areaRepository.findById(area.getId()).get();
        // Disconnect from session so that the updates on updatedArea are not directly saved in db
        em.detach(updatedArea);
        updatedArea
            .nameArea(UPDATED_NAME_AREA)
            .urlLogo(UPDATED_URL_LOGO)
            .urlLogoContentType(UPDATED_URL_LOGO_CONTENT_TYPE)
            .stateArea(UPDATED_STATE_AREA);
        AreaDTO areaDTO = areaMapper.toDto(updatedArea);

        restAreaMockMvc.perform(put("/api/areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isOk());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
        Area testArea = areaList.get(areaList.size() - 1);
        assertThat(testArea.getNameArea()).isEqualTo(UPDATED_NAME_AREA);
        assertThat(testArea.getUrlLogo()).isEqualTo(UPDATED_URL_LOGO);
        assertThat(testArea.getUrlLogoContentType()).isEqualTo(UPDATED_URL_LOGO_CONTENT_TYPE);
        assertThat(testArea.getStateArea()).isEqualTo(UPDATED_STATE_AREA);
    }

    @Test
    @Transactional
    public void updateNonExistingArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAreaMockMvc.perform(put("/api/areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArea() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        int databaseSizeBeforeDelete = areaRepository.findAll().size();

        // Delete the area
        restAreaMockMvc.perform(delete("/api/areas/{id}", area.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
