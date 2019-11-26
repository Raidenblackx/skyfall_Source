package co.edu.sena.service;

import co.edu.sena.domain.Instructor;
import co.edu.sena.repository.InstructorRepository;
import co.edu.sena.service.dto.InstructorDTO;
import co.edu.sena.service.mapper.InstructorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Instructor}.
 */
@Service
@Transactional
public class InstructorService {

    private final Logger log = LoggerFactory.getLogger(InstructorService.class);

    private final InstructorRepository instructorRepository;

    private final InstructorMapper instructorMapper;

    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    /**
     * Save a instructor.
     *
     * @param instructorDTO the entity to save.
     * @return the persisted entity.
     */
    public InstructorDTO save(InstructorDTO instructorDTO) {
        log.debug("Request to save Instructor : {}", instructorDTO);
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        instructor = instructorRepository.save(instructor);
        return instructorMapper.toDto(instructor);
    }

    /**
     * Get all the instructors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InstructorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Instructors");
        return instructorRepository.findAll(pageable)
            .map(instructorMapper::toDto);
    }


    /**
     * Get one instructor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstructorDTO> findOne(Long id) {
        log.debug("Request to get Instructor : {}", id);
        return instructorRepository.findById(id)
            .map(instructorMapper::toDto);
    }

    /**
     * Delete the instructor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Instructor : {}", id);
        instructorRepository.deleteById(id);
    }
}
