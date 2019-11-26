package co.edu.sena.service;

import co.edu.sena.domain.CourseState;
import co.edu.sena.repository.CourseStateRepository;
import co.edu.sena.service.dto.CourseStateDTO;
import co.edu.sena.service.mapper.CourseStateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CourseState}.
 */
@Service
@Transactional
public class CourseStateService {

    private final Logger log = LoggerFactory.getLogger(CourseStateService.class);

    private final CourseStateRepository courseStateRepository;

    private final CourseStateMapper courseStateMapper;

    public CourseStateService(CourseStateRepository courseStateRepository, CourseStateMapper courseStateMapper) {
        this.courseStateRepository = courseStateRepository;
        this.courseStateMapper = courseStateMapper;
    }

    /**
     * Save a courseState.
     *
     * @param courseStateDTO the entity to save.
     * @return the persisted entity.
     */
    public CourseStateDTO save(CourseStateDTO courseStateDTO) {
        log.debug("Request to save CourseState : {}", courseStateDTO);
        CourseState courseState = courseStateMapper.toEntity(courseStateDTO);
        courseState = courseStateRepository.save(courseState);
        return courseStateMapper.toDto(courseState);
    }

    /**
     * Get all the courseStates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CourseStateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourseStates");
        return courseStateRepository.findAll(pageable)
            .map(courseStateMapper::toDto);
    }


    /**
     * Get one courseState by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CourseStateDTO> findOne(Long id) {
        log.debug("Request to get CourseState : {}", id);
        return courseStateRepository.findById(id)
            .map(courseStateMapper::toDto);
    }

    /**
     * Delete the courseState by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseState : {}", id);
        courseStateRepository.deleteById(id);
    }
}
