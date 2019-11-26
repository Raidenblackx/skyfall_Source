package co.edu.sena.service;

import co.edu.sena.domain.TrimesterPlanning;
import co.edu.sena.repository.TrimesterPlanningRepository;
import co.edu.sena.service.dto.TrimesterPlanningDTO;
import co.edu.sena.service.mapper.TrimesterPlanningMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TrimesterPlanning}.
 */
@Service
@Transactional
public class TrimesterPlanningService {

    private final Logger log = LoggerFactory.getLogger(TrimesterPlanningService.class);

    private final TrimesterPlanningRepository trimesterPlanningRepository;

    private final TrimesterPlanningMapper trimesterPlanningMapper;

    public TrimesterPlanningService(TrimesterPlanningRepository trimesterPlanningRepository, TrimesterPlanningMapper trimesterPlanningMapper) {
        this.trimesterPlanningRepository = trimesterPlanningRepository;
        this.trimesterPlanningMapper = trimesterPlanningMapper;
    }

    /**
     * Save a trimesterPlanning.
     *
     * @param trimesterPlanningDTO the entity to save.
     * @return the persisted entity.
     */
    public TrimesterPlanningDTO save(TrimesterPlanningDTO trimesterPlanningDTO) {
        log.debug("Request to save TrimesterPlanning : {}", trimesterPlanningDTO);
        TrimesterPlanning trimesterPlanning = trimesterPlanningMapper.toEntity(trimesterPlanningDTO);
        trimesterPlanning = trimesterPlanningRepository.save(trimesterPlanning);
        return trimesterPlanningMapper.toDto(trimesterPlanning);
    }

    /**
     * Get all the trimesterPlannings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrimesterPlanningDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrimesterPlannings");
        return trimesterPlanningRepository.findAll(pageable)
            .map(trimesterPlanningMapper::toDto);
    }


    /**
     * Get one trimesterPlanning by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrimesterPlanningDTO> findOne(Long id) {
        log.debug("Request to get TrimesterPlanning : {}", id);
        return trimesterPlanningRepository.findById(id)
            .map(trimesterPlanningMapper::toDto);
    }

    /**
     * Delete the trimesterPlanning by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TrimesterPlanning : {}", id);
        trimesterPlanningRepository.deleteById(id);
    }
}
