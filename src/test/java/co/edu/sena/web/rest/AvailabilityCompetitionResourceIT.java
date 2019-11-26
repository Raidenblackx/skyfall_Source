package co.edu.sena.web.rest;

import co.edu.sena.SkyfallApp;
import co.edu.sena.domain.AvailabilityCompetition;
import co.edu.sena.domain.Competition;
import co.edu.sena.domain.InstructorLinking;
import co.edu.sena.repository.AvailabilityCompetitionRepository;
import co.edu.sena.service.AvailabilityCompetitionService;
import co.edu.sena.service.dto.AvailabilityCompetitionDTO;
import co.edu.sena.service.mapper.AvailabilityCompetitionMapper;
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
 * Integration tests for the {@link AvailabilityCompetitionResource} REST controller.
 */
@SpringBootTest(classes = SkyfallApp.class)
public class AvailabilityCompetitionResourceIT {

    @Autowired
    private AvailabilityCompetitionRepository availabilityCompetitionRepository;

    @Autowired
    private AvailabilityCompetitionMapper availabilityCompetitionMapper;

    @Autowired
    private AvailabilityCompetitionService availabilityCompetitionService;

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

    private MockMvc restAvailabilityCompetitionMockMvc;

    private AvailabilityCompetition availabilityCompetition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AvailabilityCompetitionResource availabilityCompetitionResource = new AvailabilityCompetitionResource(availabilityCompetitionService);
        this.restAvailabilityCompetitionMockMvc = MockMvcBuilders.standaloneSetup(availabilityCompetitionResource)
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
    public static AvailabilityCompetition createEntity(EntityManager em) {
        AvailabilityCompetition availabilityCompetition = new AvailabilityCompetition();
        // Add required entity
        Competition competition;
        if (TestUtil.findAll(em, Competition.class).isEmpty()) {
            competition = CompetitionResourceIT.createEntity(em);
            em.persist(competition);
            em.flush();
        } else {
            competition = TestUtil.findAll(em, Competition.class).get(0);
        }
        availabilityCompetition.setCompetition(competition);
        // Add required entity
        InstructorLinking instructorLinking;
        if (TestUtil.findAll(em, InstructorLinking.class).isEmpty()) {
            instructorLinking = InstructorLinkingResourceIT.createEntity(em);
            em.persist(instructorLinking);
            em.flush();
        } else {
            instructorLinking = TestUtil.findAll(em, InstructorLinking.class).get(0);
        }
        availabilityCompetition.setInstructorLinking(instructorLinking);
        return availabilityCompetition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvailabilityCompetition createUpdatedEntity(EntityManager em) {
        AvailabilityCompetition availabilityCompetition = new AvailabilityCompetition();
        // Add required entity
        Competition competition;
        if (TestUtil.findAll(em, Competition.class).isEmpty()) {
            competition = CompetitionResourceIT.createUpdatedEntity(em);
            em.persist(competition);
            em.flush();
        } else {
            competition = TestUtil.findAll(em, Competition.class).get(0);
        }
        availabilityCompetition.setCompetition(competition);
        // Add required entity
        InstructorLinking instructorLinking;
        if (TestUtil.findAll(em, InstructorLinking.class).isEmpty()) {
            instructorLinking = InstructorLinkingResourceIT.createUpdatedEntity(em);
            em.persist(instructorLinking);
            em.flush();
        } else {
            instructorLinking = TestUtil.findAll(em, InstructorLinking.class).get(0);
        }
        availabilityCompetition.setInstructorLinking(instructorLinking);
        return availabilityCompetition;
    }

    @BeforeEach
    public void initTest() {
        availabilityCompetition = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvailabilityCompetition() throws Exception {
        int databaseSizeBeforeCreate = availabilityCompetitionRepository.findAll().size();

        // Create the AvailabilityCompetition
        AvailabilityCompetitionDTO availabilityCompetitionDTO = availabilityCompetitionMapper.toDto(availabilityCompetition);
        restAvailabilityCompetitionMockMvc.perform(post("/api/availability-competitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(availabilityCompetitionDTO)))
            .andExpect(status().isCreated());

        // Validate the AvailabilityCompetition in the database
        List<AvailabilityCompetition> availabilityCompetitionList = availabilityCompetitionRepository.findAll();
        assertThat(availabilityCompetitionList).hasSize(databaseSizeBeforeCreate + 1);
        AvailabilityCompetition testAvailabilityCompetition = availabilityCompetitionList.get(availabilityCompetitionList.size() - 1);
    }

    @Test
    @Transactional
    public void createAvailabilityCompetitionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = availabilityCompetitionRepository.findAll().size();

        // Create the AvailabilityCompetition with an existing ID
        availabilityCompetition.setId(1L);
        AvailabilityCompetitionDTO availabilityCompetitionDTO = availabilityCompetitionMapper.toDto(availabilityCompetition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvailabilityCompetitionMockMvc.perform(post("/api/availability-competitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(availabilityCompetitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvailabilityCompetition in the database
        List<AvailabilityCompetition> availabilityCompetitionList = availabilityCompetitionRepository.findAll();
        assertThat(availabilityCompetitionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAvailabilityCompetitions() throws Exception {
        // Initialize the database
        availabilityCompetitionRepository.saveAndFlush(availabilityCompetition);

        // Get all the availabilityCompetitionList
        restAvailabilityCompetitionMockMvc.perform(get("/api/availability-competitions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(availabilityCompetition.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAvailabilityCompetition() throws Exception {
        // Initialize the database
        availabilityCompetitionRepository.saveAndFlush(availabilityCompetition);

        // Get the availabilityCompetition
        restAvailabilityCompetitionMockMvc.perform(get("/api/availability-competitions/{id}", availabilityCompetition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(availabilityCompetition.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAvailabilityCompetition() throws Exception {
        // Get the availabilityCompetition
        restAvailabilityCompetitionMockMvc.perform(get("/api/availability-competitions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvailabilityCompetition() throws Exception {
        // Initialize the database
        availabilityCompetitionRepository.saveAndFlush(availabilityCompetition);

        int databaseSizeBeforeUpdate = availabilityCompetitionRepository.findAll().size();

        // Update the availabilityCompetition
        AvailabilityCompetition updatedAvailabilityCompetition = availabilityCompetitionRepository.findById(availabilityCompetition.getId()).get();
        // Disconnect from session so that the updates on updatedAvailabilityCompetition are not directly saved in db
        em.detach(updatedAvailabilityCompetition);
        AvailabilityCompetitionDTO availabilityCompetitionDTO = availabilityCompetitionMapper.toDto(updatedAvailabilityCompetition);

        restAvailabilityCompetitionMockMvc.perform(put("/api/availability-competitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(availabilityCompetitionDTO)))
            .andExpect(status().isOk());

        // Validate the AvailabilityCompetition in the database
        List<AvailabilityCompetition> availabilityCompetitionList = availabilityCompetitionRepository.findAll();
        assertThat(availabilityCompetitionList).hasSize(databaseSizeBeforeUpdate);
        AvailabilityCompetition testAvailabilityCompetition = availabilityCompetitionList.get(availabilityCompetitionList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingAvailabilityCompetition() throws Exception {
        int databaseSizeBeforeUpdate = availabilityCompetitionRepository.findAll().size();

        // Create the AvailabilityCompetition
        AvailabilityCompetitionDTO availabilityCompetitionDTO = availabilityCompetitionMapper.toDto(availabilityCompetition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvailabilityCompetitionMockMvc.perform(put("/api/availability-competitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(availabilityCompetitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvailabilityCompetition in the database
        List<AvailabilityCompetition> availabilityCompetitionList = availabilityCompetitionRepository.findAll();
        assertThat(availabilityCompetitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvailabilityCompetition() throws Exception {
        // Initialize the database
        availabilityCompetitionRepository.saveAndFlush(availabilityCompetition);

        int databaseSizeBeforeDelete = availabilityCompetitionRepository.findAll().size();

        // Delete the availabilityCompetition
        restAvailabilityCompetitionMockMvc.perform(delete("/api/availability-competitions/{id}", availabilityCompetition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AvailabilityCompetition> availabilityCompetitionList = availabilityCompetitionRepository.findAll();
        assertThat(availabilityCompetitionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
