package co.edu.sena.service;

import co.edu.sena.domain.ScheduleAvailability;
import co.edu.sena.repository.ScheduleAvailabilityRepository;
import co.edu.sena.service.dto.ScheduleAvailabilityDTO;
import co.edu.sena.service.mapper.ScheduleAvailabilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ScheduleAvailability}.
 */
@Service
@Transactional
public class ScheduleAvailabilityService {

    private final Logger log = LoggerFactory.getLogger(ScheduleAvailabilityService.class);

    private final ScheduleAvailabilityRepository scheduleAvailabilityRepository;

    private final ScheduleAvailabilityMapper scheduleAvailabilityMapper;

    public ScheduleAvailabilityService(ScheduleAvailabilityRepository scheduleAvailabilityRepository, ScheduleAvailabilityMapper scheduleAvailabilityMapper) {
        this.scheduleAvailabilityRepository = scheduleAvailabilityRepository;
        this.scheduleAvailabilityMapper = scheduleAvailabilityMapper;
    }

    /**
     * Save a scheduleAvailability.
     *
     * @param scheduleAvailabilityDTO the entity to save.
     * @return the persisted entity.
     */
    public ScheduleAvailabilityDTO save(ScheduleAvailabilityDTO scheduleAvailabilityDTO) {
        log.debug("Request to save ScheduleAvailability : {}", scheduleAvailabilityDTO);
        ScheduleAvailability scheduleAvailability = scheduleAvailabilityMapper.toEntity(scheduleAvailabilityDTO);
        scheduleAvailability = scheduleAvailabilityRepository.save(scheduleAvailability);
        return scheduleAvailabilityMapper.toDto(scheduleAvailability);
    }

    /**
     * Get all the scheduleAvailabilities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ScheduleAvailabilityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ScheduleAvailabilities");
        return scheduleAvailabilityRepository.findAll(pageable)
            .map(scheduleAvailabilityMapper::toDto);
    }


    /**
     * Get one scheduleAvailability by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScheduleAvailabilityDTO> findOne(Long id) {
        log.debug("Request to get ScheduleAvailability : {}", id);
        return scheduleAvailabilityRepository.findById(id)
            .map(scheduleAvailabilityMapper::toDto);
    }

    /**
     * Delete the scheduleAvailability by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ScheduleAvailability : {}", id);
        scheduleAvailabilityRepository.deleteById(id);
    }
}
