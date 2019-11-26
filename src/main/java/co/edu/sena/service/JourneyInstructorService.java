package co.edu.sena.service;

import co.edu.sena.domain.JourneyInstructor;
import co.edu.sena.repository.JourneyInstructorRepository;
import co.edu.sena.service.dto.JourneyInstructorDTO;
import co.edu.sena.service.mapper.JourneyInstructorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link JourneyInstructor}.
 */
@Service
@Transactional
public class JourneyInstructorService {

    private final Logger log = LoggerFactory.getLogger(JourneyInstructorService.class);

    private final JourneyInstructorRepository journeyInstructorRepository;

    private final JourneyInstructorMapper journeyInstructorMapper;

    public JourneyInstructorService(JourneyInstructorRepository journeyInstructorRepository, JourneyInstructorMapper journeyInstructorMapper) {
        this.journeyInstructorRepository = journeyInstructorRepository;
        this.journeyInstructorMapper = journeyInstructorMapper;
    }

    /**
     * Save a journeyInstructor.
     *
     * @param journeyInstructorDTO the entity to save.
     * @return the persisted entity.
     */
    public JourneyInstructorDTO save(JourneyInstructorDTO journeyInstructorDTO) {
        log.debug("Request to save JourneyInstructor : {}", journeyInstructorDTO);
        JourneyInstructor journeyInstructor = journeyInstructorMapper.toEntity(journeyInstructorDTO);
        journeyInstructor = journeyInstructorRepository.save(journeyInstructor);
        return journeyInstructorMapper.toDto(journeyInstructor);
    }

    /**
     * Get all the journeyInstructors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JourneyInstructorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JourneyInstructors");
        return journeyInstructorRepository.findAll(pageable)
            .map(journeyInstructorMapper::toDto);
    }


    /**
     * Get one journeyInstructor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JourneyInstructorDTO> findOne(Long id) {
        log.debug("Request to get JourneyInstructor : {}", id);
        return journeyInstructorRepository.findById(id)
            .map(journeyInstructorMapper::toDto);
    }

    /**
     * Delete the journeyInstructor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JourneyInstructor : {}", id);
        journeyInstructorRepository.deleteById(id);
    }
}
