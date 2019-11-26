package co.edu.sena.service;

import co.edu.sena.domain.AvailabilityCompetition;
import co.edu.sena.repository.AvailabilityCompetitionRepository;
import co.edu.sena.service.dto.AvailabilityCompetitionDTO;
import co.edu.sena.service.mapper.AvailabilityCompetitionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AvailabilityCompetition}.
 */
@Service
@Transactional
public class AvailabilityCompetitionService {

    private final Logger log = LoggerFactory.getLogger(AvailabilityCompetitionService.class);

    private final AvailabilityCompetitionRepository availabilityCompetitionRepository;

    private final AvailabilityCompetitionMapper availabilityCompetitionMapper;

    public AvailabilityCompetitionService(AvailabilityCompetitionRepository availabilityCompetitionRepository, AvailabilityCompetitionMapper availabilityCompetitionMapper) {
        this.availabilityCompetitionRepository = availabilityCompetitionRepository;
        this.availabilityCompetitionMapper = availabilityCompetitionMapper;
    }

    /**
     * Save a availabilityCompetition.
     *
     * @param availabilityCompetitionDTO the entity to save.
     * @return the persisted entity.
     */
    public AvailabilityCompetitionDTO save(AvailabilityCompetitionDTO availabilityCompetitionDTO) {
        log.debug("Request to save AvailabilityCompetition : {}", availabilityCompetitionDTO);
        AvailabilityCompetition availabilityCompetition = availabilityCompetitionMapper.toEntity(availabilityCompetitionDTO);
        availabilityCompetition = availabilityCompetitionRepository.save(availabilityCompetition);
        return availabilityCompetitionMapper.toDto(availabilityCompetition);
    }

    /**
     * Get all the availabilityCompetitions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AvailabilityCompetitionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AvailabilityCompetitions");
        return availabilityCompetitionRepository.findAll(pageable)
            .map(availabilityCompetitionMapper::toDto);
    }


    /**
     * Get one availabilityCompetition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AvailabilityCompetitionDTO> findOne(Long id) {
        log.debug("Request to get AvailabilityCompetition : {}", id);
        return availabilityCompetitionRepository.findById(id)
            .map(availabilityCompetitionMapper::toDto);
    }

    /**
     * Delete the availabilityCompetition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AvailabilityCompetition : {}", id);
        availabilityCompetitionRepository.deleteById(id);
    }
}
