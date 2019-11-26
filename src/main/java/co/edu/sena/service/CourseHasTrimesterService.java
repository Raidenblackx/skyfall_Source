package co.edu.sena.service;

import co.edu.sena.domain.CourseHasTrimester;
import co.edu.sena.repository.CourseHasTrimesterRepository;
import co.edu.sena.service.dto.CourseHasTrimesterDTO;
import co.edu.sena.service.mapper.CourseHasTrimesterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CourseHasTrimester}.
 */
@Service
@Transactional
public class CourseHasTrimesterService {

    private final Logger log = LoggerFactory.getLogger(CourseHasTrimesterService.class);

    private final CourseHasTrimesterRepository courseHasTrimesterRepository;

    private final CourseHasTrimesterMapper courseHasTrimesterMapper;

    public CourseHasTrimesterService(CourseHasTrimesterRepository courseHasTrimesterRepository, CourseHasTrimesterMapper courseHasTrimesterMapper) {
        this.courseHasTrimesterRepository = courseHasTrimesterRepository;
        this.courseHasTrimesterMapper = courseHasTrimesterMapper;
    }

    /**
     * Save a courseHasTrimester.
     *
     * @param courseHasTrimesterDTO the entity to save.
     * @return the persisted entity.
     */
    public CourseHasTrimesterDTO save(CourseHasTrimesterDTO courseHasTrimesterDTO) {
        log.debug("Request to save CourseHasTrimester : {}", courseHasTrimesterDTO);
        CourseHasTrimester courseHasTrimester = courseHasTrimesterMapper.toEntity(courseHasTrimesterDTO);
        courseHasTrimester = courseHasTrimesterRepository.save(courseHasTrimester);
        return courseHasTrimesterMapper.toDto(courseHasTrimester);
    }

    /**
     * Get all the courseHasTrimesters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CourseHasTrimesterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourseHasTrimesters");
        return courseHasTrimesterRepository.findAll(pageable)
            .map(courseHasTrimesterMapper::toDto);
    }


    /**
     * Get one courseHasTrimester by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CourseHasTrimesterDTO> findOne(Long id) {
        log.debug("Request to get CourseHasTrimester : {}", id);
        return courseHasTrimesterRepository.findById(id)
            .map(courseHasTrimesterMapper::toDto);
    }

    /**
     * Delete the courseHasTrimester by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseHasTrimester : {}", id);
        courseHasTrimesterRepository.deleteById(id);
    }
}
