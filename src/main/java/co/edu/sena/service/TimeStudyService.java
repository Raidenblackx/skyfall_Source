package co.edu.sena.service;

import co.edu.sena.domain.TimeStudy;
import co.edu.sena.repository.TimeStudyRepository;
import co.edu.sena.service.dto.TimeStudyDTO;
import co.edu.sena.service.mapper.TimeStudyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TimeStudy}.
 */
@Service
@Transactional
public class TimeStudyService {

    private final Logger log = LoggerFactory.getLogger(TimeStudyService.class);

    private final TimeStudyRepository timeStudyRepository;

    private final TimeStudyMapper timeStudyMapper;

    public TimeStudyService(TimeStudyRepository timeStudyRepository, TimeStudyMapper timeStudyMapper) {
        this.timeStudyRepository = timeStudyRepository;
        this.timeStudyMapper = timeStudyMapper;
    }

    /**
     * Save a timeStudy.
     *
     * @param timeStudyDTO the entity to save.
     * @return the persisted entity.
     */
    public TimeStudyDTO save(TimeStudyDTO timeStudyDTO) {
        log.debug("Request to save TimeStudy : {}", timeStudyDTO);
        TimeStudy timeStudy = timeStudyMapper.toEntity(timeStudyDTO);
        timeStudy = timeStudyRepository.save(timeStudy);
        return timeStudyMapper.toDto(timeStudy);
    }

    /**
     * Get all the timeStudies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TimeStudyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TimeStudies");
        return timeStudyRepository.findAll(pageable)
            .map(timeStudyMapper::toDto);
    }


    /**
     * Get one timeStudy by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TimeStudyDTO> findOne(Long id) {
        log.debug("Request to get TimeStudy : {}", id);
        return timeStudyRepository.findById(id)
            .map(timeStudyMapper::toDto);
    }

    /**
     * Delete the timeStudy by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TimeStudy : {}", id);
        timeStudyRepository.deleteById(id);
    }
}
