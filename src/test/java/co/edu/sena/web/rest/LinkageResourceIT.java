package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.Linkage;
import co.edu.sena.repository.LinkageRepository;
import co.edu.sena.service.LinkageService;
import co.edu.sena.service.dto.LinkageDTO;
import co.edu.sena.service.mapper.LinkageMapper;
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
 * Integration tests for the {@link LinkageResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class LinkageResourceIT {

    private static final String DEFAULT_TYPE_LINK = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_LINK = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOURS = 1;
    private static final Integer UPDATED_HOURS = 2;

    private static final State DEFAULT_STATE_LINK = State.ACTIVO;
    private static final State UPDATED_STATE_LINK = State.INACTIVO;

    @Autowired
    private LinkageRepository linkageRepository;

    @Autowired
    private LinkageMapper linkageMapper;

    @Autowired
    private LinkageService linkageService;

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

    private MockMvc restLinkageMockMvc;

    private Linkage linkage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LinkageResource linkageResource = new LinkageResource(linkageService);
        this.restLinkageMockMvc = MockMvcBuilders.standaloneSetup(linkageResource)
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
    public static Linkage createEntity(EntityManager em) {
        Linkage linkage = new Linkage()
            .typeLink(DEFAULT_TYPE_LINK)
            .hours(DEFAULT_HOURS)
            .stateLink(DEFAULT_STATE_LINK);
        return linkage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Linkage createUpdatedEntity(EntityManager em) {
        Linkage linkage = new Linkage()
            .typeLink(UPDATED_TYPE_LINK)
            .hours(UPDATED_HOURS)
            .stateLink(UPDATED_STATE_LINK);
        return linkage;
    }

    @BeforeEach
    public void initTest() {
        linkage = createEntity(em);
    }

    @Test
    @Transactional
    public void createLinkage() throws Exception {
        int databaseSizeBeforeCreate = linkageRepository.findAll().size();

        // Create the Linkage
        LinkageDTO linkageDTO = linkageMapper.toDto(linkage);
        restLinkageMockMvc.perform(post("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isCreated());

        // Validate the Linkage in the database
        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeCreate + 1);
        Linkage testLinkage = linkageList.get(linkageList.size() - 1);
        assertThat(testLinkage.getTypeLink()).isEqualTo(DEFAULT_TYPE_LINK);
        assertThat(testLinkage.getHours()).isEqualTo(DEFAULT_HOURS);
        assertThat(testLinkage.getStateLink()).isEqualTo(DEFAULT_STATE_LINK);
    }

    @Test
    @Transactional
    public void createLinkageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = linkageRepository.findAll().size();

        // Create the Linkage with an existing ID
        linkage.setId(1L);
        LinkageDTO linkageDTO = linkageMapper.toDto(linkage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinkageMockMvc.perform(post("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Linkage in the database
        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeLinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = linkageRepository.findAll().size();
        // set the field null
        linkage.setTypeLink(null);

        // Create the Linkage, which fails.
        LinkageDTO linkageDTO = linkageMapper.toDto(linkage);

        restLinkageMockMvc.perform(post("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isBadRequest());

        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoursIsRequired() throws Exception {
        int databaseSizeBeforeTest = linkageRepository.findAll().size();
        // set the field null
        linkage.setHours(null);

        // Create the Linkage, which fails.
        LinkageDTO linkageDTO = linkageMapper.toDto(linkage);

        restLinkageMockMvc.perform(post("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isBadRequest());

        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateLinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = linkageRepository.findAll().size();
        // set the field null
        linkage.setStateLink(null);

        // Create the Linkage, which fails.
        LinkageDTO linkageDTO = linkageMapper.toDto(linkage);

        restLinkageMockMvc.perform(post("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isBadRequest());

        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLinkages() throws Exception {
        // Initialize the database
        linkageRepository.saveAndFlush(linkage);

        // Get all the linkageList
        restLinkageMockMvc.perform(get("/api/linkages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(linkage.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeLink").value(hasItem(DEFAULT_TYPE_LINK)))
            .andExpect(jsonPath("$.[*].hours").value(hasItem(DEFAULT_HOURS)))
            .andExpect(jsonPath("$.[*].stateLink").value(hasItem(DEFAULT_STATE_LINK.toString())));
    }
    
    @Test
    @Transactional
    public void getLinkage() throws Exception {
        // Initialize the database
        linkageRepository.saveAndFlush(linkage);

        // Get the linkage
        restLinkageMockMvc.perform(get("/api/linkages/{id}", linkage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(linkage.getId().intValue()))
            .andExpect(jsonPath("$.typeLink").value(DEFAULT_TYPE_LINK))
            .andExpect(jsonPath("$.hours").value(DEFAULT_HOURS))
            .andExpect(jsonPath("$.stateLink").value(DEFAULT_STATE_LINK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLinkage() throws Exception {
        // Get the linkage
        restLinkageMockMvc.perform(get("/api/linkages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLinkage() throws Exception {
        // Initialize the database
        linkageRepository.saveAndFlush(linkage);

        int databaseSizeBeforeUpdate = linkageRepository.findAll().size();

        // Update the linkage
        Linkage updatedLinkage = linkageRepository.findById(linkage.getId()).get();
        // Disconnect from session so that the updates on updatedLinkage are not directly saved in db
        em.detach(updatedLinkage);
        updatedLinkage
            .typeLink(UPDATED_TYPE_LINK)
            .hours(UPDATED_HOURS)
            .stateLink(UPDATED_STATE_LINK);
        LinkageDTO linkageDTO = linkageMapper.toDto(updatedLinkage);

        restLinkageMockMvc.perform(put("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isOk());

        // Validate the Linkage in the database
        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeUpdate);
        Linkage testLinkage = linkageList.get(linkageList.size() - 1);
        assertThat(testLinkage.getTypeLink()).isEqualTo(UPDATED_TYPE_LINK);
        assertThat(testLinkage.getHours()).isEqualTo(UPDATED_HOURS);
        assertThat(testLinkage.getStateLink()).isEqualTo(UPDATED_STATE_LINK);
    }

    @Test
    @Transactional
    public void updateNonExistingLinkage() throws Exception {
        int databaseSizeBeforeUpdate = linkageRepository.findAll().size();

        // Create the Linkage
        LinkageDTO linkageDTO = linkageMapper.toDto(linkage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinkageMockMvc.perform(put("/api/linkages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Linkage in the database
        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLinkage() throws Exception {
        // Initialize the database
        linkageRepository.saveAndFlush(linkage);

        int databaseSizeBeforeDelete = linkageRepository.findAll().size();

        // Delete the linkage
        restLinkageMockMvc.perform(delete("/api/linkages/{id}", linkage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Linkage> linkageList = linkageRepository.findAll();
        assertThat(linkageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
