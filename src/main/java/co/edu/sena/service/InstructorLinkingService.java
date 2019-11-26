package co.edu.sena.service;

import co.edu.sena.domain.InstructorLinking;
import co.edu.sena.repository.InstructorLinkingRepository;
import co.edu.sena.service.dto.InstructorLinkingDTO;
import co.edu.sena.service.mapper.InstructorLinkingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InstructorLinking}.
 */
@Service
@Transactional
public class InstructorLinkingService {

    private final Logger log = LoggerFactory.getLogger(InstructorLinkingService.class);

    private final InstructorLinkingRepository instructorLinkingRepository;

    private final InstructorLinkingMapper instructorLinkingMapper;

    public InstructorLinkingService(InstructorLinkingRepository instructorLinkingRepository, InstructorLinkingMapper instructorLinkingMapper) {
        this.instructorLinkingRepository = instructorLinkingRepository;
        this.instructorLinkingMapper = instructorLinkingMapper;
    }

    /**
     * Save a instructorLinking.
     *
     * @param instructorLinkingDTO the entity to save.
     * @return the persisted entity.
     */
    public InstructorLinkingDTO save(InstructorLinkingDTO instructorLinkingDTO) {
        log.debug("Request to save InstructorLinking : {}", instructorLinkingDTO);
        InstructorLinking instructorLinking = instructorLinkingMapper.toEntity(instructorLinkingDTO);
        instructorLinking = instructorLinkingRepository.save(instructorLinking);
        return instructorLinkingMapper.toDto(instructorLinking);
    }

    /**
     * Get all the instructorLinkings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InstructorLinkingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InstructorLinkings");
        return instructorLinkingRepository.findAll(pageable)
            .map(instructorLinkingMapper::toDto);
    }


    /**
     * Get one instructorLinking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstructorLinkingDTO> findOne(Long id) {
        log.debug("Request to get InstructorLinking : {}", id);
        return instructorLinkingRepository.findById(id)
            .map(instructorLinkingMapper::toDto);
    }

    /**
     * Delete the instructorLinking by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InstructorLinking : {}", id);
        instructorLinkingRepository.deleteById(id);
    }
}
