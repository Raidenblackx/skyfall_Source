package co.edu.sena.service;

import co.edu.sena.domain.PlanningActivity;
import co.edu.sena.repository.PlanningActivityRepository;
import co.edu.sena.service.dto.PlanningActivityDTO;
import co.edu.sena.service.mapper.PlanningActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PlanningActivity}.
 */
@Service
@Transactional
public class PlanningActivityService {

    private final Logger log = LoggerFactory.getLogger(PlanningActivityService.class);

    private final PlanningActivityRepository planningActivityRepository;

    private final PlanningActivityMapper planningActivityMapper;

    public PlanningActivityService(PlanningActivityRepository planningActivityRepository, PlanningActivityMapper planningActivityMapper) {
        this.planningActivityRepository = planningActivityRepository;
        this.planningActivityMapper = planningActivityMapper;
    }

    /**
     * Save a planningActivity.
     *
     * @param planningActivityDTO the entity to save.
     * @return the persisted entity.
     */
    public PlanningActivityDTO save(PlanningActivityDTO planningActivityDTO) {
        log.debug("Request to save PlanningActivity : {}", planningActivityDTO);
        PlanningActivity planningActivity = planningActivityMapper.toEntity(planningActivityDTO);
        planningActivity = planningActivityRepository.save(planningActivity);
        return planningActivityMapper.toDto(planningActivity);
    }

    /**
     * Get all the planningActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanningActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanningActivities");
        return planningActivityRepository.findAll(pageable)
            .map(planningActivityMapper::toDto);
    }


    /**
     * Get one planningActivity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PlanningActivityDTO> findOne(Long id) {
        log.debug("Request to get PlanningActivity : {}", id);
        return planningActivityRepository.findById(id)
            .map(planningActivityMapper::toDto);
    }

    /**
     * Delete the planningActivity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PlanningActivity : {}", id);
        planningActivityRepository.deleteById(id);
    }
}
