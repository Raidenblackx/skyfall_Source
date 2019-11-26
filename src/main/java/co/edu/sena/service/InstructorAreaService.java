package co.edu.sena.service;

import co.edu.sena.domain.InstructorArea;
import co.edu.sena.repository.InstructorAreaRepository;
import co.edu.sena.service.dto.InstructorAreaDTO;
import co.edu.sena.service.mapper.InstructorAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InstructorArea}.
 */
@Service
@Transactional
public class InstructorAreaService {

    private final Logger log = LoggerFactory.getLogger(InstructorAreaService.class);

    private final InstructorAreaRepository instructorAreaRepository;

    private final InstructorAreaMapper instructorAreaMapper;

    public InstructorAreaService(InstructorAreaRepository instructorAreaRepository, InstructorAreaMapper instructorAreaMapper) {
        this.instructorAreaRepository = instructorAreaRepository;
        this.instructorAreaMapper = instructorAreaMapper;
    }

    /**
     * Save a instructorArea.
     *
     * @param instructorAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public InstructorAreaDTO save(InstructorAreaDTO instructorAreaDTO) {
        log.debug("Request to save InstructorArea : {}", instructorAreaDTO);
        InstructorArea instructorArea = instructorAreaMapper.toEntity(instructorAreaDTO);
        instructorArea = instructorAreaRepository.save(instructorArea);
        return instructorAreaMapper.toDto(instructorArea);
    }

    /**
     * Get all the instructorAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InstructorAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InstructorAreas");
        return instructorAreaRepository.findAll(pageable)
            .map(instructorAreaMapper::toDto);
    }


    /**
     * Get one instructorArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstructorAreaDTO> findOne(Long id) {
        log.debug("Request to get InstructorArea : {}", id);
        return instructorAreaRepository.findById(id)
            .map(instructorAreaMapper::toDto);
    }

    /**
     * Delete the instructorArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InstructorArea : {}", id);
        instructorAreaRepository.deleteById(id);
    }
}
