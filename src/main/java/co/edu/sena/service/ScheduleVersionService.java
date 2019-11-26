package co.edu.sena.service;

import co.edu.sena.domain.ScheduleVersion;
import co.edu.sena.repository.ScheduleVersionRepository;
import co.edu.sena.service.dto.ScheduleVersionDTO;
import co.edu.sena.service.mapper.ScheduleVersionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ScheduleVersion}.
 */
@Service
@Transactional
public class ScheduleVersionService {

    private final Logger log = LoggerFactory.getLogger(ScheduleVersionService.class);

    private final ScheduleVersionRepository scheduleVersionRepository;

    private final ScheduleVersionMapper scheduleVersionMapper;

    public ScheduleVersionService(ScheduleVersionRepository scheduleVersionRepository, ScheduleVersionMapper scheduleVersionMapper) {
        this.scheduleVersionRepository = scheduleVersionRepository;
        this.scheduleVersionMapper = scheduleVersionMapper;
    }

    /**
     * Save a scheduleVersion.
     *
     * @param scheduleVersionDTO the entity to save.
     * @return the persisted entity.
     */
    public ScheduleVersionDTO save(ScheduleVersionDTO scheduleVersionDTO) {
        log.debug("Request to save ScheduleVersion : {}", scheduleVersionDTO);
        ScheduleVersion scheduleVersion = scheduleVersionMapper.toEntity(scheduleVersionDTO);
        scheduleVersion = scheduleVersionRepository.save(scheduleVersion);
        return scheduleVersionMapper.toDto(scheduleVersion);
    }

    /**
     * Get all the scheduleVersions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ScheduleVersionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ScheduleVersions");
        return scheduleVersionRepository.findAll(pageable)
            .map(scheduleVersionMapper::toDto);
    }


    /**
     * Get one scheduleVersion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScheduleVersionDTO> findOne(Long id) {
        log.debug("Request to get ScheduleVersion : {}", id);
        return scheduleVersionRepository.findById(id)
            .map(scheduleVersionMapper::toDto);
    }

    /**
     * Delete the scheduleVersion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ScheduleVersion : {}", id);
        scheduleVersionRepository.deleteById(id);
    }
}
