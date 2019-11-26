package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.PlanningActivity;
import co.edu.sena.domain.TrimesterPlanning;
import co.edu.sena.domain.ProyectActivity;
import co.edu.sena.repository.PlanningActivityRepository;
import co.edu.sena.service.PlanningActivityService;
import co.edu.sena.service.dto.PlanningActivityDTO;
import co.edu.sena.service.mapper.PlanningActivityMapper;
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

/**
 * Integration tests for the {@link PlanningActivityResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class PlanningActivityResourceIT {

    @Autowired
    private PlanningActivityRepository planningActivityRepository;

    @Autowired
    private PlanningActivityMapper planningActivityMapper;

    @Autowired
    private PlanningActivityService planningActivityService;

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

    private MockMvc restPlanningActivityMockMvc;

    private PlanningActivity planningActivity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanningActivityResource planningActivityResource = new PlanningActivityResource(planningActivityService);
        this.restPlanningActivityMockMvc = MockMvcBuilders.standaloneSetup(planningActivityResource)
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
    public static PlanningActivity createEntity(EntityManager em) {
        PlanningActivity planningActivity = new PlanningActivity();
        // Add required entity
        TrimesterPlanning trimesterPlanning;
        if (TestUtil.findAll(em, TrimesterPlanning.class).isEmpty()) {
            trimesterPlanning = TrimesterPlanningResourceIT.createEntity(em);
            em.persist(trimesterPlanning);
            em.flush();
        } else {
            trimesterPlanning = TestUtil.findAll(em, TrimesterPlanning.class).get(0);
        }
        planningActivity.setTrimesterPlanning(trimesterPlanning);
        // Add required entity
        ProyectActivity proyectActivity;
        if (TestUtil.findAll(em, ProyectActivity.class).isEmpty()) {
            proyectActivity = ProyectActivityResourceIT.createEntity(em);
            em.persist(proyectActivity);
            em.flush();
        } else {
            proyectActivity = TestUtil.findAll(em, ProyectActivity.class).get(0);
        }
        planningActivity.setProyectActivity(proyectActivity);
        return planningActivity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanningActivity createUpdatedEntity(EntityManager em) {
        PlanningActivity planningActivity = new PlanningActivity();
        // Add required entity
        TrimesterPlanning trimesterPlanning;
        if (TestUtil.findAll(em, TrimesterPlanning.class).isEmpty()) {
            trimesterPlanning = TrimesterPlanningResourceIT.createUpdatedEntity(em);
            em.persist(trimesterPlanning);
            em.flush();
        } else {
            trimesterPlanning = TestUtil.findAll(em, TrimesterPlanning.class).get(0);
        }
        planningActivity.setTrimesterPlanning(trimesterPlanning);
        // Add required entity
        ProyectActivity proyectActivity;
        if (TestUtil.findAll(em, ProyectActivity.class).isEmpty()) {
            proyectActivity = ProyectActivityResourceIT.createUpdatedEntity(em);
            em.persist(proyectActivity);
            em.flush();
        } else {
            proyectActivity = TestUtil.findAll(em, ProyectActivity.class).get(0);
        }
        planningActivity.setProyectActivity(proyectActivity);
        return planningActivity;
    }

    @BeforeEach
    public void initTest() {
        planningActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanningActivity() throws Exception {
        int databaseSizeBeforeCreate = planningActivityRepository.findAll().size();

        // Create the PlanningActivity
        PlanningActivityDTO planningActivityDTO = planningActivityMapper.toDto(planningActivity);
        restPlanningActivityMockMvc.perform(post("/api/planning-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planningActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanningActivity in the database
        List<PlanningActivity> planningActivityList = planningActivityRepository.findAll();
        assertThat(planningActivityList).hasSize(databaseSizeBeforeCreate + 1);
        PlanningActivity testPlanningActivity = planningActivityList.get(planningActivityList.size() - 1);
    }

    @Test
    @Transactional
    public void createPlanningActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planningActivityRepository.findAll().size();

        // Create the PlanningActivity with an existing ID
        planningActivity.setId(1L);
        PlanningActivityDTO planningActivityDTO = planningActivityMapper.toDto(planningActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanningActivityMockMvc.perform(post("/api/planning-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planningActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanningActivity in the database
        List<PlanningActivity> planningActivityList = planningActivityRepository.findAll();
        assertThat(planningActivityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlanningActivities() throws Exception {
        // Initialize the database
        planningActivityRepository.saveAndFlush(planningActivity);

        // Get all the planningActivityList
        restPlanningActivityMockMvc.perform(get("/api/planning-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planningActivity.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getPlanningActivity() throws Exception {
        // Initialize the database
        planningActivityRepository.saveAndFlush(planningActivity);

        // Get the planningActivity
        restPlanningActivityMockMvc.perform(get("/api/planning-activities/{id}", planningActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planningActivity.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanningActivity() throws Exception {
        // Get the planningActivity
        restPlanningActivityMockMvc.perform(get("/api/planning-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanningActivity() throws Exception {
        // Initialize the database
        planningActivityRepository.saveAndFlush(planningActivity);

        int databaseSizeBeforeUpdate = planningActivityRepository.findAll().size();

        // Update the planningActivity
        PlanningActivity updatedPlanningActivity = planningActivityRepository.findById(planningActivity.getId()).get();
        // Disconnect from session so that the updates on updatedPlanningActivity are not directly saved in db
        em.detach(updatedPlanningActivity);
        PlanningActivityDTO planningActivityDTO = planningActivityMapper.toDto(updatedPlanningActivity);

        restPlanningActivityMockMvc.perform(put("/api/planning-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planningActivityDTO)))
            .andExpect(status().isOk());

        // Validate the PlanningActivity in the database
        List<PlanningActivity> planningActivityList = planningActivityRepository.findAll();
        assertThat(planningActivityList).hasSize(databaseSizeBeforeUpdate);
        PlanningActivity testPlanningActivity = planningActivityList.get(planningActivityList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanningActivity() throws Exception {
        int databaseSizeBeforeUpdate = planningActivityRepository.findAll().size();

        // Create the PlanningActivity
        PlanningActivityDTO planningActivityDTO = planningActivityMapper.toDto(planningActivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanningActivityMockMvc.perform(put("/api/planning-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planningActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanningActivity in the database
        List<PlanningActivity> planningActivityList = planningActivityRepository.findAll();
        assertThat(planningActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanningActivity() throws Exception {
        // Initialize the database
        planningActivityRepository.saveAndFlush(planningActivity);

        int databaseSizeBeforeDelete = planningActivityRepository.findAll().size();

        // Delete the planningActivity
        restPlanningActivityMockMvc.perform(delete("/api/planning-activities/{id}", planningActivity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanningActivity> planningActivityList = planningActivityRepository.findAll();
        assertThat(planningActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
