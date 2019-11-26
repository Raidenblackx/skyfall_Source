package co.edu.sena.service;

import co.edu.sena.domain.Phase;
import co.edu.sena.repository.PhaseRepository;
import co.edu.sena.service.dto.PhaseDTO;
import co.edu.sena.service.mapper.PhaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Phase}.
 */
@Service
@Transactional
public class PhaseService {

    private final Logger log = LoggerFactory.getLogger(PhaseService.class);

    private final PhaseRepository phaseRepository;

    private final PhaseMapper phaseMapper;

    public PhaseService(PhaseRepository phaseRepository, PhaseMapper phaseMapper) {
        this.phaseRepository = phaseRepository;
        this.phaseMapper = phaseMapper;
    }

    /**
     * Save a phase.
     *
     * @param phaseDTO the entity to save.
     * @return the persisted entity.
     */
    public PhaseDTO save(PhaseDTO phaseDTO) {
        log.debug("Request to save Phase : {}", phaseDTO);
        Phase phase = phaseMapper.toEntity(phaseDTO);
        phase = phaseRepository.save(phase);
        return phaseMapper.toDto(phase);
    }

    /**
     * Get all the phases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PhaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Phases");
        return phaseRepository.findAll(pageable)
            .map(phaseMapper::toDto);
    }


    /**
     * Get one phase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PhaseDTO> findOne(Long id) {
        log.debug("Request to get Phase : {}", id);
        return phaseRepository.findById(id)
            .map(phaseMapper::toDto);
    }

    /**
     * Delete the phase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Phase : {}", id);
        phaseRepository.deleteById(id);
    }
}
