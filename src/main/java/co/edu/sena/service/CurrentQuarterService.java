package co.edu.sena.service;

import co.edu.sena.domain.CurrentQuarter;
import co.edu.sena.repository.CurrentQuarterRepository;
import co.edu.sena.service.dto.CurrentQuarterDTO;
import co.edu.sena.service.mapper.CurrentQuarterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CurrentQuarter}.
 */
@Service
@Transactional
public class CurrentQuarterService {

    private final Logger log = LoggerFactory.getLogger(CurrentQuarterService.class);

    private final CurrentQuarterRepository currentQuarterRepository;

    private final CurrentQuarterMapper currentQuarterMapper;

    public CurrentQuarterService(CurrentQuarterRepository currentQuarterRepository, CurrentQuarterMapper currentQuarterMapper) {
        this.currentQuarterRepository = currentQuarterRepository;
        this.currentQuarterMapper = currentQuarterMapper;
    }

    /**
     * Save a currentQuarter.
     *
     * @param currentQuarterDTO the entity to save.
     * @return the persisted entity.
     */
    public CurrentQuarterDTO save(CurrentQuarterDTO currentQuarterDTO) {
        log.debug("Request to save CurrentQuarter : {}", currentQuarterDTO);
        CurrentQuarter currentQuarter = currentQuarterMapper.toEntity(currentQuarterDTO);
        currentQuarter = currentQuarterRepository.save(currentQuarter);
        return currentQuarterMapper.toDto(currentQuarter);
    }

    /**
     * Get all the currentQuarters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CurrentQuarterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CurrentQuarters");
        return currentQuarterRepository.findAll(pageable)
            .map(currentQuarterMapper::toDto);
    }


    /**
     * Get one currentQuarter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CurrentQuarterDTO> findOne(Long id) {
        log.debug("Request to get CurrentQuarter : {}", id);
        return currentQuarterRepository.findById(id)
            .map(currentQuarterMapper::toDto);
    }

    /**
     * Delete the currentQuarter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CurrentQuarter : {}", id);
        currentQuarterRepository.deleteById(id);
    }
}
