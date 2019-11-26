package co.edu.sena.service;

import co.edu.sena.domain.WorkingDay;
import co.edu.sena.repository.WorkingDayRepository;
import co.edu.sena.service.dto.WorkingDayDTO;
import co.edu.sena.service.mapper.WorkingDayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WorkingDay}.
 */
@Service
@Transactional
public class WorkingDayService {

    private final Logger log = LoggerFactory.getLogger(WorkingDayService.class);

    private final WorkingDayRepository workingDayRepository;

    private final WorkingDayMapper workingDayMapper;

    public WorkingDayService(WorkingDayRepository workingDayRepository, WorkingDayMapper workingDayMapper) {
        this.workingDayRepository = workingDayRepository;
        this.workingDayMapper = workingDayMapper;
    }

    /**
     * Save a workingDay.
     *
     * @param workingDayDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkingDayDTO save(WorkingDayDTO workingDayDTO) {
        log.debug("Request to save WorkingDay : {}", workingDayDTO);
        WorkingDay workingDay = workingDayMapper.toEntity(workingDayDTO);
        workingDay = workingDayRepository.save(workingDay);
        return workingDayMapper.toDto(workingDay);
    }

    /**
     * Get all the workingDays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkingDayDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkingDays");
        return workingDayRepository.findAll(pageable)
            .map(workingDayMapper::toDto);
    }


    /**
     * Get one workingDay by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkingDayDTO> findOne(Long id) {
        log.debug("Request to get WorkingDay : {}", id);
        return workingDayRepository.findById(id)
            .map(workingDayMapper::toDto);
    }

    /**
     * Delete the workingDay by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkingDay : {}", id);
        workingDayRepository.deleteById(id);
    }
}
